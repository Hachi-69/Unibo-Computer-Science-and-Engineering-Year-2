package a06.e1;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di far spostare una * prima in verticale nelle celle abilitate, poi in orizzontale, 
     * poi ricominciando daccapo. 
     * In particolare:
     * 1 - la griglia ha sempre l'aspetto in figura fig1 (si notino le celle disabilitate) -- la cella in cui si incrociano la riga 
     * orizzontale e quella verticale va scelta in modo random;
     * 2 - all'inizio la * è positionata in alto (fig 1);
     * 3 - clickando sulla *, questa scende di una posizione (fig 2); se invece si clicka fuori dalla *, allora scende di 2 posizioni (fig 3);
     * 4 - arrivata in fondo (fig 4), il prossimo click la porterà sulla linea orizzontale (fig 5)
     * 5 - a questo punto, ogni click la sposta a destra (di 1 casella se cisi clicka sopra, di due altrimenti);
     * 6 - arrivata al limite destro, il prossimo click la riporta alla posizione iniziale (punto 2 qui sopra);
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione del reset della posizione una volta arrivati in fondo
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
