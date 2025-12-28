package a06.e2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();
    private final Model m = new ModelImpl();
    private final Map<JButton, Position> buttonsMap = new HashMap<>();

    public GUI(int width) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * width, 70 * width);

        JPanel panel = new JPanel(new GridLayout(width, width));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            Position pos = buttonsMap.get(jb);
            int val = m.getNumberAt(pos);
            jb.setText(String.valueOf(val));

            if (m.hideAfterTwo()) {
                cells.stream()
                        .filter(b -> b.isEnabled())
                        .forEach(b -> b.setText(""));
            } else {
                if (m.isEqual(val)) {
                    cells.stream()
                            .filter(b -> b.isEnabled())
                            .filter(b -> m.getNumberAt(buttonsMap.get(b)) == val)
                            .filter(b -> !"".equals(b.getText()))
                            .forEach(b -> b.setEnabled(false));
                    if (m.isGameOver()) {
                        cells.forEach(b -> b.setEnabled(false));
                    }
                }
            }
        };

        m.initGame(width);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final JButton jb = new JButton();
                this.cells.add(jb);
                this.buttonsMap.put(jb, new Position(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        this.setVisible(true);
    }

}
