import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Okno extends JFrame {
    private Wizytowki wizytowki = new Wizytowki();
    private JList<Wizytowka> lista;
    private Wizytowka selected;

    public static void main(String[] args) {
        new Okno();
    }

    public Okno() {
        lista = new JList<Wizytowka>();

        lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lista.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        lista.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(lista);
        listScroller.setPreferredSize(new Dimension(100, 400));
        JTextField tfNazwa = new JTextField("-Nazwisko-");
        JTextField tfWzrost = new JTextField("-Wzrost-");

        JPanel panel = new JPanel();
        panel.add(listScroller);


        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selected = lista.getSelectedValue();
                if (selected == null) return;

                JOptionPane.showMessageDialog(panel,
                        "Osoba o nazwisku "+selected.getNazwisko()
                );
            }
        });

        JButton dodaj = new JButton("Dodaj wizytowke");
        dodaj.addActionListener(e -> {
            if (tfNazwa.getText().equals("-Nazwisko-") || tfWzrost.getText().equals("-Wzrost-"))
                JOptionPane.showMessageDialog(panel, "Podaj dane osoby", "brak danych", JOptionPane.ERROR_MESSAGE);
            else {
                int wzrost = 0;
                try {
                    wzrost = Integer.parseInt(tfWzrost.getText());
                    Wizytowka w = new Wizytowka(tfNazwa.getText(), wzrost);
                    wizytowki.dodajWizytowke(w);
                    lista.setListData(wizytowki.getWizytowki());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            panel, "Wzrost musi byc liczba", "Zly format wzrostu", JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        });

        JButton usun = new JButton("Usun wizytowke");
        usun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                int dec = JOptionPane.showConfirmDialog(
                        panel,
                        "Usunac osobe o nazwisku "+selected.getNazwisko(),
                        "Usunac?",
                        JOptionPane.YES_NO_OPTION
                );
                if (dec == JOptionPane.OK_OPTION) {
                    if (selected == null) return;
                    wizytowki.usunWizytowke(selected);
                    lista.setListData(wizytowki.getWizytowki());}
                }
            }
        });

        JButton odczytaj = new JButton("Odczytaj");
        odczytaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    JOptionPane.showMessageDialog(panel,
                            "Osoba o nazwisku "+selected.getNazwisko()
                    );
                }
            }
        });


        JPanel panel2 = new JPanel();
        panel2.add(dodaj);
        panel2.add(usun);
        panel2.add(odczytaj);
        panel2.add(tfNazwa);
        panel2.add(tfWzrost);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

       // wizytowki.usunWizytowke(selected);
        //lista.setListData(wizytowki.getWizytowki());

        getContentPane().add(panel);
        //getContentPane().add(lista);  - nalezy do panelu
        ///getContentPane().add(listScroller); - nalezy do listy wiec wsadzmy na gore

        // utworzyc nowy panel na to!!
        //getContentPane().add(tfNazwa);
        //getContentPane().add(tfWzrost);
        //getContentPane().add(dodaj);
        getContentPane().add(panel2);

        setLayout(new FlowLayout());
        pack();
        setVisible(true);

    }


}
//panele do konkretnych kontrole w pracy domowej
//nazwy wizytowek brane z toString wizytowki
//multiselect - usun kilka na raz

/*   @Override
            public void actionPerformed(ActionEvent e) {
                if (selected == null) return;
                else {
                int dec = JOptionPane.showConfirmDialog(
                        panel,
                        "Usunac osobe o nazwisku "+selected.getNazwisko(),
                        "Usunac?",
                        JOptionPane.YES_NO_OPTION
                );
                if (dec == JOptionPane.OK_OPTION) {
                    if (selected == null) return;
                    wizytowki.usunWizytowke(selected);
                    lista.setListData(wizytowki.getWizytowki());}*/

//Sprawdzic czemu return nie wychodzi