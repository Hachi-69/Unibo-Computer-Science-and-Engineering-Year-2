package a02a.sol2;

import java.util.List;

public interface Logic {

    List<Position> path();

    void advance(int advancing);

    Position mark();

    boolean isPitfall(Position p);

    boolean isOver();
}
