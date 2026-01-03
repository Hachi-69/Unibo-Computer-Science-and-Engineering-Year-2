package a01a.e2;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class GUI extends JFrame {

    // Mappa per associare ogni bottone alla sua posizione logica
    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * size, 70 * size);

        this.logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            Position position = cells.get(jb);

            // 1. Notifica la logica del click
            logic.selected(position);

            // 2. Ridisegna l'intera griglia basandosi sul nuovo stato della logica
            this.refresh();
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                Position pos = new Position(j, i);
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        this.refresh(); // Disegna lo stato iniziale
        this.setVisible(true);
    }

    private void refresh() {
        // Itera su tutti i bottoni e aggiorna il testo chiedendolo alla logica
        for (var entry : cells.entrySet()) {
            JButton jb = entry.getKey();
            Position pos = entry.getValue();

            // Chiama il metodo che recupera il contenuto ("*", "1", "o", ecc.)
            String content = logic.getCellContent(pos);
            jb.setText(content != null ? content : "");
        }
    }
}