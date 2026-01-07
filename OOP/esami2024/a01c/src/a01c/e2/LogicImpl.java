package a01c.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicImpl implements Logic {

    private final Map<Position, Integer> grid = new HashMap<>();
    private int size;
    private int corner;
    private Position topLeft;
    private Position topRight;
    private Position botRight;
    private Position botLeft;
    private boolean gameOver;

    @Override
    public void init(int size) {
        this.size = size;
        this.corner = 1;
        topLeft = new Position(0, 0);
        topRight = new Position(0, 0);
        botRight = new Position(0, 0);
        botLeft = new Position(0, 0);
        gameOver = false;
        grid.clear();
    }

    @Override
    public int getAtPos(Position p) {
        return grid.getOrDefault(p, 0);
    }

    @Override
    public void hit(Position p) {
        if (gameOver) {
            init(size);
            gameOver = false;
        }

        if (!checkHitOrder(p)) {
            init(size);
            return;
        }

        switch (corner) {
            case 1 -> topLeft = p;
            case 2 -> topRight = p;
            case 3 -> botRight = p;
            case 4 -> botLeft = p;
        }

        grid.put(p, corner);

        if (corner == 4) {
            calculateRectangle();
            gameOver = true;
        } else {
            corner++;
        }
    }

    @Override
    public void calculateRectangle() {
        int cicles = (botLeft.y() - topLeft.y()) - 1;

        for (int j = 1; j <= cicles; j++) {
            for (int i = topLeft.x() + 1; i < topRight.x(); i++) {
                grid.put(new Position(i, topLeft.y() + j), -1);
            }
        }
    }

    @Override
    public boolean checkHitOrder(Position p) {
        switch (corner) {
            case 1 -> {
                return true;
            }
            case 2 -> {
                return topLeft.x() < p.x() && topLeft.y() == p.y();
            }

            case 3 -> {
                return topRight.y() < p.y() && topRight.x() == p.x();
            }

            case 4 -> {
                return p.x() == topLeft.x() && p.y() == botRight.y();
            }
            case 5 -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }
}
