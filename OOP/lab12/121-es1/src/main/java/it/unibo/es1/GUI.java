package it.unibo.es1;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple GUI class that creates a window with buttons based on the Logics implementation.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<JButton> buttons = new ArrayList<>();

    /**
     * Constructor that initializes the GUI with a specified number of buttons.
     *
     * @param size the number of buttons to create
     */
    public GUI(final int size) {
        final Logics logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout());
        // ActionListener for the buttons
        logics.values().forEach(v -> {
            final JButton button = new JButton(String.valueOf(v));
            buttons.add(button);
            button.addActionListener(e -> {
                final int buttonPosition = buttons.indexOf(button);
                button.setText(String.valueOf(logics.hit(buttonPosition)));
                button.setEnabled(logics.enabledStates().get(buttonPosition));
                if (logics.toQuit()) {
                    this.dispose();
                    // System.exit(0); // Too abrupt!
                }
            });
            this.getContentPane().add(button);
        });
        final JButton ok = new JButton("Print");
        this.getContentPane().add(ok);
        ok.addActionListener(e -> System.out.println(logics.result())); // NOPMD: required by the exercise
        // Make the frame visible
        pack();
        this.setVisible(true);
    }

}
