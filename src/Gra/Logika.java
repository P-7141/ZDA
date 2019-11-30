package Gra;

public class Logika {

    private int punktyGraczaNiebieskiego = 0;
    private int punktyGraczaCzerwonego = 0;
    String[][] wcisnietePrzyciski;
    int rozmiar;

    private boolean czyTuraGraczaNiebieskiego = false;

    public void setRozmiar(int rozmiar) {
        this.rozmiar = rozmiar;
    }

    public void oznaczPunktGracza(int ktoryWiersz, int ktoraKolumna) {
        if (czyTuraGraczaNiebieskiego) {
            wcisnietePrzyciski[ktoryWiersz][ktoraKolumna] = "niebieski";
        } else {
            wcisnietePrzyciski[ktoryWiersz][ktoraKolumna] = "czerwony";
        }
    }

    public int policzPunktyZaPoziom(String gracz) {
        boolean czyPunktZaWierwsz = false;
        int punktyZaKolumny = 0;
        for (int i = 0; i < rozmiar; i++) {

            for (int j = 0; j < rozmiar; j++) {
                if (gracz.equalsIgnoreCase(getUzytkownikZPrzycisku(i, j))) {
                    czyPunktZaWierwsz = true;
                } else {
                    czyPunktZaWierwsz = false;
                    break;
                }
            }
            if (czyPunktZaWierwsz) punktyZaKolumny += rozmiar;
        }
        return punktyZaKolumny;
    }

    public int policzPunktyZaPion(String gracz) {
        boolean czyPunktZKolumny = false;
        int punktyZaKolumny = 0;
        for (int i = 0; i < rozmiar; i++) {

            for (int j = 0; j < rozmiar; j++) {
                if (gracz.equalsIgnoreCase(getUzytkownikZPrzycisku(j, i))) {
                    czyPunktZKolumny = true;
                } else {
                    czyPunktZKolumny = false;
                    break;
                }
            }
            if (czyPunktZKolumny) punktyZaKolumny += rozmiar;
        }
        return punktyZaKolumny;
    }

    public int policzPunktyZaSkosZLewejWDol(String gracz) {
        boolean czyPunktZaSkos = false;
        int punktyZaKolumny = 0;
        for (int i = 0; i < rozmiar; i++) {
            int zachowaneI = i;
            for (int j = 0; j < policzDlugoscSkosuNaPlanszy(zachowaneI); j++) {
                if (gracz.equalsIgnoreCase(getUzytkownikZPrzycisku(i, j))) {
                    czyPunktZaSkos = true;
                    i+=1;
                } else {
                    czyPunktZaSkos = false;
                    break;
                }
            }
            if (czyPunktZaSkos) punktyZaKolumny += (rozmiar-zachowaneI);
        }
        return punktyZaKolumny;
    }

   /* public int policzPunktyZaSkosZPrawejWDol(String gracz) {
        boolean czyPunktZaSkos = false;
        int punktyZaKolumny = 0;
        for (int i = 0; i < rozmiar; i++) {
            int zachowaneI = i;
            for (int j = rozmiar-1; j < (rozmiar-policzDlugoscSkosuNaPlanszy(zachowaneI))+1; j--) {
                if (gracz.equalsIgnoreCase(getUzytkownikZPrzycisku(i, j))) {
                    czyPunktZaSkos = true;
                    i+=1;
                } else {
                    czyPunktZaSkos = false;
                    break;
                }
            }
            if (czyPunktZaSkos) punktyZaKolumny += (rozmiar-zachowaneI);
        }
        return punktyZaKolumny;
    }*/


    public void sprawdzCzyDodacPunkt() {
        if (czyTuraGraczaNiebieskiego) {
            punktyGraczaCzerwonego = policzPunktyZaPoziom("czerwony") + policzPunktyZaPion("czerwony") +
            policzPunktyZaSkosZLewejWDol("czerwony");// + policzPunktyZaSkosZPrawejWDol("czerwony");
        } else punktyGraczaNiebieskiego = policzPunktyZaPoziom("niebieski") + policzPunktyZaPion("niebieski") +
                policzPunktyZaSkosZLewejWDol("niebieski");// + policzPunktyZaSkosZPrawejWDol("niebieski");

    }

    public String getUzytkownikZPrzycisku(int wiersz, int kolumna) {
        return wcisnietePrzyciski[wiersz][kolumna];
    }

    public boolean isCzyTuraGraczaNiebieskiego() {
        return czyTuraGraczaNiebieskiego;
    }

    public void setCzyTuraGraczaNiebieskiego(boolean czyTuraNiebieskiego) {
        czyTuraGraczaNiebieskiego = czyTuraNiebieskiego;
    }

    public int getPunktyGraczaNiebieskiego() {
        return punktyGraczaNiebieskiego;
    }

    public int getPunktyGraczaCzerwonego() {
        return punktyGraczaCzerwonego;
    }

    public boolean sprawdzCzyPozostalyPolaDoZaznaczenia() {
        boolean czyPozostaly;
        if (Gui.getLiczbaWszystkichZaznaczonychPol() >= rozmiar * rozmiar) {
            czyPozostaly = false;
        } else {
            czyPozostaly = true;
        }
        return czyPozostaly;
    }

    public String podajWygranego() {
        String wygrany = "remis";
        if (punktyGraczaCzerwonego==punktyGraczaNiebieskiego) return wygrany;
        else if (punktyGraczaNiebieskiego>punktyGraczaCzerwonego) {
            wygrany = "Niebieski";
        } else wygrany = "Czerwony";
        return wygrany;
    }

    public int policzDlugoscSkosuNaPlanszy(int x) {
        int dlugosc = 0;
        switch (x) {
            case 0:
                dlugosc = rozmiar;
                break;
            case 1:
                dlugosc = rozmiar-1;
                break;
            case 2:
                dlugosc = rozmiar-2;
                break;
            case 3:
                dlugosc = rozmiar-3;
                break;
            case 4:
                dlugosc = rozmiar-4;
                break;
            case 5:
                dlugosc = rozmiar-5;
                break;
            case 6:
                dlugosc = rozmiar-6;
                break;
            case 7:
                dlugosc = rozmiar-7;
                break;
            case 8:
                dlugosc = rozmiar-8;
                break;
        } return dlugosc;
    }

    public static void main(String[] args) {

    }

}
