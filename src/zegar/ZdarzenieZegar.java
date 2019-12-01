package zegar;

import java.awt.*;

public class ZdarzenieZegar extends AWTEvent {

    int godzina;

    public static final int ZEGAR_EVENT = AWTEvent.RESERVED_ID_MAX + 150;

    public ZdarzenieZegar(Object source, int godzina) {
        super(source, ZEGAR_EVENT);
        this.godzina = godzina;
    }

    public int getGodzina() {return godzina;}
}



// zacznij od zdarzenia, powinno miec pola: godzina, ktora mozna przeczytac w sluchaczu pozniej (w konstruktorze czy secie godzina) zacznij od 5;00 np(nie systemowy)
// osobna klasa interfejsowa - robimy interface - jedna metoda wybilaGodzina
// kontrolka do liczenia godziny kontrolka dziedziczy po JComponent, tworzy watek, dodaje sluchaczy, ma petle - czeka iles czasu, i tworzy nowe zdarzenie z informacja
// klasa testowa - ramka, komponent, sluchacz, ktory bedzie wypisywal