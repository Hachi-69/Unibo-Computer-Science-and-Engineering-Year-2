package a01b.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicImpl implements Logic {

    private int size;
    private final Map<Position, String> grid = new HashMap<>();
    private Position prevPos;

    @Override
    public void init(int size) {
        this.size = size;
        this.prevPos = new Position(-1, 1);
        this.grid.clear();
    }

    @Override
    public boolean hit(Position p) {
        if (prevPos.x() == -1) {
            prevPos = p;
            grid.put(p, "*");
            return true;
        } else {
            if (prevPos.x() == p.x() && ((p.y() - prevPos.y()) % 2 == 0)) {
                grid.put(p, "*");
                calculateRhombus(prevPos, p);
                return true;
            }
        }
        init(size);
        return false;
    }

    @Override
    public void calculateRhombus(Position p1, Position p2) {
        int centerX = p1.x();
        int minY = Math.min(p1.y(), p2.y());
        int maxY = Math.max(p1.y(), p2.y());

        int centerY = (minY + maxY) / 2;
        int radius = (maxY - minY) / 2;

        for (int r = minY; r <= maxY; r++) {

            int distY = Math.abs(r - centerY);
            int span = radius - distY;

            for (int c = centerX - span; c <= centerX + span; c++) {

                if (c >= 0 && c < this.size && r >= 0 && r < this.size) {
                    Position pos = new Position(c, r);

                    if (!pos.equals(p1) && !pos.equals(p2)) {
                        this.grid.put(pos, "o");
                    }
                }
            }
        }
    }

    @Override
    public String getAtPos(Position p) {
        return grid.get(p);
    }

}
