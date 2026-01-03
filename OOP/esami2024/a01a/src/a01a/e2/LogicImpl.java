package a01a.e2;

import java.util.HashMap;
import java.util.Map;

public class LogicImpl implements Logic {
    private final Map<Position, String> grid = new HashMap<>();
    private final int size;
    private boolean gameOver = false;

    public LogicImpl(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.put(new Position(i, j), "");
            }
        }
    }

    @Override
    public String getCellContent(Position p) {
        return this.grid.get(p);
    }

    @Override
    public boolean selected(Position p) {
        // 1. GESTIONE RESET
        // Se il perimetro è visibile (gioco finito), al click successivo (ovunque sia)
        // si resetta tutto.
        if (this.gameOver) {
            this.startOver(); // Pulisce la griglia
            this.gameOver = false; // Reimposta lo stato
            return true; // Dice alla GUI di ridisegnare (la griglia vuota)
        }

        // 2. CONTROLLO BORDI
        // L'utente non può selezionare celle sul bordo della griglia
        if (p.x() == 0 || p.x() == this.size - 1 || p.y() == 0 || p.y() == this.size - 1) {
            return false; // Nessun cambiamento, la GUI non deve ridisegnare
        }

        // 3. LOGICA DI GIOCO
        String currentContent = this.grid.get(p);

        if ("*".equals(currentContent)) {
            // CASO A: Clicco su una stella esistente -> Calcolo perimetro e finisco il
            // gioco
            this.calculatePerimeter();
            this.gameOver = true;
        } else {
            // CASO B: Cella vuota (o comunque interna) -> Metto una stella
            this.grid.put(p, "*");
        }

        // Ritorna true perché la griglia è cambiata e la GUI deve aggiornarsi
        return true;
    }

    @Override
    public void calculatePerimeter() {
        // Inizializza i valori min/max agli opposti
        int minX = size;
        int maxX = -1;
        int minY = size;
        int maxY = -1;

        // 1. Scansiona la griglia per trovare l'estensione delle stelle ("*")
        for (var entry : this.grid.entrySet()) {
            if (entry.getValue().equals("*")) {
                Position p = entry.getKey();
                if (p.x() < minX)
                    minX = p.x();
                if (p.x() > maxX)
                    maxX = p.x();
                if (p.y() < minY)
                    minY = p.y();
                if (p.y() > maxY)
                    maxY = p.y();
            }
        }

        // 2. Calcola i bordi del perimetro (ALLARGANDO DI 1 rispetto alle stelle)
        int left = minX - 1;
        int right = maxX + 1;
        int top = minY - 1;
        int bottom = maxY + 1;

        // 3. Disegna il perimetro ("o") sulle righe e colonne esterne calcolate

        // Disegna linee orizzontali (sopra e sotto)
        for (int x = left; x <= right; x++) {
            this.grid.put(new Position(x, top), "o");
            this.grid.put(new Position(x, bottom), "o");
        }

        // Disegna linee verticali (sinistra e destra)
        for (int y = top; y <= bottom; y++) {
            this.grid.put(new Position(left, y), "o");
            this.grid.put(new Position(right, y), "o");
        }

        // 4. Posiziona i numeri nei vertici (sovrascrivendo le "o")
        this.grid.put(new Position(left, top), "2"); // Alto-Sinistra
        this.grid.put(new Position(right, top), "1"); // Alto-Destra
        this.grid.put(new Position(right, bottom), "3"); // Basso-Destra
        this.grid.put(new Position(left, bottom), "4"); // Basso-Sinistra
    }

    private void startOver() {
        for (Position p : this.grid.keySet()) {
            this.grid.put(p, "");
        }
    }
}
