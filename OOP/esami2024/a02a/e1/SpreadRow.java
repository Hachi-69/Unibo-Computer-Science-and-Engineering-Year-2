package a02a.e1;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * A SpreadRow is like a Spreadsheet (like in Excel), but it is just one row.
 * Cells could contain numbers, formulas, or be empty.
 * The number of available cells is fixed by the constructor, numbered 0, 1, 2, .., size-1
 * You can add numbers or formulas where you want. For simplicity assume a formula 
 * (like summing two cells) uses cells on its left-side.
 * At any point you can compute content of cells, which is done left to right.
 * For example, you can have:
 * - nothing on cell 0
 * - number 10 on cell 1
 * - number 20 on cell 2
 * - nothing on cell 3
 * - sum of cells 1 and 2 on cell 4
 * In this case, computing results give:
 * [Optional.empty(), Optional.of(10), Optional.of(20), Optional.empty(), Optional.of(30)]
 */
public interface SpreadRow {

    /**
     * @return the fixed number of cells in this SpreadRow
     */
    int size();

    /**
     * @param index
     * @return whether at position index there's a formula
     */
    boolean isFormula(int index);

    /**
     * @param index
     * @return whether at position index there's a number
     */
    boolean isNumber(int index);

    /**
     * @param index
     * @return whether cell at position index is empty
     */
    boolean isEmpty(int index);

    /**
     * @return results of computing values of all cells, from left to right
     */
    List<Optional<Integer>> computeValues();

    /**
     * @param index
     * @param number
     * puts number in the cell at position index
     */
    void putNumber(int index, int number);
    
    /**
     * @param resultIndex
     * @param index1
     * @param index2
     * in the cell at position resultIndex puts formula "#index1 + #index2"
     */
    void putSumOfTwoFormula(int resultIndex, int index1, int index2);

    /**
     * @param index
     * @param indexes
     * in the cell at position resultIndex puts formula "multiply values at all indexes"
     * e.g. putMultiplyElementsFormula(4, Set.of(1,2,3)), puts formula 
     * "#index1 * #index2 * #index3" in the cell at index 4
     */
    void putMultiplyElementsFormula(int resultIndex, Set<Integer> indexes);

}
