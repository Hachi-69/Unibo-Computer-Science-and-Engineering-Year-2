package a02b.sol2;

import java.util.Optional;

public interface Logic {

    boolean isOver();

    boolean inArea(Position pos);

    boolean hit(Position position);

    void restart();

    Optional<Integer> getValue(Position position);
}
