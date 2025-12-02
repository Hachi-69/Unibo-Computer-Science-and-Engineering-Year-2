package it.unibo.oop.workers01;

import java.util.List;

/**
 * The sum of all elements in a list of integers.
 */
@FunctionalInterface
public interface SumList {

    /**
     * Sums up all elements in the given list.
     *
     * @param list
     *            an arbitrary-sized list of integers
     * @return the sum of its elements
     */
    long sum(List<Integer> list);

}
