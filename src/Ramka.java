import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Ramka extends JFrame {

    private static int liczbaKlikniecDowolonego = 0;
    private JButton wcisnietyPoprzednio;
    private JTextField poleTekstowe;
    private JLabel etykieta;

    public static void main(String[] args) {
        new Ramka();
    }

    public Ramka() {

        JButton b = new JButton("przycisk b");
        JButton c = new JButton("przycisk c");
        JButton d = new JButton("przycisk d ");

        getContentPane().add(b);
        getContentPane().add(c);
        getContentPane().add(d);

        Sluchacz s = new Sluchacz();
        Sluchacz s2 = new Sluchacz();
        Sluchacz s3 = new Sluchacz();


        b.addActionListener(s);
        c.addActionListener(s2);
        d.addActionListener(s3);

        poleTekstowe = new JTextField("Wpisz tekst");
        getContentPane().add(poleTekstowe);
        poleTekstowe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poleTekstowe.getAccessibleContext();
            }
        });

        etykieta = new JLabel("Etykietka");
        getContentPane().add(etykieta);


        setLayout(new FlowLayout());
        pack();
        setVisible(true);

    }

    public class Sluchacz implements ActionListener {
        private int clickCounter;

        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            JButton j = (JButton) o;
            j.setEnabled(false);

            if (wcisnietyPoprzednio!=null){
                wcisnietyPoprzednio.setEnabled(true);
            }

            wcisnietyPoprzednio=j;

            System.out.println("klikniecia przycisku : "+clickCounter);
            clickCounter++;
        }
    }

}


// doprecyzowanie tekst znika z etykiety z drugiego slajdu ostatnia linijka
//Napisz program okienkowy zawierający przycisk,
//etykietę i pole tekstowe.