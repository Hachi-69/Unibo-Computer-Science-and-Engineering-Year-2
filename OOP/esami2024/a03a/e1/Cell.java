package a03a.e1;

import java.util.Set;

/**
 * An interface modelling a cell of a spreadsheet-like systems (like e.g. Excel).
 * This cell always gives a result, of type E, but this might be the result of a 
 * calculation based on the result of other cells it depends from.
 * A cell may be modifiable, in which case you can write a value in it, otherwise you can't.
 */
public interface Cell<E> {

    /**
     * @return the result of evaluating this cell
     */
    E getResult();

    /**
     * @return whether this cell can be (re)written
     */
    boolean isModifiable();

    /**
     * @return the list of cells this cell depends from
     */
    Set<Cell<E>> dependsFrom();

    /**
     * @param value
     * @throws UnsupportedOperationException if the cell was not modifiable
     * Rewrites the cell, putting a value in it.
     */
    void write(E value);

}
