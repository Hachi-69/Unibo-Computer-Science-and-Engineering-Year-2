package a06.sol1;

import java.util.Random;

public class LogicImpl implements Logic {

    private int size;
    private Position corner;
    private Position current;

    public LogicImpl(int size) {
        this.size = size;
        var random = new Random();
        this.corner = new Position(random.nextInt(size), random.nextInt(size));
        this.current = new Position(this.corner.x(), 0);
    }

    @Override
    public boolean inArea(Position pos) {
        return pos.x() == corner.x() || (pos.y() == corner.y() && pos.x() >= corner.x());
    }

    @Override
    public void jump() {
        if (current.x() == corner.x()) {
            current = current.y() == size - 1 ? new Position(corner.x() + 1, corner.y()) : new Position(current.x(), current.y() + 1);
        } else {
            current = new Position(current.x() + 1, current.y());
        }
        if (current.x() >= size){
            this.current = new Position(this.corner.x(), 0);
        }
    }

    @Override
    public void move() {
        this.jump();
        this.jump();
    }

    @Override
    public Position getPosition() {
        return this.current;
    }

}
