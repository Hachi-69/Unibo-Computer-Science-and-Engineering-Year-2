package a02a.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogicImpl implements Logic {

    private final Map<Position, String> grid = new HashMap<>();
    private final int size;
    private final Random rnd;
    private boolean moveThree;
    private Position pawnPosition;
    private int obstaclesRemaining;
    private boolean gameOver;

    public LogicImpl(int size) {
        this.grid.clear();
        this.size = size;
        this.rnd = new Random();
        this.moveThree = true;
        this.pawnPosition = new Position(0, 0);
        this.obstaclesRemaining = 0;
        this.gameOver = false;
        border();
        randomObstacle();
        this.grid.put(pawnPosition, "*");
    }

    @Override
    public String getAtPos(Position p) {
        return grid.getOrDefault(p, "");
    }

    @Override
    public void advance() {
        grid.put(pawnPosition, "-");
        int steps = this.moveThree ? 3 : 4;
        this.moveThree = !this.moveThree;

        for (int i = 0; i < steps; i++) {
            pawnPosition = nextStep(pawnPosition);
        }

        if ("o".equals(grid.get(pawnPosition))) {
            grid.put(pawnPosition, "-");
            obstaclesRemaining--;

            if (obstaclesRemaining == 0) {
                grid.put(pawnPosition, "*");
                this.gameOver = true;
            } else {
                pawnPosition = new Position(0, 0);
                grid.put(pawnPosition, "*");
            }
        } else {
            grid.put(pawnPosition, "*");
        }

        grid.put(pawnPosition, "*");
    }

    private Position nextStep(Position p) {
        int x = p.x();
        int y = p.y();

        if (y == 0 && x < size - 1) {
            return new Position(x + 1, y);
        }
        if (x == size - 1 && y < size - 1) {
            return new Position(x, y + 1);
        }
        if (y == size - 1 && x > 0) {
            return new Position(x - 1, y);
        }
        if (x == 0 && y > 0) {
            return new Position(x, y - 1);
        }

        return p;
    }

    private void randomObstacle() {
        Position p;
        int obstacles = 0;
        while (obstacles < 3) {
            p = new Position(rnd.nextInt(size), rnd.nextInt(size));
            if ("-".equals(getAtPos(p)) && !"o".equals(getAtPos(p)) && !p.equals(this.pawnPosition)) {
                grid.put(p, "o");
                obstacles++;
            }
        }
        this.obstaclesRemaining = obstacles;
    }

    private void border() {
        for (int j = 0; j < size; j++) {
            grid.put(new Position(j, 0), "-");
        }

        for (int j = 0; j < size; j++) {
            grid.put(new Position(0, j), "-");
        }

        for (int j = 0; j < size; j++) {
            grid.put(new Position(j, size - 1), "-");
        }

        for (int j = 0; j < size; j++) {
            grid.put(new Position(size - 1, j), "-");
        }
    }

    @Override
    public boolean isOver() {
        return this.gameOver;
    }
}
