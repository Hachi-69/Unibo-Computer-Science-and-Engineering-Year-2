package it.unibo.oop.reactivegui02;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.oop.JFrameUtil;

import java.io.Serial;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

/**
 * A simple Swing-based reactive GUI demonstrating concurrent updates to a label
 * from a background thread. The GUI provides controls to increment, decrement,
 * and stop a counter displayed on screen.
 *
 * <p>
 * The counter is updated by an {@code Agent} running on a dedicated thread.
 * UI updates are marshalled onto the Event Dispatch Thread via
 * {@link javax.swing.SwingUtilities#invokeAndWait(Runnable)} to ensure thread
 * safety.
 * </p>
 *
 * <p>
 * Buttons:
 * <ul>
 * <li>{@code up}: starts incrementing the counter</li>
 * <li>{@code down}: starts decrementing the counter</li>
 * <li>{@code stop}: stops the agent and disables all controls</li>
 * </ul>
 * </p>
 *
 * <p>
 * Logging is used to report exceptions occurring in the background thread.
 * </p>
 */
public final class ConcurrentGUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentGUI.class);
    private final JLabel display = new JLabel();
    private final JButton up = new JButton("up");
    private final JButton down = new JButton("down");
    private final JButton stop = new JButton("stop");

    /**
     * Initializes the ConcurrentGUI window and its reactive components.
     * 
     * <p>
     * The constructor:
     * <ul>
     * <li>Sizes and shows the main JFrame via
     * {@code JFrameUtil.dimensionJFrame}.</li>
     * <li>Creates a panel containing the display label and control buttons (Up,
     * Down, Stop), and adds it to the frame.</li>
     * <li>Starts a background {@code Agent} in a dedicated thread to perform
     * counting.</li>
     * <li>Wires button actions to control the agent: stop counting, increment, and
     * decrement.</li>
     * </ul>
     * Side effects:
     * <ul>
     * <li>Spawns a new thread for the {@code Agent}, which updates the UI
     * display.</li>
     * <li>Makes the frame visible.</li>
     * </ul>
     * Threading notes:
     * <ul>
     * <li>User interactions occur on the Swing Event Dispatch Thread (EDT), while
     * counting runs in a background thread.</li>
     * <li>Ensure any updates to Swing components are performed on the EDT.</li>
     * </ul>
     */
    public ConcurrentGUI() {
        super();
        JFrameUtil.dimensionJFrame(this);
        final JPanel panel = new JPanel();
        panel.add(display);
        panel.add(up);
        panel.add(down);
        panel.add(stop);
        this.getContentPane().add(panel);
        this.setVisible(true);

        final Agent agent = new Agent();
        new Thread(agent).start();

        stop.addActionListener(e -> agent.stopCounting());
        up.addActionListener(e -> agent.countUp());
        down.addActionListener(e -> agent.countDown());
    }

    private final class Agent implements Runnable {
        private volatile boolean bStop;
        private volatile boolean bUp;
        private volatile boolean bDown;
        private int counter;

        @Override
        public void run() {
            while (!this.bStop) {
                try {
                    final String nextText = Integer.toString(this.counter);
                    SwingUtilities.invokeAndWait(() -> ConcurrentGUI.this.display.setText(nextText));
                    if (bUp) {
                        this.counter++;
                    } else if (bDown) {
                        this.counter--;
                    }
                    Thread.sleep(100);
                } catch (InvocationTargetException | InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }

            }

        }

        public void stopCounting() {
            this.bStop = true;
            ConcurrentGUI.this.up.setEnabled(false);
            ConcurrentGUI.this.down.setEnabled(false);
            ConcurrentGUI.this.stop.setEnabled(false);
        }

        public void countUp() {
            this.bUp = true;
            this.bDown = false;
        }

        public void countDown() {
            this.bDown = true;
            this.bUp = false;
        }
    }
}
