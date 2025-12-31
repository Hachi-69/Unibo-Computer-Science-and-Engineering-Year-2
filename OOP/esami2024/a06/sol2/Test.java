package a06.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di indicare numeri in una sorta di "micro-sudoku", dove si vince se si riempiono tutte
     * le caselle e non ci sono ripetizioni né nelle righe né nelle colonne. 
     * In particolare:
     * 1 - la griglia (dimensione N, indicata nel costruttore qui sotto) è inizialmente vuota (figura 1);
     * 2 - clickando su una casella vuota, compare un 1, riclickandoci il 2; arrivati ad N, se si clicka di nuovo si torna alla cella vuota, e così via;
     * 3 - si possono clickare caselle in un qualunque ordine -- ad esempio ad un certo punto ci si trova come da figura 2;
     * 4 - non appena tutte le celle hanno un numero, e nelle righe e nelle colonne non ci sono ripetizioni, l'applicazione si chiuda -- 
     * ad esempio si esce se, come da figura 3, si clicka sulla cella in basso a destra, che diventerebbe un 2.
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque al raggiungimento della 
     * totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione delle caselle vuote (ossia è accettabile che nelle caselle ci sia sempre un numero da 1 a N, ad esempio 1 all'inizio)
     * Si noti che il codice prodotto feve funzionare per qualsiasi valore N della dimensione della griglia. 
     * 
     * La classe GUI fornita, da modificare, include codice che potrebbe essere utile per la soluzione.
     * 
     * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria (e assenza di difetti al codice): 10 punti
	 * - qualità del codice: 4 punti
	 * - correttezza della parte opzionale: 3 punti
     */


    public static void main(String[] args) throws java.io.IOException {
        new GUI(3); 
    }
}
