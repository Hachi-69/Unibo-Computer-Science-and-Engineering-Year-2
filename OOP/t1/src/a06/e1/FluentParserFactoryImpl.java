package a06.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class FluentParserFactoryImpl implements FluentParserFactory {

    @Override
    public FluentParser<Integer> naturals() {
        return new FluentParser<Integer>() {
            private int expected = 0;
            @Override
            public FluentParser<Integer> accept(Integer value) {
                if (value.equals(expected)) {
                    expected++;
                    return this;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }

    @Override
    public FluentParser<List<Integer>> incrementalNaturalLists() {
        return new FluentParser<List<Integer>>() {
            private final List<Integer> expected = new ArrayList<>();
            private int val = 0;
            @Override
            public FluentParser<List<Integer>> accept(List<Integer> value) {
                if (value.equals(expected)) {
                    expected.add(val);
                    val++;
                    return this;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }

    @Override
    public FluentParser<Integer> repetitiveIncrementalNaturals() {
        return new FluentParser<Integer>() {
            private int expected = 0;
            private int max = 0;
            @Override
            public FluentParser<Integer> accept(Integer value) {
                if (value.equals(expected)) {
                    if (expected == max) {
                        max++;
                        expected = 0;
                    } else {
                        expected++;
                    }
                    return this;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }

    @Override
    public FluentParser<String> repetitiveIncrementalStrings(String s) {
        return new FluentParser<String>() {
            private String expected = s;
            private String max = s;
            @Override
            public FluentParser<String> accept(String value) {
                if (value.equals(expected)) {
                    if (expected.equals(max)) {
                        max += s;
                        expected = s;
                    } else {
                        expected += s;
                    }
                    return this;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }

    @Override
    public FluentParser<Pair<Integer, List<String>>> incrementalPairs(int i0, UnaryOperator<Integer> op, String s) {
        return new FluentParser<Pair<Integer, List<String>>>() {
            private int currentI = i0;
            @Override
            public FluentParser<Pair<Integer, List<String>>> accept(Pair<Integer, List<String>> value) {
                List<String> expectedList = new ArrayList<>();
                for (int i = 0; i < currentI; i++) {
                    expectedList.add(s);
                }
                Pair<Integer, List<String>> expected = new Pair<>(currentI, expectedList);
                if (value.equals(expected)) {
                    currentI = op.apply(currentI);
                    return this;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }
}
