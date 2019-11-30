package Gra;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private Logika logika = new Logika();

    private JPanel panelGry = new JPanel();
    private JPanel panelOpisu = new JPanel();
    private JPanel gracze = new JPanel();
    private JPanel punktyITura = new JPanel();
    private JTextField rozmiarPlanszy = new JTextField("Wprowadz dlugosc i wysokosc planszy '7' lub '8'");
    private JButton zatwierdzRozmiarPlanszy = new JButton("Zatwierdz");
    private JLabel gracz1 = new JLabel("Czerwony");
    private JLabel gracz2 = new JLabel("Niebieski");

    private JLabel punktyNiebieskiego = new JLabel("Pkt niebieski:" + logika.getPunktyGraczaNiebieskiego());
    private JLabel punktyCzerwonego = new JLabel("Pkt czerwony:" + logika.getPunktyGraczaCzerwonego());

    private Font f = new Font("Monaco", Font.BOLD, 14);
    private JButton[][] przyciski;
    private int[][] wcisnietePrzyciski;

    private JFrame rozmiarGryOkno = new JFrame("Wybierz rozmiar planszy do gry");
    static int rozmiar;
    public static int liczbaWszystkichZaznaczonychPol = 0;

    public static void main(String[] args) {
        new Gui();
    }

    public Gui() {

        rozmiarGryOkno.setVisible(true);
        rozmiarGryOkno.setLayout(new FlowLayout());
        rozmiarGryOkno.setSize(400, 300);

        rozmiarGryOkno.add(rozmiarPlanszy, CENTER_ALIGNMENT);
        rozmiarGryOkno.add(zatwierdzRozmiarPlanszy);
        //.setLayout(new BoxLayout(rozmiarPlanszy, BoxLayout.PAGE_AXIS));
        zatwierdzRozmiarPlanszy.setLayout(new BoxLayout(zatwierdzRozmiarPlanszy, BoxLayout.PAGE_AXIS));


        setTitle("Gra turowa");
        this.setSize(600, 600);

        panelOpisu.setLayout(new BoxLayout(panelOpisu, BoxLayout.Y_AXIS));

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
        ////punktyITura.setLayout(new BoxLayout(punktyITura, BoxLayout.PAGE_AXIS));

        punktyITura.add(punktyNiebieskiego);
        punktyITura.add(punktyCzerwonego);



        zatwierdzRozmiarPlanszy.addActionListener(actionEvent -> {
            if (rozmiarPlanszy.getText().equals("Wprowadz dlugosc i wysokosc planszy '7' lub '8'")) {
                JOptionPane.showMessageDialog(panelGry, "Wybierz rozmiar planszy '7' lub '8'", "Rozmiar planszy", JOptionPane.ERROR_MESSAGE);

            } else {
                try {
                    rozmiar = Integer.parseInt(rozmiarPlanszy.getText());
                    logika.wcisnietePrzyciski = new String[rozmiar][rozmiar];
                    logika.setRozmiar(rozmiar);
                    przyciski = new JButton[rozmiar][rozmiar];
                    panelGry.setLayout(new GridLayout(rozmiar, rozmiar, 2, 2));
                    rozmiarGryOkno.setVisible(false);
                    this.setVisible(true);

                    for (int i = 0; i < rozmiar; i++) {
                        for (int j = 0; j < rozmiar; j++) {
                            JButton b = new JButton();
                            przyciski[i][j] = b;
                            panelGry.add(b);
                            wcisnietePrzyciski = new int[rozmiar][rozmiar];
                            int k = 0;
                            wcisnietePrzyciski[i][j] = k;
                            int finalI = i;
                            int finalJ = j;
                            b.addActionListener(actionEvent2 -> {
                                Object o = actionEvent2.getSource();
                                JButton j1 = (JButton) o;
                                j1.setEnabled(false);

                                if (logika.isCzyTuraGraczaNiebieskiego()) {
                                    j1.setOpaque(true);
                                    j1.setBackground(Color.BLUE);
                                    logika.oznaczPunktGracza(finalI, finalJ);
                                    logika.setCzyTuraGraczaNiebieskiego(false);
                                    liczbaWszystkichZaznaczonychPol++;
                                    gracz1.setFont(f);
                                    gracz1.setOpaque(true);
                                    gracz1.setBackground(Color.RED);
                                    gracz1.setForeground(Color.WHITE);
                                    gracz2.setBackground(null);
                                    gracz2.setForeground(null);
                                    logika.sprawdzCzyDodacPunkt();
                                    punktyNiebieskiego.setText("Pkt niebieski: " + logika.getPunktyGraczaNiebieskiego());
                                    if (!logika.sprawdzCzyPozostalyPolaDoZaznaczenia()) {
                                        JOptionPane.showMessageDialog(panelGry, "Wygral gracz: "+
                                                logika.podajWygranego() , "Koniec gry!", JOptionPane.ERROR_MESSAGE);
                                        System.exit(0);
                                    }
                                } else {
                                    j1.setOpaque(true);
                                    j1.setBackground(Color.RED);
                                    logika.oznaczPunktGracza(finalI, finalJ);
                                    logika.setCzyTuraGraczaNiebieskiego(true);
                                    liczbaWszystkichZaznaczonychPol++;
                                    gracz2.setFont(f);
                                    gracz2.setOpaque(true);
                                    gracz2.setBackground(Color.BLUE);
                                    gracz2.setForeground(Color.WHITE);
                                    gracz1.setBackground(null);
                                    gracz1.setForeground(null);
                                    logika.sprawdzCzyDodacPunkt();
                                    punktyCzerwonego.setText("Pkt czerwony: " + logika.getPunktyGraczaCzerwonego());
                                    if (!logika.sprawdzCzyPozostalyPolaDoZaznaczenia()) {
                                        JOptionPane.showMessageDialog(panelGry, "Wygral gracz: "+
                                                logika.podajWygranego() , "Koniec gry!", JOptionPane.ERROR_MESSAGE);
                                        System.exit(0);
                                    }
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
        podzielenieGlowne.setDividerLocation(150);
        getContentPane().add(podzielenieGlowne);

        pack();
        this.setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static int getRozmiar() {
        return rozmiar;
    }

    public static int getLiczbaWszystkichZaznaczonychPol() {
        return liczbaWszystkichZaznaczonychPol;
    }
}
