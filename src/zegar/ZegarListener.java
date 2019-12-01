package zegar;

import java.util.EventListener;

public interface ZegarListener extends EventListener {

    int wybilaGodzina(ZdarzenieZegar zegarEvent);
}
