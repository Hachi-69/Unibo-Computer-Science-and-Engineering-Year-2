package a05.sol1;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A factory of skipping of various sorts. 
 * The first one is already implemented, for the sake of example:
 * you may implement others using this template.
 */
public interface SkippingFactory {

    /**
     * @param <E>
     * @param e
     * @return a skipper where the output mimicks the input except that elements equals to @param e are skipped
     */
    default <E> Skipping<E> skipElement(E e){
        // note that Skipping is a functional interface, hence it can be implemented
        // by a lambda sup -> ...
        return sup -> new Supplier<>(){

            @Override
            public E get() {
                E out;
                do { 
                    out = sup.get();
                    // if the input sup produces an element equals to e, I skip it!
                } while (out.equals(e));
                return out;
            }};
    }

    /**
     * @param <E>
     * @param predicate
     * @return a skipper that skips elements passing @param predicate
     */
    <E> Skipping<E> skipByPredicate(Predicate<E> predicate);

    /**
     * @param <E>
     * @return a skipper that skips the elements necessary so that the output does not produce two (or more) consecutive duplicates
     */
    <E> Skipping<E> skipConsecutiveDuplicates();

    /**
     * @param <E>
     * @param skip
     * @param retain
     * @return a skipper that skips @param skip elements, then retains (i.e. produces) @param retain elements, than again skips @param skip elements, and so on.
     */
    <E> Skipping<E> skipAndRetain(int skip, int retain);

    /**
     * @param sum
     * @return a skipper of integers that keep skipping elements until their sum reaches @param sum, than starts again summing
     */
    Skipping<Integer> skipUntilSumReaches(int sum);
}
