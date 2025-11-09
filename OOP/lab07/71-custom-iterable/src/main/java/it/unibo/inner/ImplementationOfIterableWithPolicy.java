package it.unibo.inner;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplementationOfIterableWithPolicy<T> implements IterableWithPolicy<T> {

    private final List<T> elements;
    private Predicate<T> filter;

    public ImplementationOfIterableWithPolicy(final T[] elements) {
        this(elements, new Predicate<T>() {
            @Override
            public boolean test(T elem) {
                return true;
            }
        });
    }

    public ImplementationOfIterableWithPolicy(final T[] elements, final Predicate<T> filter) {
        this.elements = List.of(elements);
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new filterIterator();
    }

    class filterIterator implements Iterator<T> {

        private int c = 0;

        public boolean hasNext() {
            while (c < elements.size()) {
                final T elem = elements.get(c);
                if (filter.test(elem)) {
                    return true;
                }
                c++;
            }
            return false;
        }

        public T next() {
            if (hasNext()) {
                return elements.get(c++);
            }
            throw new NoSuchElementException("End of list reached");
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ImplementationOfIterableWithPolicy{");
        sb.append("elements=").append(elements);
        sb.append(", filter=").append(filter);
        sb.append('}');
        return sb.toString();
    }

}
