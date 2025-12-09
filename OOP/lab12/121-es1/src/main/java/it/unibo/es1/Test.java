package it.unibo.es1;

/**
 * Main class to run the GUI application.
 */
public final class Test {

    private Test() {
    }

    /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine
     * test/resources/gui.png fornita (N=4), attraverso
     * il completamento della classe GUI fornita (il cui costruttore prenda il numero N dei pulsanti, tranne Print) da
     * visualizzare. I pulsanti hanno indicazione iniziale 0.
     * Alla pressione di un pulsante questo passa a 1, poi ripremendolo a 2, poi a 3 e così via. Arrivato a N il
     * pulsante si disattiva e non può più essere premuto. Premendo Print, si stampi a video una stringa che contenga
     * il valore dei pulsanti; nel caso dell'immagine fornita, premendo Print si ottenga ESATTAMENTE la stampa:
     *
     * <<0|2|1|3>>
     *
     * Nel caso in cui dopo una certa pressione tutti i pulsanti abbiano lo stesso valore, si esca senza stampe.
     *
     * La classe GUI fornisce un template di partenza. Per ottemperare al Single Responsibility Principle (SRP),
     * tutta la logica dell'applicazione va implementata a parte, ossia nella classe LogicsImpl che implementa
     * l'interfaccia già fornita Logics.
     */

    /**
     * Main method to launch the GUI.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
        new GUI(4); 
    }

}
