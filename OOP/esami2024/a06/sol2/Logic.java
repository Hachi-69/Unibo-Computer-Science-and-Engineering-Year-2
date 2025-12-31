package a06.sol2;

public interface Logic {

    void hit(Position pos);

    boolean hasValue(Position pos);

    int getValue(Position pos);    

    boolean isOver();
}
