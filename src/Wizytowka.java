
public class Wizytowka {

    private String nazwisko;
    private int wzrost ; //w cm


    //konstruktor
    public Wizytowka(){}
    public Wizytowka(String n, int w){
        nazwisko = n;
        wzrost = w;
    }

    // akcesory
    public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public int getWzrost() {
        return wzrost;
    }
    public void setWzrost(int wzrost) {
        this.wzrost = wzrost;
    }

    public String toString() {
        return nazwisko + wzrost;
    }


}
