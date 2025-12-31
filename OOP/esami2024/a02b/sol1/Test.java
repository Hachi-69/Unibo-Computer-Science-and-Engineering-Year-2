package a02b.sol1;

import static org.junit.Assert.*;
import java.util.*;

public class Test {

	/*
	 * Implementare l'interfaccia SimpleDB come indicato nel metodo init qui sotto. 
	 * Realizza un DB fatto di tabelle e viste (una vista mosra una parte di una tabella). Si 
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

	private SimpleDB simpleDB;

	@org.junit.Before
	public void init() {
		this.simpleDB = new SimpleDBImpl();
		this.prepareDB();
	}

	private void prepareDB(){
		// prepara un DB di test, fatto di due tabelle, contenenti 3 studenti (101,102,103) e 2 corsi (1001,1002)
		this.simpleDB.createTable("Student");
		this.simpleDB.createTable("Course");
		// aggiunge la tupla 101,Name,Red alla tabella Student, e se simile per gli altri
		this.simpleDB.addTuple("Student", 101, "Name", "Red");
		this.simpleDB.addTuple("Student", 101, "Year", 2020);
		this.simpleDB.addTuple("Student", 101, "Course", 1001);
		this.simpleDB.addTuple("Student", 102, "Name", "White");
		this.simpleDB.addTuple("Student", 102, "Year", 2021);
		this.simpleDB.addTuple("Student", 102, "Course", 1002);
		this.simpleDB.addTuple("Student", 103, "Name", "Green");
		this.simpleDB.addTuple("Student", 103, "Year", 2020);
		this.simpleDB.addTuple("Student", 103, "Course", 1002);
		this.simpleDB.addTuple("Course", 1001, "Name", "OOP");
		this.simpleDB.addTuple("Course", 1002, "Name", "SISOP");
	}

	@org.junit.Test
	public void testTable() {
		// verifica sugli id delle due tabelle
		assertEquals(Set.of(101, 102, 103), this.simpleDB.ids("Student"));
		assertEquals(Set.of(1001, 1002), this.simpleDB.ids("Course"));
		// verifica sulle descrizioni della tabelle studente (nell'id 101)
		assertEquals(Set.of("Name", "Year", "Course"), this.simpleDB.values("Student", 101).keySet());
		// verifica su certe tuple della tabella student
		assertEquals("Red", this.simpleDB.values("Student", 101).get("Name"));
		assertEquals(2021, this.simpleDB.values("Student", 102).get("Year"));
		// verifica sulle descrizioni della tabelle course (nell'id 1001)
		assertEquals(Set.of("Name"), this.simpleDB.values("Course", 1001).keySet());
		// verifica su certa tupla della tabella course
		assertEquals("SISOP", this.simpleDB.values("Course", 1002).get("Name"));
	}

	@org.junit.Test
	public void testViewBySingleDescription() {
		// si aggiunge una vista Student_Name, che per ogni studente riporta solo il nome
		// è come student, ma mostra solo i dati su "Name"
		this.simpleDB.createViewOfSingleDescription("Student_Name", "Student", "Name");
		assertEquals(Set.of(101, 102, 103), this.simpleDB.ids("Student_Name"));
		// l'unica informazione per l'id 101 è che il nome è Red, e simile per gli altri
		assertEquals(Map.of("Name", "Red"), this.simpleDB.values("Student_Name", 101));
		assertEquals(Map.of("Name", "White"), this.simpleDB.values("Student_Name", 102));
		assertEquals(Map.of("Name", "Green"), this.simpleDB.values("Student_Name", 103));
	}

	@org.junit.Test
	public void testViewBySingleId() {
		// si aggiunge una vista Student_101, che riporta solo i dati dello studente 101
		this.simpleDB.createViewOfSingleId("Student_101", "Student", 101);
		assertEquals(Set.of(101), this.simpleDB.ids("Student_101"));
		assertEquals(Map.of("Name", "Red", "Year", 2020, "Course", 1001), this.simpleDB.values("Student_101", 101));
	}


	@org.junit.Test
	public void testViewOfView() {
		// si combinano due viste, creando una vista Student_101_Name, che riporta solo il nome dello studente 101
		this.simpleDB.createViewOfSingleDescription("Student_Name", "Student", "Name");
		this.simpleDB.createViewOfSingleId("Student_101_Name", "Student_Name", 101);
		assertEquals(Set.of(101), this.simpleDB.ids("Student_101_Name"));
		assertEquals(Map.of("Name", "Red"), this.simpleDB.values("Student_101_Name", 101));
	}
}