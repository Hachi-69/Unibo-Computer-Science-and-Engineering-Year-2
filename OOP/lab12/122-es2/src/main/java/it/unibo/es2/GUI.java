package it.unibo.es2;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The GUI class representing the graphical user interface of the application.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new LinkedHashMap<>();
    //private final Logics logics;

    /**
     * Constructs a GUI with the specified size.
     *
     * @param size the size of the grid
     */
    public GUI(final int size) {
        //this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        // Layout
        final var panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, panel);
        // Buttons
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton button = new JButton(" ");
                button.addActionListener(e -> {
                    final Pair<Integer, Integer> buttonPosition = buttons.get(button);
                    button.setText(buttonPosition.toString());
                    if (buttonPosition.equals(new Pair<>(0, 0))) {
                        // System.exit(1); // Too brutal!
                        dispose();
                    }
                });
                this.buttons.put(button, new Pair<>(i, j));
                panel.add(button);
            }
        }
        this.setVisible(true);
    }
}
