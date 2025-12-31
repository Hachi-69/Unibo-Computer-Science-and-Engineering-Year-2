package a03a.sol2;

public interface Logic {

    boolean select(Position p);

    boolean selectionIsOver();

    void goDown();

    boolean isSelected(Position value);

    boolean isOver();

    void reset();

    boolean isObstacle(Position value); 
}
