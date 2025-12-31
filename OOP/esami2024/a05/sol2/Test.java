package a05.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di selezionare caselle mostrando/cancellando delle croci, che non si devono mai
     * interesecare tra loro. 
     * In particolare:
     * 1 - la griglia è inizialmente vuota
     * 2 - selezionando una casella vuota, si disegna una croce come in figura (a 9 celle), centrata in tale casella selezionata
     * (la figura 1 mostra come appare la griglia dopo due click, in posizioni x,y: 3,4 e  7,7)
     * 3 - ad un click, la croce viene in realtà disegnata solo se nessuna delle sue caselle aveva già una "*" sopra,
     * in tal caso il click vanga ignorato;
     * 4 - se il click viene effettuato su una cella qualunque che ha già una "*" allora si cancella tutta la corrispondente croce
     * (ossia le "*" nelle sue 9 caselle vengono cancellate);
     * 5 - se il click creerebbe una croce perfettamente allineata orizzontalmente o verticalmente con una croca già esistente,
     * l'applicazione termini.
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione della chiusura dell'applicazione.
     *  
     * La classe GUI fornita, da modificare, include codice che potrebbe essere utile per la soluzione.
     * 
     * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria (e assenza di difetti al codice): 10 punti
	 * - qualità del codice: 4 punti
	 * - correttezza della parte opzionale: 3 punti
     */


    public static void main(String[] args) throws java.io.IOException {
        new GUI(11); 
    }
}
