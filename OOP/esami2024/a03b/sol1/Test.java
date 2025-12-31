package a03b.sol1;

import static org.junit.Assert.*;
import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia DependencyArrayFactory come indicato nel metodo init qui sotto. 
	 * Realizza una factory per dei DependencyArray che sarebbero array nei quali alcuni suoi elementi
	 * potrebbero non essere scrivibili, e/o potrebbero essere vincolati ad avere un valore che dipende 
	 * da quello di altri, ad esempio l'ultimo elemento potrebbe sempre essere la somma dei precedenti. Si 
	 * leggano i commenti all'interfaccia fornita e specialmente i test qui sotto per i dettagli.
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

	private DependencyArrayFactory factory;

	@org.junit.Before
	public void init() {
		this.factory = new DependencyArrayFactoryImpl();
	}

	@org.junit.Test
	public void testImmutable() {
		// un DependecnyArray che corrisponde ad un semplice array immutabile
		var array = this.factory.immutable(List.of(10,20,30));
		assertEquals(3, array.size());
		assertEquals(10, array.read(0).intValue());
		assertEquals(20, array.read(1).intValue());
		assertEquals(30, array.read(2).intValue());
		assertThrows(UnsupportedOperationException.class, () -> array.write(0, 100));
		assertThrows(UnsupportedOperationException.class, () -> array.write(1, 100));
		assertThrows(UnsupportedOperationException.class, () -> array.write(2, 100));
		assertEquals(List.of(10,20,30), array.elements());
	}

	@org.junit.Test
	public void testMutable() {
		// un DependencyArray che corrisponde ad un semplice array mutabile
		var array = this.factory.mutable(List.of(10,20,30));
		assertEquals(3, array.size());
		assertEquals(10, array.read(0).intValue());
		assertEquals(20, array.read(1).intValue());
		assertEquals(30, array.read(2).intValue());
		assertEquals(List.of(10,20,30), array.elements());
		array.write(0, 11);
		assertEquals(11, array.read(0).intValue());
		assertEquals(List.of(11,20,30), array.elements());
		array.write(2, 31);
		assertEquals(List.of(11,20,31), array.elements());
	}

	@org.junit.Test
	public void testWithSomeOfElementsAtTheEnd() {
		// un DependencyArray mutabile, ma che in fondo ha un elemento che è la somma dei precedenti
		var array = this.factory.withSumOfElementsAtTheEnd(List.of(10,20,30));
		assertEquals(4, array.size());
		assertEquals(10, array.read(0).intValue());
		assertEquals(20, array.read(1).intValue());
		assertEquals(30, array.read(2).intValue());
		assertEquals(60, array.read(3).intValue()); // somma dei precedenti
		assertEquals(List.of(10,20,30, 60), array.elements());
		// cambio il primo, l'ultimo è automaticamente cambiato, e così via...
		array.write(0, 11);
		assertEquals(List.of(11,20,30, 61), array.elements());
		assertEquals(11, array.read(0).intValue());
		array.write(2, 31);
		assertEquals(List.of(11,20,31,62), array.elements());
		// l'ultimo, ossia la somma, non è modificabile
		assertThrows(UnsupportedOperationException.class, () -> array.write(3, 0));
	}

	@org.junit.Test
	public void testChangeOneToRandom() {
		var start = this.factory.mutable(List.of("10","20","30"));
		// un DependencyArray simile a start, ma dove in posizione 1 si legge un valore random diverso ogni volta preso da "19","20","21"
		var array = this.factory.clonedWithOneRandom(start, 1, List.of("19", "20", "21"));
		assertEquals(3, array.size());
		assertEquals("10", array.read(0)); 
		assertEquals("30", array.read(2));
		// leggendo 100 volte dalla posizione 1, capiterà di leggere "19", "20", "21"
		Set<String> randomResults = new HashSet<>();
		for (int i=0; i<100; i++){
			randomResults.add(array.read(1));
		}
		assertEquals(Set.of("19", "20", "21"), randomResults);
		// la posizione 0 (e la 2) sono scrivibili
		array.write(0, "9");
		var list = array.elements();
		assertEquals(3, list.size());
		assertEquals("9", list.get(0));
		assertEquals("30", list.get(2));
		assertTrue(Set.of("19", "20", "21").contains(list.get(1)));
		// la posizione 1 non è scrivibile
		assertThrows(UnsupportedOperationException.class, () -> array.write(1, "0"));
	}

	@org.junit.Test
	public void testWithAddedProduct() {
		var start = this.factory.mutable(List.of(10, 2 ,3));
		// un DependencyArray simile a start, ma dove si aggiunge in fondo un elemento che è il prodotto dei precedenti
		var array = this.factory.clonedWithAddedProduct(start);
		assertEquals(4, array.size());
		assertEquals(10, array.read(0).intValue());
		assertEquals(2, array.read(1).intValue());
		assertEquals(3, array.read(2).intValue());
		assertEquals(60, array.read(3).intValue());
		assertEquals(List.of(10,2,3, 60), array.elements());
		array.write(0, -10);
		assertEquals(List.of(-10,2,3, -60), array.elements());
		assertThrows(UnsupportedOperationException.class, () -> array.write(3, 0));
		// riapplico il metodo creando un nuovo array, array2
		var array2 = this.factory.clonedWithAddedProduct(array);
		assertEquals(List.of(-10,2,3, -60, 3600), array2.elements());
	}
}