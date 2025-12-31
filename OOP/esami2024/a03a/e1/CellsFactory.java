package a03a.e1;

import java.util.List;
import java.util.function.BinaryOperator;

/**
 * A factory of cells, and of lists of mutually-dependent cells.
 */
public interface CellsFactory {

    /**
     * @param <E>
     * @param initial
     * @return a Cell holding a modifiable value
     */
    <E> Cell<E> mutableValueCell(E initial);

    /**
     * @param c1
     * @param c2
     * @return an unmodifiable Cell always holding the sum of results of c1,c2
     */
    Cell<Integer> sumOfTwo(Cell<Integer> c1, Cell<Integer> c2);

    /**
     * @param c1
     * @param c2
     * @param c3
     * @return an unmodifiable Cell always holding the concatenation of string results of c1,c2,c3
     */
    Cell<String> concatOfThree(Cell<String> c1, Cell<String> c2, Cell<String> c3);

    /**
     * @param values, a list of size N
     * @return a list of (N+1) cells: 
     * - the former N are mutable value-cells with initial values taken from @param values
     * - the last is the sum of those N
     */
    List<Cell<Integer>> cellsWithSumOnLast(List<Integer> values);

    /**
     * @param cellList
     * @return a new list of cells, adding to @param cellList the result of applying fromReduction method
     */
    List<Cell<String>> addConcatenationOnAll(List<Cell<String>> cellList);

    /**
     * @param <E>
     * @param cellList, assumed to be non-empty
     * @param op, an operation like (x, y) -> x+y, or (x, y) -> x*y
     * @return an unmodifiable Cell holding the results of applying @param op to all results in @param cellList
     */
    <E> Cell<E> fromReduction(List<Cell<E>> cellList, BinaryOperator<E> op);
   
}
