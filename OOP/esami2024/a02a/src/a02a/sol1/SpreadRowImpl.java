package a02a.sol1;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;

public class SpreadRowImpl implements SpreadRow {

    private static record Cell(BinaryOperator<Integer> op, int base, Set<Integer> cells){
        public int result(List<Optional<Integer>> results){
            return cells.stream().map(results::get).map(Optional::get).reduce(base, op);
        }
    }
    private List<Cell> row = new LinkedList<>();

    public SpreadRowImpl(int size) {
        for (int i = 0; i < size; i++){
            this.row.add(null);
        }
    }

    @Override
    public int size() {
        return this.row.size();
    }

    @Override
    public boolean isFormula(int index) {
        return !this.isEmpty(index) && !this.row.get(index).cells().isEmpty();
    }

    @Override
    public boolean isNumber(int index) {
        return !this.isEmpty(index) && !this.isFormula(index);
    }

    @Override
    public boolean isEmpty(int index) {
        return this.row.get(index) == null;
    }

    @Override
    public List<Optional<Integer>> computeValues() {
        final List<Optional<Integer>> results = new LinkedList<>(); // used to store elements during stream computation
        return this.row
                .stream()
                .map(Optional::ofNullable)
                .map(opt -> opt.map(c -> c.result(results)))
                .peek(results::add)
                .toList();
    }

    @Override
    public void putNumber(int index, int number) {
        this.row.set(index, new Cell((x, y) -> 0, number, Collections.emptySet()));
    }

    @Override
    public void putSumOfTwoFormula(int resultIndex, int index1, int index2) {
        this.row.set(resultIndex, new Cell((x, y) -> x+y, 0, Set.of(index1, index2)));
    }

    @Override
    public void putMultiplyElementsFormula(int index, Set<Integer> indexes) {
        this.row.set(index, new Cell((x, y) -> x*y, 1, Set.copyOf(indexes)));
    }
}
