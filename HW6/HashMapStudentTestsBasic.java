import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * HashMapStudentTests
 *
 * These tests are NOT exhaustive.
 * You should definitely write your own.
 *
 * @author CS 1332 TAs and Austin
 * @version 1.0
 */
public class HashMapStudentTestsBasic {

    private HashMap<String, String> directory;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        directory = new HashMap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testput() {
        directory.put(new String("Jonathan"), "Former TA: 1332");
        assertEquals(1, directory.size());
        directory.put(new String("Raymond"), "TA: 1332");
        assertEquals(2, directory.size());
        directory.put(new String("Jonathan"), "TA: 4400");
        assertEquals(2, directory.size());
        directory.put(new String("Ashley"), "TA: 1332");
        assertEquals(3, directory.size());
        directory.put(new String("Mary"), "Professor: 1332");
        assertEquals(4, directory.size());
        directory.put(new String("Monica"), "Professor: 2050");
        assertEquals(5, directory.size());
        directory.put(new String("Bob"), "TA: 1332");
        assertEquals(6, directory.size());
        directory.put(new String("Roy"), "TA: 4400");
        assertEquals(7, directory.size());
        directory.put(new String("Joe"), "TA: 1332");
        assertEquals(8, directory.size());
        directory.put(new String("Sally"), "Professor: 1332");
        assertEquals(9, directory.size());
        directory.put(new String("Fred"), "Professor: 2050");
        assertEquals(10, directory.size());
        directory.put(new String("derF"), "Professor: 2050");
        assertEquals(11, directory.size());
        directory.put(new String("redF"), "Professor: 2050");
        assertEquals(12, directory.size());
        directory.put(new String("erdF"), "Professor: 2050");
        assertEquals(13, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        putStuff();
        assertEquals("TA: 4400", directory.remove(new String("Jonathan")));
        assertFalse(directory.containsKey(new String("Jonathan")));
    }

    @Test(timeout = TIMEOUT)
    public void testPutManyDuplicates() {
        for (int i = 0; i < 1000; i++) {
            directory.put("Austin", "Austin");
        }
        assertEquals(1, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testPutMany() {
        HashMap<Integer, String> directory2 = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            directory2.put(i, "WordsWordsWords");
        }
        assertEquals(1000, directory2.size());
    }

    @Test(timeout = TIMEOUT)
    public void testPutManyRemoveMany() {
        HashMap<Integer, String> directory2 = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            directory2.put(i, "WordsWordsWords");
        }
        assertEquals(1000, directory2.size());
        assertEquals(1535, directory2.getTable().length);
        for (int i = 0; i < 1000; i++) {
            directory2.remove(i);
        }
        assertEquals(0, directory2.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutNullValue() {
        directory.put("Birds the Word", null);
        assertEquals(0, directory.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutNullKey() {
        directory.put(null, "Birds the Word");
        assertEquals(0, directory.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNullKey() {
        directory.get(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNullKey() {
        directory.containsKey(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNullKey() {
        directory.remove(null);
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        putStuff();
        assertEquals(6, directory.size());
        directory.clear();
        assertEquals(0, directory.size());
        assertEquals(11, directory.getTable().length);
    }
//
    @Test(timeout = TIMEOUT)
    public void testGet() {
        putStuff();
        MapEntry<String, String>[] arr = directory.getTable();
        directory.put("Ashley", "TA");
        assertTrue(directory.containsKey("Ashley"));
        assertEquals("TA", directory.get(new String("Ashley")));
    }
//
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetEmpty() {
        assertEquals("TA", directory.get(new String("Ashley")));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetIllegal() {
        directory.get(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        directory.put(new String("Chad"), "TA: 1332");
        directory.put(new String("Ashley"), "TA: 1332");
        assertTrue(directory.containsKey(new String("Chad")));
        assertFalse(directory.containsKey(new String("Austin")));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        putStuff();
        HashSet<String> set = new HashSet<>();
        set.add("Jonathan");
        set.add("Bob");
        set.add("Roy");
        set.add("Joe");
        set.add("Sally");
        set.add("Fred");
        assertEquals(set.size(), directory.keySet().size());
        assertEquals(set, directory.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        putStuff();
        assertEquals(6, directory.values().size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNoSuchElement() {
        putStuff();
        directory.remove("Ronnie");
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNoSuchElement() {
        putStuff();
        directory.get("Ronnie");
    }

    @Test(timeout =  TIMEOUT)
    public void testResizeMakeSmaller() {
        putStuff();
        assertEquals(6, directory.size());
        assertEquals(11, directory.getTable().length);
        directory.resizeBackingTable(9);
        assertEquals(6, directory.size());
        assertEquals(9, directory.getTable().length);

    }
    /**
     * Put a baseline of items to the hash map.
     */
    private void putStuff() {
        directory.put("Jonathan", "TA: 4400");
        directory.put(new String("Bob"), "TA: 1332");
        directory.put(new String("Roy"), "TA: 4400");
        directory.put(new String("Joe"), "TA: 1332");
        directory.put(new String("Sally"), "Professor: 1332");
        directory.put(new String("Fred"), "Professor: 2050");
    }
}
