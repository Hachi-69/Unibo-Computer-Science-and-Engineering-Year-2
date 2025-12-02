package it.unibo.oop.workers02;

/**
 * The operation of summing all values in a matrix.
 */
@FunctionalInterface
public interface SumMatrix {

    /**
     * Sums up all elements in the given matrix.
     *
     * @param matrix
     *            an arbitrary-sized matrix
     * @return the sum of its elements
     */
    double sum(double[][] matrix);
}
