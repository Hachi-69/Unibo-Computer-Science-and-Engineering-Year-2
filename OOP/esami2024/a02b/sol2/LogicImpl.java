package a02b.sol2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class LogicImpl implements Logic {

    private final int size;
    private Set<Position> hit = new HashSet<>();

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public boolean isOver() {
        return this.hit.size() == 5;
    }

    @Override
    public boolean inArea(Position pos) {
        return pos.x() > pos.y() && pos.x() < size - 1;
    }

    @Override
    public boolean hit(Position position) {
        if (this.hit.contains(position)){
            this.hit.remove(position);
            return false;
        } else {
            this.hit.add(position);
            return true;
        }
    }

    @Override
    public void restart() {
        this.hit.clear();
    }

    @Override
    public Optional<Integer> getValue(Position position) {
        if (!this.isOver() || (position.x() != position.y() && position.x() < size - 1)){
            return Optional.empty();
        }
        return position.x() == position.y() ? Optional.of((int)this.hit.stream().filter(p -> p.x() == position.x()).count())
                : Optional.of((int)this.hit.stream().filter(p -> p.y() == position.y()).count());
    }

}
