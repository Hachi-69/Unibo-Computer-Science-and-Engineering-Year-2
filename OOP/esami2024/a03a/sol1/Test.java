package a03a.sol1;

import static org.junit.Assert.*;
import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia CellsFactory come indicato nel metodo init qui sotto. 
	 * Realizza una factory per delle "celle" simili a quelle di un foglio di calcolo (come Excel),
	 * che posso contenere valori o operazioni sul valore di altre celle. Si 
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

	private CellsFactory factory;

	@org.junit.Before
	public void init() {
		this.factory = new CellsFactoryImpl();
	}
	
	@org.junit.Test
	public void testMutableValue() {
		// cella di valore 10
		var cell = this.factory.mutableValueCell(10);
		assertTrue(cell.isModifiable());
		assertTrue(cell.dependsFrom().isEmpty());
		assertEquals(10, cell.getResult().intValue());
		// cambiamo il valore in 20
		cell.write(20);
		assertTrue(cell.isModifiable());
		assertTrue(cell.dependsFrom().isEmpty());
		assertEquals(20, cell.getResult().intValue());
	}

	@org.junit.Test
	public void testSumOfTwo() {
		var cell1 = this.factory.mutableValueCell(10);
		var cell2 = this.factory.mutableValueCell(20);
		// cella che conterrà la somma dei valori contenuti in cell1 e cell2
		var sum = this.factory.sumOfTwo(cell1, cell2);
		assertFalse(sum.isModifiable());
		assertEquals(Set.of(cell1, cell2), sum.dependsFrom());
		assertEquals(30, sum.getResult().intValue());
		// se cambio cell1, la somma poi cambia, eccetera...
		cell1.write(11);
		assertEquals(31, sum.getResult().intValue());
		cell2.write(21);
		assertEquals(32, sum.getResult().intValue());
		assertThrows(UnsupportedOperationException.class, () -> sum.write(0));
	}

	@org.junit.Test
	public void testConcatOfThree() {
		var cell1 = this.factory.mutableValueCell("10");
		var cell2 = this.factory.mutableValueCell("20");
		var cell3 = this.factory.mutableValueCell("30");
		// cella che conterrà la concatenazione dei valori contenuti in tre altre celle
		var c = this.factory.concatOfThree(cell1, cell2, cell3);
		assertFalse(c.isModifiable());
		assertEquals(Set.of(cell1, cell2, cell3), c.dependsFrom());
		assertEquals("102030", c.getResult());
		// se cambio cell1, il risultato della concatenazione cambia, eccetera...
		cell1.write("11");
		assertEquals("112030", c.getResult());
		assertThrows(UnsupportedOperationException.class, () -> c.write(""));
	}

	@org.junit.Test
	public void testWithSumOnLast() {
		// crea 4 celle, 3 con valori e poi una con la somma
		var list = this.factory.cellsWithSumOnLast(List.of(10,20,30));
		assertEquals(4, list.size());
		assertTrue(list.get(0).isModifiable());
		assertTrue(list.get(1).isModifiable());
		assertTrue(list.get(2).isModifiable());
		assertFalse(list.get(3).isModifiable());
		assertEquals(Set.of(list.get(0),list.get(1),list.get(2)), list.get(3).dependsFrom());
		assertEquals(10, list.get(0).getResult().intValue());
		assertEquals(20, list.get(1).getResult().intValue());
		assertEquals(30, list.get(2).getResult().intValue());
		assertEquals(60, list.get(3).getResult().intValue());
		list.get(1).write(21);
		assertEquals(61, list.get(3).getResult().intValue());
		assertThrows(UnsupportedOperationException.class, () -> list.get(3).write(0));
	}

	@org.junit.Test
	public void testAddConcatenationOnAll() {
		var cell1 = this.factory.mutableValueCell("10");
		var cell2 = this.factory.mutableValueCell("20");
		var cell3 = this.factory.mutableValueCell("30");
		// crea una lista con 4 celle, 3 sono quelle date, ne aggiunge una con la concatenazione
		var list = this.factory.addConcatenationOnAll(List.of(cell1, cell2, cell3));
		assertEquals(4, list.size());
		assertTrue(list.get(0).isModifiable());
		assertTrue(list.get(1).isModifiable());
		assertTrue(list.get(2).isModifiable());
		assertFalse(list.get(3).isModifiable());
		assertEquals(Set.of(list.get(0),list.get(1),list.get(2)), list.get(3).dependsFrom());
		assertEquals("10", list.get(0).getResult());
		assertEquals("20", list.get(1).getResult());
		assertEquals("30", list.get(2).getResult());
		assertEquals("102030", list.get(3).getResult());
		list.get(1).write("21");
		assertEquals("102130", list.get(3).getResult());
		assertThrows(UnsupportedOperationException.class, () -> list.get(3).write("0s"));
		// list2 ha 5 elementi, opera sui 4 precedenti concatenandoli con una ","
		var list2 = this.factory.addConcatenationOnAll(list);
		assertEquals("102130102130", list2.get(4).getResult());
	}

	@org.junit.Test
	public void testFromReduction() {
		var cell1 = this.factory.mutableValueCell("10");
		var cell2 = this.factory.mutableValueCell("20");
		var cell3 = this.factory.mutableValueCell("30");
		// crea una cella che concatena i risultati di 3 celle
		var cell = this.factory.fromReduction(List.of(cell1, cell2, cell3), String::concat);
		assertFalse(cell.isModifiable());
		assertEquals(Set.of(cell1, cell2, cell3), cell.dependsFrom());
		assertEquals("102030", cell.getResult());
		cell2.write("21");
		assertEquals("102130", cell.getResult());
		assertThrows(UnsupportedOperationException.class, () -> cell.write("0"));
		// crea un'altra cella che concatena con virgola
		var anotherCell = this.factory.fromReduction(List.of(cell1, cell2, cell3, cell), (x, y) -> x + "," + y);
		assertEquals("10,21,30,102130", anotherCell.getResult());
	}

}