package a04.sol1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class GatheringFactoryImpl implements GatheringFactory {

    private <I, O> Gathering<I, O> generic(
        Predicate<List<I>> canRelease,
        Function<List<I>,O> output,
        Consumer<List<I>> clean
    ) {
        return i -> new Supplier<>() {
            List<I> state = new ArrayList<>();

            @Override
            public O get() {
                do {
                   state.add(i.get());
                } while (!canRelease.test(state));
                var out = output.apply(state);
                clean.accept(state);
                return out;
            }

        };
    }
    
    @Override
    public <E> Gathering<E, List<E>> scanToList() {
        return generic(s -> true, s -> new ArrayList<>(s), s -> {});
    }

    @Override
    public <E> Gathering<E, E> scanAndReduce(BinaryOperator<E> op) {
        return generic(s -> true, s -> s.stream().reduce(op).get(), s -> {});
    }

    @Override
    public <E> Gathering<E, List<E>> slide(int size) {
        return generic(s -> s.size() == size, s -> new ArrayList<>(s), s -> {s.remove(0);});
    }

    @Override
    public <E> Gathering<E, Pair<E, E>> pairs() {
        return generic(s -> s.size() == 2, s -> new Pair<>(s.get(0), s.get(1)), s -> {s.remove(0);});
    }

    @Override
    public Gathering<Integer, Integer> sumLastThree() {
        return generic(s -> s.size() == 3, s -> s.get(0) + s.get(1)+ s.get(2), s -> {s.remove(0);});
    }

}
