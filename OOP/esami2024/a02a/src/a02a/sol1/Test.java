package a02a.sol1;

import static org.junit.Assert.*;
import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia SpreadRow come indicato nel metodo init qui sotto. 
	 * Realizza una specie di "foglio excel" (Spredsheet) fatto da una unica riga. Si 
	 * leggano i commenti all'interfaccia fornita e ai test qui sotto per i dettagli.
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
	 * Si tolga il commento dal metodo initFactory.
	 * 
	 * Indicazioni di punteggio:
	 * - correttezza della parte obbligatoria (e assenza di difetti al codice): 10 punti
	 * - correttezza della parte opzionale: 3 punti (ulteriore metodo della factory)
	 * - qualità della soluzione: 4 punti (per buon design)
	 */

	private SpreadRow spreadRow;

	@org.junit.Before
	public void initFactory() {
		this.spreadRow = new SpreadRowImpl(5); // 5 celle
	}

	@org.junit.Test
	public void testInitial() {
		// uno SpreadRow con tutte celle vuote: _,_,_,_,_
		assertEquals(5, this.spreadRow.size());
		assertTrue(this.spreadRow.isEmpty(0));
		assertTrue(this.spreadRow.isEmpty(1));
		assertTrue(this.spreadRow.isEmpty(2));
		assertTrue(this.spreadRow.isEmpty(3));
		assertTrue(this.spreadRow.isEmpty(4));
		assertFalse(this.spreadRow.isFormula(0));
		assertFalse(this.spreadRow.isNumber(0));
	}

	@org.junit.Test
	public void testAddNumbers() {
		// uno SpreadRow con valori 10,20,30 nelle prime tre celle: 10,20,30,_,_
		assertEquals(5, this.spreadRow.size());
		this.spreadRow.putNumber(0, 10);
		this.spreadRow.putNumber(1, 20);
		this.spreadRow.putNumber(2, 30);
		assertFalse(this.spreadRow.isEmpty(0));
		assertFalse(this.spreadRow.isEmpty(1));
		assertFalse(this.spreadRow.isEmpty(2));
		assertTrue(this.spreadRow.isEmpty(3));
		assertTrue(this.spreadRow.isEmpty(4));
		assertTrue(this.spreadRow.isNumber(0));
		assertTrue(this.spreadRow.isNumber(1));
		assertTrue(this.spreadRow.isNumber(2));
		assertEquals(List.of(Optional.of(10), Optional.of(20), Optional.of(30), Optional.empty(), Optional.empty()),
			this.spreadRow.computeValues());
	}

	@org.junit.Test
	public void testSumOfTwo() {
		// SpreadRow: _,10,20,_,#1+#2
		assertEquals(5, this.spreadRow.size());
		this.spreadRow.putNumber(1, 10);
		this.spreadRow.putNumber(2, 20);
		this.spreadRow.putSumOfTwoFormula(4, 1, 2);
		assertTrue(this.spreadRow.isFormula(4));
		assertEquals(List.of(Optional.empty(), Optional.of(10), Optional.of(20), Optional.empty(), Optional.of(30)),
			this.spreadRow.computeValues());
		// SpreadRow: _,11,20,_,#1+#2
		this.spreadRow.putNumber(1, 11);
		assertEquals(List.of(Optional.empty(), Optional.of(11), Optional.of(20), Optional.empty(), Optional.of(31)),
			this.spreadRow.computeValues());	
	}

	@org.junit.Test
	public void testMultiply() {
		// SpreadRow: -1,10,20,30,#0*#2*#3
		assertEquals(5, this.spreadRow.size());
		this.spreadRow.putNumber(0, -1);
		this.spreadRow.putNumber(1, 10);
		this.spreadRow.putNumber(2, 20);
		this.spreadRow.putNumber(3, 30);
		this.spreadRow.putMultiplyElementsFormula(4, Set.of(0, 2, 3));
		assertTrue(this.spreadRow.isFormula(4));
		assertEquals(List.of(Optional.of(-1), Optional.of(10), Optional.of(20), Optional.of(30), Optional.of(-600)),
			this.spreadRow.computeValues());
		// SpreadRow: 1,10,20,30,#0*#2*#3
		this.spreadRow.putNumber(0, 1);
		assertEquals(List.of(Optional.of(1), Optional.of(10), Optional.of(20), Optional.of(30), Optional.of(600)),
			this.spreadRow.computeValues());
	}

	@org.junit.Test
	public void testCombined() {
		// SpreadRow: 10,20,#0+#1,#1+#2,#2*#3
		assertEquals(5, this.spreadRow.size());
		this.spreadRow.putNumber(0, 10);
		this.spreadRow.putNumber(1, 20);
		this.spreadRow.putSumOfTwoFormula(2, 0, 1); // 30
		this.spreadRow.putSumOfTwoFormula(3, 1, 2); // 50
		this.spreadRow.putMultiplyElementsFormula(4, Set.of(2, 3)); // 1500
		assertEquals(List.of(Optional.of(10), Optional.of(20), Optional.of(30), Optional.of(50), Optional.of(1500)),
			this.spreadRow.computeValues());
	}
}