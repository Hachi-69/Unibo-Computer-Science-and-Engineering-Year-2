package a03b.e2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di selezionare 5 celle con direzione su ("^") o giù ("v") e quindi farle 
     * rispettivamente salire e scendere una a una fino a che non arrivano in fondo alla griglia (in alto o basso).
     * In particolare:
     * 1 - clickando su una cella non già selezionata, questa riporterà "^"
     * 2 - riclickandoci passa da "^" a "v" e viceversa
     * 3 - dopo averne selezionate 5 (ognuna o su o giù, si veda fig1) ogni successivo click (ovunque sia, è irrilevante) farà spostare
     * una cella selezionata su (se "^") o giù (se "v") di una casella
     * -- 3a un cella selezionata si sposta solo se nella posizione d'arrivo non c'è già un'altra casella oppure se finisce la griglia
     * -- 3b: si scelga la prossima cella da spostare in modo arbitrario, ma garantendo in qualche modo che tutte si spostino più o meno 
     * assieme (ossia non prima una fino in fondo, poi un'altra,...)
     * 4 - quando nessuna cella può più spostarsi (fig2) si cancellino le selezioni e si ricominci dal punto 1
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione dello scontro fra celle selezionate (si può assumere non succederà)
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
