package a02b.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di spuntare celle in un'area definita (diventano "*"), e dopo averlo fatto 5 volte
     * di mostrare fuori da tale area quante celle spuntate ci sono nella stessa riga o nella stessa colonna. In particolare:
     * 1 - la griglia ha sempre le celle disabilitate mostrate in figura (ossia quelle nell'ultima colonna e quelle sotto la diagonale principale)
     * 2 - all'inizio nessuna cella ha del testo
     * 3 - l'utente seleziona celle nella zona clickabile (ogni volta compare un "*", e se si riclicka lì viene ignorato)
     * 4 - quando l'utente seleziona la quinta "*" allora compaiono i conteggi come segue:
     * - nella diagonale principale compare in ogni cella il numero delle celle spuntate nella stessa colonna
     * - nell'ultima colonna compare in ogni cella il numero delle celle spuntate nella stessa riga
     * 5 - al prossimo click si ricomincia daccapo
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione dei conteggi nell'ultima colonna (è obbligatorio realizzare i conteggi nella diagonale principale)
     * - gestione della ripartenza del gioco
     *  
     * La classe GUI fornita, da modificare, include codice che potrebbe essere utile per la soluzione.
     * 
     * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria (e assenza di difetti al codice): 10 punti
	 * - qualità del codice: 4 punti
	 * - correttezza della parte opzionale (e assenza di difetti al codice): 3 punti
     */


    public static void main(String[] args) throws java.io.IOException {
        new GUI(10); 
    }
}
