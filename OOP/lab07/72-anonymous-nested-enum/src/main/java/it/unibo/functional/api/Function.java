package it.unibo.functional.api;

/**
 * This class models a function of a single parameter as an Object.
 *
 * @param <I> the input type
 * @param <O> the output type
 */
@SuppressWarnings("PMD.ImplicitFunctionalInterface")
public interface Function<I, O> {

    /**
     * Calls the function.
     *
     * @param input the input of the function
     * @return the result of the function evaluation on the provided input
     */
    O call(I input);

    /**
     * Returns the identity function, which returns the same object that is
     * provided as input.
     *
     * @param <T> the input (and output) type of the function
     * @return A new {@code Function} that implements the identity function with
     * the provided type
     */
    static <T> Function<T, T> identity() {
        return new Function<>() {
            @Override
            public T call(final T input) {
                return input;
            }
        };
    }

}
