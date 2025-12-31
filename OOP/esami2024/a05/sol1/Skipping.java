package a05.sol1;

import java.util.function.Supplier;

/**
 * Note that a java.util.function.Supplier is an object with simply a get method, used to extract a value:
 * it is hence similar to an infinite iterator.
 * A Skipping is essentially a transformer from a supplier to a supplier: generally, it produces a new supplier
 * identical to the input one, which just skips some elements, according to an internal policy.
 */
@FunctionalInterface
public interface Skipping<E> {

    /**
     * @param supplier
     * @return a new supplier, obtaining skipping some of the elements from the input to compute the next value
     */
    Supplier<E> produce(Supplier<E> supplier);
}
