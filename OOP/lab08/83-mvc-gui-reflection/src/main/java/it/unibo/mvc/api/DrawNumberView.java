package it.unibo.mvc.api;

/**
 * Represents a view (boundary) architectural component of the application.
 */
public interface DrawNumberView {

    /**
     * Sets the controller controlled by this view (if works as input).
     *
     * @param observer the controller to attach
     */
    void setController(DrawNumberController observer);

    /**
     * This method is called before the UI is used. It should finalize its status (if needed).
     */
    void start();

    /**
     * Tells the UI to display the result of the draw.
     *
     * @param res the result of the last draw
     */
    void result(DrawResult res);
}
