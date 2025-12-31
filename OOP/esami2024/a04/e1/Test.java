package a04.e1;

import static org.junit.Assert.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test {

	/*
	 * Implementare l'interfaccia GatheringFactory come indicato nel metodo init qui sotto. 
	 * Realizza una factory per dei Gathering che sarebbero dei trasformatori (via raccoglimento)
	 * da supplier (fornitori) di elementi a supplier (fornitori) di elementi -- essenzialmente,
	 * da iteratore infinito a iteratore infinito. Si leggano i commenti all'interfaccia fornita 
	 * e specialmente i test qui sotto per i dettagli.
	 * 
	 * Sono considerati opzionali ai fini della possibilità di correggere
	 * l'esercizio, ma concorrono comunque al raggiungimento della totalità del
	 * punteggio:
	 * 
	 * - far passare tutti i test (ossia, nella parte obbligatoria è sufficiente 
	 * che passino tutti i test qui sotto tranne uno a piacimento)
	 * - la buona progettazione della soluzione, utilizzando soluzioni progettuali che portino a
	 * codice succinto che evita ripetizioni e sprechi di memoria.
	 * 
	 * Si tolga il commento dal metodo init.
	 * 
	 * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria (e assenza di difetti al codice): 10 punti
	 * - correttezza della parte opzionale: 3 punti (ulteriore metodo della factory)
	 * - qualità della soluzione: 4 punti (per buon design)
	 */

	private GatheringFactory factory;

	@org.junit.Before
	public void init() {
		//this.factory = new GatheringFactoryImpl();
	}

	// utility che produce una lista dei primi n valori prodotto dal supplier
    public static <T> List<T> toList(int limit, Supplier<T> supplier){
		return Stream.generate(supplier).limit(limit).toList();
	}

    // utility che crea un supplier da uno stream
	public static <T> Supplier<T> fromStream(Stream<T> stream){
        var it = stream.iterator();
		return it :: next;
	}
	
	@org.junit.Test
	public void testOne() {
		// un gathering che raccoglie uno alla volta, ossia non fa nulla
		var gathering = this.factory.<String>gatherOne(); 
		// un supplier generato da uno stream, che produrrà via via "", "+", "++",...
		var stream = Stream.iterate("", s -> s + "+");
		var inputSup = fromStream(stream);
		// calcolo il supplier in uscita, che però dovrà fare la stessa cosa: produrre via via "", "+", "++",...
		var outputSup = gathering.produce(inputSup);
		// verifico attraverso assert successive
		assertEquals("", outputSup.get());
		assertEquals("+", outputSup.get());
		assertEquals("++", outputSup.get());
		assertEquals("+++", outputSup.get());
		assertEquals("++++", outputSup.get());

		// stesso test di cui sopra, ma più conciso (come usato nei test successivi)
		var sup2 = gathering.produce(fromStream(Stream.iterate("", s -> s + "+")));
		assertEquals(
			List.of("", "+", "++", "+++", "++++"),
			toList(5, gathering.produce(sup2))); // controllo solo i primi 5
	}
	
	@org.junit.Test
	public void testScanToList() {
		var g = this.factory.<Integer>scanToList(); // liste degli elementi fin qui
		// input supplier: 0,1,2,3,4,...
		var sup = g.produce(fromStream(Stream.iterate(0, i -> i + 1)));
		// output supplier: [0],[0,1],[0,1,2],...
		assertEquals(
			List.of(
				List.of(0), List.of(0, 1), List.of(0,1,2), List.of(0,1,2,3)),
			toList(4, sup));  // controllo solo i primi 4
	}	

	@org.junit.Test
	public void testSlide() {
		var g = this.factory.<Integer>slide(3); // liste degli ultimi 3 elementi
		// input supplier: 0, 10, 20, 30,...
		var sup = g.produce(fromStream(Stream.iterate(0, i -> i + 10)));

		assertEquals(
			List.of(
				List.of(0, 10,20), List.of(10,20,30), List.of(20,30,40), List.of(30,40,50)),
			toList(4, sup));

		var g2 = this.factory.<Integer>slide(2); // liste degli ultimi 2 elementi
		// input supplier: 0, 10, 20, 30,...
		var sup2 = g2.produce(fromStream(Stream.iterate(0, i -> i + 10)));

		assertEquals(
			List.of(
				List.of(0, 10), List.of(10,20), List.of(20,30), List.of(30,40)),
			toList(4, sup2));	
	}	

	
	@org.junit.Test
	public void testPairs() {
		var g = this.factory.<String>pairs(); // coppie degli ultimi 2 elementi
		// input supplier: "", ".", "..", "...", eccetera
		var sup = g.produce(fromStream(Stream.iterate("", s -> s + ".")));

		assertEquals(List.of(
			new Pair<>("", "."), new Pair<>(".",".."), new Pair<>("..","..."), new Pair<>("...","....")), 
			toList(4, sup));
	}	

	@org.junit.Test
	public void testSumLastThree() {
		var g = this.factory.sumLastThree(); // somme degli ultimi 3
		// input supplier: 0, 10, 20, 30,...
		var sup = g.produce(fromStream(Stream.iterate(0, i -> i + 10)));

		assertEquals(
			List.of( 0+10+20, 10+20+30, 20+30+40, 30+40+50), 
			toList(4, sup));
	}	

	@org.junit.Test
	public void testScanAndReduce() {
		var g = this.factory.<Integer>scanAndReduce((s, e) -> s + e); // somma degli elementi fin qui
		// input supplier: 0,1,2,3,4...
		var sup = g.produce(fromStream(Stream.iterate(0, i -> i + 1)));

		assertEquals(
			List.of(0, 0+1, 0+1+2, 0+1+2+3, 0+1+2+3+4),
			toList(5, sup));

		var g2 = this.factory.<Integer>scanAndReduce((s, e) -> s * e); // prodotto degli elementi fin qui
		// input supplier: 1,2,3,4...
		var sup2 = g2.produce(fromStream(Stream.iterate(1, i -> i + 1))); // 1,2,3,4,...

		assertEquals(
			List.of(1, 1*2, 1*2*3, 1*2*3*4, 1*2*3*4*5),
			toList(5, sup2));	
	}	
}