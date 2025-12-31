package a01a.e1;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ComposableParserFactoryImpl implements ComposableParserFactory {

    @Override
    public <X> ComposableParser<X> empty() {
        return fromList(List.of());
    }

    @Override
    public <X> ComposableParser<X> one(X x) {
        return fromList(List.of(x));
    }

    @Override
    public <X> ComposableParser<X> fromList(List<X> list) {
        return new ComposableParser<X>() {
            private final List<X> expected = list;
            private int index = 0;

            @Override
            public boolean parse(X t) {
                if (end()) {
                    return false;
                }
                if (t.equals(expected.get(index))) {
                    index++;
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public boolean end() {
                return index == expected.size();
            }

        };
    }

    @Override
    public <X> ComposableParser<X> fromAnyList(Set<List<X>> input) {
        return new ComposableParser<X>() {
            private Set<List<X>> expected = input;

            @Override
            public boolean parse(X t) {
                expected = expected.stream()
                        .filter(list -> !list.isEmpty() && list.get(0).equals(t))
                        .map(list -> list.subList(1, list.size()))
                        .collect(Collectors.toSet());
                return !expected.isEmpty();
            }

            @Override
            public boolean end() {
                return expected.stream().anyMatch(List::isEmpty);
            }

        };
    }

    @Override
    public <X> ComposableParser<X> seq(ComposableParser<X> parser, List<X> list) {
        return new ComposableParser<X>() {
            private final ComposableParser<X> p1 = parser;
            private final ComposableParser<X> p2 = fromList(list);
            private boolean switchedToSecond = false;

            @Override
            public boolean parse(X t) {
                if (switchedToSecond) {
                    return p2.parse(t);
                }

                boolean p1WasAtEnd = p1.end();

                if (p1.parse(t)) {
                    return true;
                }

                if (p1WasAtEnd) {
                    switchedToSecond = true;
                    return p2.parse(t);
                }

                return false;
            }

            @Override
            public boolean end() {
                if (switchedToSecond) {
                    return p2.end();
                } else {
                    return p1.end() && p2.end();
                }
            }
        };
    }

    @Override
    public <X> ComposableParser<X> or(ComposableParser<X> p1, ComposableParser<X> p2) {
        return new ComposableParser<X>() {
            // Teniamo traccia di quali parser sono ancora validi
            private boolean p1Alive = true;
            private boolean p2Alive = true;

            @Override
            public boolean parse(X t) {
                // Se p1 è ancora vivo, proviamo a fargli mangiare il token
                if (p1Alive) {
                    p1Alive = p1.parse(t);
                }

                // Se p2 è ancora vivo, proviamo a fargli mangiare il token
                if (p2Alive) {
                    p2Alive = p2.parse(t);
                }

                // Il risultato è positivo se almeno uno dei due è sopravvissuto
                return p1Alive || p2Alive;
            }

            @Override
            public boolean end() {
                // Abbiamo finito con successo se almeno uno dei parser ancora vivi è arrivato
                // alla fine
                return (p1Alive && p1.end()) || (p2Alive && p2.end());
            }
        };
    }

}
