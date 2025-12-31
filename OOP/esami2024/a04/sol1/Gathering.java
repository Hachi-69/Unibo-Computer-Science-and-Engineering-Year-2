package a04.sol1;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Note that A java.util.function.Supplier is an object with simply a get method, used to extract a value:
 * it is hence similar to an infinite iterator.
 * A Gathering is essentially a transformer from a supplier to a supplier: generally, it consumes from the input supplier
 * only what strictly needed to produce something for the output supplier, and does not keep track of values from the
 * past that are no longer needed.
 */
@FunctionalInterface
public interface Gathering<I, O> {

    /**
     * @param supplier
     * @return a new supplier, obtaining gathering some elements from the input to compute the next value
     */
    Supplier<O> produce(Supplier<I> supplier);
}
