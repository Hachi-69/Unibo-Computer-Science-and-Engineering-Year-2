package it.unibo.mvc.api;

/**
 * Controller interface.
 */
public interface DrawNumberController {

    /**
     * Makes a guess.
     *
     * @param n the attempt
     */
    void newAttempt(int n);

    /**
     * Resets the current game (if any is running) and starts a new one.
     */
    void resetGame();

    /**
     * Gracefully quits from the application.
     */
    void quit();

    /**
     * Adds a new view.
     *
     * @param newView the view to be added
     */
    void addView(DrawNumberView newView);
}
