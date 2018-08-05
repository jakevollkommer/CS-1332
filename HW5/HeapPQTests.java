import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by sbaek67 on 2/14/2017.
 */
public class HeapPQTests {

    private static final int TIMEOUT = 200;
    private HeapInterface<Integer> maxHeap;
    private PriorityQueueInterface<Integer> maxPriorityQueue;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<Integer>();
        maxPriorityQueue = new MaxPriorityQueue<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testHeapAddSimple() {
        Integer[] arr = new Integer[11];

        assertTrue(maxHeap.isEmpty());
        maxHeap.add(100);
        arr[1] = 100;
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(1, maxHeap.size());

        assertFalse(maxHeap.isEmpty());
        maxHeap.add(99);
        arr[2] = 99;
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(2, maxHeap.size());

        maxHeap.add(98);
        arr[3] = 98;
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(3, maxHeap.size());

        maxHeap.add(97);
        arr[4] = 97;
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(4, maxHeap.size());

        maxHeap.add(96);
        maxHeap.add(95);
        maxHeap.add(94);
        maxHeap.add(93);
        maxHeap.add(92);
        maxHeap.add(91);

        arr[5] = 96;
        arr[6] = 95;
        arr[7] = 94;
        arr[8] = 93;
        arr[9] = 92;
        arr[10] = 91;

        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(10, maxHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testHeapAddResize() {
        assertTrue(maxHeap.isEmpty());
        Integer[] array1 = new Integer[11];
        Integer[] array2 = new Integer[11 * 2 + 1];
        Integer[] array3 = new Integer[(11 * 2 + 1) * 2 + 1];
        int j = 100;
        for (int i = 1; i < 11; i++) {
            array1[i] = j;
            array2[i] = j;
            array3[i] = j;
            maxHeap.add(j);
            j--;
            assertArrayEquals(array1, maxHeap.getBackingArray());
            assertEquals(11, maxHeap.getBackingArray().length);
            assertEquals(i, maxHeap.size());
        }
        assertFalse(maxHeap.isEmpty());
        assertArrayEquals(array1, maxHeap.getBackingArray());
        assertEquals(11, maxHeap.getBackingArray().length);
        assertEquals(10, maxHeap.size());

        for (int i = 11; i < 11 * 2 + 1; i++) {
            array2[i] = j;
            array3[i] = j;
            maxHeap.add(j);
            j--;
            assertArrayEquals(array2, maxHeap.getBackingArray());
            assertEquals(23, maxHeap.getBackingArray().length);
            assertEquals(i, maxHeap.size());
        }
        assertArrayEquals(array2, maxHeap.getBackingArray());
        assertEquals(23, maxHeap.getBackingArray().length);
        assertEquals(22, maxHeap.size());

        for (int i = 23; i < (11 * 2 + 1) * 2 + 1; i++) {
            array3[i] = j;
            maxHeap.add(j);
            j--;
            assertArrayEquals(array3, maxHeap.getBackingArray());
            assertEquals(47, maxHeap.getBackingArray().length);
            assertEquals(i, maxHeap.size());
        }
        assertArrayEquals(array3, maxHeap.getBackingArray());
        assertEquals(47, maxHeap.getBackingArray().length);
        assertEquals(46, maxHeap.size());
    }

    /**
     * helper method that populates Integer[] with given variable argument
     * so that I don't have to manually populate everything each time!
     * tested separately, but feel free to test it on your own
     *
     * ex)
     * Integer[] arr = new Integer[11];
     * arr[1] = 2;   arr[2] = 100;    arr[3] = 200;
     *
     * arr is same as getArray(2, 100, 200);
     *
     * @param arr
     * @return
     */
    private Integer[] getArray(Integer... arr) {
        int size = 11;
        while (size - 1 < arr.length) {
            size = size * 2 + 1;
        }
        Integer[] temp = new Integer[size];
        for (int i = 0; i < arr.length; i++) {
            temp[i + 1] = arr[i];
        }
        return temp;
    }

    @Test(timeout = TIMEOUT)
    public void testHeapAddUpHeap() {
        assertTrue(maxHeap.isEmpty());
        Integer[] arr = new Integer[11];
        assertArrayEquals(arr, maxHeap.getBackingArray());

        maxHeap.add(50);
        arr = getArray(50);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertFalse(maxHeap.isEmpty());

        maxHeap.add(55);
        arr = getArray(55, 50);
        assertArrayEquals(arr, maxHeap.getBackingArray());

        maxHeap.add(60);
        arr = getArray(60, 50, 55);
        assertArrayEquals(arr, maxHeap.getBackingArray());

        maxHeap.add(65);
        arr = getArray(65, 60, 55, 50);
        assertArrayEquals(arr, maxHeap.getBackingArray());

        maxHeap.add(70);
        arr = getArray(70, 65, 55, 50, 60);
        assertArrayEquals(arr, maxHeap.getBackingArray());

        maxHeap.add(75);
        arr = getArray(75, 65, 70, 50, 60, 55);
        assertArrayEquals(arr, maxHeap.getBackingArray());

        maxHeap.add(80);
        arr = getArray(80, 65, 75, 50, 60, 55, 70);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(11, maxHeap.getBackingArray().length);

        maxHeap.add(5);
        maxHeap.add(10);
        maxHeap.add(15);
        maxHeap.add(20);
        arr = getArray(80, 65, 75, 50, 60, 55, 70, 5, 10, 15, 20);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(23, maxHeap.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testHeapAddNull() {
        assertTrue(maxHeap.isEmpty());
        maxHeap.add(100);
        maxHeap.add(200);
        assertFalse(maxHeap.isEmpty());
        assertEquals(2, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);
        maxHeap.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapClear() {
        assertTrue(maxHeap.isEmpty());

        for (int i = 1; i < 46; i++) {
            maxHeap.add(i);
            assertEquals(i, maxHeap.size());
            assertEquals(i < 11 ? 11 : i < 23 ? 23 : 47, maxHeap.getBackingArray().length);
        }
        assertEquals(45, maxHeap.size());
        assertEquals(47, maxHeap.getBackingArray().length);
        assertFalse(maxHeap.isEmpty());

        maxHeap.clear();
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
        assertArrayEquals(getArray(), maxHeap.getBackingArray());
        assertEquals(11, maxHeap.getBackingArray().length);
    }

    @Test
    public void testHeapPropertyRandom() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            set.add((int) (Math.random() * Integer.MAX_VALUE));
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            maxHeap.add(iterator.next());
        }
        Comparable[] arr = maxHeap.getBackingArray();
        for (int i = 2; i <= maxHeap.size(); i++) {
            assertTrue(arr[i].compareTo(arr[i / 2]) < 0);
            if (i * 2 + 1 < maxHeap.getBackingArray().length) {
                if (arr[i * 2] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2]) > 0);
                }
                if (arr[i * 2 + 1] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2 + 1]) > 0);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testHeapRemoveSimple() {
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
        maxHeap.add(70);
        maxHeap.add(55);
        maxHeap.add(60);
        maxHeap.add(45);
        maxHeap.add(40);
        maxHeap.add(50);
        maxHeap.add(30);
        Integer[] arr = getArray(70, 55, 60, 45, 40, 50, 30);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(7, maxHeap.size());

        assertEquals((Integer) 70, maxHeap.remove());
        arr = getArray(60, 55, 50, 45, 40, 30);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(6, maxHeap.size());

        assertEquals((Integer) 60, maxHeap.remove());
        arr = getArray(55, 45, 50, 30, 40);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(5, maxHeap.size());

        assertEquals((Integer) 55, maxHeap.remove());
        arr = getArray(50, 45, 40, 30);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(4, maxHeap.size());

        assertEquals((Integer) 50, maxHeap.remove());
        arr = getArray(45, 30, 40);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(3, maxHeap.size());

        assertEquals((Integer) 45, maxHeap.remove());
        arr = getArray(40, 30);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(2, maxHeap.size());

        assertEquals((Integer) 40, maxHeap.remove());
        arr = getArray(30);
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(1, maxHeap.size());
        assertFalse(maxHeap.isEmpty());

        assertEquals((Integer) 30, maxHeap.remove());
        arr = getArray();
        assertArrayEquals(arr, maxHeap.getBackingArray());
        assertEquals(0, maxHeap.size());
        assertTrue(maxHeap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testHeapRemoveMany() {
        ArrayList<Integer> list = new ArrayList<Integer>(10000);
        for (int i = 1; i <= 10000; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            maxHeap.add(list.get(i));
        }

        for (int i = 10000; i > 0; i--) {
            assertEquals(i, maxHeap.size());
            assertEquals((Integer) i, maxHeap.remove());
        }
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());

        assertEquals(12287, maxHeap.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapProperty() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            set.add((int) (Math.random() * Integer.MAX_VALUE));
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            maxHeap.add(iterator.next());
        }
        Comparable[] arr = maxHeap.getBackingArray();
        for (int i = 2; i <= maxHeap.size(); i++) {
            assertTrue(arr[i].compareTo(arr[i / 2]) < 0);
            if (i * 2 + 1 < maxHeap.getBackingArray().length) {
                if (arr[i * 2] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2]) > 0);
                }
                if (arr[i * 2 + 1] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2 + 1]) > 0);
                }
            }
        }
        for (int i = 0; i < 5000; i++) {
            maxHeap.remove();
        }
        arr = maxHeap.getBackingArray();
        for (int i = 2; i <= maxHeap.size(); i++) {
            assertTrue(arr[i].compareTo(arr[i / 2]) < 0);
            if (i * 2 + 1 < maxHeap.getBackingArray().length) {
                if (arr[i * 2] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2]) > 0);
                }
                if (arr[i * 2 + 1] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2 + 1]) > 0);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPQEnqueueSimple() {

        Integer[] arr = new Integer[11];

        assertTrue(maxPriorityQueue.isEmpty());
        maxPriorityQueue.enqueue(100);
        arr[1] = 100;
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(1, maxPriorityQueue.size());

        assertFalse(maxPriorityQueue.isEmpty());
        maxPriorityQueue.enqueue(99);
        arr[2] = 99;
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(2, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(98);
        arr[3] = 98;
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(3, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(97);
        arr[4] = 97;
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(4, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(96);
        maxPriorityQueue.enqueue(95);
        maxPriorityQueue.enqueue(94);
        maxPriorityQueue.enqueue(93);
        maxPriorityQueue.enqueue(92);
        maxPriorityQueue.enqueue(91);

        arr[5] = 96;
        arr[6] = 95;
        arr[7] = 94;
        arr[8] = 93;
        arr[9] = 92;
        arr[10] = 91;

        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(10, maxPriorityQueue.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPQEnqueueNull() {
        assertTrue(maxPriorityQueue.isEmpty());
        maxPriorityQueue.enqueue(100);
        maxPriorityQueue.enqueue(200);
        assertFalse(maxPriorityQueue.isEmpty());
        assertEquals(2, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);
        maxPriorityQueue.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testPQClear() {
        assertTrue(maxPriorityQueue.isEmpty());

        for (int i = 1; i < 46; i++) {
            maxPriorityQueue.enqueue(i);
            assertEquals(i, maxPriorityQueue.size());
            assertEquals(i < 11 ? 11 : i < 23 ? 23 : 47, maxPriorityQueue.getBackingHeap().getBackingArray().length);
        }
        assertEquals(45, maxPriorityQueue.size());
        assertEquals(47, maxPriorityQueue.getBackingHeap().getBackingArray().length);
        assertFalse(maxPriorityQueue.isEmpty());

        maxPriorityQueue.clear();
        assertTrue(maxPriorityQueue.isEmpty());
        assertEquals(0, maxPriorityQueue.size());
        assertArrayEquals(getArray(), maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);
    }

    @Test
    public void testPQPropertyRandom() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            set.add((int) (Math.random() * Integer.MAX_VALUE));
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            maxPriorityQueue.enqueue(iterator.next());
        }
        Comparable[] arr = maxPriorityQueue.getBackingHeap().getBackingArray();
        for (int i = 2; i <= maxPriorityQueue.size(); i++) {
            assertTrue(arr[i].compareTo(arr[i/2]) < 0);
            if (i * 2 + 1 < maxPriorityQueue.getBackingHeap().getBackingArray().length) {
                if (arr[i * 2] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2]) > 0);
                }
                if (arr[i * 2 + 1] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2 + 1]) > 0);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPQRemoveSimple() {
        assertTrue(maxPriorityQueue.isEmpty());
        assertEquals(0, maxPriorityQueue.size());
        maxPriorityQueue.enqueue(70);
        maxPriorityQueue.enqueue(55);
        maxPriorityQueue.enqueue(60);
        maxPriorityQueue.enqueue(45);
        maxPriorityQueue.enqueue(40);
        maxPriorityQueue.enqueue(50);
        maxPriorityQueue.enqueue(30);
        Integer[] arr = getArray(70,55,60,45,40,50,30);
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(7, maxPriorityQueue.size());

        assertEquals((Integer) 70, maxPriorityQueue.dequeue());
        arr = getArray(60,55,50,45,40,30);
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(6, maxPriorityQueue.size());

        assertEquals((Integer) 60, maxPriorityQueue.dequeue());
        arr = getArray(55,45,50,30,40);
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(5, maxPriorityQueue.size());

        assertEquals((Integer) 55, maxPriorityQueue.dequeue());
        arr = getArray(50,45,40,30);
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(4, maxPriorityQueue.size());

        assertEquals((Integer) 50, maxPriorityQueue.dequeue());
        arr = getArray(45,30,40);
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(3, maxPriorityQueue.size());

        assertEquals((Integer) 45, maxPriorityQueue.dequeue());
        arr = getArray(40,30);
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(2, maxPriorityQueue.size());

        assertEquals((Integer) 40, maxPriorityQueue.dequeue());
        arr = getArray(30);
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(1, maxPriorityQueue.size());
        assertFalse(maxPriorityQueue.isEmpty());

        assertEquals((Integer) 30, maxPriorityQueue.dequeue());
        arr = getArray();
        assertArrayEquals(arr, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(0, maxPriorityQueue.size());
        assertTrue(maxPriorityQueue.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testPQRemoveMany() {
        ArrayList<Integer> list = new ArrayList<Integer>(10000);
        for (int i = 1; i <= 10000; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            maxPriorityQueue.enqueue(list.get(i));
        }

        for (int i = 10000; i > 0; i--) {
            assertEquals(i, maxPriorityQueue.size());
            assertEquals((Integer) i, maxPriorityQueue.dequeue());
        }
        assertTrue(maxPriorityQueue.isEmpty());
        assertEquals(0, maxPriorityQueue.size());

        assertEquals(12287, maxPriorityQueue.getBackingHeap().getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testPQProperty() {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            set.add((int) (Math.random() * Integer.MAX_VALUE));
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            maxPriorityQueue.enqueue(iterator.next());
        }
        Comparable[] arr = maxPriorityQueue.getBackingHeap().getBackingArray();
        for (int i = 2; i <= maxPriorityQueue.size(); i++) {
            assertTrue(arr[i].compareTo(arr[i/2]) < 0);
            if (i * 2 + 1 < maxPriorityQueue.getBackingHeap().getBackingArray().length) {
                if (arr[i * 2] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2]) > 0);
                }
                if (arr[i * 2 + 1] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2 + 1]) > 0);
                }
            }
        }
        for (int i = 0; i < 5000; i++) {
            maxPriorityQueue.dequeue();
        }
        arr = maxPriorityQueue.getBackingHeap().getBackingArray();
        for (int i = 2; i <= maxPriorityQueue.size(); i++) {
            assertTrue(arr[i].compareTo(arr[i/2]) < 0);
            if (i * 2 + 1 < maxPriorityQueue.getBackingHeap().getBackingArray().length) {
                if (arr[i * 2] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2]) > 0);
                }
                if (arr[i * 2 + 1] != null) {
                    assertTrue(arr[i].compareTo(arr[i * 2 + 1]) > 0);
                }
            }
        }
    }
}
