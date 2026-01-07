package a01c.e1;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class SimpleIteratorFactoryImpl implements SimpleIteratorFactory {

    @Override
    public SimpleIterator<Integer> naturals() {
        var iterator = Stream.iterate(0, i -> i + 1).iterator();
        return iterator::next;
    }

    @Override
    public <X> SimpleIterator<X> circularFromList(List<X> list) {
        var iterator = Stream.generate(() -> list)
                .flatMap(List::stream)
                .iterator();
        return iterator::next;
    }

    @Override
    public <X> SimpleIterator<X> cut(int size, SimpleIterator<X> simpleIterator) {
        var iterator = Stream.generate(simpleIterator::next)
                .limit(size)
                .iterator();
        return iterator::next;
    }

    @Override
    public <X> SimpleIterator<Pair<X, X>> window2(SimpleIterator<X> simpleIterator) {
        SimpleIterator<List<X>> slidingWindow = window(2, simpleIterator);

        return () -> {
            List<X> list = slidingWindow.next();
            return new Pair<>(list.get(0), list.get(1));
        };
    }

    @Override
    public SimpleIterator<Integer> sumPairs(SimpleIterator<Integer> simpleIterator) {
        SimpleIterator<Pair<Integer, Integer>> pairIterator = window2(simpleIterator);

        return () -> {
            Pair<Integer, Integer> pair = pairIterator.next();
            return pair.get1() + pair.get2();
        };
    }

    @Override
    public <X> SimpleIterator<List<X>> window(int windowSize, SimpleIterator<X> simpleIterator) {
        return new SimpleIterator<List<X>>() {
            private final List<X> currentWindow = new LinkedList<>();
            private boolean initialized = false;

            @Override
            public List<X> next() {
                if (!initialized) {
                    for (int i = 0; i < windowSize; i++) {
                        currentWindow.add(simpleIterator.next());
                    }
                    initialized = true;
                } else {
                    currentWindow.remove(0);
                    currentWindow.add(simpleIterator.next());
                }
                return new LinkedList<>(currentWindow);
            }
        };

    }

}
