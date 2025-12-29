package a06.e2;

public interface Logic {
    void gameInit(int size);

    int getNumberAt(Pair<Integer, Integer> pos);

    boolean collapse();
}
