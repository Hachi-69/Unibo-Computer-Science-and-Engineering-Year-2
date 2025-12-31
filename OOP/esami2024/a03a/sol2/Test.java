package a03a.sol2;

public class Test {

	 /*
     * Scopo di questo esercizio è realizzare una GUI con l'aspetto mostrato nell'immagine fig1.png, fornita, 
     * che realizza la possibilità di selezionare 5 celle ("*") e quindi farle scendere una a una fino a che
     * non arrivano in fondo o si appoggiano su un ostacolo ("o").
     * In particolare:
     * 1 - inizialmente si posizionano 3 ostacoli ("o") in modo random -- fig1.png
     * 2 - i primi 5 click selezionano 5 celle diverse tra loro ("*") -- fig2.png
     * 3 - ad ogni successivo click (ovunque sia, è irrilevante) si fa scendere una e una sola "*"
     * -- 3a una "*" scenda di una posizione verso il basso (p.e. da fig2 a fig3), ma senza collidere con un ostacolo, 
     * con altra "*", o con la fine della griglia
     * -- 3b: si scelga la prossima "*" da far scendere in modo arbitrario, ma garantendo in qualche modo che tutte le "*" 
     * scendano più o meno assieme (ossia non prima una fino in fondo, poi un'altra,...)
     * 4 - quando nessuna cella può più scendere (si ricomincia da 2) -- fig4
     * 
     * Sono considerati opzionali ai fini della possibilità di correggere l'esercizio, ma concorrono comunque 
     * al raggiungimento della totalità del punteggio:
     * - scorporamento via delegazione di tutti gli aspetti che non sono di view in una interfaccia+classe esterna
     * - gestione degli ostacoli (si può evitare di considerarli)
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
