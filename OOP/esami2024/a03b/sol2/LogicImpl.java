package a03b.sol2;

import java.util.LinkedHashMap;

public class LogicImpl implements Logic {

    private static enum Direction {
        UP, DOWN;

        public Direction other() {
            return this == UP ? DOWN : UP;
        }

        public Position move(Position p) {
            return new Position(p.x(), p.y() + (this == UP ? -1 : 1));
        }
    }

    private static final int MAX_SELECTED = 5;
    private LinkedHashMap<Position, Direction> selected = new LinkedHashMap<>();
    private final int size;
    private boolean isOver;

    public LogicImpl(int size) {
        this.size = size;
    }

    @Override
    public void select(Position p) {
        if (!this.selectionIsOver()) {
            this.selected.merge(p, Direction.UP, (d1, d2) -> d1.other());
        }
    }

    @Override
    public boolean isUp(Position p) {
        return this.selected.get(p) == Direction.UP;
    }

    @Override
    public boolean isDown(Position p) {
        return this.selected.get(p) == Direction.DOWN;
    }

    @Override
    public boolean selectionIsOver() {
        return this.selected.size() == MAX_SELECTED;
    }

    @Override
    public void move() {
        for (int i = 0; i < MAX_SELECTED; i++) {
            var p = this.selected.keySet().iterator().next();
            var dir = this.selected.remove(p);
            var p2 = dir.move(p);
            if (!this.selected.containsKey(p2) && p2.y() != size && p2.y() != -1) {
                this.selected.put(p2, dir);
                return;
            }
            this.selected.put(p, dir);
        }
        this.isOver = true;
    }

    @Override
    public boolean isOver() {
        return this.isOver;
    }

    @Override
    public void reset() {
        this.selected.clear();
        this.isOver = false;
    }

}
