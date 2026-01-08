package a02a.sol2;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class LogicImpl implements Logic {

    private final List<Position> path;
    private int position = 0;
    private Set<Integer> pitfalls = new HashSet<>();

    public LogicImpl(int size) {
        this.path = this.buildPath(size);
        var random = new Random();                
        while (this.pitfalls.size() != 3){
            this.pitfalls.add(random.nextInt(this.path.size()));
        }
    }

    private List<Position> buildPath(int size){
        var s1 = Stream.iterate(0, i -> i < size-1, i -> i + 1).map(i -> new Position(i,0));
        var s2 = Stream.iterate(0, i -> i < size-1, i -> i + 1).map(i -> new Position(size-1,i));
        var s3 = Stream.iterate(0, i -> i < size-1, i -> i + 1).map(i -> new Position(size-1-i,size-1));
        var s4 = Stream.iterate(0, i -> i < size-1, i -> i + 1).map(i -> new Position(0,size-1-i));
        return Stream.concat(s1,
                Stream.concat(s2,
                        Stream.concat(s3, s4))).toList();
    }
    /*
    // a better (though equivalent) version of buildPath, addressing DRY
    private List<Position> alternateBuildPath(int size){ 
        return Stream.<Function<Integer, Position>>of(
                i -> new Position(i,0), 
                i -> new Position(size-1,i), 
                i -> new Position(size-1-i,size-1), 
                i -> new Position(0,size-1-i))
                    .flatMap(f -> Stream.iterate(0, i -> i < size-1, i -> i + 1).map(f))
                    .toList();
    }
    */


    @Override
    public List<Position> path() {
        return this.path;
    }

    @Override
    public void advance(int advancing) {
        this.position = (this.position + advancing) % path.size();
        if (this.pitfalls.contains(this.position)){
            this.pitfalls.remove(position);
            this.position = 0;
        }
    }

    @Override
    public Position mark() {
        return this.path.get(position);
    }

    @Override
    public boolean isPitfall(Position p) {
        return this.pitfalls.stream().map(this.path::get).anyMatch(p::equals);
    }

    @Override
    public boolean isOver() {
        return this.pitfalls.isEmpty();
    }

}
