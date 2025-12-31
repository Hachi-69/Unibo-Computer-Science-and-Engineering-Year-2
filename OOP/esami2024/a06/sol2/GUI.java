package a06.sol2;

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
            var pos = this.cells.get(jb);
            this.logic.hit(pos);
            updatePos(jb, pos);
            if (this.logic.isOver()){
                System.exit(0);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                var pos = new Position(j, i);
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        reset();
        this.setVisible(true);
    }

    private void updatePos(JButton jb, Position pos){
        jb.setText(this.logic.hasValue(pos) ? "" + this.logic.getValue(pos) : "");
    }

    private void reset() {
        for (var entry: cells.entrySet()){
            updatePos(entry.getKey(), entry.getValue());
        }
    }
}
