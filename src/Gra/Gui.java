package Gra;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private JFrame rozmiarGryOkno = new JFrame("Wybierz rozmiar planszy do gry 7x7 lub 8x8");
    Logika logika;

    private JPanel panelGry = new JPanel();
    private JPanel panelOpisu = new JPanel();
    private JPanel gracze = new JPanel();
    private JPanel punktyITura = new JPanel();

    private JTextField rozmiarPlanszy = new JTextField("Wprowadz dlugosc i wysokosc planszy '7' lub '8'");
    private JButton zatwierdzRozmiarPlanszy = new JButton("Zatwierdz");

    private JLabel gracz1 = new JLabel("Czerwony");
    private JLabel gracz2 = new JLabel("Niebieski");

    private JLabel punktyNiebieskiego = new JLabel("Pkt niebieski:0 ");
    private JLabel punktyCzerwonego = new JLabel("Pkt czerwony: 0");

    private Font f = new Font("Monaco", Font.BOLD, 14);
    private JButton[][] przyciski;
    private int[][] wcisnietePrzyciski;
    static int rozmiar;
    public static int liczbaWszystkichZaznaczonychPol = 0;

    public static void main(String[] args) {
        new Gui();
    }

    public Gui() {
        setTitle("Gra turowa");
        this.setBounds(300, 300, 700, 600);
        rozmiarGryOkno.setVisible(true);
        rozmiarGryOkno.setLayout(new FlowLayout());
        rozmiarGryOkno.setBounds(700, 400, 400, 200);
        rozmiarGryOkno.add(rozmiarPlanszy, CENTER_ALIGNMENT);
        rozmiarGryOkno.add(zatwierdzRozmiarPlanszy);

        zatwierdzRozmiarPlanszy.setLayout(new BoxLayout(zatwierdzRozmiarPlanszy, BoxLayout.PAGE_AXIS));

        panelOpisu.setLayout(new GridLayout(3, 1));
        gracze.setLayout(new FlowLayout());
        gracz1.setOpaque(true);
        gracz1.setFont(f);
        gracz1.setBackground(Color.RED);
        gracz1.setForeground(Color.WHITE);
        gracze.add(gracz1, TOP_ALIGNMENT);
        gracze.add(gracz2, BOTTOM_ALIGNMENT);

        panelOpisu.add(gracze, BOTTOM_ALIGNMENT);
        panelOpisu.add(punktyITura, TOP_ALIGNMENT);

        punktyITura.setBounds(100, 400, 50, 100);
        punktyITura.add(punktyNiebieskiego);
        punktyITura.add(punktyCzerwonego);

        zatwierdzRozmiarPlanszy.addActionListener(actionEvent -> {
            if ((!rozmiarPlanszy.getText().equals("7") &&
                    !rozmiarPlanszy.getText().equals("8"))) {
                JOptionPane.showMessageDialog(panelGry, "Wybierz rozmiar planszy '7' lub '8'", "Rozmiar planszy", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    rozmiar = Integer.parseInt(rozmiarPlanszy.getText());
                    logika = new Logika(rozmiar);
                    przyciski = new JButton[rozmiar][rozmiar];
                    panelGry.setLayout(new GridLayout(rozmiar, rozmiar, 2, 2));
                    rozmiarGryOkno.setVisible(false);
                    this.setVisible(true);
                    wcisnietePrzyciski = new int[rozmiar][rozmiar];
                    for (int i = 0; i < rozmiar; i++) {
                        for (int j = 0; j < rozmiar; j++) {
                            JButton b = new JButton();
                            przyciski[i][j] = b;
                            panelGry.add(b);
                            int finalI = i;
                            int finalJ = j;
                            b.addActionListener(actionEvent2 -> {
                                Object o = actionEvent2.getSource();
                                JButton j1 = (JButton) o;
                                j1.setEnabled(false);

                                if (logika.isCzyTuraGraczaNiebieskiego()) {
                                    j1.setOpaque(true);
                                    j1.setBackground(Color.BLUE);
                                    ustawKoloryNiebieskiemu();
                                    logika.oznaczPunktGracza(finalI, finalJ);
                                    logika.setCzyTuraGraczaNiebieskiego(false);
                                    liczbaWszystkichZaznaczonychPol++;
                                    logika.sprawdzCzyDodacPunkt();
                                    punktyNiebieskiego.setText("Pkt niebieski: " + logika.getPunktyGraczaNiebieskiego());
                                    zweryfikujCzyKoniecINowaRogrywka();
                                } else {
                                    j1.setOpaque(true);
                                    j1.setBackground(Color.RED);
                                    ustawKoloryCzerwonemu();
                                    logika.oznaczPunktGracza(finalI, finalJ);
                                    logika.setCzyTuraGraczaNiebieskiego(true);
                                    liczbaWszystkichZaznaczonychPol++;
                                    logika.sprawdzCzyDodacPunkt();
                                    punktyCzerwonego.setText("Pkt czerwony: " + logika.getPunktyGraczaCzerwonego());
                                    zweryfikujCzyKoniecINowaRogrywka();
                                }
                            });
                        }
                    }
                    this.setSize(800, 600);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            panelGry, "Dlugosc i wysokosc musi byc liczba", "Zly format dlugosci/szerokosci", JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        JSplitPane podzielenieGlowne = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelOpisu, panelGry);
        podzielenieGlowne.setDividerLocation(300);
        getContentPane().add(podzielenieGlowne);

        this.setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }


    public static int getLiczbaWszystkichZaznaczonychPol() {
        return liczbaWszystkichZaznaczonychPol;
    }

    public void ustawKoloryNiebieskiemu() {
        gracz1.setFont(f);
        gracz1.setOpaque(true);
        gracz1.setBackground(Color.RED);
        gracz1.setForeground(Color.WHITE);
        gracz2.setBackground(null);
        gracz2.setForeground(null);
    }

    public void ustawKoloryCzerwonemu() {
        gracz2.setFont(f);
        gracz2.setOpaque(true);
        gracz2.setBackground(Color.BLUE);
        gracz2.setForeground(Color.WHITE);
        gracz1.setBackground(null);
        gracz1.setForeground(null);
    }

    public void zweryfikujCzyKoniecINowaRogrywka() {
        if (!logika.sprawdzCzyPozostalyPolaDoZaznaczenia()) {
            JOptionPane.showMessageDialog(panelGry, "Wygral gracz: " +
                    logika.podajWygranego(), "Koniec gry!", JOptionPane.ERROR_MESSAGE);
            int powtorka = JOptionPane.showConfirmDialog(
                    panelGry,
                    "Czy chcesz zagrac ponownie? ",
                    "Ponowna gra?",
                    JOptionPane.YES_NO_OPTION
            );
            if (powtorka == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else {
                this.dispose();
                liczbaWszystkichZaznaczonychPol = 0;
                new Gui();
            }
        }
    }
}
