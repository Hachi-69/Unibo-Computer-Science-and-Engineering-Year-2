package a06.sol2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LogicImpl implements Logic {

    private final Map<Position, Integer> cells = new HashMap<>();
    private final List<Integer> base;
    private final int size;

    public LogicImpl(int size) {
        this.size = size;
        this.base = Stream.iterate(0, i -> i < size, i -> i + 1).toList();
    }

    @Override
    public void hit(Position pos) {
        this.cells.merge(pos, 1, (a, b) -> a + b > size ? null : a + b);
    }

    @Override
    public boolean hasValue(Position pos) {
        return this.cells.containsKey(pos);
    }

    @Override
    public int getValue(Position pos) {
        return this.cells.get(pos);
    }

    private Optional<Integer> getOpt(Position pos){
        return Optional.of(pos).filter(this::hasValue).map(this::getValue);
    }

    private boolean isOk(Set<Position> set){
        boolean b = set.stream().map(Position::x).sorted().toList().equals(base) && set.stream().map(Position::y).sorted().toList().equals(base);
        System.out.println(b+ " "+ set);
        return b;
    }

    @Override
    public boolean isOver() {
        return IntStream.rangeClosed(1, size).allMatch(i -> 
                isOk(cells.entrySet().stream().filter(e -> e.getValue() == i).map(Map.Entry::getKey).collect(Collectors.toSet())));
    }

}
