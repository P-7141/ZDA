package PrzyciskDwustanowy;

import javax.swing.*;


public class Okienko extends JFrame {

    boolean isObjectSelected = false;

    public Okienko() {
        JPanel panel = new JPanel();
        Przycisk przycisk = new Przycisk();

        panel.add(przycisk);
        getContentPane().add(panel);

        przycisk.addActionListener(e -> {
/*
            Object o = e.getSource();
            JToggleButton j = (JToggleButton) o;
            if (isObjectSelected) return;
            else {
                j.setSelected(true);
                paintComponents(Przycisk.);
            }*/

        });
    }
}
