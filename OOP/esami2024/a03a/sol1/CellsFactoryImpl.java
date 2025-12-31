package a03a.sol1;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;

public class CellsFactoryImpl implements CellsFactory {

    private static class ValueCell<E> implements Cell<E>{
        private E value;


        public ValueCell(E value) {
            this.value = value;
        }

        @Override
        public E getResult() {
            return this.value;
        }

        @Override
        public boolean isModifiable() {
            return true;
        }

        @Override
        public Set<Cell<E>> dependsFrom() {
            return Collections.emptySet();
        }

        @Override
        public void write(E value) {
            this.value = value;
        }
    }
    
    @Override
    public <E> Cell<E> mutableValueCell(E e) {
        return new ValueCell<>(e);
    }

    @Override
    public Cell<Integer> sumOfTwo(Cell<Integer> c1, Cell<Integer> c2) {
        return fromReduction(List.of(c1, c2), Integer::sum);
    }

    @Override
    public Cell<String> concatOfThree(Cell<String> c1, Cell<String> c2, Cell<String> c3) {
        return fromReduction(List.of(c1, c2, c3), String::concat);
    }

    @Override
    public <E> Cell<E> fromReduction(List<Cell<E>> cellsSet, BinaryOperator<E> op) {
        return new Cell<>(){

            @Override
            public E getResult() {
                return cellsSet.stream().map(Cell::getResult).reduce(op).get();
            }

            @Override
            public boolean isModifiable() {
                return false;
            }

            @Override
            public Set<Cell<E>> dependsFrom() {
                return new HashSet<>(cellsSet);
            }

            @Override
            public void write(E value) {
                throw new UnsupportedOperationException("Unimplemented method 'write'");
            }
        };
    }

    @Override
    public List<Cell<Integer>> cellsWithSumOnLast(List<Integer> values) {
        return addOperationOnAll(values.stream().map(v -> mutableValueCell(v)).toList(), Integer::sum);
    }

    private <E> List<Cell<E>> addOperationOnAll(List<Cell<E>> cells, BinaryOperator<E> op) {
        var outCells = new LinkedList<>(cells);
        outCells.add(fromReduction(cells, op));
        return outCells;
    }

    @Override
    public List<Cell<String>> addConcatenationOnAll(List<Cell<String>> cellList){
        var outCells = new LinkedList<>(cellList);
        outCells.add(fromReduction(cellList, String::concat));
        return outCells;
    }
}
