package it.unibo.oop;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Utility class for JFrame.
 */
public final class JFrameUtil {

    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.1;

    private JFrameUtil() {
    }

    /**
     * Dimensions a JFrame to a percentage of the screen size.
     *
     * @param target the JFrame to dimension
     */
    public static void dimensionJFrame(final JFrame target) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        target.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));
        target.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
