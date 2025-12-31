package a03b.sol2;

public interface Logic {

    void select(Position p);

    boolean isUp(Position p);

    boolean isDown(Position p);

    boolean selectionIsOver();

    void move();

    boolean isOver();

    void reset();
}
