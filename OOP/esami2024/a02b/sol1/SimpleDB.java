package a02b.sol1;

import java.util.Map;
import java.util.Set;

/**
 * A SimpleDB is a set of tables, each with its name, describing items (persons, courses, ...).
 * Each item has an id and pairs of a description and a value (either a number or a string), like "Name" -> "Mario", "Year" -> "2020" and so on.
 * The DB has also "views", which are subparts of an existing table (or view)
 * The DB is initially empty.
 * Generally, errors when working with the DB (e.g. creating a table that already exists), should not be tested.
 */
public interface SimpleDB {

    /**
     * @param name
     * adds a new empty table with name
     */
    void createTable(String name);

    /**
     * @param tableName for the table to work with
     * @param id for the item to work with
     * @param description
     * @param value
     * adds to the table, and to the item, a new pair of description/value 
     */
    void addTuple(String tableName, int id, String description, Object value);

    /**
     * @param name of the table/view to work with
     * @return its set of ids
     */
    Set<Integer> ids(String name);

    /**
     * @param name of the table/view to work with
     * @param id for the item to work with
     * @return a map associating a description to its value
     */
    Map<String, Object> values(String name, int id);

    /**
     * @param viewName
     * @param originName
     * @param description
     * creates a view named viewName containing the portion of table/view originName including only description
     */
    void createViewOfSingleDescription(String viewName, String originName, String description);

    /**
     * @param viewName
     * @param originName
     * @param id
     * creates a view named viewName containing the portion of table/view originName including only id
     */
    void createViewOfSingleId(String viewName, String originName, int id);
}
