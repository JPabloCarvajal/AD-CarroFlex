package Metodos;
import Vista.*;
import Data.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Registro {
    public static void Registro(){
        
        List<User> nue_user = new ArrayList<User>();// En este apartado se están cargando por defecto usuarios de prueba, los cuales servirán para testear el funcionamiento base del programa
        leerArchivo(nue_user);
                    for (int i = 0; i < 1; i++) {
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
                    nue_user.add(new User(selec, Docu, nam, Last, Pho, Use, Pass,Pric));
                    guardarEnArchivo(nue_user);
                    JOptionPane.showMessageDialog(null, "Registro exitoso.");
                    break;}} 
    }
    public static void leerArchivo(List<User> nue_user) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Usuario.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\-");
                if (parts.length == 8) {
                    String selec = parts[0];
                    String Docu = parts[1];
                    String nam = parts[2];
                    String Last = parts[3];
                    String Pho = parts[4];
                    String Use= parts[5];
                    String Pass = parts[6];
                    int Pric = Integer.parseInt(parts[7]);
                     nue_user.add(new User(selec, Docu, nam, Last, Pho, Use, Pass,Pric));}}     
            JOptionPane.showMessageDialog(null, "Datos leídos del usuario ");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");}} 
    public static void guardarEnArchivo(List<User> nue_user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Usuario.txt"))) {
            for (User us : nue_user) {
                String usNuevo = us.getTipo()+ "-" + us.getDoc() + "-" + us.getName() + "-" + us.getLastN()+ "-" + us.getPhone() + "-" + us.getUserN() +  "-" +us.getPassw() + "-" + us.getPrice(); 
                writer.write(usNuevo);
                writer.newLine();}      
            JOptionPane.showMessageDialog(null, "Datos guardados");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar datos");}}  
}
