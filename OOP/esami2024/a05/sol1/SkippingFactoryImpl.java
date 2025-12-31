package a05.sol1;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SkippingFactoryImpl implements SkippingFactory {

    private <E,S> Skipping<E> generic(S initial, BiPredicate<S, E> canPass, BiFunction<S, E, S> update){
        return sup -> new Supplier<>(){
            S state = initial;

            @Override
            public E get() {
                E out;
                boolean pass;
                do { 
                    out = sup.get();
                    pass = canPass.test(state, out);
                    state = update.apply(state, out);
                } while (!pass);
                return out;
            }};
    }

    @Override public <E> Skipping<E> skipByPredicate(Predicate<E> predicate) {
        return generic(
            false, 
            (s, e) -> !predicate.test(e), 
            (s, e) -> s);
    }


    @Override
    public <E> Skipping<E> skipConsecutiveDuplicates() {
        return generic(
            Optional.<E>empty(), 
            (s, e) -> !s.equals(Optional.of(e)), 
            (s, e) -> Optional.of(e));        
     }

    @Override
    public <E> Skipping<E> skipAndRetain(int skip, int retain) {
        record State(boolean skipping, int remaining){};
        return generic(
            new State(true, skip), 
            (s, e) -> !s.skipping(), 
            (s, e) -> s.remaining() == 1 
                ? new State(!s.skipping(), s.skipping() ? retain : skip)
                : new State(s.skipping(), s.remaining() - 1));
    }

    @Override
    public Skipping<Integer> skipUntilSumReaches(int sum) {
        return generic(
            0, 
            (s, e) -> s + e >= sum, 
            (s, e) -> s + e < sum ? s + e : 0);
    }

}
