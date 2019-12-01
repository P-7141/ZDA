package zegar;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Godzina");
        Kontrolka kontrolka = new Kontrolka();

        panel.add(label);
        panel.add(kontrolka);
        getContentPane().add(panel);

        kontrolka.addZegarListener(zegarEvent -> {
            System.out.println(zegarEvent.getGodzina());
            label.setText("Godzina: "+ String.valueOf(zegarEvent.getGodzina()));
            return zegarEvent.getGodzina();
        });

        this.setBounds(900,400, 400,400);
        //setLayout(new FlowLayout());
        pack();
        setVisible(true);
    }
}
