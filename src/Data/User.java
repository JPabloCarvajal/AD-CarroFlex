package Data;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String Tipo, Name, LastN, Phone, Doc, UserN, Passw;
    private int Price;
    private static Map<Integer, Integer> expenses;
    
    public User(String Tipo, String Doc, String Name, String LastN, String Phone, String UserN, String Passw, int Price, Map<Integer, Integer> expenses){
        this.Tipo=Tipo;
        this.Doc=Doc;
        this.Name=Name;
        this.LastN=LastN;
        this.Phone=Phone;
        this.UserN=UserN;
        this.Passw=Passw;
        this.Price=Price;
        this.expenses=expenses;

    }
    public String getTipo(){
        return Tipo;
    }
    public void setTipo(String Tip){
        this.Tipo=Tip;
    }
    public String getDoc(){
        return Doc;
    }
    public void setDoc(String Docu){
        this.Doc=Docu;
    }
    public String getName(){
        return Name;
    }
    public void setName(String nam){
        this.Name=nam;
    }
    public String getLastN(){
        return LastN;
    }
    public void setLastN(String Last){
        this.LastN=Last;
    }
    public String getPhone(){
        return Phone;
    }
    public void setPhone(String Pho){
        this.Phone=Pho;
    }
    public String getUserN(){
        return UserN;
    }
    public void serUserN(String Use){
        this.UserN=Use;
    }
    public String getPassw(){
        return Passw;
    }
    public void setPassw(String Pass){
        this.Passw=Pass;
    }
    public int getPrice(){
        return Price;
    }
    public void setPrice(int Pric){
        this.Price=Pric;
    }
    public Map<Integer, Integer> getExpenses() {
        return expenses;
    }

    public void setExpenses(Map<Integer, Integer> expenses) {
        this.expenses = expenses;
    }
    //NUEVO
    private String name;
    private int price;

    // Constructor, getters y setters

    public void showRentInfo(List<Cars> inventory) {
        for (Cars car : inventory) {
            if (car.getArrendatario().equals(getName())) {
                car.showCarInfo();
            }
        }
    }

    public void rechargeBalance(int amount) {
        setPrice(getPrice() + amount);
    }
        public static void agregarGastosAnuales(int año, int gastos) {
        expenses.put(año, gastos);
    }
        public static int obtenerGastosParaAño(int año, int gastos) {
        if (!expenses.containsKey(año)) {
            expenses.put(año, gastos);
        }else {
            int gastosoriginales=expenses.get(año);
            expenses.put(año,gastosoriginales+gastos);
        }
        return expenses.get(año);
    }
}


