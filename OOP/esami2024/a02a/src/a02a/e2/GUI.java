package a02a.e2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private Logic logic;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);

        logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            logic.advance();
            draw();

            if (logic.isOver()) {
                JOptionPane.showMessageDialog(this, "Game Over!");
                System.exit(0);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                this.cells.put(jb, new Position(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        draw();
        this.setVisible(true);
    }

    private void draw() {
        for (var elem : cells.entrySet()) {
            elem.getKey().setEnabled(false);
        }

        for (var elem : cells.entrySet()) {
            JButton jb = elem.getKey();
            Position p = elem.getValue();
            String content = logic.getAtPos(p);

            if ("-".equals(content) || "o".equals(content) || "*".equals(content)) {
                jb.setText("");
                jb.setEnabled(true);
            }
            if ("*".equals(content)) {
                jb.setText(content);
            }
            if ("o".equals(content)) {
                jb.setText("o");
            }
        }
    }
}
