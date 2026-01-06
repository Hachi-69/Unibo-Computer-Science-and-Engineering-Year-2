package a01b.e1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class IteratorsCombinersImpl implements IteratorsCombiners {

    // 1. Metodo privato helper: gestisce l'avanzamento parallelo (DRY)
    private <A, B, C> Iterator<C> combine(Iterator<A> i1, Iterator<B> i2, BiFunction<A, B, C> combiner) {
        return new Iterator<C>() {
            @Override
            public boolean hasNext() {
                return i1.hasNext() && i2.hasNext();
            }

            @Override
            public C next() {
                if (hasNext()) {
                    return combiner.apply(i1.next(), i2.next());
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public <X> Iterator<X> alternate(Iterator<X> i1, Iterator<X> i2) {
        return new Iterator<X>() {
            private boolean alternate;

            @Override
            public boolean hasNext() {
                if (i1.hasNext()) {
                    return true;
                } else {
                    return i2.hasNext();
                }
            }

            @Override
            public X next() {
                if (!alternate && i1.hasNext()) {
                    alternate = true;
                    return i1.next();
                }
                if (alternate && i2.hasNext()) {
                    alternate = false;
                    return i2.next();
                }

                if (i1.hasNext()) {
                    return i1.next();
                }
                if (i2.hasNext()) {
                    return i2.next();
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public <X> Iterator<X> seq(Iterator<X> i1, Iterator<X> i2) {
        return new Iterator<X>() {

            @Override
            public boolean hasNext() {
                if (i1.hasNext()) {
                    return true;
                } else {
                    return i2.hasNext();
                }
            }

            @Override
            public X next() {
                if (i1.hasNext()) {
                    return i1.next();
                } else {
                    return i2.next();
                }
            }
        };
    }

    @Override
    public <X> Iterator<X> map2(Iterator<X> i1, Iterator<X> i2, BinaryOperator<X> operator) {
        return combine(i1, i2, operator);
    }

    @Override
    public <X, Y, Z> Iterator<Pair<X, Y>> zip(Iterator<X> i1, Iterator<Y> i2) {
        return combine(i1, i2, Pair::new);
    }

}
