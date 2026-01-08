package a02a.e2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza una variante semplificata del gioco dell'oca: la pedina del giocatore ("*") può essere avanzata di
     * un certo numero di caselle ogni volta, procedendo in senso orario nelle celle del percorso (quelle del bordo), e
     * se si finisce su un ostacolo ("o") si ricomincia daccapo. In particolare:
     * 1 - le celle del bordo della griglia sono le uniche abilitate, si piazza la pedina in alto a sinistra, e si collocano
     * tre ostacoli in posizioni random
     * 2 - ad ogni click in un qualunque cella, si avanza la pedina di 3 celle la prima volta, di 4 celle la seconda,
     * poi di 3 celle, poi di 4 celle, poi di 3 celle, e così via (si procede all'infinito in modo circolare)... 
     * 3 - se si finisce su un ostacolo, la pedina viene riposizionata nella sua celle iniziale (in alto a sinistra), 
     * e quell'ostacolo viene rimosso
     * 4 - quando si finisce sull'ultimo ostacolo presente, si chiuda la partita
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - rimozione degli ostacoli e chiusura partita
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
