package a01b.e2;

public interface Logic {
    void init(int size);

    boolean hit(Position p);

    void calculateRhombus(Position p1, Position p2);

    String getAtPos(Position p);
}
