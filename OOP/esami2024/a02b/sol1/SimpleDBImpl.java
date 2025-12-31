package a02b.sol1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SimpleDBImpl implements SimpleDB {

    // a Table or View
    interface DBObject { 
        Set<Integer> ids();
        Function<Integer, Set<Pair<String, Object>>> elements();
    }
    // Table implementation
    record Table(Map<Integer, Set<Pair<String, Object>>> values) implements DBObject{

        @Override
        public Function<Integer, Set<Pair<String, Object>>> elements() {
            return values::get;
        }

        @Override
        public Set<Integer> ids() {
            return values.keySet();
        }
    }

    // View implementation, note elements are not a data structure, but a function
    record View(Supplier<Set<Integer>> idsSupplier, Function<Integer, Set<Pair<String, Object>>> elements) implements DBObject{

        @Override
        public Set<Integer> ids() {
            return idsSupplier.get();
        }

    }

    private final Map<String, DBObject> objects = new HashMap<>();

    @Override
    public void createTable(String name) {
        this.objects.put(name, new Table(new HashMap<>()));
    }

    @Override
    public void addTuple(String tableName, int id, String description, Object value) {
        ((Table)this.objects.get(tableName)).values().merge(id, new HashSet<>(Set.of(new Pair<>(description, value))), (s1,s2) -> {s1.addAll(s2); return s1;});
    }

    @Override
    public Set<Integer> ids(String name) {
        return this.objects.get(name).ids();
    }

    @Override
    public Map<String, Object> values(String name, int id) {
        return this.objects.get(name).elements().apply(id).stream().collect(Collectors.toMap(Pair::get1, Pair::get2));
    }

    @Override
    public void createViewOfSingleDescription(String viewName, String originName, String description) {
        this.objects.put(viewName, new View(
            () -> this.objects.get(originName).ids(),
            id -> this.objects.get(originName).elements().apply(id).stream().filter(p -> p.get1().equals(description)).collect(Collectors.toSet())));
    }

    @Override
    public void createViewOfSingleId(String viewName, String originName, int id) {
        this.objects.put(viewName, new View(
            () -> Set.of(id),
            i -> this.objects.get(originName).elements().apply(i)));
    }
}
