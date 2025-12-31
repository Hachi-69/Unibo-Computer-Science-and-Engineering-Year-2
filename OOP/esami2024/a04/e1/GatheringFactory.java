package a04.e1;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/**
 * A factory of gathering of various sorts. 
 * The first one is already implemented, for the sake of example:
 * you may implement others using this template.
 */
public interface GatheringFactory {

    /**
     * @param <E>
     * @return a trivial gathering, where output and input suppliers are the same
     */
    default <E> Gathering<E, E> gatherOne(){
        // note that Gathering is a functional interface, hence it can be implemented
        // by a lambda sup -> ...
        return sup -> new Supplier<>(){

            @Override
            public E get() {
                return sup.get();
            }};
    }

    /**
     * @param <E>
     * @return a gathering that, taken a supplier producing iteratively (i0, i1, i2,...), it creates a supplier producing
     * ([i0], [i0, i1], [i0, i1, i2],...), that is, first list [i0] then list [i0,i1], etcetera, namely, the list of all
     * elements consumed from the input so far.
     */
    <E> Gathering<E, List<E>> scanToList();

    /**
     * @param <E>
     * @param size
     * @return a gathering that, taken a supplier producing iteratively (i0, i1, i2,...) and assuming size = 3, it creates a supplier producing
     * ([i0 ,i1, i2], [i1, i2, i3], [i2, i3, i4],...), namely, each produced element is the list of the latest 'size' elements consumed from
     * the input.
     */
    <E> Gathering<E, List<E>> slide(int size);

    /**
     * @param <E>
     * @return a gathering that, taken a supplier producing iteratively (i0, i1, i2,...), creates a supplier producing
     * (<i0 ,i1>, <i1, i2>, <i2, i3>, ...), namely, each produced element is the pair of the latest 2 elements consumed from the input.
     */
    <E> Gathering<E, Pair<E, E>> pairs();

    /**
     * @return a gathering that, taken a supplier producing iteratively numbers (i0, i1, i2,...), creates a supplier producing
     * (i01+i1+i2, i1+i2+i3, ...), namely, each produced element is the sum of the latest 3 numbers consumed from the input.
     */
    Gathering<Integer, Integer> sumLastThree();

    /**
     * @param <E>
     * @param op, and operator like + or *
     * @return a gathering that, taken a supplier producing iteratively numbers (i0, i1, i2,...), creates a supplier first producing
     * i0, then op(i0,i1), then op(op(i0,i1),i2), etcetera, namely, each produced element is the application of binary operator op
     * to all elements consumed so far (that is, a reduce).
     */
    <E> Gathering<E, E> scanAndReduce(BinaryOperator<E> op);
}
