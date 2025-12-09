package it.unibo.es1;

import java.util.List;

/**
 * Interface defining the logic for a slot-based application.
 */
public interface Logics {

    /**
     * The number of slots.
     *
     * @return the number of slots
     */
    int size();

    /**
     * The current values for every slot.
     *
     * @return ordered list of the integers in each slot
     */
    List<Integer> values();

    /**
     * The enabled/disabled states of each position.
     *
     * @return ordered booleans stating whether a slot is enabled
     */
    List<Boolean> enabledStates();

    /**
     * Increment the value of the specified slot.
     *
     * @param elem the slot to increment
     * @return the new value a button should show after being pressed
     */
    int hit(int elem);

    /**
     * Returns the current state as a string.
     *
     * @return the current state as a string
     */
    String result();

    /**
     * True if it is time to quit (i.e., all slots have the same value).
     *
     * @return whether it is time to quit
     */
    boolean toQuit();
}
