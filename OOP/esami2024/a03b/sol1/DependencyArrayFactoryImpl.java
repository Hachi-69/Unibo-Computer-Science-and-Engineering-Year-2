package a03b.sol1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class DependencyArrayFactoryImpl implements DependencyArrayFactory {

    private static class DependencyArrayImpl<E> implements DependencyArray<E> {
        private final List<E> initialElements;
        private final IntFunction<Boolean> flags;

        private DependencyArrayImpl(IntFunction<Boolean> flags, List<E> initialElements) {
            this.initialElements = new LinkedList<>(initialElements);
            this.flags = flags;
        }

        @Override
        public int size() {
            return this.initialElements.size();
        }

        @Override
        public E read(int position) {
            return initialElements.get(position);
        }

        @Override
        public void write(int position, E value) {
            if (!flags.apply(position)) {
                throw new UnsupportedOperationException();
            }
            this.initialElements.set(position, value);
        }

        @Override
        public List<E> elements() {
            return IntStream.range(0, size()).mapToObj(this::read).toList();
        }
    }

    @Override
    public <E> DependencyArray<E> immutable(List<E> initial) {
        return new DependencyArrayImpl<>(pos -> false, initial);
    }

    @Override
    public <E> DependencyArray<E> mutable(List<E> initial) {
        return new DependencyArrayImpl<>(pos -> true, initial);
    }

    @Override
    public DependencyArray<Integer> withSumOfElementsAtTheEnd(List<Integer> initial) {
        return withAddedReduction(mutable(initial), 0, (x, y) -> x + y);
    }

    @Override
    public <E> DependencyArray<E> clonedWithOneRandom(DependencyArray<E> array, int pos, List<E> randomElements) {
        var random = new Random();
        return new DependencyArrayImpl<>(p -> p != pos, array.elements()) {
            @Override
            public E read(int position) {
                if (position == pos) {
                    return randomElements.get(random.nextInt(size()));
                }
                return super.read(position);
            }
        };
    }

    private <E> DependencyArray<E> withAddedReduction(DependencyArray<E> array, E base, BinaryOperator<E> op) {
        return new DependencyArrayImpl<>(p -> p != array.size(), array.elements()) {

            @Override
            public int size() {
                return array.size() + 1;
            }

            @Override
            public E read(int position) {
                if (position == array.size()) {
                    return IntStream.range(0, size()-1).mapToObj(this::read).reduce(base, op);
                }
                return super.read(position);
            }

        };

    }

    @Override
    public DependencyArray<Integer> clonedWithAddedProduct(DependencyArray<Integer> array) {
        return withAddedReduction(array, 1, (x, y) -> x * y);
    }

}
