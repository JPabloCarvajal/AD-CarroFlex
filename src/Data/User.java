package Data;


public class User {
    private String Tipo, Name, LastN, Phone, Doc, UserN, Passw;
    private int Price;
    
    public User(String Tipo, String Doc, String Name, String LastN, String Phone, String UserN, String Passw, int Price){
        this.Tipo=Tipo;
        this.Doc=Doc;
        this.Name=Name;
        this.LastN=LastN;
        this.Phone=Phone;
        this.UserN=UserN;
        this.Passw=Passw;
        this.Price=Price;

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
}


