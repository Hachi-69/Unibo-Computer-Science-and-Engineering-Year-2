package a06.e2;

public interface Model {

    int getNumberAt(Position pos);

    void initGame(int size);

    boolean isGameOver();

    boolean isEqual(int num);

    boolean hideAfterTwo();
}
