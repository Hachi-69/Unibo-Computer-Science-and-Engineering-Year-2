package it.unibo.oop.reactivegui03;

import java.io.Serial;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.oop.JFrameUtil;

/**
 * Third experiment with reactive gui.
 */
public final class AnotherConcurrentGUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnotherConcurrentGUI.class);
    private static final Long WAITING_TIME_BEFORE_STOP = 10_000L;

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
    public AnotherConcurrentGUI() {
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

        new Thread(() -> {
            try {
                Thread.sleep(WAITING_TIME_BEFORE_STOP);
                agent.stopCounting();
            } catch (final InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }).start();

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
                    SwingUtilities.invokeAndWait(() -> AnotherConcurrentGUI.this.display.setText(nextText));
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
            try {
                this.bStop = true;
                SwingUtilities.invokeAndWait(() -> {
                    AnotherConcurrentGUI.this.up.setEnabled(false);
                    AnotherConcurrentGUI.this.down.setEnabled(false);
                    AnotherConcurrentGUI.this.stop.setEnabled(false);
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
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
