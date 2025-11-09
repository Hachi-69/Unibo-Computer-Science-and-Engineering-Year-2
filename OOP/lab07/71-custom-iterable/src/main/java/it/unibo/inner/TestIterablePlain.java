package it.unibo.inner;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.ImplementationOfIterableWithPolicy;
import java.util.List;

import static it.unibo.inner.test.Assertions.assertContentEqualsInOrder;

public class TestIterablePlain {

    private TestIterablePlain() {
    }

    private static <T> IterableWithPolicy<T> getIterableWithPolicy(T[] elements) {
        return new ImplementationOfIterableWithPolicy<T>(elements); // TODO: return the implementation of IterableWithPolicy
    }

    public static void main(final String[] args) {
        final String[] test1 = {"pippo", "pluto", "paperino"};
        final IterableWithPolicy<String> evenIterable = getIterableWithPolicy(test1);
        assertContentEqualsInOrder(List.of("pippo", "pluto", "paperino"), evenIterable);
        final String[] test2 = {};
        final IterableWithPolicy<String> emptyIterable = getIterableWithPolicy(test2);
        assertContentEqualsInOrder(List.of(), emptyIterable);
        String[] test3 = {"foo"};
        final IterableWithPolicy<String> oneIterable = getIterableWithPolicy(test3);
        assertContentEqualsInOrder(List.of("foo"), oneIterable);
    }
}
