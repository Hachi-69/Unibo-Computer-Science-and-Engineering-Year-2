package a01c.e2;

public interface Logic {
    void init(int size);

    int getAtPos(Position p);

    void hit(Position p);

    void calculateRectangle();

    boolean checkHitOrder(Position p);
}
