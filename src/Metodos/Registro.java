package Metodos;
import Vista.*;
import Data.*;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Registro {
    public void Registro(){
        
        List<User> nue_user = new ArrayList<User>();// En este apartado se están cargando por defecto usuarios de prueba, los cuales servirán para testear el funcionamiento base del programa
        List<User> pUser = leerArchivo();
                    while(true){
                    String[] opt = { "Arrendador", "Arrendatario" };
                    String selec = (String) JOptionPane.showInputDialog(null, "Seleccione tipo de cuenta: ", "Tipo de cuenta", JOptionPane.PLAIN_MESSAGE, null, opt, opt[0]);
                    if(selec==null){return;}                   
                    String nam = JOptionPane.showInputDialog(null, "Ingrese su nombre: ");
                    if(nam==null){return;}
                    String Last = JOptionPane.showInputDialog(null, "Ingrese su apellido");
                    if(Last==null){return;}
                    String Pho = JOptionPane.showInputDialog(null, "Ingrese su numero telefonico");
                    if(Pho==null){return;}
                    String Docu = JOptionPane.showInputDialog(null, "Ingrese su documento: ");
                    if(Docu==null){return;}
                    String Use = JOptionPane.showInputDialog(null, "Nombre de usuario: ");
                    if(Use==null){return;}
                    String Pass = JOptionPane.showInputDialog(null, "Contraseña: ");
                    if(Pass==null){return;}
                    int Pric=0; 
                    Map<Integer, Integer> expenses = new HashMap<>();
                    User nuevoUsuario=new User(selec, Docu, nam, Last, Pho, Use, Pass,Pric,expenses);
                    nue_user.add(nuevoUsuario);
                    guardarEnArchivo(nue_user);
                    JOptionPane.showMessageDialog(null, "Registro exitoso.");
                    break;}
    }
    public ArrayList<User> leerArchivo() {
        ArrayList<User> nue_user= new ArrayList<>();
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try {
            fileReader=new FileReader("Usuario.txt");
            bufferedReader= new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\-");
                if (line.trim().isEmpty()) {
                // Ignorar líneas vacías
                continue;
                }
                if (parts.length == 8) {
                    String selec = parts[0];
                    String Docu = parts[1];
                    String nam = parts[2];
                    String Last = parts[3];
                    String Pho = parts[4];
                    String Use= parts[5];
                    String Pass = parts[6];
                    int Pric = Integer.parseInt(parts[7]);
                    Map<Integer, Integer> expenses = new HashMap<>();
                     User nue_user2=new User(selec, Docu, nam, Last, Pho, Use, Pass,Pric, expenses);
                     nue_user.add(nue_user2);
                }
            }
            fileReader.close();
            JOptionPane.showMessageDialog(null, "Datos leídos del usuario ");
        } catch (Exception e) {
            e.printStackTrace();}
        return nue_user;
    } 
    public void guardarEnArchivo(List<User> nue_user) {
      FileWriter fileWriter=null;
      PrintWriter printWriter=null; 
        try {
            fileWriter= new FileWriter("Usuario.txt",true);
            printWriter=new PrintWriter(fileWriter);
            for (User us : nue_user) {
                String usNuevo = us.getTipo()+ "-" + us.getDoc() + "-" + us.getName() + "-" + us.getLastN()+ "-" + us.getPhone() + "-" + us.getUserN() +  "-" +us.getPassw() + "-" + us.getPrice(); 
                printWriter.println(usNuevo);
                }      
            JOptionPane.showMessageDialog(null, "Datos guardados");
        } catch (IOException e) {                 
                JOptionPane.showMessageDialog(null, "Error al guardar datos");
                e.printStackTrace();
        }finally{
            try{
                if(printWriter!= null){
                    printWriter.close();
                }
                if(fileWriter != null){ 
                    fileWriter.close();
                }
            }catch (IOException e){
                e.printStackTrace(); 
            }
    }
    }  
}
