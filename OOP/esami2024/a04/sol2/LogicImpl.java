package a04.sol2;

import java.util.List;

public class LogicImpl implements Logic {

    private int size;
    private List<Position> points;
    private List<Position> rotations;
    private int point = 0;
    private int rotation = 0;

    public LogicImpl(int size) {
        this.size = size;
        points = List.of(
            new Position(1, 1), 
            new Position((size - 1) / 2, (size - 1) / 2),
            new Position(size - 2, size - 2)
        );
        rotations = List.of(
            new Position(-1, -1),
            new Position(-1, 0),
            new Position(-1, 1),
            new Position(0, 1),
            new Position(1, 1),
            new Position(1, 0),
            new Position(1, -1),
            new Position(0, -1)
        );
    }

    private boolean near(Position p1, Position p2){
        return Math.abs(p1.x()-p2.x()) <= 1 && Math.abs(p1.y()-p2.y()) <= 1 && !p1.equals(p2);
    }

    @Override
    public boolean inArea(Position pos) {
        return points.stream().anyMatch(p -> near(pos, p));
    }

    @Override
    public void jump() {
        this.point = (this.point + 1) % this.points.size();
        this.rotation = 0;
    }

    @Override
    public void move() {
        this.rotation = (this.rotation + 1) % this.rotations.size();
    }

    @Override
    public Position getPosition() {
        System.out.println(this.point+" "+this.rotation);
        return sum(this.rotations.get(this.rotation), this.points.get(this.point));
    }

    private Position sum(Position p1, Position p2) {
        return new Position(p1.x() + p2.x(), p1.y() + p2.y());
    }

}
