package a06.e2;

import java.util.Map;

public interface Logic {
    void gameInit(int size);

    int getNumberAt(Pair<Integer, Integer> pos);

    boolean collapse();
}
