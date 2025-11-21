package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "My second Java graphical interface";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Creates a new SimpleGUIWithFileChooser.
     */
    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JPanel inner = new JPanel();
        inner.setLayout(new BorderLayout());

        final JTextField textField = new JTextField();
        final JButton browse = new JButton("Browse...");
        final JButton save = new JButton("Save");
        final JTextArea textArea = new JTextArea();

        final Controller controller = new Controller();

        textField.setText(controller.getCurrentFilePath());
        textField.setEditable(false);

        canvas.add(inner, BorderLayout.NORTH);
        inner.add(textField, BorderLayout.CENTER);
        inner.add(browse, BorderLayout.LINE_END);
        canvas.add(save, BorderLayout.SOUTH);
        canvas.add(textArea,BorderLayout.CENTER);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
         * Handlers
         */
        browse.addActionListener(e -> {
            final JFileChooser fileChooser = new JFileChooser();
            switch (fileChooser.showSaveDialog(canvas)) {
                case JFileChooser.APPROVE_OPTION -> controller.setCurrentFile(fileChooser.getSelectedFile());
                case JFileChooser.CANCEL_OPTION -> {
                }
                default -> JOptionPane.showMessageDialog(frame, "an error has occurred");
            }
            textField.setText(controller.getCurrentFilePath());
        });

        save.addActionListener(e -> {
            try {
                controller.write(textArea.getText());
            } catch (final IOException ex) {
                ex.printStackTrace(); // NOPMD
            }
        });
    }

    private void display() {

        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
    }
}
