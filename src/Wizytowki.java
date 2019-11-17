import java.util.ArrayList;

public class Wizytowki {
    private ArrayList<Wizytowka> wizytowki = new ArrayList<>();

    public Wizytowka[] getWizytowki() {
        return wizytowki.toArray(new Wizytowka[wizytowki.size()]);
    }

    public void dodajWizytowke(Wizytowka wizytowka) {
        wizytowki.add(wizytowka);
    }

    public void usunWizytowke(Wizytowka selected) {
        wizytowki.remove(selected);
    }
}
