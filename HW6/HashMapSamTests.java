import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by sbaek67 on 2/21/2017.
 * @version 1.0
 */
public class HashMapSamTests {

    private HashMap<Integer, String> directory;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        directory = new HashMap<Integer, String>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialize() {
        assertEquals(11, directory.getTable().length);
        assertEquals(0, directory.size());
        MapEntry<Integer, String>[] arr = new MapEntry[11];
        assertArrayEquals(arr, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testInitializeCustom() {
        assertEquals(1, (new HashMap<Integer, String>(1)).getTable().length);
        assertEquals(100, (new HashMap<Integer, String>(100)).getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testPutSimple() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(1, "a01"));
        arr[1] = new MapEntry<Integer, String>(1, "a01");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertNull(directory.put(10, "a10"));
        arr[10] = new MapEntry<Integer, String>(10, "a10");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());

        assertNull(directory.put(11, "a11"));
        arr[0] = new MapEntry<Integer, String>(11, "a11");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(3, directory.size());

        assertNull(directory.put(-2, "a-2"));
        arr[2] = new MapEntry<Integer, String>(-2, "a-2");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(4, directory.size());

        assertNull(directory.put(25, "a25"));
        arr[3] = new MapEntry<Integer, String>(25, "a25");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(5, directory.size());

        assertNull(directory.put(-15, "a-15"));
        arr[4] = new MapEntry<Integer, String>(-15, "a-15");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(6, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testPutReplaceValue() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(1, "a01"));
        arr[1] = new MapEntry<Integer, String>(1, "a01");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertNull(directory.put(5, "a05"));
        arr[5] = new MapEntry<Integer, String>(5, "a05");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());

        assertEquals("a05", directory.put(5, "replaced"));
        arr[5] = new MapEntry<Integer, String>(5, "replaced");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());

        assertEquals("a01", directory.put(1, "a01"));
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutKeyNull() {
        directory.put(111, "hello!");
        directory.put(null, "hello");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPutValueNull() {
        directory.put(111, "there!");
        directory.put(111, null);
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(0, "a00"));
        arr[0] = new MapEntry<Integer, String>(0, "a00");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertNull(directory.put(5, "a05"));
        arr[5] = new MapEntry<Integer, String>(5, "a05");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());

        assertNull(directory.put(20, "a20"));
        arr[9] = new MapEntry<Integer, String>(20, "a20");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(3, directory.size());

        directory.resizeBackingTable(11 * 2 + 1);
        arr = new MapEntry[11 * 2 + 1];
        arr[0] = new MapEntry<Integer, String>(0, "a00");
        arr[5] = new MapEntry<Integer, String>(5, "a05");
        arr[20] = new MapEntry<Integer, String>(20, "a20");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(3, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testPutResize() {
        MapEntry<Integer, String>[] arr = new MapEntry[95]; // ((11*2+1)*2+1)*2+1 == 95

        for (int i = 0; i < 60; i++) {
            arr[i] = new MapEntry<Integer, String>(i, "aa" + i);
            directory.put(i, "aa" + i);
        }
        assertEquals(60, directory.size());
        assertArrayEquals(arr, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testPutRandom() {
        for (int i = 0; i < 10000; i++) {
            int ind = (int) (Math.random() * Integer.MAX_VALUE / 2) - Integer.MAX_VALUE;
            directory.put(ind, "hello!");
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPutQuad() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(0, "first"));
        arr[0] = new MapEntry<Integer, String>(0, "first");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertNull(directory.put(11, "second"));
        arr[1] = new MapEntry<Integer, String>(11, "second");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());

        assertNull(directory.put(22, "third"));
        arr[4] = new MapEntry<Integer, String>(22, "third");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(3, directory.size());

        assertNull(directory.put(33, "fourth"));
        arr[9] = new MapEntry<Integer, String>(33, "fourth");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(4, directory.size());

        assertNull(directory.put(44, "fifth"));
        arr[5] = new MapEntry<Integer, String>(44, "fifth");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(5, directory.size());

        assertNull(directory.put(55, "sixth"));
        arr[3] = new MapEntry<Integer, String>(55, "sixth");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(6, directory.size());

        assertNull(directory.put(1, "seventh"));
        arr[2] = new MapEntry<Integer, String>(1, "seventh");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(7, directory.size());

        assertNull(directory.put(66, "eighth"));
        assertEquals(11 * 2 + 1, directory.getTable().length);
        assertEquals(8, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testPutRemoveQuad() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(0, "first"));
        arr[0] = new MapEntry<Integer, String>(0, "first");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertNull(directory.put(11, "second"));
        arr[1] = new MapEntry<Integer, String>(11, "second");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());

        assertNull(directory.put(22, "third"));
        arr[4] = new MapEntry<Integer, String>(22, "third");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(3, directory.size());

        assertNull(directory.put(33, "fourth"));
        arr[9] = new MapEntry<Integer, String>(33, "fourth");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(4, directory.size());

        assertNull(directory.put(44, "fifth"));
        arr[5] = new MapEntry<Integer, String>(44, "fifth");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(5, directory.size());

        assertNull(directory.put(55, "sixth"));
        arr[3] = new MapEntry<Integer, String>(55, "sixth");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(6, directory.size());

        assertNull(directory.put(1, "seventh"));
        arr[2] = new MapEntry<Integer, String>(1, "seventh");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(7, directory.size());

        assertEquals("first", directory.remove(0));
        assertEquals("second", directory.remove(11));
        assertEquals("third", directory.remove(22));
        assertEquals("fourth", directory.remove(33));
        assertEquals("fifth", directory.remove(44));
        assertEquals("sixth", directory.remove(55));
        assertEquals(1, directory.size());

        assertNull(directory.put(0, "hi!"));
        assertEquals(2, directory.size());
        assertEquals(11, directory.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveOne() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(1, "a01"));
        arr[1] = new MapEntry<Integer, String>(1, "a01");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertEquals("a01", directory.remove(1));
        assertEquals(0, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSimple() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(1, "a01"));
        arr[1] = new MapEntry<Integer, String>(1, "a01");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertNull(directory.put(10, "a10"));
        arr[10] = new MapEntry<Integer, String>(10, "a10");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());

        assertNull(directory.put(11, "a11"));
        arr[0] = new MapEntry<Integer, String>(11, "a11");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(3, directory.size());

        assertNull(directory.put(-2, "a-2"));
        arr[2] = new MapEntry<Integer, String>(-2, "a-2");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(4, directory.size());

        assertNull(directory.put(25, "a25"));
        arr[3] = new MapEntry<Integer, String>(25, "a25");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(5, directory.size());

        assertNull(directory.put(-15, "a-15"));
        arr[4] = new MapEntry<Integer, String>(-15, "a-15");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(6, directory.size());

        assertEquals("a-15", directory.remove(-15));
        assertEquals(5, directory.size());

        assertEquals("a-2", directory.remove(-2));
        assertEquals(4, directory.size());

        assertEquals("a11", directory.remove(11));
        assertEquals(3, directory.size());

        assertEquals("a01", directory.remove(1));
        assertEquals(2, directory.size());

        assertEquals("a25", directory.remove(25));
        assertEquals(1, directory.size());

        assertEquals("a10", directory.remove(10));
        assertEquals(0, directory.size());

        assertArrayEquals(arr, directory.getTable());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(1, "a01"));
        arr[1] = new MapEntry<Integer, String>(1, "a01");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        directory.remove(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAdd() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(1, "a01"));
        arr[1] = new MapEntry<Integer, String>(1, "a01");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertNull(directory.put(10, "a10"));
        arr[10] = new MapEntry<Integer, String>(10, "a10");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(2, directory.size());

        assertNull(directory.put(11, "a11"));
        arr[0] = new MapEntry<Integer, String>(11, "a11");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(3, directory.size());

        assertNull(directory.put(4, "a04"));
        arr[4] = new MapEntry<Integer, String>(4, "a04");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(4, directory.size());

        assertNull(directory.put(25, "a25"));
        arr[3] = new MapEntry<Integer, String>(25, "a25");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(5, directory.size());

        assertNull(directory.put(-16, "a-15"));
        arr[5] = new MapEntry<Integer, String>(-16, "a-15");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(6, directory.size());

        assertEquals("a-15", directory.remove(-16));
        assertArrayEquals(arr, directory.getTable());
        assertEquals(5, directory.size());

        assertEquals("a01", directory.remove(1));
        assertArrayEquals(arr, directory.getTable());
        assertEquals(4, directory.size());
        assertTrue(directory.getTable()[1].isRemoved());

        assertNull(directory.put(1, "second"));
        arr[1] = new MapEntry<Integer, String>(1, "second");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(5, directory.size());
        assertFalse(directory.getTable()[1].isRemoved());

        assertEquals("a10", directory.remove(10));
        assertEquals(4, directory.size());
        assertTrue(directory.getTable()[10].isRemoved());

        assertEquals("a04", directory.remove(4));
        assertEquals(3, directory.size());
        assertTrue(directory.getTable()[4].isRemoved());

        assertNull(directory.put(22, "hello!"));
        arr[4] = new MapEntry<Integer, String>(22, "hello!");
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNotContain() {
        directory.put(100, "hello!");
        directory.put(123, "hello!");
        directory.put(132, "hello!");
        directory.put(111, "hello!");

        directory.remove(100);
        directory.remove(111);
        directory.remove(99);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(1, "a01"));
        arr[1] = new MapEntry<Integer, String>(1, "a01");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertEquals("a01", directory.get(1));
        directory.get(null);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        assertNull(directory.put(1, "a01"));
        assertEquals(1, directory.size());

        assertNull(directory.put(10, "a10"));
        assertEquals(2, directory.size());

        assertNull(directory.put(11, "a11"));
        assertEquals(3, directory.size());

        assertNull(directory.put(4, "a04"));
        assertEquals(4, directory.size());

        assertNull(directory.put(25, "a25"));
        assertEquals(5, directory.size());

        assertNull(directory.put(-15, "a-15"));
        assertEquals(6, directory.size());

        assertEquals("a-15", directory.get(-15));
        assertEquals("a25", directory.get(25));
        assertEquals("a04", directory.get(4));
        assertEquals("a11", directory.get(11));
        assertEquals("a10", directory.get(10));
        assertEquals("a01", directory.get(1));
        assertEquals(6, directory.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNotContain() {
        assertNull(directory.put(1, "a01"));
        assertEquals(1, directory.size());

        assertNull(directory.put(10, "a10"));
        assertEquals(2, directory.size());

        assertNull(directory.put(11, "a11"));
        assertEquals(3, directory.size());

        assertNull(directory.put(4, "a04"));
        assertEquals(4, directory.size());

        assertNull(directory.put(25, "a25"));
        assertEquals(5, directory.size());

        assertNull(directory.put(-15, "a-15"));
        assertEquals(6, directory.size());

        assertEquals("a-15", directory.get(-15));
        assertEquals("a25", directory.get(25));
        assertEquals("a04", directory.get(4));
        assertEquals("a11", directory.get(11));
        assertEquals("a10", directory.get(10));
        assertEquals("a01", directory.get(1));
        assertEquals(6, directory.size());

        directory.get(111);
    }

    @Test(timeout = TIMEOUT)
    public void testGetMany() {
        for (int i = 0; i < 1000; i++) {
            assertNull(directory.put(i * i + i, i + "hi!"));
        }
        assertEquals(1000, directory.size());
        for (int i = 0; i < 1000; i++) {
            assertEquals(i + "hi!", directory.get(i * i + i));
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        MapEntry<Integer, String>[] arr = new MapEntry[11];

        assertNull(directory.put(1, "a01"));
        arr[1] = new MapEntry<Integer, String>(1, "a01");
        assertArrayEquals(arr, directory.getTable());
        assertEquals(1, directory.size());

        assertEquals("a01", directory.get(1));
        directory.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        assertNull(directory.put(1, "a01"));
        assertEquals(1, directory.size());

        assertNull(directory.put(10, "a10"));
        assertEquals(2, directory.size());

        assertNull(directory.put(11, "a11"));
        assertEquals(3, directory.size());

        assertNull(directory.put(4, "a04"));
        assertEquals(4, directory.size());

        assertNull(directory.put(25, "a25"));
        assertEquals(5, directory.size());

        assertNull(directory.put(-15, "a-15"));
        assertEquals(6, directory.size());

        assertTrue(directory.containsKey(1));
        assertTrue(directory.containsKey(10));
        assertTrue(directory.containsKey(11));
        assertTrue(directory.containsKey(4));
        assertTrue(directory.containsKey(25));
        assertTrue(directory.containsKey(-15));
        assertFalse(directory.containsKey(2));
        assertFalse(directory.containsKey(3));
        assertFalse(directory.containsKey(5));
        assertFalse(directory.containsKey(6));
        assertEquals(6, directory.size());
    }

    @Test(timeout = TIMEOUT)
    public void testContainsMany() {
        for (int i = -1000; i < 1000; i++) {
            assertNull(directory.put(i * 2, i + "hi!"));
        }
        assertEquals(2000, directory.size());
        for (int i = -2000; i < 2000; i++) {
            if (i % 2 == 0) {
                assertTrue(directory.containsKey(i));
            } else {
                assertFalse(directory.containsKey(i));
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        for (int i = 0; i < 1000; i++) {
            directory.put((int) (Math.random() * Integer.MIN_VALUE), "oh no!");
        }
        assertTrue(directory.size() > 900);
        directory.clear();
        assertEquals(0, directory.size());
        assertEquals(11, directory.getTable().length);
        MapEntry<Integer, String>[] arr = new MapEntry[11];
        assertArrayEquals(arr, directory.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        for (int i = 0; i < 1000; i++) {
            assertNull(directory.put(i * 2, "hello!"));
        }
        assertEquals(1000, directory.size());
        Set<Integer> set = directory.keySet();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                assertTrue(set.contains(i));
            } else {
                assertFalse(set.contains(i));
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testValueList() {
        for (int i = 0; i < 1000; i++) {
            assertNull(directory.put(i, (i * 2) + "hello!"));
        }
        assertEquals(1000, directory.size());
        List<String> list = directory.values();
        for (int i = 0; i < 1000; i++) {
            assertEquals((i * 2) + "hello!", list.get(i));
        }
    }
}
