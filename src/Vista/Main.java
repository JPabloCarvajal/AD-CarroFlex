package Vista;
import Data.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Main {
     private static Date validarFecha(String fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String[] partesFecha = fecha.split("/");
        
        if (partesFecha.length != 3) {
            return null; // Formato de fecha incorrecto
        }

        try {
            int dia = Integer.parseInt(partesFecha[0]);
            int mes = Integer.parseInt(partesFecha[1]);
            int año = Integer.parseInt(partesFecha[2]);

            if (dia < 1 || dia > 31 || mes < 1 || mes > 12) {
                return null; // Día o mes fuera de rango
            }

            Date fechaParseada = formatoFecha.parse(fecha);

            // Comprobar si la fecha analizada es válida
            if (!formatoFecha.format(fechaParseada).equals(fecha)) {
                return null; // Fecha no válida
            }

            return fechaParseada;
        } catch (ParseException | NumberFormatException ex) {
            return null; // Fecha inválida
        }
        
        
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
                    
                     nue_user.add(new User(selec, Docu, nam, Last, Pho, Use, Pass,Pric));
                    
                    
                }
            }
            JOptionPane.showMessageDialog(null, "Datos leídos del usuario ");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }
    }

    public static void guardarEnArchivo(List<User> nue_user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Usuario.txt"))) {
            for (User us : nue_user) {
                String usNuevo = us.getTipo()+ "-" + us.getDoc() + "-" + us.getName() + "-" + us.getLastN()+ "-" + us.getPhone() + "-" + us.getUserN() +  "-" +us.getPassw() + "-" + us.getPrice(); 
                writer.write(usNuevo);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Datos guardados");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar datos");
        }
    }

// public static void leercArchivo(List<Cars> inventory) {
//        try (BufferedReader reader = new BufferedReader(new FileReader("Carro.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split("\\-");
//                if (parts.length == 8) {
//                    String Bra = parts[0];
//                    String Clr = parts[1];
//                    String Mtr = parts[2];
//                    String Sta = parts[3];
//                    int k = Integer.parseInt(parts[4]);
//                    String Pro= parts[5];
//                    String Arr = parts[6];
//                    String date1 = parts[7];
//                    String date2 = parts[8];
//                    int pr = Integer.parseInt(parts[9]);
//                    
//                      inventory.add(new Cars(Bra, Clr, Mtr, Sta, k,Pro, Arr, date1, date2, pr));
//                    
//                    
//                }
//            }
//            JOptionPane.showMessageDialog(null, "Datos leídos");
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
//        }
//    }

//    public static void guardarcEnArchivo(List<Cars> inventory) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Carro.txt"))) {
//            for (Cars c : inventory ) {
//                String usNuevo = c.getBrand() + "-" + c.getColor() + "-" + c.getPlaque() + "-" + c.getState() + "-" + c.getkm() + "-" + c.getProprietarie() + "-" + c.getArrendatario() + "-" + c.getDate_p() + "-" + c.getDate_g() + "-" + c.getPrecio(); 
//                writer.write(usNuevo);
//                writer.newLine();
//            }
//            JOptionPane.showMessageDialog(null, "Datos guardados");
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Error al guardar datos");
//        }
//    }
//

     

    public static void main(String[] args) {
        int x;
        long valor_total= 0;

        // En este apartado se están cargando por defecto usuarios de prueba, los cuales servirán para testear el funcionamiento base del programa
        List<User> nue_user = new ArrayList<User>();
        leerArchivo(nue_user);
        
        List<Cars> inventory = new ArrayList<Cars>();
        inventory.add(new Cars("Mazda", "Blanco", "CFE234","Bien",0,"Camilo", "||", "Sin arrendar", "||", 1000));
        inventory.add(new Cars("Ferrari", "Gris", "GRE654","Regular",0,"Pedro", "||", "Sin arrendar", "||", 2000));
        inventory.add(new Cars("Jeep", "Negro", "HTE723","Excelente",50,"Pedro", "Luz", "22/2/2022", "22/2/2023", 900));
        
        // Se están declarando variables
        String Menu,Menu_cl,Menu_dño,Menu_fil;
         Menu = "1. Iniciar Sesión \n2. Registrarse\n3. Salir";
         Menu_cl = "1. Reservar Auto\n2. Ver reserv\n3. Devolver Auto\n4. Agregar dinero\n5. Cerrar sesión";
        Menu_dño = "1.Autos alquilados\n2. Añadir automóviles\n3.Cerrar sesión";
        Menu_fil= "1. Mostrar todos los autos\n2. Filtrar por marca\n3. Filtrar por color\n";
        int cnt = 1;
        boolean isValid = true;

        // Se crea el menú principal del aplicativo
       int Menu_election =Integer.parseInt(JOptionPane.showInputDialog(null, Menu,"Carroflex",JOptionPane.INFORMATION_MESSAGE));

        // Se crea variable de la clase para poder guardar información relativa a esta
        User Login_usern2 = null;
        User busqueda = null;

        // Se inicializa un do while para permanecer el tiempo que desee el usuario dentro del aplicativo
        do {
            // Se plantea el inicio de sesión con la opción 1
            if (Menu_election == 1) {
                // Se piden los datos relativos al inicio de sesión
                String Login_usern = JOptionPane.showInputDialog(null, "Ingrese su usuario");
                String Login_passw = JOptionPane.showInputDialog(null, "Ingrese su contraseña");

                // Se declara un boolean de tipo False, para que a la hora de rectificar el los valores sea cambiado a True en caso de que la condición se cumpla
                // El for each está dado para rectificar si los valores ingresados son los correctos con respecto a los asignados en el ArrayList
                // A su vez se almacenan los datos del usuario en la variable Login_usern2
                boolean aut = false;
                for (User login : nue_user) {
                    if (login.getUserN().equals(Login_usern) && login.getPassw().equals(Login_passw)) {
                        aut = true;
                        Login_usern2 = login;
                        break;
                    }
                }
                // En caso de que las condiciones anteriores sean cumplidas se logra acceder a los menus correspondientes a cada tipo de usuario
                // Para obtener el tipo de usuario que es, asignamos primero el tipo de usuario a un String con la función .getTipo en la variable Login_usern2, posterior a eso se utiliza la funcion .equals() para compararlos
                if (aut) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                    String tip = Login_usern2.getTipo();
                    if ("Arrendador".equals(tip)) {
                        int men_dño = Integer.parseInt(JOptionPane.showInputDialog(null, Menu_dño));
                        while (men_dño < 3) {
                            if (men_dño == 1) {
                                for (Cars name : inventory) {
                                    if (name.getProprietarie().equals(Login_usern2.getName())) {
                                        for (User names : nue_user) {
                                            if (names.getName().equals(Login_usern2.getName())) {
                                                JOptionPane.showMessageDialog(null, "Marca: " + name.getBrand() + "\n" + "Color: " + name.getColor() + "\n" + "Placa: " + name.getPlaque() + "\n" + "Arrendatario: " + name.getArrendatario() + "\n" + "Fecha de arriendo: " + name.getDate_p() + "\n" + "Fecha de devolución: " + name.getDate_g());
                                                break;
                                            }
                                        }
                                    }
                                }
                                men_dño = Integer.parseInt(JOptionPane.showInputDialog(null, Menu_dño));
                            }
                            if (men_dño == 2) {
                                for (int i = 0; i < 1; i++) {
                                        String Bra = JOptionPane.showInputDialog(null, "Marca del auto: ");
                                        String Clr = JOptionPane.showInputDialog(null, "Color del auto: ");
                                        String Mtr = JOptionPane.showInputDialog(null, "Matrícula del auto: ");
                                        String Pro = JOptionPane.showInputDialog(null, "Propietario del auto: ");
                                        String Sta = JOptionPane.showInputDialog(null, "Estado del vehículo: ");
                                        int km = Integer.parseInt(JOptionPane.showInputDialog(null, "Kilometraje del vehículo: "));
                                        String Arr = "||";
                                        String date1 = "Sin arrendar";
                                        String date2 = "||";

                                        // Agregar un nuevo automóvil a la lista
                                        inventory.add(new Cars(Bra, Clr, Mtr, Sta, km, Pro, Arr, date1, date2, 0));
                                       
                                }
                                JOptionPane.showMessageDialog(null, "Registro exitoso.");
                                men_dño = Integer.parseInt(JOptionPane.showInputDialog(null, Menu_dño));
                            }
                        }
                    }
                    if ("Arrendatario".equals(tip)) {
                        int men_cl = Integer.parseInt(JOptionPane.showInputDialog(null, Menu_cl));
                        while (men_cl < 5) {
                            if (men_cl == 1) {
                                List<Cars> Free = new ArrayList<Cars>();
                                for (Cars inv : inventory) {
                                    if (inv.getArrendatario() == "||") {
                                        Free.add(inv);
                                    }
                                }
                                String[] espec = new String[Free.size()];
                                for (int i = 0; i < Free.size(); i++) {
                                    Cars automovil = Free.get(i);
                                    espec[i] = automovil.getBrand() + " " + automovil.getColor();
                                }
                                int Filtrado=Integer.parseInt(JOptionPane.showInputDialog(null,Menu_fil));
                                if(Filtrado==1){
                                if (Free.size() > 0) {
                                    String seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec,
                                            espec[0]);
                                    if (seleccion == null) {
                                        break;
                                    }
                                    Cars autslc = null;
                                    for (Cars slc : inventory) {
                                        if ((slc.getBrand() + " " + slc.getColor()).equals(seleccion)) {
                                            autslc = slc;
                                            break;
                                        }
                                    }
                                    if (autslc != null) {

                                        String Men = "Marca: " + autslc.getBrand() + "\n" + "Color: " + autslc.getColor() + "\n" + "Placa: " + autslc.getPlaque() + "\n" + "Estado del vehiculo: "+ autslc.getState() + "\n" + "Kilometraje del vehiculo: " + autslc.getkm() + "\n" +"Propietario: " + autslc.getProprietarie() + "\n" + "Precio(Dia): " + autslc.getPrecio() + "$";
                                    while (true) {
                                        int opt = JOptionPane.showConfirmDialog(
                                         null,
                                        Men + "\nEstas seguro de rentar este auto?",
                                        "Detalle del vehiculo",
                                        JOptionPane.YES_NO_OPTION);
                                 if (opt == JOptionPane.YES_OPTION) {
                //Parte de Cristian Que se encarga de ingresar la fecha de inicio y devolucion y calcula cuanto debe pagar//    
String f1 ;
String f2 = " ";
while (true) {
    f1 = JOptionPane.showInputDialog(null, "Ingrese la fecha de inicio (dd/MM/yyyy)");
    if(f1==null){
     break;   
    }
    f2 = JOptionPane.showInputDialog(null, "Ingrese la fecha de devolución (dd/MM/yyyy)");
 if(f2==null){
        continue;
    }
    // Validar las fechas ingresadas
    Date fechainicio = validarFecha(f1);
    Date fechafinal = validarFecha(f2);

    if (fechainicio != null && fechafinal != null) {
        // Verificar que la fecha inicial no sea mayor a la fecha final
        if (fechainicio.after(fechafinal)) {
            JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser mayor que la fecha de devolución.");
        } else {
            // Las fechas son válidas, calculamos el tiempo transcurrido y el valor total
            long tiempo_transcurrido = fechafinal.getTime() - fechainicio.getTime();
            TimeUnit unidad = TimeUnit.DAYS;
            long dias = unidad.convert(tiempo_transcurrido, TimeUnit.MILLISECONDS);
            valor_total = dias * autslc.getPrecio();
            JOptionPane.showMessageDialog(null, "El Valor a pagar es " + valor_total + "$");
            break; // Salir del bucle si las fechas son válidas
        }
    } else {
        JOptionPane.showMessageDialog(null, "Fechas no válidas");
    }
}
if(f1==null){
     continue;   
    }
    
int opt_prc=JOptionPane.showConfirmDialog(null, 
                                          "El Valor a pagar es " + valor_total + "$"+"\n"+"Está seguro de querer rentar este vehiculo?",
                                          "Precio total",
                                          JOptionPane.YES_NO_OPTION);
                                           if(opt_prc==JOptionPane.YES_OPTION){
                                            if(Login_usern2.getPrice() >= valor_total){    
                                               String cdn = Login_usern2.getName();
                                                for (Cars name : inventory) {
                                                    if (name.getArrendatario().equals("||")) {
                                                        autslc.setArrendatario(cdn);
                                                        autslc.setDate_p(f1);
                                                        autslc.setDate_g(f2);
                                                        break;
                                                    }
                                                }
                                                JOptionPane.showMessageDialog(null, "Renta exitosa");
                                                Login_usern2.setPrice(Login_usern2.getPrice()- (int)valor_total);
                                                for (User buscar : nue_user){
                                                    if(autslc.getProprietarie()== buscar.getName()){
                                                     busqueda = buscar;   
                                                    }
                                                }
                                                busqueda.setPrice((int)valor_total);
                                                JOptionPane.showMessageDialog(null, "Nuevo saldo "+Login_usern2.getPrice());
                                                
                                                break;
                                            }
                                             if(Login_usern2.getPrice() < valor_total){
                                              JOptionPane.showMessageDialog(null, "El monto supera el saldo disponible ");  
                                            }
                                            } else{
                                               seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec,
                                            espec[0]);
                                               if (seleccion == null) {
                                          break;
                                    }
                                           }
                                            } else {
                                                seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec,
                                            espec[0]);
                                                if (seleccion == null) {
                                        break;
                                    }
                                            }
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "No hay coches");
                                }
                            }
                                if(Filtrado==2){
                                    String Marca=JOptionPane.showInputDialog(null,"Marca a filtrar: ");
                                    List<Cars> Mrc = new ArrayList<Cars>();
                                for (Cars inv_m : Free) {
                                    if (inv_m.getBrand().equals(Marca)) {
                                        Mrc.add(inv_m);
                                    }
                                }
                                String[] espec_m = new String[Mrc.size()];
                                for (int i = 0; i < Mrc.size(); i++) {
                                    Cars automovil_m = Mrc.get(i);
                                    espec_m[i] = automovil_m.getBrand() + " " + automovil_m.getColor();
                                }
                                if (Mrc.size() > 0) {
                                    String seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec_m,
                                            espec_m[0]);
                                    if (seleccion == null) {
                                        break;
                                    }
                                    Cars autslc_m = null;
                                    for (Cars slc_m : inventory) {
                                        if ((slc_m.getBrand() + " " + slc_m.getColor()).equals(seleccion)) {
                                            autslc_m = slc_m;
                                            break;
                                        }
                                    }
                                    if (autslc_m != null) {

                                        String Men = "Marca: " + autslc_m.getBrand() + "\n" + "Color: " + autslc_m.getColor() + "\n" + "Placa: " + autslc_m.getPlaque() + "\n" + "Estado del vehiculo: "+ autslc_m.getState() + "\n" + "Kilometraje del vehiculo: " + autslc_m.getkm() + "\n" +"Propietario: " + autslc_m.getProprietarie() + "\n" + "Precio(Dia): " + autslc_m.getPrecio() + "$";
                                    while (true) {
                                        int opt_m = JOptionPane.showConfirmDialog(
                                         null,
                                        Men + "\nEstas seguro de rentar este auto?",
                                        "Detalle del vehiculo",
                                        JOptionPane.YES_NO_OPTION);
                                        if(opt_m==JOptionPane.YES_OPTION){
 String f1;
 String f2 = " ";
while (true) {
    f1 = JOptionPane.showInputDialog(null, "Ingrese la fecha de inicio (dd/MM/yyyy)");
    if(f1==null){
     break;   
    }
    f2 = JOptionPane.showInputDialog(null, "Ingrese la fecha de devolución (dd/MM/yyyy)");
if(f2==null){
        continue;
    }
    // Validar las fechas ingresadas
    Date fechainicio = validarFecha(f1);
    Date fechafinal = validarFecha(f2);

    if (fechainicio != null && fechafinal != null) {
        // Verificar que la fecha inicial no sea mayor a la fecha final
        if (fechainicio.after(fechafinal)) {
            JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser mayor que la fecha de devolución.");
        } else {
            // Las fechas son válidas, calculamos el tiempo transcurrido y el valor total
            long tiempo_transcurrido = fechafinal.getTime() - fechainicio.getTime();
            TimeUnit unidad = TimeUnit.DAYS;
            long dias = unidad.convert(tiempo_transcurrido, TimeUnit.MILLISECONDS);
            valor_total = dias * autslc_m.getPrecio();
            JOptionPane.showMessageDialog(null, "El Valor a pagar es " + valor_total + "$");
            break; // Salir del bucle si las fechas son válidas
        }
    } else {
        JOptionPane.showMessageDialog(null, "Fechas no válidas");
    }
}
if(f1==null){
     continue;   
    }
    
int opt_prc=JOptionPane.showConfirmDialog(null, 
                                          "El Valor a pagar es " + valor_total + "$"+"\n"+"Está seguro de querer rentar este vehiculo?",
                                          "Precio total",
                                          JOptionPane.YES_NO_OPTION);
                                           if(opt_prc==JOptionPane.YES_OPTION){
                                            
                                               if(Login_usern2.getPrice() >= valor_total){    
                                               String cdn = Login_usern2.getName();
                                                for (Cars name : inventory) {
                                                    if (name.getArrendatario().equals("||")) {
                                                        autslc_m.setArrendatario(cdn);
                                                        autslc_m.setDate_p(f1);
                                                        autslc_m.setDate_g(f2);
                                                        break;
                                                    }
                                                }
                                                JOptionPane.showMessageDialog(null, "Renta exitosa");
                                                Login_usern2.setPrice(Login_usern2.getPrice()- (int)valor_total);
                                                for (User buscar : nue_user){
                                                    if(autslc_m.getProprietarie()== buscar.getName()){
                                                     busqueda = buscar;   
                                                    }
                                                }
                                                busqueda.setPrice((int)valor_total);
                                                JOptionPane.showMessageDialog(null, "Nuevo saldo "+Login_usern2.getPrice());
                                                break;
                                            }
                                            if(Login_usern2.getPrice() < valor_total){
                                              JOptionPane.showMessageDialog(null, "El monto supera el saldo disponible ");  
                                            }
                                            } else{
                                               seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec,
                                            espec[0]);
                                               if (seleccion == null) {
                                          break;
                                    }
                                           } 
                                        } else {
                                                seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec,
                                            espec[0]);
                                                if (seleccion == null) {
                                        break;
                                    }
                                            }
                                    }
                                    }
                                    } else {
                                    JOptionPane.showMessageDialog(null, "No hay coches disponibles con estas especificaciones.");
                                }
                                }
                                if(Filtrado==3){
                                    String Color=JOptionPane.showInputDialog(null,"Color a filtrar: ");
                                    List<Cars> Clr = new ArrayList<Cars>();
                                for (Cars inv_cl : Free) {
                                    if (inv_cl.getColor().equals(Color)) {
                                        Clr.add(inv_cl);
                                    }
                                }
                                String[] espec_cl = new String[Clr.size()];
                                for (int i = 0; i < Clr.size(); i++) {
                                    Cars automovil_cl = Clr.get(i);
                                    espec_cl[i] = automovil_cl.getBrand() + " " + automovil_cl.getColor();
                                }
                                if (Clr.size() > 0) {
                                    String seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec_cl,
                                            espec_cl[0]);
                                    if (seleccion == null) {
                                        break;
                                    }
                                    Cars autslc_cl = null;
                                    for (Cars slc_cl : inventory) {
                                        if ((slc_cl.getBrand() + " " + slc_cl.getColor()).equals(seleccion)) {
                                            autslc_cl = slc_cl;
                                            break;
                                        }
                                    }
                                    if (autslc_cl != null) {

                                        String Men = "Marca: " + autslc_cl.getBrand() + "\n" + "Color: " + autslc_cl.getColor() + "\n" + "Placa: " + autslc_cl.getPlaque() + "\n" + "Estado del vehiculo: "+ autslc_cl.getState() + "\n" + "Kilometraje del vehiculo: " + autslc_cl.getkm() + "\n" +"Propietario: " + autslc_cl.getProprietarie() + "\n" + "Precio(Dia): " + autslc_cl.getPrecio() + "$";
                                    while (true) {
                                        int opt_m = JOptionPane.showConfirmDialog(
                                         null,
                                        Men + "\nEstas seguro de rentar este auto?",
                                        "Detalle del vehiculo",
                                        JOptionPane.YES_NO_OPTION);
                                        if(opt_m==JOptionPane.YES_OPTION){
String f1;
String f2 = " ";
while (true) {
    f1 = JOptionPane.showInputDialog(null, "Ingrese la fecha de inicio (dd/MM/yyyy)");
    if(f1==null){
     break;   
    }
    f2 = JOptionPane.showInputDialog(null, "Ingrese la fecha de devolución (dd/MM/yyyy)");
   if(f2==null){
        continue;
    }
    // Validar las fechas ingresadas
    Date fechainicio = validarFecha(f1);
    Date fechafinal = validarFecha(f2);

    if (fechainicio != null && fechafinal != null) {
        // Verificar que la fecha inicial no sea mayor a la fecha final
        if (fechainicio.after(fechafinal)) {
            JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser mayor que la fecha de devolución.");
        } else {
            // Las fechas son válidas, calculamos el tiempo transcurrido y el valor total
            long tiempo_transcurrido = fechafinal.getTime() - fechainicio.getTime();
            TimeUnit unidad = TimeUnit.DAYS;
            long dias = unidad.convert(tiempo_transcurrido, TimeUnit.MILLISECONDS);
            valor_total = dias * autslc_cl.getPrecio();
            JOptionPane.showMessageDialog(null, "El Valor a pagar es " + valor_total + "$");
            break; // Salir del bucle si las fechas son válidas
        }
    } else {
        JOptionPane.showMessageDialog(null, "Fechas no válidas");
    }
} 
if(f1==null){
    continue;
}
                                         int opt_prc=JOptionPane.showConfirmDialog(null, 
                                          "El Valor a pagar es " + valor_total + "$"+"\n"+"Está seguro de querer rentar este vehiculo?",
                                          "Precio total",
                                          JOptionPane.YES_NO_OPTION);
                                           if(opt_prc==JOptionPane.YES_OPTION){
                                            if(Login_usern2.getPrice() >= valor_total){     
                                               String cdn = Login_usern2.getName();
                                                for (Cars name : inventory) {
                                                    if (name.getArrendatario().equals("||")) {
                                                        autslc_cl.setArrendatario(cdn);
                                                        autslc_cl.setDate_p(f1);
                                                        autslc_cl.setDate_g(f2);
                                                        break;
                                                    }
                                                }
                                                JOptionPane.showMessageDialog(null, "Renta exitosa");
                                                Login_usern2.setPrice(Login_usern2.getPrice()- (int)valor_total);
                                                for (User buscar : nue_user){
                                                    if(autslc_cl.getProprietarie()== buscar.getName()){
                                                     busqueda = buscar;   
                                                    }
                                                }
                                                busqueda.setPrice((int)valor_total);
                                                JOptionPane.showMessageDialog(null, "Nuevo saldo "+Login_usern2.getPrice());
                                                break;
                                            }if(Login_usern2.getPrice() < valor_total){
                                              JOptionPane.showMessageDialog(null, "El monto supera el saldo disponible ");  
                                            }
                                            } else{
                                               seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec_cl,
                                            espec_cl[0]);
                                               if (seleccion == null) {
                                          break;
                                    }
                                           } 
                                        } else {
                                                seleccion = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            espec_cl,
                                            espec_cl[0]);
                                                if (seleccion == null) {
                                        break;
                                    }
                                            }
                                    }
                                    }
                                    } else {
                                    JOptionPane.showMessageDialog(null, "No hay coches disponibles con estas especificaciones.");
                                }
                                }
                                men_cl = Integer.parseInt(JOptionPane.showInputDialog(null, Menu_cl));

                            }

                            if (men_cl == 2) {
                                for (Cars name : inventory) {
                                    if (name.getArrendatario().equals(Login_usern2.getName())) {
                                        for (User names : nue_user) {
                                            if (names.getName().equals(Login_usern2.getName())) {
                                                JOptionPane.showMessageDialog(null, "Marca: " + name.getBrand() + "\n" + "Color: " + name.getColor() + "\n" + "Placa: " + name.getPlaque() + "\n" + "Estado del vehiculo: "+ name.getState() + "\n" + "Kilometraje del vehiculo: " + name.getkm() + "\n" + "Arrendatario: " + name.getArrendatario() + "\n" + "Fecha de arriendo: " + name.getDate_p() + "\n" + "Fecha de devolución: " + name.getDate_g());
                                                break;
                                            }
                                            }
                                        }else{JOptionPane.showMessageDialog(null,"No tienes autos alquilados.");
                                            break;
                                    }
                                }
                                men_cl = Integer.parseInt(JOptionPane.showInputDialog(null, Menu_cl));
                            }
                            if(men_cl==3){
                                List<Cars> Cl = new ArrayList<Cars>();
                                for (Cars Cl_inv : inventory) {
                                    if (Cl_inv.getArrendatario().equals(Login_usern2.getName())) {
                                        Cl.add(Cl_inv);
                                    }
                                }
                                String[] Au_cl = new String[Cl.size()];
                                for (int i = 0; i < Cl.size(); i++) {
                                    Cars automovil = Cl.get(i);
                                    Au_cl[i] = automovil.getBrand() + " " + automovil.getColor();
                                }
                                if (Cl.size() > 0) {
                                    String Dvl = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil para devolver",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            Au_cl,
                                            Au_cl[0]);
                                    if (Dvl == null) {
                                        break;
                                    }
                                    Cars autslc_2 = null;
                                    for (Cars slc_2 : inventory) {
                                        if ((slc_2.getBrand() + " " + slc_2.getColor()).equals(Dvl)) {
                                            autslc_2 = slc_2;
                                            break;
                                        }
                                    }
                                    if (autslc_2 != null) {

                                        String Men = "Marca: " + autslc_2.getBrand() + "\n" + "Color: " + autslc_2.getColor() + "\n" + "Placa: " + autslc_2.getPlaque() + "\n" + "Estado del vehiculo: "+ autslc_2.getState() + "\n" + "Kilometraje del vehiculo: " + autslc_2.getkm() + "\n" + "Propietario: " + autslc_2.getProprietarie() + "\n" + "Precio(Dia): " + autslc_2.getPrecio() + "$";
                                    while (true) {
                                        int opt_2 = JOptionPane.showConfirmDialog(
                                         null,
                                        Men + "\nEstas seguro de querer devolver este auto?",
                                        "Devolución del vehiculo",
                                        JOptionPane.YES_NO_OPTION);
                                        
                                        if(opt_2==JOptionPane.YES_OPTION){
                                            //Agregar estado nuevo del vehiculo y los comentarios
                                            //Estado del vehiculo//
                                        String estado;
                                        
                                        estado = JOptionPane.showInputDialog(null, "Ingrese el estado del vehiculo "); 
                                        
                                  try {
                                     String input = JOptionPane.showInputDialog(null, "Ingrese el kilometraje del vehiculo (anterior kilometraje " + autslc_2.getkm() + ")");
                                       
                                      int kilometraje = Integer.parseInt(input);

                                      if (kilometraje < autslc_2.getkm()) {
                                      JOptionPane.showMessageDialog(null, "El quilometraje es menor al anterior");
                                       } else if (kilometraje > autslc_2.getkm()) {
                                         autslc_2.setState(estado);
                                         autslc_2.setkm(kilometraje);
                                         autslc_2.setArrendatario("||");
                                         JOptionPane.showMessageDialog(null, "Estado y kilometraje guardados");
                                         break;
                                      
                                        }
                                       } catch (NumberFormatException e) {
       
                                        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
                                          }
                                  break;
                                         
                                        //IMPORTANTE: poner la parte que Devuelva el carro//
                                        } else {
                                            Dvl = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Selecciona un auto o presiona Cancelar para salir:",
                                            "Seleccionar Automovil para devolver",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            Au_cl,
                                            Au_cl[0]);
                                      if (Dvl == null) {
                                        break;
                                    }
                                        }
                                    }
                            }
                        } else {
                                    JOptionPane.showMessageDialog(null, "No tienes coches alquilados");
                                }
                     men_cl = Integer.parseInt(JOptionPane.showInputDialog(null, Menu_cl));
                    }
                            if(men_cl==4){
                               
                                try{
                                int nue_prc=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cantidad a recargar: "));
                                Login_usern2.setPrice(Login_usern2.getPrice()+nue_prc);
                                JOptionPane.showMessageDialog(null,"Nuevo saldo: "+Login_usern2.getPrice());
                                break;
                                
                                }catch(NumberFormatException e){
                                   JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido."); 
                                }
                              
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales invalidas");
                }
            Menu_election = Integer.parseInt(JOptionPane.showInputDialog(null, Menu,"Carroflex",JOptionPane.INFORMATION_MESSAGE));
            }
            if (Menu_election == 2) {
                for (int i = 0; i < 1; i++) {
                    String[] opt = { "Arrendador", "Arrendatario" };
                    String selec = (String) JOptionPane.showInputDialog(null, "Seleccione tipo de cuenta: ", "Tipo de cuenta", JOptionPane.PLAIN_MESSAGE, null, opt, opt[0]);
                    String nam = JOptionPane.showInputDialog(null, "Ingrese su nombre: ");
                    String Last = JOptionPane.showInputDialog(null, "Ingrese su apellido");
                    String Pho = JOptionPane.showInputDialog(null, "Ingrese su numero telefonico");
                    String Docu = JOptionPane.showInputDialog(null, "Ingrese su documento: ");
                    String Use = JOptionPane.showInputDialog(null, "Nombre de usuario: ");
                    String Pass = JOptionPane.showInputDialog(null, "Contraseña: ");
                    int Pric=0;

                    nue_user.add(new User(selec, Docu, nam, Last, Pho, Use, Pass,Pric));
                    guardarEnArchivo(nue_user);
                }
                JOptionPane.showMessageDialog(null, "Registro exitoso.");
                Menu_election = Integer.parseInt(JOptionPane.showInputDialog(null, Menu,"Carroflex",JOptionPane.INFORMATION_MESSAGE));
            }
        } while (Menu_election != 3);
        JOptionPane.showMessageDialog(null, "Has salido de Carroflex");
    }
}

     

