import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * HashMapStudentTests
 *
 * These tests are NOT exhaustive.
 * You should definitely write your own.
 *
 * @author Vishal Vijayakumar
 * @version 1.0
 */
public class HashMapTests {

    private HashMap<String, Integer> directory;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        directory = new HashMap<>();
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutNullKey() {
        assertEquals(0, directory.size());

        directory.put(null, 2);
        assertEquals(0, directory.size());
        directory.put(null, 3);
        assertEquals(0, directory.size());
        assertFalse(directory.keySet().contains(null));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutNullValue() {
        assertEquals(0, directory.size());

        directory.put("a2", null);
        assertEquals(0, directory.size());
        directory.put("a3", null);
        assertEquals(0, directory.size());
        assertFalse(directory.values().contains(null));
    }

    @Test(timeout = TIMEOUT)
    public void testPutSimple() {
        assertEquals(0, directory.size());
        MapEntry[] testArr1 = new MapEntry[11];

        assertNull(directory.put("a1", 1));
        testArr1[9] = new MapEntry("a1", 1);
        assertEquals(1, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a2", 2));
        testArr1[10] = new MapEntry("a2", 2);
        assertEquals(2, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a3", 3));
        testArr1[0] = new MapEntry("a3", 3);
        assertEquals(3, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a4", 4));
        testArr1[1] = new MapEntry("a4", 4);
        assertEquals(4, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a5", 5));
        testArr1[2] = new MapEntry("a5", 5);
        assertEquals(5, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a6", 6));
        testArr1[3] = new MapEntry("a6", 6);
        assertEquals(6, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a7", 7));
        testArr1[4] = new MapEntry("a7", 7);
        assertEquals(7, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutResizeSimple() {
        assertEquals(0, directory.size());
        MapEntry[] testArr1 = new MapEntry[11];
        MapEntry[] testArr2 = new MapEntry[23];

        assertNull(directory.put("a1", 1));
        testArr1[9] = new MapEntry("a1", 1);
        assertEquals(1, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a2", 2));
        testArr1[10] = new MapEntry("a2", 2);
        assertEquals(2, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a3", 3));
        testArr1[0] = new MapEntry("a3", 3);
        assertEquals(3, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a4", 4));
        testArr1[1] = new MapEntry("a4", 4);
        assertEquals(4, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a5", 5));
        testArr1[2] = new MapEntry("a5", 5);
        assertEquals(5, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a6", 6));
        testArr1[3] = new MapEntry("a6", 6);
        assertEquals(6, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a7", 7));
        testArr1[4] = new MapEntry("a7", 7);
        assertEquals(7, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());


        testArr2[20] = new MapEntry("a1", 1);
        testArr2[21] = new MapEntry("a2", 2);
        testArr2[22] = new MapEntry("a3", 3);
        testArr2[0] = new MapEntry("a4", 4);
        testArr2[1] = new MapEntry("a5", 5);
        testArr2[2] = new MapEntry("a6", 6);
        testArr2[3] = new MapEntry("a7", 7);

        assertNull(directory.put("a8", 8));
        testArr2[4] = new MapEntry("a8", 8);
        assertEquals(8, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a9", 9));
        testArr2[5] = new MapEntry("a9", 9);
        assertEquals(9, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutReplaceSimple() {
        assertEquals(0, directory.size());

        MapEntry[] testArr = new MapEntry[11];

        assertNull(directory.put("a1", 1));
        assertEquals(1, directory.size());
        testArr[9] = new MapEntry("a1", 1);
        assertArrayEquals(testArr, directory.getTable());

        assertEquals(directory.put("a1", 2), (Integer) 1);
        assertEquals(1, directory.size());
        testArr[9] = new MapEntry("a1", 2);
        assertArrayEquals(testArr, directory.getTable());

        assertNull(directory.put("a2", 3));
        assertEquals(2, directory.size());
        testArr[10] = new MapEntry("a2", 3);
        assertArrayEquals(testArr, directory.getTable());

        assertEquals(directory.put("a1", 10), (Integer) 2);
        assertEquals(2, directory.size());
        testArr[9] = new MapEntry("a1", 10);
        assertArrayEquals(testArr, directory.getTable());

        assertEquals(directory.put("a2", 3), (Integer) 3);
        assertEquals(2, directory.size());
        testArr[10] = new MapEntry("a2", 3);
        assertArrayEquals(testArr, directory.getTable());

        assertEquals(directory.put("a2", 20), (Integer) 3);
        assertEquals(2, directory.size());
        testArr[10] = new MapEntry("a2", 20);
        assertArrayEquals(testArr, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutCollisionResolutionSimple() {
        assertEquals(0, directory.size());
        MapEntry[] testArr1 = new MapEntry[11];
        MapEntry[] testArr2 = new MapEntry[23];

        assertNull(directory.put("a1", 1));
        testArr1[9] = new MapEntry("a1", 1);
        assertEquals(1, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a2", 2));
        testArr1[10] = new MapEntry("a2", 2);
        assertEquals(2, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a3", 3));
        testArr1[0] = new MapEntry("a3", 3);
        assertEquals(3, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a4", 4));
        testArr1[1] = new MapEntry("a4", 4);
        assertEquals(4, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a5", 5));
        testArr1[2] = new MapEntry("a5", 5);
        assertEquals(5, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a6", 6));
        testArr1[3] = new MapEntry("a6", 6);
        assertEquals(6, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a7", 7));
        testArr1[4] = new MapEntry("a7", 7);
        assertEquals(7, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());


        testArr2[20] = new MapEntry("a1", 1);
        testArr2[21] = new MapEntry("a2", 2);
        testArr2[22] = new MapEntry("a3", 3);
        testArr2[0] = new MapEntry("a4", 4);
        testArr2[1] = new MapEntry("a5", 5);
        testArr2[2] = new MapEntry("a6", 6);
        testArr2[3] = new MapEntry("a7", 7);

        assertNull(directory.put("a8", 8));
        testArr2[4] = new MapEntry("a8", 8);
        assertEquals(8, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a9", 9));
        testArr2[5] = new MapEntry("a9", 9);
        assertEquals(9, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a10", 10));
        testArr2[10] = new MapEntry("a10", 10);
        assertEquals(10, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a11", 11));
        testArr2[6] = new MapEntry("a11", 11);
        assertEquals(11, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a12", 12));
        testArr2[7] = new MapEntry("a12", 12);
        assertEquals(12, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a13", 13));
        testArr2[8] = new MapEntry("a13", 13);
        assertEquals(13, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a14", 14));
        testArr2[9] = new MapEntry("a14", 14);
        assertEquals(14, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a15", 15));
        testArr2[15] = new MapEntry("a15", 15);
        assertEquals(15, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutRemovedMarkerSimple() {
        assertEquals(0, directory.size());
        MapEntry[] testArr1 = new MapEntry[11];
        MapEntry[] testArr2 = new MapEntry[23];

        assertNull(directory.put("a1", 1));
        testArr1[9] = new MapEntry("a1", 1);
        assertEquals(1, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a2", 2));
        testArr1[10] = new MapEntry("a2", 2);
        assertEquals(2, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a3", 3));
        testArr1[0] = new MapEntry("a3", 3);
        assertEquals(3, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a4", 4));
        testArr1[1] = new MapEntry("a4", 4);
        assertEquals(4, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a5", 5));
        testArr1[2] = new MapEntry("a5", 5);
        assertEquals(5, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a6", 6));
        testArr1[3] = new MapEntry("a6", 6);
        assertEquals(6, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());

        assertNull(directory.put("a7", 7));
        testArr1[4] = new MapEntry("a7", 7);
        assertEquals(7, directory.size());
        assertEquals(11, directory.getTable().length);
        assertArrayEquals(testArr1, directory.getTable());


        testArr2[20] = new MapEntry("a1", 1);
        testArr2[21] = new MapEntry("a2", 2);
        testArr2[22] = new MapEntry("a3", 3);
        testArr2[0] = new MapEntry("a4", 4);
        testArr2[1] = new MapEntry("a5", 5);
        testArr2[2] = new MapEntry("a6", 6);
        testArr2[3] = new MapEntry("a7", 7);

        assertNull(directory.put("a8", 8));
        testArr2[4] = new MapEntry("a8", 8);
        assertEquals(8, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertNull(directory.put("a9", 9));
        testArr2[5] = new MapEntry("a9", 9);
        assertEquals(9, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertEquals((Integer) 5, directory.remove("a5"));
        assertNull(directory.put("a10", 10));
        testArr2[1] = new MapEntry("a10", 10);
        assertEquals(9, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertEquals((Integer) 7, directory.remove("a7"));
        assertNull(directory.put("a11", 11));
        testArr2[3] = new MapEntry("a11", 11);
        assertEquals(9, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());

        assertEquals((Integer) 9, directory.remove("a9"));
        assertNull(directory.put("a13", 13));
        testArr2[5] = new MapEntry("a13", 13);
        assertEquals(9, directory.size());
        assertEquals(23, directory.getTable().length);
        assertArrayEquals(testArr2, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutRemovedMarkerComplex() {
        assertEquals(0, directory.size());

        directory.put("a1", 1);
        //9
        directory.put("a2", 2);
        //10
        directory.put("a3", 3);
        //0
        directory.put("a4", 4);
        //1
        directory.put("a11", 11);
        //9
        directory.put("a12", 12);
        //10

        assertEquals((Integer) 1, directory.remove("a1"));
        assertEquals((Integer) 11, directory.put("a11", 110));
        assertEquals(5, directory.size());
        assertTrue(directory.getTable()[9].isRemoved());
        assertEquals("a11", directory.getTable()[2].getKey());
        assertEquals((Integer) 110, directory.getTable()[2].getValue());


        directory.remove("a2");
        assertEquals((Integer) 12, directory.put("a12", 120));
        assertEquals(4, directory.size());
        assertTrue(directory.getTable()[10].isRemoved());
        assertEquals("a12", directory.getTable()[3].getKey());
        assertEquals((Integer) 120, directory.getTable()[3].getValue());
    }

    @Test(timeout = TIMEOUT)
    public void testPutForcedResize() {
        directory.put("a3", 3);
        directory.put("a4", 4);
        directory.put("a7", 7);
        directory.put("a1", 1);
        directory.put("a8", 8);
        directory.put("a6", 6);

        assertEquals(11, directory.getTable().length);
        directory.put("a13", 13);
        assertEquals(23, directory.getTable().length);
        assertTrue(directory.keySet().contains("a13"));
    }

    @Test
    public void testAddRandom() {
        int randNum = (int) (Math.random() * 1000) + 1;
        for (int i = 0; i < randNum; i++) {
            directory.put("a"+i, i);
        }

        for (int i = 0; i < randNum; i++) {
            assertTrue(directory.keySet().contains("a"+i));
            assertTrue(directory.values().contains(i));
        }

        assertEquals( randNum, directory.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        assertEquals(0, directory.size());

        directory.put("a1", 1);
        assertEquals(1, directory.size());

        directory.remove(null);
        assertEquals(1, directory.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNonExistent() {
        assertEquals(0, directory.size());

        directory.put("a1", 1);
        directory.put("a2", 2);
        directory.put("a3", 3);

        assertEquals(3, directory.size());

        directory.remove("a9");
        assertEquals(3, directory.size());

        directory.remove("a10");
        assertEquals(3, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        directory.put("a1", 1);
        directory.put("a2", 2);
        directory.put("a3", 3);
        directory.put("a4", 4);
        directory.put("a5", 5);
        directory.put("a6", 6);
        directory.put("a7", 7);

        assertEquals(7, directory.size());

        assertEquals((Integer) 1, directory.remove("a1"));
        assertEquals(6, directory.size());
        assertTrue(directory.getTable()[9].isRemoved());
        assertNotNull(directory.getTable()[9]);

        assertEquals((Integer) 2, directory.remove("a2"));
        assertEquals(5, directory.size());
        assertTrue(directory.getTable()[10].isRemoved());
        assertNotNull(directory.getTable()[10]);

        assertEquals((Integer) 3, directory.remove("a3"));
        assertEquals(4, directory.size());
        assertTrue(directory.getTable()[0].isRemoved());
        assertNotNull(directory.getTable()[0]);

        assertEquals((Integer) 4, directory.remove("a4"));
        assertEquals(3, directory.size());
        assertTrue(directory.getTable()[1].isRemoved());
        assertNotNull(directory.getTable()[1]);

        assertEquals((Integer) 5, directory.remove("a5"));
        assertEquals(2, directory.size());
        assertTrue(directory.getTable()[2].isRemoved());
        assertNotNull(directory.getTable()[2]);

        assertEquals((Integer) 6, directory.remove("a6"));
        assertEquals(1, directory.size());
        assertTrue(directory.getTable()[3].isRemoved());
        assertNotNull(directory.getTable()[3]);

        assertEquals((Integer) 7, directory.remove("a7"));
        assertEquals(0, directory.size());
        assertTrue(directory.getTable()[4].isRemoved());
        assertNotNull(directory.getTable()[4]);
    }

    @Test
    public void testRemoveRandom() {
        assertEquals(0, directory.size());

        int randNum = (int) Math.random() * 1000 + 1;
        for (int i = 0; i < randNum; i++) {
            directory.put("a"+i, i);
        }

        assertEquals(randNum, directory.size());
        for (int i = 0; i < randNum; i++) {
            directory.remove("a"+i);
            assertEquals(randNum - i - 1, directory.size());
        }

        assertEquals(0, directory.size());

        int numNullEntries = 0;
        for (MapEntry mapEntry : directory.getTable()) {
            if (mapEntry != null) {
                numNullEntries++;
            }
        }

        assertTrue(numNullEntries > 0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        directory.put("a1", 1);
        directory.get(null);
        directory.put("a10", 0);
        directory.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNonExistent() {
        directory.put("a1", 1);
        directory.put("a2", 2);
        directory.put("a3", 3);

        directory.get("a4");
        directory.get("a5");
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        directory.put("a1", 1);
        directory.put("a2", 2);
        directory.put("a3", 3);
        directory.put("a4", 4);
        directory.put("a5", 5);
        directory.put("a6", 6);
        directory.put("a7", 7);
        directory.put("a8", 8);
        directory.put("a9", 9);
        directory.put("a10", 10);

        assertEquals((Integer) 1, directory.get("a1"));
        assertEquals((Integer) 2, directory.get("a2"));
        assertEquals((Integer) 3, directory.get("a3"));
        assertEquals((Integer) 4, directory.get("a4"));
        assertEquals((Integer) 5, directory.get("a5"));
        assertEquals((Integer) 6, directory.get("a6"));
        assertEquals((Integer) 7, directory.get("a7"));
        assertEquals((Integer) 8, directory.get("a8"));
        assertEquals((Integer) 9, directory.get("a9"));
        assertEquals((Integer) 10, directory.get("a10"));
    }

    @Test
    public void testGetRandom() {
        int randNum = (int) (Math.random() * 1000) + 1;
        for (int i = 0; i < randNum; i++) {
            directory.put("a"+i, i);
        }

        for (int i = 0; i < randNum; i++) {
            assertEquals((Integer) i, directory.get("a"+i));
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsKeyNull() {
        directory.put("a1", 1);
        directory.containsKey(null);
        directory.put("a2", 2);
        directory.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        directory.put("a1", 1);
        directory.put("a2", 1);
        directory.put("a3", 1);
        directory.put("a4", 1);
        directory.put("a5", 1);
        directory.put("a6", 1);
        directory.put("a7", 1);

        assertTrue(directory.containsKey("a1"));
        assertTrue(directory.containsKey("a2"));
        assertTrue(directory.containsKey("a3"));
        assertTrue(directory.containsKey("a4"));
        assertTrue(directory.containsKey("a5"));
        assertTrue(directory.containsKey("a6"));
        assertTrue(directory.containsKey("a7"));
    }

    @Test
    public void testContainsKeyRandom() {
        int randNum = (int) (Math.random() * 1000) + 1;

        for (int i = 0; i < randNum; i++) {
            directory.put("a"+i, i);
        }

        for (int i = 0; i < randNum; i++) {
            assertTrue(directory.containsKey("a"+i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertEquals(0, directory.size());

        for (int i = 0; i < 1000; i++) {
            directory.put("a"+i, i);
        }

        assertEquals(1000, directory.size());

        directory.clear();

        for (MapEntry mapEntry : directory.getTable()) {
            assertTrue(mapEntry == null);
        }
        assertEquals(0, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSize() {
        int randNum = (int)(Math.random()*1000)+1;

        for (int i = 0; i < randNum; i++) {
            directory.put("a"+i, i);
            assertEquals(i+1, directory.size());
        }

        for (int i = 0; i < randNum; i++) {
            directory.remove("a"+i);
            assertEquals(randNum-i-1, directory.size());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        for (int i = 0; i < 1000; i++) {
            directory.put("a"+i, i);
        }

        for (int i = 0; i < 1000; i++) {
            assertTrue(directory.keySet().contains("a"+i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testValueSet() {
        for (int i = 0; i < 1000; i++) {
            directory.put("a"+i, i);
        }

        for (int i = 0; i < 1000; i++) {
            assertTrue(directory.values().contains(i));
        }
    }
}
