package a01b.e2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class GUI extends JFrame {

    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic = new LogicImpl();

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);

        logic.init(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            if (logic.hit(new Position(cells.get(jb).x(), cells.get(jb).y()))) {
            } else {
                for (var elem : cells.entrySet()) {
                    elem.getKey().setText("");
                }
            }
            draw();
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
            JButton jbtn = elem.getKey();
            Position pos = elem.getValue();

            String content = logic.getAtPos(pos);
            jbtn.setText((content != null ? content : ""));
        }
    }
}