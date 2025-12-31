package a05.e1;

import static org.junit.Assert.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test {

	/*
	 * Implementare l'interfaccia SkippingFactory come indicato nel metodo init qui sotto. 
	 * Realizza una factory per dei Skipping che sarebbero dei trasformatori  da supplier (fornitori) 
	 * di elementi a supplier (fornitori) di elementi -- l'unica differenza introdotta è che qualche
	 * elemento dell'input non viene prodotto nell'output. Si leggano i commenti all'interfaccia fornita 
	 * e specialmente i test qui sotto per i dettagli.
	 * 
	 * Sono considerati opzionali ai fini della possibilità di correggere
	 * l'esercizio, ma concorrono comunque al raggiungimento della totalità del
	 * punteggio:
	 * 
	 * - far passare tutti i test (ossia, nella parte obbligatoria è sufficiente 
	 * che passino tutti i test -- oltre i due che già passano -- qui sotto tranne uno a piacimento)
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

	private SkippingFactory factory;

	@org.junit.Before
	public void init() {
		// this.factory = new SkippingFactoryImpl();
	}

	// utility che produce una lista dei primi n valori prodotti dal supplier
    public static <T> List<T> toList(int limit, Supplier<T> supplier){
		return Stream.generate(supplier).limit(limit).toList();
	}

    // utility che crea un supplier da uno stream
	public static <T> Supplier<T> fromStream(Stream<T> stream){
        var it = stream.iterator();
		return it :: next;
	}

	// utility che crea un supplier che ciclicamente produce i valori di una lista
	public static <T> Supplier<T> repetitive(List<T> list){
        return fromStream(Stream.generate(() -> list).flatMap(l -> l.stream()));
	}
	
	@org.junit.Test
	public void testElement() {
		// uno skipping che salta tutte le occorrenza del "++"
		var skipping = this.factory.skipElement("++");
		// un supplier generato da uno stream, che produrrà via via "", "+", "++",...
		var stream = Stream.iterate("", s -> s + "+");
		var inputSup = fromStream(stream);
		// calcolo il supplier in uscita, che dovrà produrre via via "", "+", "+++", "++++"
		var outputSup = skipping.produce(inputSup);
		// verifico attraverso assert successive
		assertEquals("", outputSup.get());
		assertEquals("+", outputSup.get());
		assertEquals("+++", outputSup.get());
		assertEquals("++++", outputSup.get());

		// stesso test di cui sopra, ma più conciso (come usato nei test successivi)
		var sup2 = skipping.produce(fromStream(Stream.iterate("", s -> s + "+")));
		assertEquals(
			List.of("", "+", "+++", "++++", "+++++"),
			toList(5, skipping.produce(sup2))); // controllo solo i primi 5
	}

	@org.junit.Test
	public void testElement2() {
		// analogo test, ma saltando tutti gli zeri in un supplier che fornisce 0,1,2,0,1,2,0,1,2...
		var skipping = this.factory.skipElement(0);
		
		var inputSup = repetitive(List.of(0,1,2));
		var outputSup = skipping.produce(inputSup);
		// verifico attraverso assert successive
		assertEquals(1, outputSup.get().intValue());
		assertEquals(2, outputSup.get().intValue());
		assertEquals(1, outputSup.get().intValue());
		assertEquals(2, outputSup.get().intValue());

		// versione concisa
		assertEquals(
			List.of(1,2,1,2,1,2,1,2),
			toList(8, skipping.produce(repetitive(List.of(0,1,2)))));

	}

	@org.junit.Test
	public void testPredicate() {
		var skipping = this.factory.<Integer>skipByPredicate(i -> i % 3 == 0);
		// si skippano i numeri divisibile per tre
		
		assertEquals(
			List.of(1,2,4,5,7,8,10,11),
			toList(8, skipping.produce(fromStream(Stream.iterate(0, i -> i + 1)))));
	}

	@org.junit.Test
	public void testConsecutiveDuplicates() {
		var skipping = this.factory.skipConsecutiveDuplicates();
		// si skippano le duplicazioni successive, ad esempio:
		// input: 10,20,0,0,30,10, 10,20,0,0,30,10, 10,20,0,0,30,10...
		// output: 10,20,0,30,10, 20,0,30,10, ...
		
		assertEquals(
			List.of(10, 20, 0, 30, 10, 20, 0, 30),
			toList(8, skipping.produce(repetitive(List.of(10,20,0, 0, 30, 10)))));
	}

	@org.junit.Test
	public void testSkipAndRetain() {
		var skipping = this.factory.<Integer>skipAndRetain(2, 4);
		// si skippano 2 elementi e se ne tengono 4, se ne skippano 2 e se ne tengono 4, eccetera, ad esempio:
		// input: 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,...
		// output: 2,3,4,5,8,9,10,11,14,15,...
		
		assertEquals(
			List.of(2,3,4,5,8,9,10,11,14,15),
			toList(10, skipping.produce(fromStream(Stream.iterate(0, i -> i + 1)))));
	}

	@org.junit.Test
	public void testSkipUntilSumReaches() {
		var skipping = this.factory.skipUntilSumReaches(30);
		// si skippano elementi finché la loro somma non diventa maggiore di 30
		// input: 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,...
		// output: 8, 11, 14, 16, 18
		// infatti (0+1+2+3+4+5+6+7+8 >= 30, 9+10+11 >= 30, 12+13+14 >= 30, 15+16 >= 30,...)
		
		assertEquals(
			List.of(8, 11, 14, 16, 18),
			toList(5, skipping.produce(fromStream(Stream.iterate(0, i -> i + 1)))));
	}
}	
