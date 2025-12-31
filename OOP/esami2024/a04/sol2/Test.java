package a04.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di far girare una * in un primo gruppo/cerchio di 8 celle, poi in un secondo, poi in una terzo,
     * (rispettivamente quella più a sinistra, quella al centro, e poi quella a destra), poi ricominciando dal primo e così via. 
     * In particolare:
     * 1 - la griglia ha sempre l'aspetto in figura fig1 (si notino le celle disabilitate);
     * 2 - all'inizio la * è positionata in alto a sinistra;
     * 3 - clickando su una cella qualunque che NON contiene la *, questa si sposta di una casella in senso antiorario rimanendo 
     * nello stesso gruppo/cerchio in cui si trova (fig2, e poi la volta successiva fig3, eccetera, per un numero indefinito di volte);
     * 4 - quando l'utente clicka la *, questa si sposta nella posizione alto-sx del prossimo gruppo/cerchio di celle (fig 4),
     * e da lì si procederà come al punto 3 (in senso antiorario ad ogni click successivo);
     * 5 - quando l'utente riclicka la *, ci si sposta nell'ultimo gruppo/cerchio di celle (fig 6), procedendo al solito 
     * (in senso antiorario ad ogni click successivo);
     * 6 - al prossimo click su *, non essendoci altro gruppo di celle, si ricominci d'accapo, come da punto 2.
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione di cosa succede dopo il secondo click sulla * (ossia ignorare i movimenti sul terzo gruppo)
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
