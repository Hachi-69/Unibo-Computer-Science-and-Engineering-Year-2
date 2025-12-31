package a03b.e1;

import java.util.List;

/**
 * A factory of dependency arrays of various sort.
 */
public interface DependencyArrayFactory {

    /**
     * @param <E>
     * @param initial
     * @return a dependency array with all immutable values, taken from @param initial
     */
    <E> DependencyArray<E> immutable(List<E> initial);

    /**
     * @param <E>
     * @param initial
     * @return a dependency array with all mutable values, taken from @param initial
     */
    <E> DependencyArray<E> mutable(List<E> initial);

    /**
     * @param <E>
     * @param initial, a list of N elements
     * @return a dependency array of N+1 elements, where the former N are mutable and taken from @param initial,
     * while the last (which is immutable) always reports the sum of the results of those N elements
     */
    DependencyArray<Integer> withSumOfElementsAtTheEnd(List<Integer> initial);

    /**
     * @param <E>
     * @param array
     * @param pos
     * @param randomElements
     * @return a new mutable array with values copied from current elements of @param array, but changing element at @pos:
     * there, each read gives a random element taken from the list @param randomElements
     */
    <E> DependencyArray<E> clonedWithOneRandom(DependencyArray<E> array, int pos, List<E> randomElements);

    /**
     * @param array
     * @return a new mutable array with values copied from current elements of @param array, but adding a new element at
     * the end which is the product of all the previous
     */
    DependencyArray<Integer> clonedWithAddedProduct(DependencyArray<Integer> array);
}
