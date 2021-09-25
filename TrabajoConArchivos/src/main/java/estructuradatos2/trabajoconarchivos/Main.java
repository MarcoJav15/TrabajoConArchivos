package estructuradatos2.trabajoconarchivos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

        private void leerArchivo() throws FileNotFoundException{
            BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("agenda.txt"));
            String texto = br.readLine();
            while(texto != null){
                System.out.println(texto);
                texto = br.readLine();
            }

            br.close();
            } catch(FileNotFoundException ex){
                System.out.println("No encontré el archivo solicitado");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex){
                System.out.println("Tengo problemas para cerrar el archivo");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
      private void escribirArchivo(String name, String age, String phone){
        try{
            FileWriter escritorArchivo = new FileWriter("Agenda.txt");
            
            int n = name.length();
            int a = age.length();
            int p = phone.length();
            
           //Nombre
            if(n==30){
                escritorArchivo.write(name);
            }
            else if(n<30){
                String nombre="";
                for(int x = n; x < 30; x++){
                    nombre = nombre + "0";
                }
                escritorArchivo.write(name+nombre);
            }
            else{
                System.out.println("El nombre no puede ser mayor a 30 caracteres");
            }
            //Edad
             if(a==2){
                escritorArchivo.write(age);
            }
            else if(n<2){
                String nuevo="";
                for(int x = n; x < 2; x++){
                    nuevo = nuevo + "0";
                }
                escritorArchivo.write(age+nuevo);
            }
            else{
                System.out.println("La edad no puede ser mayor a 2 caracteres");
            }
            //phone
            if(p==8){
                escritorArchivo.write(phone);
            }
            else if(n<2){
                String nuevo="";
                for(int x = n; x < 8; x++){
                    nuevo = nuevo + "0";
                }
                escritorArchivo.write(phone+nuevo);
            }
            else{
                System.out.println("El telefono no puede ser mayor a 8 caracteres");
            }
 
 
            escritorArchivo.close();
            
        }catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
        
        
        
        
  private void escrituraBinaria(String name, String age, String phone){
        try{
            FileOutputStream archivo = new FileOutputStream("Agenda.bin");
            
            int i = name.length();
            String separador = "•••";
             
            DataOutputStream data = new DataOutputStream(archivo);
            data.writeBytes(name+separador);
            data.writeBytes(age+separador);
            data.writeBytes(phone+separador);
        
            
            archivo.close();
        } catch(FileNotFoundException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
  
  
  private void lecturaBinaria(){
      try{
     FileInputStream archivo = new FileInputStream("Agenda.bin");
      
     DataInputStream data = new DataInputStream(archivo);
     
        byte nombreBytes[] = data.readNBytes(30);  
        byte edadBytes[] = data.readNBytes(2);
        byte numeroBytes[] = data.readNBytes(8);
        String nombre = new String(nombreBytes);
        System.out.print(nombre);
          
        String edad = new String(edadBytes);
        System.out.print(edad);
 
        String phone = new String(numeroBytes);
        System.out.print(phone);

    
        archivo.close();
       
    } catch(FileNotFoundException ex){
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }catch (IOException ex){
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
}
  }

    
   
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
         Main main = new Main();
        Scanner ingresar = new Scanner(System.in);
        String n, t, e;
        
       
       
       
        System.out.println("Ingrese el nombre: ");
        n = ingresar.nextLine();
        System.out.println("Ingrese la edad: ");
        e = ingresar.nextLine();
        System.out.println("Ingrese el número de teléfono");
        t = ingresar.nextLine();
        
        main.escrituraBinaria(n, e, t);
        main.escribirArchivo(n, e, t);
 
        System.out.println("Archivo secuencial con separadores binarios (bin)");
        main.lecturaBinaria();
        System.out.println("\n");
        System.out.println("Archivo secuencial con separador por tamaño (txt)");
        main.leerArchivo();
        
    }
    
}
