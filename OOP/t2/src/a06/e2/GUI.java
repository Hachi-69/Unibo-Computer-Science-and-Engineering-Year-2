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
    // Mappa per ricordarci quale bottone corrisponde a quale posizione logica
    private final Map<JButton, Position> buttonsMap = new HashMap<>();

    public GUI(int width) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70 * width, 70 * width);

        JPanel panel = new JPanel(new GridLayout(width, width));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();

            // 1. Recuperi la posizione del bottone cliccato dalla mappa della View
            Position pos = buttonsMap.get(jb);

            // 2. Chiedi al model il valore in quella posizione
            int val = m.getNumberAt(pos);

            // Mostri il valore
            jb.setText(String.valueOf(val));

            if (m.hideAfterTwo()) {
                cells.stream()
                        .filter(b -> b.isEnabled())
                        .forEach(b -> b.setText(""));
            } else {
                if (m.isEqual(val)) { // Passi il valore (int) invece di map.get(jb)
                    cells.stream()
                            .filter(b -> b.isEnabled())
                            // QUI CAMBIA LA LOGICA DI FILTRAGGIO:
                            // Per ogni bottone 'b', recuperi la sua posizione e chiedi al model se il
                            // valore corrisponde a 'val'
                            .filter(b -> m.getNumberAt(buttonsMap.get(b)) == val)
                            .filter(b -> !"".equals(b.getText()))
                            .forEach(b -> b.setEnabled(false));
                    if (m.isGameOver()) {
                        // Se è finita, disabilitiamo TUTTI i bottoni rimasti
                        cells.forEach(b -> b.setEnabled(false));
                    }
                }
            }
        };

        m.initGame(width);

        // 2. Crea la griglia grafica
        for (int i = 0; i < width; i++) { // i = riga (y)
            for (int j = 0; j < width; j++) { // j = colonna (x)
                final JButton jb = new JButton();

                // Aggiungiamo il bottone alla lista (utile per disabilitarli tutti alla fine)
                this.cells.add(jb);

                // Mappiamo il bottone fisico alla sua coordinata logica (x, y)
                // Nota: Position(x, y) -> x è la colonna (j), y è la riga (i)
                this.buttonsMap.put(jb, new Position(j, i));

                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        this.setVisible(true);
    }

}
