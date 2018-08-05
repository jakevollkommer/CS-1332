import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
  * Simple test cases for heaps and priority queues.
  * Write your own tests to ensure you cover all edge cases.
  *
  * @author Jered Tupik (jtupik3)
  * @version 1.0
  */
public class HeapPQBasicTests {

    private static final int TIMEOUT = 200;
    private HeapInterface<Integer> maxHeap;
    private PriorityQueueInterface<Integer> maxPriorityQueue;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<>();
        maxPriorityQueue = new MaxPriorityQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testHeap() {
        maxHeap.add(43);
        maxHeap.add(89);
        maxHeap.add(17);
        maxHeap.add(64);
        maxHeap.add(5);

        Integer[] expected = new Integer[11];
        expected[1] = 89;
        expected[2] = 64;
        expected[3] = 17;
        expected[4] = 43;
        expected[5] = 5;
        assertArrayEquals(expected,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());

        assertEquals(new Integer(89), maxHeap.remove());
        assertEquals(new Integer(64), maxHeap.remove());
        assertEquals(3, maxHeap.size());
        assertFalse(maxHeap.isEmpty());
        assertEquals(new Integer(43), maxHeap.remove());
        assertEquals(new Integer(17), maxHeap.remove());
        assertEquals(new Integer(5), maxHeap.remove());
        assertTrue(maxHeap.isEmpty());
        Integer[] finalExpected = new Integer[11];
        assertArrayEquals(finalExpected,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPriorityQueue() {
        maxPriorityQueue.enqueue(43);
        maxPriorityQueue.enqueue(89);
        maxPriorityQueue.enqueue(17);
        maxPriorityQueue.enqueue(64);
        maxPriorityQueue.enqueue(5);

        assertEquals(new Integer(89), maxPriorityQueue.dequeue());
        assertEquals(new Integer(64), maxPriorityQueue.dequeue());
        assertEquals(3, maxPriorityQueue.size());
        assertFalse(maxPriorityQueue.isEmpty());
        assertEquals(new Integer(43), maxPriorityQueue.dequeue());
        assertEquals(new Integer(17), maxPriorityQueue.dequeue());
        assertEquals(new Integer(5), maxPriorityQueue.dequeue());
        assertTrue(maxPriorityQueue.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        for (int i = 0; i < 10; i++) {
            maxHeap.add(i * i * i - 74);
        }

        assertEquals(10, maxHeap.size());
        assertEquals(11, ((MaxHeap<Integer>) maxHeap).getBackingArray().length);

        maxHeap.add(10 * 10 * 10 - 74);

        assertEquals(11, maxHeap.size());
        assertEquals(23, ((MaxHeap<Integer>) maxHeap).getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testAddIncreasingHeap() {
        assertEquals(0, maxHeap.size());

        for (int i = 1; i <= 7; i++) {
            maxHeap.add(i);
            assertEquals(i, maxHeap.size());
        }

        Integer[] expected = {null, 7, 4, 6, 1, 3, 2, 5, null, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddDecreasingHeap() {
        assertEquals(0, maxHeap.size());

        for (int i = 7; i >= 1; i--) {
            maxHeap.add(i);
            assertEquals(8 - i, maxHeap.size());
        }

        Integer[] expected = {null, 7, 6, 5, 4, 3, 2, 1, null, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullHeap() {
        assertEquals(0, maxHeap.size());

        maxHeap.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddIncreasingPriorityQueue() {
        assertEquals(0, maxHeap.size());
        assertEquals(0, maxPriorityQueue.size());

        for (int i = 1; i <= 7; i++) {
            maxHeap.add(i);
            maxPriorityQueue.enqueue(i);
            assertEquals(i, maxPriorityQueue.size());
        }

        Integer[] expected = {null, 7, 4, 6, 1, 3, 2, 5, null, null, null};
        HeapInterface<Integer> heap = maxPriorityQueue.getBackingHeap();

        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddDecreasingPriorityQueue() { 
        assertEquals(0, maxHeap.size());
        assertEquals(0, maxPriorityQueue.size());

        for (int i = 7; i >= 1; i--) {
            maxHeap.add(i);
            maxPriorityQueue.enqueue(i);
            assertEquals(8 - i, maxPriorityQueue.size());
        }

        Integer[] expected = {null, 7, 6, 5, 4, 3, 2, 1, null, null, null};
        HeapInterface<Integer> heap = maxPriorityQueue.getBackingHeap();

        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNullPriorityQueue() {
        assertEquals(0, maxPriorityQueue.size());

        maxPriorityQueue.enqueue(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRandomHeap() {
        assertEquals(0, maxHeap.size());

        java.util.Random rand = new java.util.Random();
        java.util.ArrayList<Integer> nums = new java.util.ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            int num = rand.nextInt() % 1000;
            if (!nums.contains(num)) {
                maxHeap.add(num);
                nums.add(num);
            }
            assertEquals(nums.size(), maxHeap.size());
        }

        java.util.Collections.sort(nums);
        while (nums.size() != 0) {
            int num = nums.remove(nums.size() - 1);
            assertEquals((Integer) num, maxHeap.remove());
            assertEquals(nums.size(), maxHeap.size());
        }

        assertEquals(0, maxHeap.size());
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testRemoveEmptyHeap() {
        assertEquals(0, maxHeap.size());

        maxHeap.remove();
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRandomPriorityQueue() {
        assertEquals(0, maxPriorityQueue.size());

        java.util.Random rand = new java.util.Random();
        java.util.ArrayList<Integer> nums = new java.util.ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            int num = rand.nextInt() % 1000;
            if (!nums.contains(num)) {
                maxPriorityQueue.enqueue(num);
                nums.add(num);
            }
            assertEquals(nums.size(), maxPriorityQueue.size());
        }

        java.util.Collections.sort(nums);
        while (nums.size() != 0) {
            int num = nums.remove(nums.size() - 1);
            assertEquals((Integer) num, maxPriorityQueue.dequeue());
            assertEquals(nums.size(), maxPriorityQueue.size());
        }

        assertEquals(0, maxPriorityQueue.size());
    }

    @Test(timeout = TIMEOUT, expected = java.util.NoSuchElementException.class)
    public void testRemoveEmptyPriorityQueue() {
        assertEquals(0, maxPriorityQueue.size());

        maxPriorityQueue.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testSizeAddHeap() {
        assertEquals(0, maxHeap.size());

        for (int i = 0; i < 1000; i++) {
            maxHeap.add(i);
            assertEquals(i + 1, maxHeap.size());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testSizeRemoveHeap() {
        assertEquals(0, maxHeap.size());

        for (int i = 0; i < 1000; i++) {
            maxHeap.add(i);
            assertEquals(i + 1, maxHeap.size());
        }

        for (int i = 0; i < 1000; i++) {
            maxHeap.remove();
            assertEquals(999 - i, maxHeap.size());
        }

        assertEquals(0, maxHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSizeRandomHeap() {
        assertEquals(0, maxHeap.size());

        java.util.Random rand = new java.util.Random();
        java.util.ArrayList<Integer> nums = new java.util.ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            int num = rand.nextInt() % 1000;
            if (!nums.contains(num)) {
                maxHeap.add(num);
                nums.add(num);
            }
            assertEquals(nums.size(), maxHeap.size());
        }
       
        while (nums.size() != 0) {
            maxHeap.remove();
            nums.remove(0);
            assertEquals(nums.size(), maxHeap.size());
        }
        
        assertEquals(0, maxHeap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSizeAddPriorityQueue() {
        assertEquals(0, maxPriorityQueue.size());

        for (int i = 0; i < 1000; i++) {
            maxPriorityQueue.enqueue(i);
            assertEquals(i + 1, maxPriorityQueue.size());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testSizeRemovePriorityQueue() {
        assertEquals(0, maxPriorityQueue.size());

        for (int i = 0; i < 1000; i++) {
            maxPriorityQueue.enqueue(i);
            assertEquals(i + 1, maxPriorityQueue.size());
        }

        for (int i = 0; i < 1000; i++) {
            maxPriorityQueue.dequeue();
            assertEquals(999 - i, maxPriorityQueue.size());
        }

        assertEquals(0, maxPriorityQueue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSizeRandomPriorityQueue() {
        assertEquals(0, maxPriorityQueue.size());

        java.util.Random rand = new java.util.Random();
        java.util.ArrayList<Integer> nums = new java.util.ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            int num = rand.nextInt() % 1000;
            if (!nums.contains(num)) {
                maxPriorityQueue.enqueue(num);
                nums.add(num);
            }
            assertEquals(nums.size(), maxPriorityQueue.size());
        }
       
        while (nums.size() != 0) {
            maxPriorityQueue.dequeue();
            nums.remove(0);
            assertEquals(nums.size(), maxPriorityQueue.size());
        }
        
        assertEquals(0, maxPriorityQueue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyHeap() {
        assertEquals(true, maxHeap.isEmpty());

        for (int i = 0; i < 1000; i++) {
            maxHeap.add(i);
            assertEquals(false, maxHeap.isEmpty());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyPriorityQueue() {
        assertEquals(true, maxPriorityQueue.isEmpty());

        for (int i = 0; i < 1000; i++) {
            maxPriorityQueue.enqueue(i);
            assertEquals(false, maxPriorityQueue.isEmpty());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testClearHeap() {
        assertEquals(0, maxHeap.size());

        for (int i = 0; i < 1000; i++) {
            maxHeap.add(i);
            assertEquals(i + 1, maxHeap.size());
        }
        
        maxHeap.clear();
        Comparable[] backingArray = maxHeap.getBackingArray();
        
        assertEquals(0, maxHeap.size());
        assertEquals(HeapInterface.INITIAL_CAPACITY, backingArray.length);
    }

    @Test(timeout = TIMEOUT)
    public void testClearPriorityQueue() {
        assertEquals(0, maxPriorityQueue.size());

        for (int i = 0; i < 1000; i++) {
            maxPriorityQueue.enqueue(i);
            assertEquals(i + 1, maxPriorityQueue.size());
        }
        
        maxPriorityQueue.clear();
        
        assertEquals(0, maxPriorityQueue.size());
        assertEquals(0, maxPriorityQueue.getBackingHeap().size());
    }
}