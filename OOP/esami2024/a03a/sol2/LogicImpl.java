package a03a.sol2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LogicImpl implements Logic {

    private static final int MAX_SELECTED = 5;
    private static final int MAX_OBSTACLES = 3;
    private final int size;
    private final List<Position> selected = new LinkedList<>();
    private final Set<Position> obstacles = new HashSet<>();
    private boolean isOver = false;

    public LogicImpl(int size) {
        this.size = size;
        Random random = new Random();
        while (this.obstacles.size() != MAX_OBSTACLES){
            this.obstacles.add(new Position(random.nextInt(size), random.nextInt(size)));
        }
    }

    @Override
    public boolean select(Position p) {
        if (this.selectionIsOver() || this.selected.contains(p)) {
            return false;
        }
        this.selected.add(p);
        return true;
    }

    @Override
    public boolean selectionIsOver() {
        return this.selected.size() == MAX_SELECTED;
    }

    @Override
    public void goDown() {
        for (int i = 0; i < MAX_SELECTED; i++) {
            var p = this.selected.remove(0);
            var p2 = new Position(p.x(), p.y() + 1);
            if (!this.selected.contains(p2) && !this.obstacles.contains(p2) && p2.y() != size) {
                this.selected.add(p2);
                return;
            }
            this.selected.add(p);
        }
        this.isOver = true;
    }

    @Override
    public boolean isSelected(Position p) {
        return this.selected.contains(p);
    }

    @Override
    public boolean isOver() {
        return this.isOver;
    }

    @Override
    public void reset() {
        this.isOver = false;
        this.selected.clear();
    }

    @Override
    public boolean isObstacle(Position p) {
        return this.obstacles.contains(p);
    }

}
