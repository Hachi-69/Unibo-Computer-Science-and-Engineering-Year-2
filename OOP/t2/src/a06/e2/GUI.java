package a06.e2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();
    private final model m = new modelImpl();
    private final Map<JButton, Integer> map = new HashMap<>();

    public GUI(int width) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * width, 70 * width);

        JPanel panel = new JPanel(new GridLayout(width, width));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            jb.setText(String.valueOf(map.get(jb)));
            if (m.hideAfterTwo()) {
                cells.stream()
                        .filter(b -> b.isEnabled())
                        .forEach(b -> b.setText(""));
            } else {
                if (m.isEqual(map.get(jb))) {
                    cells.stream()
                            .filter(b -> b.isEnabled())
                            .filter(b -> Objects.equals(map.get(b), map.get(jb)))
                            .filter(b -> !"".equals(b.getText()))
                            .forEach(b -> {
                                b.setEnabled(false);
                                b.setText(String.valueOf(map.get(b)));
                            });
                }
            }
        };

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final JButton jb = new JButton();
                this.cells.add(jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        for (int i = 0; i < width * width; i++) {
            map.put(cells.get(i), m.createRandomNumbers());
        }
        this.setVisible(true);
    }

}
