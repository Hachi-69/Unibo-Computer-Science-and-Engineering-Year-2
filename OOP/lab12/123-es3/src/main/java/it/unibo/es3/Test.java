package it.unibo.es3;

/**
 * Main class to start the application.
 */
public final class Test {

    private Test() { }

    /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita,
     * che realizza una semplice animazione, che effettua uno scatto a ogni pressione del pulsante ">", mostrata nella
     * sequenza delle immagini fig1.png, fig2.png, fig3.png, fig4.png,...
     * 1 - all'inizio la griglia è vuota, con tre celle attive (con asterisco) scelte in modo random
     * 2 - a ogni pressione si attiva anche ogni cella che abbia una cella vicina già attiva
     *     (vicina orizzontalmente, verticalmente, o diagonalmente)
     * 3 - quando tutte le celle sono attive si esca
     *
     * Al solito ci si raccomanda di scorporare tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     *
     * La classe GUI fornisce codice che potrebbe essere utile per la soluzione, ma che va riadattato ispirandosi
     * eventualmente agli esercizi precedenti.
     */

    /**
     * Main method.
     *
     * @param args command line arguments (ignored)
     */
    public static void main(final String... args) {
        new GUI(10); // only use odd numbers as arguments!
    }
}
