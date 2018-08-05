import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Simple test cases for heaps and priority queues.
 * Write your own tests to ensure you cover all edge cases.
 *
 * @author Vishal Vijayakumar
 * @version 1.0
 */

public class HeapPQStudentTests {
    private static final int TIMEOUT = 200;
    private HeapInterface<Integer> maxHeap;
    private PriorityQueueInterface<Integer> maxPriorityQueue;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<>();
        maxPriorityQueue = new MaxPriorityQueue<>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullHeap() {
        assertTrue(maxHeap.isEmpty());
        maxHeap.add(1);
        assertEquals(1, maxHeap.size());
        maxHeap.add(null);
        assertEquals(1, maxHeap.size());
        assertEquals((Integer) 1, maxHeap.remove());
    }

    @Test(timeout = TIMEOUT)
    public void testAddBasicBestCaseHeap() {
        assertEquals(0, maxHeap.size());

        Integer[] testArr1 = new Integer[11];

        maxHeap.add(100);
        testArr1[1] = 100;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        maxHeap.add(90);
        testArr1[2] = 90;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        maxHeap.add(80);
        testArr1[3] = 80;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        maxHeap.add(70);
        testArr1[4] = 70;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        maxHeap.add(60);
        testArr1[5] = 60;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        maxHeap.add(50);
        testArr1[6] = 50;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        maxHeap.add(40);
        testArr1[7] = 40;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        maxHeap.add(30);
        testArr1[8] = 30;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        maxHeap.add(20);
        testArr1[9] = 20;
        assertArrayEquals(testArr1, maxHeap.getBackingArray());

        assertEquals(11, maxHeap.getBackingArray().length);
        assertEquals(9, maxHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddBasicWorstCaseHeap() {
        assertEquals(0, maxHeap.size());

        maxHeap.add(10);
        assertArrayEquals(new Integer[] {null, 10, null, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(1, maxHeap.size());

        maxHeap.add(20);
        assertArrayEquals(new Integer[] {null, 20, 10, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(2, maxHeap.size());

        maxHeap.add(30);
        assertArrayEquals(new Integer[] {null, 30, 10, 20, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(3, maxHeap.size());

        maxHeap.add(40);
        assertArrayEquals(new Integer[] {null, 40, 30, 20, 10, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(4, maxHeap.size());

        maxHeap.add(50);
        assertArrayEquals(new Integer[] {null, 50, 40, 20, 10, 30, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(5, maxHeap.size());

        maxHeap.add(60);
        assertArrayEquals(new Integer[] {null, 60, 40, 50, 10, 30, 20, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(6, maxHeap.size());

        maxHeap.add(70);
        assertArrayEquals(new Integer[] {null, 70, 40, 60, 10, 30, 20, 50, null, null, null}, maxHeap.getBackingArray());
        assertEquals(7, maxHeap.size());

        maxHeap.add(80);
        assertArrayEquals(new Integer[] {null, 80, 70, 60, 40, 30, 20, 50, 10, null, null}, maxHeap.getBackingArray());
        assertEquals(8, maxHeap.size());

        maxHeap.add(90);
        assertArrayEquals(new Integer[] {null, 90, 80, 60, 70, 30, 20, 50, 10, 40, null}, maxHeap.getBackingArray());
        assertEquals(9, maxHeap.size());

        assertEquals(11, maxHeap.getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testAddResizeBestCaseHeap() {
        assertEquals(0, maxHeap.size());

        Integer[] testArr1 = new Integer[11];
        Integer[] testArr2 = new Integer[23];

        for (int i = 20; i > 10; i--) {
            maxHeap.add(i);
            testArr1[21 - i] = i;
            testArr2[21 - i] = i;
            assertArrayEquals(testArr1, maxHeap.getBackingArray());
            assertEquals(21 - i, maxHeap.size());
        }

        maxHeap.add(4);
        testArr2[11] = 4;
        assertArrayEquals(testArr2, maxHeap.getBackingArray());
        assertEquals(11, maxHeap.size());

        maxHeap.add(3);
        testArr2[12] = 3;
        assertArrayEquals(testArr2, maxHeap.getBackingArray());
        assertEquals(12, maxHeap.size());

        maxHeap.add(2);
        testArr2[13] = 2;
        assertArrayEquals(testArr2, maxHeap.getBackingArray());
        assertEquals(13, maxHeap.size());

        maxHeap.add(1);
        testArr2[14] = 1;
        assertArrayEquals(testArr2, maxHeap.getBackingArray());
        assertEquals(14, maxHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddResizeWorstCaseHeap() {
        assertEquals(0, maxHeap.size());

        maxHeap.add(10);
        assertArrayEquals(new Integer[] {null, 10, null, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(1, maxHeap.size());

        maxHeap.add(20);
        assertArrayEquals(new Integer[] {null, 20, 10, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(2, maxHeap.size());

        maxHeap.add(30);
        assertArrayEquals(new Integer[] {null, 30, 10, 20, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(3, maxHeap.size());

        maxHeap.add(40);
        assertArrayEquals(new Integer[] {null, 40, 30, 20, 10, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(4, maxHeap.size());

        maxHeap.add(50);
        assertArrayEquals(new Integer[] {null, 50, 40, 20, 10, 30, null, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(5, maxHeap.size());

        maxHeap.add(60);
        assertArrayEquals(new Integer[] {null, 60, 40, 50, 10, 30, 20, null, null, null, null}, maxHeap.getBackingArray());
        assertEquals(6, maxHeap.size());

        maxHeap.add(70);
        assertArrayEquals(new Integer[] {null, 70, 40, 60, 10, 30, 20, 50, null, null, null}, maxHeap.getBackingArray());
        assertEquals(7, maxHeap.size());

        maxHeap.add(80);
        assertArrayEquals(new Integer[] {null, 80, 70, 60, 40, 30, 20, 50, 10, null, null}, maxHeap.getBackingArray());
        assertEquals(8, maxHeap.size());

        maxHeap.add(90);
        assertArrayEquals(new Integer[] {null, 90, 80, 60, 70, 30, 20, 50, 10, 40, null}, maxHeap.getBackingArray());
        assertEquals(9, maxHeap.size());

        maxHeap.add(100);
        assertArrayEquals(new Integer[] {null, 100, 90, 60, 70, 80, 20, 50, 10, 40, 30}, maxHeap.getBackingArray());
        assertEquals(10, maxHeap.size());

        assertEquals(11, maxHeap.getBackingArray().length);

        maxHeap.add(110);
        assertEquals(11, maxHeap.size());
        assertEquals(23, maxHeap.getBackingArray().length);
        assertArrayEquals(new Integer[] {null, 110, 100, 60, 70, 90, 20, 50, 10, 40, 30, 80, null, null, null, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());

        maxHeap.add(120);
        assertEquals(12, maxHeap.size());
        assertEquals(23, maxHeap.getBackingArray().length);
        assertArrayEquals(new Integer[] {null, 120, 100, 110, 70, 90, 60, 50, 10, 40, 30, 80, 20, null, null, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testHeapOrderPropertyAddHeap() {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            //add to set to avoid duplicates
            set.add((int) (Math.random() * 10000));
        }

        for (int a : set) {
            maxHeap.add(a);
        }

        for (int i = maxHeap.size(); i >= 2; i--) {
            assertTrue(maxHeap.getBackingArray()[i / 2].compareTo(maxHeap.getBackingArray()[i]) > 0);

            if ((2 * i + 1) <= maxHeap.size()) {
                if (maxHeap.getBackingArray()[2 * i] != null) {
                    assertTrue(maxHeap.getBackingArray()[i].compareTo(maxHeap.getBackingArray()[i * 2]) > 0);
                }

                if (maxHeap.getBackingArray()[2 * i + 1] != null) {
                    assertTrue(maxHeap.getBackingArray()[i].compareTo(maxHeap.getBackingArray()[i * 2 + 1]) > 0);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveEmptyHeap() {
        assertEquals(0, maxHeap.size());
        maxHeap.remove();
        assertEquals(0, maxHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBasicHeap() {
        assertTrue(maxHeap.isEmpty());
        assertEquals(11, maxHeap.getBackingArray().length);

        for (int i = 10; i > 0; i--) {
            maxHeap.add(i);
        }

        assertEquals(10, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 10, maxHeap.remove());
        assertEquals(9, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 9, maxHeap.remove());
        assertEquals(8, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 8, maxHeap.remove());
        assertEquals(7, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 7, maxHeap.remove());
        assertEquals(6, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 6, maxHeap.remove());
        assertEquals(5, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 5, maxHeap.remove());
        assertEquals(4, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 4, maxHeap.remove());
        assertEquals(3, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 3, maxHeap.remove());
        assertEquals(2, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 2, maxHeap.remove());
        assertEquals(1, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertEquals((Integer) 1, maxHeap.remove());
        assertEquals(0, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        assertTrue(maxHeap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveComplexHeap() {
        assertEquals(0, maxHeap.size());
        assertEquals(11, maxHeap.getBackingArray().length);

        for (int i = 1; i <= 10000; i++) {
            maxHeap.add(i);
        }

        assertEquals(10000, maxHeap.size());
        assertEquals(12287, maxHeap.getBackingArray().length);

        for (int i = 10000; i > 0; i--) {
            assertEquals((Integer) i, maxHeap.remove());
            assertEquals(i - 1, maxHeap.size());
            assertEquals(12287, maxHeap.getBackingArray().length);
        }

    }

    @Test(timeout = TIMEOUT)
    public void testHeapOrderPropertyRemoveHeap() {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            //add to set to avoid duplicates
            set.add((int) (Math.random() * 10000));
        }

        for (int a : set) {
            maxHeap.add(a);
        }

        for (int i = 2; i <= maxHeap.size(); i++) {
            maxHeap.remove();
            if (maxHeap.getBackingArray()[i] != null) {
                assertTrue(maxHeap.getBackingArray()[i / 2].compareTo(maxHeap.getBackingArray()[i]) > 0);

                if ((2 * i + 1) <= maxHeap.size()) {
                    if (maxHeap.getBackingArray()[2 * i] != null) {
                        assertTrue(maxHeap.getBackingArray()[i].compareTo(maxHeap.getBackingArray()[i * 2]) > 0);
                    }

                    if (maxHeap.getBackingArray()[2 * i + 1] != null) {
                        assertTrue(maxHeap.getBackingArray()[i].compareTo(maxHeap.getBackingArray()[i * 2 + 1]) > 0);
                    }
                }
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyHeap() {
        assertEquals(0, maxHeap.size());
        assertTrue(maxHeap.isEmpty());

        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);
        maxHeap.add(4);
        maxHeap.add(5);

        assertFalse(maxHeap.isEmpty());

        maxHeap.remove();
        maxHeap.remove();
        maxHeap.remove();
        maxHeap.remove();
        assertFalse(maxHeap.isEmpty());
        maxHeap.remove();

        assertTrue(maxHeap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testSizeHeap() {
        assertEquals(0, maxHeap.size());

        int randLimit = (int) (Math.random() * 10000);

        for (int i = 1; i <= randLimit; i++) {
            maxHeap.add(i);
            assertEquals(i, maxHeap.size());
        }

        for (int i = randLimit; i > 0; i--) {
            maxHeap.remove();
            assertEquals(i - 1, maxHeap.size());
        }

        assertEquals(0, maxHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClearHeap() {
        assertEquals(0, maxHeap.size());

        for (int i = 0; i < 100; i++) {
            maxHeap.add(i);
        }

        assertFalse(maxHeap.isEmpty());

        maxHeap.clear();
        assertArrayEquals(new Integer[] {null, null, null, null, null, null, null, null, null, null, null}, maxHeap.getBackingArray());
        assertTrue(maxHeap.isEmpty());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullPQ() {
        assertTrue(maxPriorityQueue.isEmpty());
        maxPriorityQueue.enqueue(1);
        assertEquals(1, maxPriorityQueue.size());
        maxPriorityQueue.enqueue(null);
        assertEquals(1, maxPriorityQueue.size());
        assertEquals((Integer) 1, maxPriorityQueue.dequeue());
    }

    @Test(timeout = TIMEOUT)
    public void testAddBasicBestCasePQ() {
        assertEquals(0, maxPriorityQueue.size());

        Integer[] testArr1 = new Integer[11];

        maxPriorityQueue.enqueue(100);
        testArr1[1] = 100;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(90);
        testArr1[2] = 90;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(80);
        testArr1[3] = 80;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(70);
        testArr1[4] = 70;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(60);
        testArr1[5] = 60;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(50);
        testArr1[6] = 50;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(40);
        testArr1[7] = 40;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(30);
        testArr1[8] = 30;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(20);
        testArr1[9] = 20;
        assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());

        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);
        assertEquals(9, maxPriorityQueue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddBasicWorstCasePQ() {
        assertEquals(0, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(10);
        assertArrayEquals(new Integer[] {null, 10, null, null, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(1, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(20);
        assertArrayEquals(new Integer[] {null, 20, 10, null, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(2, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(30);
        assertArrayEquals(new Integer[] {null, 30, 10, 20, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(3, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(40);
        assertArrayEquals(new Integer[] {null, 40, 30, 20, 10, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(4, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(50);
        assertArrayEquals(new Integer[] {null, 50, 40, 20, 10, 30, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(5, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(60);
        assertArrayEquals(new Integer[] {null, 60, 40, 50, 10, 30, 20, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(6, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(70);
        assertArrayEquals(new Integer[] {null, 70, 40, 60, 10, 30, 20, 50, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(7, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(80);
        assertArrayEquals(new Integer[] {null, 80, 70, 60, 40, 30, 20, 50, 10, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(8, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(90);
        assertArrayEquals(new Integer[] {null, 90, 80, 60, 70, 30, 20, 50, 10, 40, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(9, maxPriorityQueue.size());

        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testAddResizeBestCasePQ() {
        assertEquals(0, maxPriorityQueue.size());

        Integer[] testArr1 = new Integer[11];
        Integer[] testArr2 = new Integer[23];

        for (int i = 20; i > 10; i--) {
            maxPriorityQueue.enqueue(i);
            testArr1[21 - i] = i;
            testArr2[21 - i] = i;
            assertArrayEquals(testArr1, maxPriorityQueue.getBackingHeap().getBackingArray());
            assertEquals(21 - i, maxPriorityQueue.size());
        }

        maxPriorityQueue.enqueue(4);
        testArr2[11] = 4;
        assertArrayEquals(testArr2, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(11, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(3);
        testArr2[12] = 3;
        assertArrayEquals(testArr2, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(12, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(2);
        testArr2[13] = 2;
        assertArrayEquals(testArr2, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(13, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(1);
        testArr2[14] = 1;
        assertArrayEquals(testArr2, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(14, maxPriorityQueue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddResizeWorstCasePQ() {
        assertEquals(0, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(10);
        assertArrayEquals(new Integer[] {null, 10, null, null, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(1, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(20);
        assertArrayEquals(new Integer[] {null, 20, 10, null, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(2, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(30);
        assertArrayEquals(new Integer[] {null, 30, 10, 20, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(3, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(40);
        assertArrayEquals(new Integer[] {null, 40, 30, 20, 10, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(4, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(50);
        assertArrayEquals(new Integer[] {null, 50, 40, 20, 10, 30, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(5, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(60);
        assertArrayEquals(new Integer[] {null, 60, 40, 50, 10, 30, 20, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(6, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(70);
        assertArrayEquals(new Integer[] {null, 70, 40, 60, 10, 30, 20, 50, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(7, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(80);
        assertArrayEquals(new Integer[] {null, 80, 70, 60, 40, 30, 20, 50, 10, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(8, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(90);
        assertArrayEquals(new Integer[] {null, 90, 80, 60, 70, 30, 20, 50, 10, 40, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(9, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(100);
        assertArrayEquals(new Integer[] {null, 100, 90, 60, 70, 80, 20, 50, 10, 40, 30}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertEquals(10, maxPriorityQueue.size());

        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        maxPriorityQueue.enqueue(110);
        assertEquals(11, maxPriorityQueue.size());
        assertEquals(23, maxPriorityQueue.getBackingHeap().getBackingArray().length);
        assertArrayEquals(new Integer[] {null, 110, 100, 60, 70, 90, 20, 50, 10, 40, 30, 80, null, null, null, null, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());

        maxPriorityQueue.enqueue(120);
        assertEquals(12, maxPriorityQueue.size());
        assertEquals(23, maxPriorityQueue.getBackingHeap().getBackingArray().length);
        assertArrayEquals(new Integer[] {null, 120, 100, 110, 70, 90, 60, 50, 10, 40, 30, 80, 20, null, null, null, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testHeapOrderPropertyAddPQ() {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            //add to set to avoid duplicates
            set.add((int) (Math.random() * 10000));
        }

        for (int a : set) {
            maxPriorityQueue.enqueue(a);
        }

        for (int i = maxPriorityQueue.size(); i >= 2; i--) {
            assertTrue(maxPriorityQueue.getBackingHeap().getBackingArray()[i / 2].compareTo(maxPriorityQueue.getBackingHeap().getBackingArray()[i]) > 0);

            if ((2 * i + 1) <= maxPriorityQueue.size()) {
                if (maxPriorityQueue.getBackingHeap().getBackingArray()[2 * i] != null) {
                    assertTrue(maxPriorityQueue.getBackingHeap().getBackingArray()[i].compareTo(maxPriorityQueue.getBackingHeap().getBackingArray()[i * 2]) > 0);
                }

                if (maxPriorityQueue.getBackingHeap().getBackingArray()[2 * i + 1] != null) {
                    assertTrue(maxPriorityQueue.getBackingHeap().getBackingArray()[i].compareTo(maxPriorityQueue.getBackingHeap().getBackingArray()[i * 2 + 1]) > 0);
                }
            }
        }
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveEmptyPQ() {
        assertEquals(0, maxPriorityQueue.size());
        maxPriorityQueue.dequeue();
        assertEquals(0, maxPriorityQueue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveBasicPQ() {
        assertTrue(maxPriorityQueue.isEmpty());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        for (int i = 10; i > 0; i--) {
            maxPriorityQueue.enqueue(i);
        }

        assertEquals(10, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 10, maxPriorityQueue.dequeue());
        assertEquals(9, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 9, maxPriorityQueue.dequeue());
        assertEquals(8, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 8, maxPriorityQueue.dequeue());
        assertEquals(7, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 7, maxPriorityQueue.dequeue());
        assertEquals(6, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 6, maxPriorityQueue.dequeue());
        assertEquals(5, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 5, maxPriorityQueue.dequeue());
        assertEquals(4, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 4, maxPriorityQueue.dequeue());
        assertEquals(3, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 3, maxPriorityQueue.dequeue());
        assertEquals(2, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 2, maxPriorityQueue.dequeue());
        assertEquals(1, maxPriorityQueue.size());
        assertEquals(11,  maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertEquals((Integer) 1, maxPriorityQueue.dequeue());
        assertEquals(0, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        assertTrue(maxPriorityQueue.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveComplexPQ() {
        assertEquals(0, maxPriorityQueue.size());
        assertEquals(11, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        for (int i = 1; i <= 10000; i++) {
            maxPriorityQueue.enqueue(i);
        }

        assertEquals(10000, maxPriorityQueue.size());
        assertEquals(12287, maxPriorityQueue.getBackingHeap().getBackingArray().length);

        for (int i = 10000; i > 0; i--) {
            assertEquals((Integer) i, maxPriorityQueue.dequeue());
            assertEquals(i - 1, maxPriorityQueue.size());
            assertEquals(12287, maxPriorityQueue.getBackingHeap().getBackingArray().length);
        }

    }

    @Test(timeout = TIMEOUT)
    public void testHeapOrderPropertyRemovePQ() {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            //add to set to avoid duplicates
            set.add((int) (Math.random() * 10000));
        }

        for (int a : set) {
            maxPriorityQueue.enqueue(a);
        }

        for (int i = 2; i <= maxPriorityQueue.size(); i++) {
            maxPriorityQueue.dequeue();
            if (maxPriorityQueue.getBackingHeap().getBackingArray()[i] != null) {
                assertTrue(maxPriorityQueue.getBackingHeap().getBackingArray()[i / 2].compareTo(maxPriorityQueue.getBackingHeap().getBackingArray()[i]) > 0);

                if ((2 * i + 1) <= maxPriorityQueue.size()) {
                    if (maxPriorityQueue.getBackingHeap().getBackingArray()[2 * i] != null) {
                        assertTrue(maxPriorityQueue.getBackingHeap().getBackingArray()[i].compareTo(maxPriorityQueue.getBackingHeap().getBackingArray()[i * 2]) > 0);
                    }

                    if (maxPriorityQueue.getBackingHeap().getBackingArray()[2 * i + 1] != null) {
                        assertTrue(maxPriorityQueue.getBackingHeap().getBackingArray()[i].compareTo(maxPriorityQueue.getBackingHeap().getBackingArray()[i * 2 + 1]) > 0);
                    }
                }
            }
        }
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyPQ() {
        assertEquals(0, maxPriorityQueue.size());
        assertTrue(maxPriorityQueue.isEmpty());

        maxPriorityQueue.enqueue(1);
        maxPriorityQueue.enqueue(2);
        maxPriorityQueue.enqueue(3);
        maxPriorityQueue.enqueue(4);
        maxPriorityQueue.enqueue(5);

        assertFalse(maxPriorityQueue.isEmpty());

        maxPriorityQueue.dequeue();
        maxPriorityQueue.dequeue();
        maxPriorityQueue.dequeue();
        maxPriorityQueue.dequeue();
        assertFalse(maxPriorityQueue.isEmpty());
        maxPriorityQueue.dequeue();

        assertTrue(maxPriorityQueue.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testSizePQ() {
        assertEquals(0, maxPriorityQueue.size());

        int randLimit = (int) (Math.random() * 10000);

        for (int i = 1; i <= randLimit; i++) {
            maxPriorityQueue.enqueue(i);
            assertEquals(i, maxPriorityQueue.size());
        }

        for (int i = randLimit; i > 0; i--) {
            maxPriorityQueue.dequeue();
            assertEquals(i - 1, maxPriorityQueue.size());
        }

        assertEquals(0, maxPriorityQueue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClearPQ() {
        assertEquals(0, maxPriorityQueue.size());

        for (int i = 0; i < 100; i++) {
            maxPriorityQueue.enqueue(i);
        }

        assertFalse(maxPriorityQueue.isEmpty());

        maxPriorityQueue.clear();
        assertArrayEquals(new Integer[] {null, null, null, null, null, null, null, null, null, null, null}, maxPriorityQueue.getBackingHeap().getBackingArray());
        assertTrue(maxPriorityQueue.isEmpty());
    }
}