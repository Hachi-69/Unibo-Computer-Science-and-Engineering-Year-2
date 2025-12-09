package it.unibo.es3;

import java.io.Serializable;

/**
 * A standard generic Pair implemented as a record.
 * Note that, in older exams (before records were introduced in Java)
 * this class may be provided as a standard Java class
 * with getters, hashCode, equals, and toString well implemented.
 *
 * @param <X> type of first element
 * @param <Y> type of second element
 * @param x first element
 * @param y second element
 */
public record Pair<X, Y>(X x, Y y) implements Serializable { }
