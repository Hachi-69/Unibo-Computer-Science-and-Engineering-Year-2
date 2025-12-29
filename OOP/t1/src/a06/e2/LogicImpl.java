package a06.e2;

import java.util.*;

public class LogicImpl implements Logic {

    private final Map<Pair<Integer, Integer>, Integer> grid = new HashMap<>();
    private final Random rnd = new Random();
    private int size;

    @Override
    public void gameInit(int size) {
        this.grid.clear();
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int val = this.rnd.nextInt(1, 3);
                this.grid.put(new Pair<>(i, j), val);
            }
        }
    }

    @Override
    public int getNumberAt(Pair<Integer, Integer> pos) {
        return this.grid.get(pos);
    }

    @Override
    public boolean collapse() {
        boolean anyCollapse = false;
        for (int j = 0; j < this.size; j++) {
            boolean collapsed = false;
            for (int i = this.size - 1; i > 0; i--) {
                if (collapsed) {
                    break;
                }
                final Pair<Integer, Integer> currentPos = new Pair<>(i, j);
                final Pair<Integer, Integer> abovePos = new Pair<>(i - 1, j);

                final int currentVal = this.grid.get(currentPos);
                final int aboveVal = this.grid.get(abovePos);

                if (currentVal != 0 && currentVal == aboveVal) {
                    this.grid.put(currentPos, currentVal + aboveVal);
                    this.grid.put(abovePos, 0);
                    collapsed = true;
                    anyCollapse = true;
                }
            }

            List<Integer> columnValues = new ArrayList<>();
            for (int i = 0; i < this.size; i++) {
                int val = this.grid.get(new Pair<>(i, j));
                if (val != 0) {
                    columnValues.add(val);
                }
            }

            int currentIdx = this.size - 1;
            for (int k = columnValues.size() - 1; k >= 0; k--) {
                this.grid.put(new Pair<>(currentIdx, j), columnValues.get(k));
                currentIdx--;
            }

            while (currentIdx >= 0) {
                this.grid.put(new Pair<>(currentIdx, j), 0);
                currentIdx--;
            }
        }
        return anyCollapse;
    }

}
