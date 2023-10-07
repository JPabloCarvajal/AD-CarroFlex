package Data;


public class Cars {
    String Brand, Plaque, Color, Proprietarie, Arrendatario,Date_p, Date_g, State;
    private int crPrecio, km;
    
    
    public Cars(String Brand, String Color, String Plaque, String State, int km ,String Proprietarie, String Arrendatario,String Date_p, String Date_g, int crPrecio){
        this.Brand=Brand;
        this.Color=Color;
        this.Plaque=Plaque;
        this.State=State;
        this.km=km;
        this.Proprietarie=Proprietarie;
        this.Arrendatario=Arrendatario;
        this.Date_p=Date_p;
        this.Date_g=Date_g;
        this.crPrecio = crPrecio;
    }
    public String getBrand(){
        return Brand;
    }
    public void setBrand(String Bra){
        this.Brand=Bra;
    }
    public String getColor(){
        return Color;
    }
    public void setColor(String Clr){
        this.Color=Clr;
    }
    public String getPlaque(){
        return Plaque;
    }
    public void setPlaque(String Plc){
        this.Plaque=Plc;
    }
    public String getState(){
        return State;
    }
    public void setState(String sta){
        this.State=sta;
    }
    public int getkm(){
        return km;
    }
    public void setkm(int x){
        this.km=x;
    }
    public String getProprietarie(){
        return Proprietarie;
    }
    public void setPropietarie(String Pro){
        this.Proprietarie=Pro;
    }
    public String getDate_p(){
        return Date_p;
    }
    public String getArrendatario(){
        return Arrendatario;
    }
    public void setArrendatario(String Arr){
        this.Arrendatario=Arr;
    }
    public void setDate_p(String date1){
        this.Date_p=date1;
    }
    public String getDate_g(){
        return Date_g;
    }
    public void setDate_g(String date2){
        this.Date_g=date2;
    }
    public int getPrecio(){
        return crPrecio;
    }
    public void setPrecio(int precio){
        this.crPrecio = precio;
    }
    

}
