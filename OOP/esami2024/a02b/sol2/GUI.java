package a02b.sol2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;
    private final int size;

    public GUI(int size) {
        this.size = size;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);
        this.logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var position = this.cells.get(jb);
            if (this.logic.isOver()) {
                this.logic.restart();
                this.reset();
            }
            jb.setText(this.logic.hit(position) ? "*" : " ");
            if (this.logic.isOver()) {
                for (var entry: cells.entrySet()){
                    this.logic.getValue(entry.getValue()).ifPresent(i -> entry.getKey().setText(String.valueOf(i)));
                }
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                var pos = new Position(j, i);
                if (!this.logic.inArea(pos)) {
                    jb.setEnabled(false);
                }
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void reset() {
        for (var jb: cells.keySet()){
            jb.setText(" ");
        }
    }
}
