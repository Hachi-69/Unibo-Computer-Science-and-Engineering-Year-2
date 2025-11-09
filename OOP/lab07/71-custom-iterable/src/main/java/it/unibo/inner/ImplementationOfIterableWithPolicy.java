package it.unibo.inner;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class ImplementationOfIterableWithPolicy<T> implements IterableWithPolicy<T> {

    private final T[] array;

    public ImplementationOfIterableWithPolicy(final T[] a) {
        this(a, t -> true ); // TODO controllare perchè funziona
    }

    public ImplementationOfIterableWithPolicy(final T[] a, Predicate<T> p) {
        this.array = a;
    }

    @Override
    public void setIterationPolicy(Predicate filter) {
        // TODO Auto-generated method stub

    }

    @Override
    public Iterator<T> iterator() {
        return new UniboIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ImplementationOfIterableWithPolicy{");
        sb.append("array=").append(java.util.Arrays.toString(array));
        sb.append('}');
        return sb.toString();
    }

    class UniboIterator implements Iterator<T> {

        private int c = 0;

        public boolean hasNext() {
            return c < ImplementationOfIterableWithPolicy.this.array.length;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return ImplementationOfIterableWithPolicy.this.array[c++];
        }

    }

}
