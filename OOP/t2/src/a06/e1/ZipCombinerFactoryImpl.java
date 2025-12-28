package a06.e1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ZipCombinerFactoryImpl implements ZipCombinerFactory {

    @Override
    public <X, Y> ZipCombiner<X, Y, Pair<X, Y>> classical() {
        return (l1, l2) -> IntStream.range(0, Math.min(l1.size(), l2.size()))
                .mapToObj(i -> new Pair<>(l1.get(i), l2.get(i)))
                .collect(Collectors.toList());
    }

    @Override
    public <X, Y, Z> ZipCombiner<X, Y, Z> mapFilter(Predicate<X> predicate, Function<Y, Z> mapper) {
        return (l1, l2) -> IntStream.range(0, Math.min(l1.size(), l2.size()))
                .filter(i -> predicate.test(l1.get(i)))
                .mapToObj(i -> mapper.apply(l2.get(i)))
                .collect(Collectors.toList());
    }

    @Override
    public <Y> ZipCombiner<Integer, Y, List<Y>> taker() {
        return (l1, l2) -> {
            Iterator<Y> l2Iterator = l2.iterator();
            List<List<Y>> result = new ArrayList<>();

            for (Integer count : l1) {
                List<Y> tmp = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    if (l2Iterator.hasNext()) {
                        tmp.add(l2Iterator.next());
                    } else {
                        break;
                    }
                }

                if (tmp.size() == count) {
                    result.add(tmp);
                } else {
                    break;
                }
            }

            return result;
        };
    }

    @Override
    public <X> ZipCombiner<X, Integer, Pair<X, Integer>> countUntilZero() {
        return (l1, l2) -> {
            Iterator<Integer> l2Iterator = l2.iterator();
            List<Pair<X, Integer>> result = new ArrayList<>();

            for (X elem : l1) {
                if (!l2Iterator.hasNext()) {
                    break;
                }

                int counter = 0;
                while (l2Iterator.hasNext()) {
                    if (l2Iterator.next() != 0) {
                        counter++;
                    } else {
                        break;
                    }
                }
                result.add(new Pair<>(elem, counter));
            }
            return result;
        };
    }
}
