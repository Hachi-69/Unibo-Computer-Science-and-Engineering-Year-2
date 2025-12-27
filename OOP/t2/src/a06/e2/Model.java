package a06.e2;

public interface Model {

    // Restituisce il numero associato a una coordinata (1-6)
    int getNumberAt(Position pos);

    // Inizializza la griglia (puoi farlo anche nel costruttore dell'impl)
    void initGame(int size);

    boolean isGameOver();

    boolean isEqual(int num);

    boolean hideAfterTwo();
}
