package a03b.e1;

import java.util.List;

/**
 * A DependencyArray is just like an array, with two notable differences: 
 * 1 - some of its elements may not be written (if you try you get an UnsupportedOperationException) 
 * 2 - some of its elements are the (dynamic) result of a computation done on ther elements, like a sum.
 */
public interface DependencyArray<E> {

    /**
     * @return the size of the array, that is fixed at creation time
     */
    int size();

    /**
     * @param position
     * @return the value at this @param position, independently of it being a value or result of a computaiton
     */
    E read(int position);

    /**
     * @param position
     * @param value
     * @throws UnsupportedOperationException if this elements can't be rewritten
     * writes @param value at @param position
     */
    void write(int position, E value);

    /**
     * @return the list of results by reading all elements of the array
     */
    List<E> elements();
   
}
