package zegar;

import javax.swing.*;
import java.awt.*;


public class Kontrolka extends JComponent implements Runnable {

    int godzina;

    public void addZegarListener(ZegarListener listener) {
        listenerList.add(ZegarListener.class, listener);
    }

    public void removeGameListener(ZegarListener listener) {
        listenerList.remove(ZegarListener.class, listener);
    }

    @Override
    public void processEvent(AWTEvent evt) {
        if (evt instanceof ZdarzenieZegar) {
            ZegarListener[] listeners = listenerList.getListeners(ZegarListener.class);
            ZdarzenieZegar zdarzenieZegar = (ZdarzenieZegar) evt;
            for (int i = 0; i < listeners.length; i++) {
                listeners[i].wybilaGodzina(zdarzenieZegar);
            }
        } else {
            super.processEvent(evt);
        }
    }

    Kontrolka() {
        godzina = 0;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            ZdarzenieZegar zdarzenieZegar = new ZdarzenieZegar(this, godzina);
            queue.postEvent(zdarzenieZegar);
            godzina++;

        }
    }
}
