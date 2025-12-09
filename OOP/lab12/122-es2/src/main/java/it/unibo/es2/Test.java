package it.unibo.es2;

/**
 * Application entry point.
 */
public final class Test {

    private Test() { }

    /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine test/resources/fig.png, fornita,
     * che contiene una griglia quadrata di pulsanti inizialmente senza label. Il costruttore di GUI deve prendere in
     * ingresso il numero di righe (o colonne)---e quindi dovrà funzionare per qualunque numero (diciamo fra 3 e 7).
     * Alla pressione di un pulsante la sua label diventi "*", se viene ripremuto torni a " ", e così via.
     * Quando tutte le label su una riga qualsiasi sono "*" o tutte le label su una colonna qualsiasi sono "*"
     * l'applicazione si chiuda.
     *
     * La GUI fornita realizza già gli elementi grafici necessari, ma con una logica preimpostata e non corretta.
     * La si modifiche aggiungendo una interfaccia Logics e una implementazione LogicsImpl come nell'esercizio
     * precedente. Si faccia attenzione che la logica non contenga concetti di View.
     */

    /**
     * Application entry point.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
        new GUI(4);
    }
}
