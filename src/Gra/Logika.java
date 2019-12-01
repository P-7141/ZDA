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
        boolean czyWszystkieWLinii = false;

        int punktyZaWszystkieLinie = 0;
        for (int i = rozmiar - 2; i > 0; i--) {
            int punktyZaLinie = 0;
            int k = i;
            for (int j = 0; j < rozmiar - i; ) {

                if (gracz.equalsIgnoreCase(getUzytkownikZPrzycisku(k, j))) {
                    czyWszystkieWLinii = true;
                    int punktyWPojedynczejLinii = punktyZaLinie;
                    punktyZaLinie = punktyWPojedynczejLinii + 1;
                } else {
                    czyWszystkieWLinii = false;
                    break;
                }
                j++;
                k++;

            }
            if (czyWszystkieWLinii) punktyZaWszystkieLinie += punktyZaLinie;

        }
        return punktyZaWszystkieLinie;
    }

    public int policzPunktyZaSkosZPrawejWDol(String gracz) {
        boolean czyWszystkieWLinii = false;

        int punktyZaWszystkieLinie = 0;
        for (int j = rozmiar - 2; j > -0.5; j--) {
            int punktyZaLinie = 0;
            int k = j;
            for (int i = 0; i < rozmiar - j; ) {
                if (gracz.equalsIgnoreCase(getUzytkownikZPrzycisku(i, k))) {
                    czyWszystkieWLinii = true;
                    int punktyWPojedynczejLinii = punktyZaLinie;
                    punktyZaLinie = punktyWPojedynczejLinii + 1;
                } else {
                    czyWszystkieWLinii = false;
                    break;
                }
                i++;
                k++;

            }
            if (czyWszystkieWLinii) punktyZaWszystkieLinie += punktyZaLinie;

        }
        return punktyZaWszystkieLinie;
    }

    /*public int policzPunktyZaSkosZLewejWGore(String gracz) {
        boolean czyWszystkieWLinii = false;

        int punktyZaWszystkieLinie = 0;
        for (int i = rozmiar - 2; i > 0; i--) {
            int punktyZaLinie = 0;
            int k = i;
            for (int j = rozmiar-1; j*//*4*//* > rozmiar-i-1*//*2*//*;) {
                System.out.println(k + " " + j);

                if (gracz.equalsIgnoreCase(getUzytkownikZPrzycisku(k, j))) {
                    czyWszystkieWLinii = true;
                    int punktyWPojedynczejLinii = punktyZaLinie;
                    punktyZaLinie = punktyWPojedynczejLinii+1;
                } else {
                    czyWszystkieWLinii = false;
                    break;
                }
                j--;
                k--;

            } if (czyWszystkieWLinii) punktyZaWszystkieLinie+=punktyZaLinie;

        }
        return punktyZaWszystkieLinie;
    }*/

    public void sprawdzCzyDodacPunkt() {
        if (czyTuraGraczaNiebieskiego) {
            punktyGraczaCzerwonego = policzPunktyZaPoziom("czerwony") + policzPunktyZaPion("czerwony") +
                    policzPunktyZaSkosZLewejWDol("czerwony") + policzPunktyZaSkosZPrawejWDol("czerwony");
        } else punktyGraczaNiebieskiego = policzPunktyZaPoziom("niebieski") + policzPunktyZaPion("niebieski") +
                policzPunktyZaSkosZLewejWDol("niebieski") + policzPunktyZaSkosZPrawejWDol("niebieski");

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
        if (punktyGraczaCzerwonego == punktyGraczaNiebieskiego) return wygrany;
        else if (punktyGraczaNiebieskiego > punktyGraczaCzerwonego) {
            wygrany = "Niebieski";
        } else wygrany = "Czerwony";
        return wygrany;
    }

    public static void main(String[] args) {

    }

}
