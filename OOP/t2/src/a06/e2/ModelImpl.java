package a06.e2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ModelImpl implements Model {

    private int counter = 0;
    private int prev = 0;
    private final Random random = new Random();
    private final Map<Position, Integer> grid = new HashMap<>();
    private final List<Integer> remainingNumbers = new ArrayList<>();

    @Override
    public void initGame(int width) {
        this.grid.clear();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                int val = random.nextInt(1, 7);
                grid.put(new Position(i, j), val);
                remainingNumbers.add(val);
            }
        }
    }

    @Override
    public int getNumberAt(Position pos) {
        return this.grid.get(pos);
    }

    @Override
    public boolean isEqual(int num) {
        if (this.prev == 0) {
            this.prev = num;
        } else {
            if (this.prev == num) {
                this.prev = 0;
                remainingNumbers.remove(Integer.valueOf(num));
                remainingNumbers.remove(Integer.valueOf(num));
                return true;
            } else {
                this.prev = 0;
            }
        }
        return false;
    }

    @Override
    public boolean hideAfterTwo() {
        if (this.counter == 2) {
            this.counter = 0;
            return true;
        } else {
            this.counter++;
            return false;
        }
    }

    @Override
    public boolean isGameOver() {
        return remainingNumbers.stream().distinct().count() == remainingNumbers.size();
    }

}
