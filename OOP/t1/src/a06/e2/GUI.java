package a06.e2;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GUI extends JFrame {

    private final List<JButton> cells = new ArrayList<>();
    private final Logic logic = new LogicImpl();

    public GUI(int size) {
        logic.gameInit(size);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton go = new JButton("Go");
        main.add(BorderLayout.SOUTH, go);

        go.addActionListener(e -> {
            go.setEnabled(logic.collapse());
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    final int index = i * size + j;
                    final int number = logic.getNumberAt(new Pair<>(i, j));
                    cells.get(index).setText(String.valueOf((number == 0 ? "" : number)));
                }
            }
        });

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(String.valueOf(logic.getNumberAt(new Pair<>(i, j))));
                this.cells.add(jb);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
}
