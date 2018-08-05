import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class StacksQueuesTests {
    private ArrayQueue<Integer> aqi;
    private ArrayStack<Integer> asi;
    private LinkedQueue<Integer> lqi;
    private LinkedStack<Integer> lsi;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        aqi = new ArrayQueue<>();
        asi = new ArrayStack<>();
        lqi = new LinkedQueue<>();
        lsi = new LinkedStack<>();
    }
    /********************************************
     * Array Queue Tests, including exceptions
     * Tests will fail if enqueue(data) doesn't work
     ********************************************/
    @Test(timeout = TIMEOUT)
    public void testAQIEnqueue() {
        assertTrue(aqi.isEmpty());
        for (int i = 0; i < 11; i++) {
            aqi.enqueue(i);
            assertEquals(i + 1, aqi.size());
        }
        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, aqi.getBackingArray());

        for (int j = 0; j < 11; j++) {
            aqi.enqueue(j);
            assertEquals(12 + j, aqi.size());
        }
        Integer[] expected2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1, 2, 3, 4,
                5, 6, 7, 8, 9, 10, null};
        assertArrayEquals(expected2, aqi.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAQIDequeue() {
        assertTrue(aqi.isEmpty());
        for (int i = 0; i < 11; i++) {
            aqi.enqueue(i);
            assertEquals(i + 1, aqi.size());
        }
        aqi.enqueue(11);
        assertEquals(12, aqi.size());
        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, null,
                null, null, null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, aqi.getBackingArray());

        int j = 0;
        while(!aqi.isEmpty()) {
            Integer k = aqi.dequeue();
            assertEquals(expected[j], k);
            assertEquals(11 - j, aqi.size());
            j++;
        }

        assertArrayEquals(new Integer[QueueInterface.INITIAL_CAPACITY * 2 + 1],
                aqi.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAQICircular() {
        for (int i = 0; i < 11; i++) {
            aqi.enqueue(i);
            assertEquals(i + 1, aqi.size());
        }
        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, aqi.getBackingArray());
        aqi.dequeue();
        aqi.dequeue();
        assertEquals(9, aqi.size());
        aqi.enqueue(11);
        aqi.enqueue(12);

        Integer[] expected2 = {11, 12, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected2, aqi.getBackingArray());

        assertEquals(new Integer(2), aqi.dequeue());
        aqi.enqueue(13);
        aqi.enqueue(14);
        Integer[] expected3 = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, null,
                null, null, null, null, null, null, null, null, null, null};
        assertArrayEquals(expected3, aqi.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAQIEnqueueThrowsIAENullData() {
        aqi.enqueue(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testAQIDequeueThrowsNSEEEmpty() {
        aqi.dequeue();
    }

    /*************************************************
     * Linked Queue Tests, including exceptions
     * Tests will fail if enqueue(data) doesn't work
     *************************************************/
    @Test(timeout = TIMEOUT)
    public void testLQIEnqueue() {
        assertTrue(lqi.isEmpty());
        for (int i = 0; i < 11; i++) {
            lqi.enqueue(i);
            assertEquals(i + 1, lqi.size());
        }

        LinkedNode<Integer> iter = lqi.getHead();
        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int j = 0; j < lqi.size(); j++) {
            assertEquals(expected[j], iter.getData());
            iter = iter.getNext();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testLQIDequeue() {
        assertTrue(aqi.isEmpty());
        for (int i = 0; i < 11; i++) {
            aqi.enqueue(i);
            assertEquals(i + 1, aqi.size());
        }

        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int j = 0; j < lqi.size(); j++) {
            assertEquals(expected[j], lqi.dequeue());
            assertEquals(10 - j, lqi.size());
        }

        assertNull(lqi.getHead());
        assertNull(lqi.getTail());
        assertEquals(0, lqi.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLQIEnqueueThrowsIAENullData() {
        lqi.enqueue(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testLQIDequeueThrowsNSEEEmpty() {
        lqi.dequeue();
    }

    /**********************************************
     * Array Stack Tests, including exceptions
     * Tests will fail if push(data) doesn't work
     **********************************************/
    @Test(timeout = TIMEOUT)
    public void testASIPush() {
        assertTrue(asi.isEmpty());
        for (int i = 0; i < 11; i++) {
            asi.push(i);
            assertEquals(i + 1, asi.size());
        }

        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, asi.getBackingArray());

        asi.push(11);
        assertEquals(12, asi.size());

        Integer[] expected2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, null,
                null, null, null, null, null, null, null, null, null, null};

        assertArrayEquals(expected2, asi.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testASIPop() {
        assertTrue(asi.isEmpty());
        for (int i = 0; i < 11; i++) {
            asi.push(i);
            assertEquals(i + 1, asi.size());
        }

        Integer[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertArrayEquals(expected, asi.getBackingArray());

        for (int j = 10; j >= 0; j--) {
            assertEquals(expected[j], asi.pop());
            assertEquals(j, asi.size());
        }

        assertArrayEquals(new Integer[11], asi.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testASIPushThrowsIAENullData() {
        asi.push(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testASIPopThrowsNSEEEmpty() {
        asi.pop();
    }

    /***********************************************
     * Linked Stack Tests, including exceptions
     * Tests will fail if push(data) doesn't work
     ***********************************************/
    @Test(timeout = TIMEOUT)
    public void testLSIPush() {
        assertTrue(lsi.isEmpty());
        for (int i = 0; i < 11; i++) {
            lsi.push(i);
            assertEquals(i + 1, lsi.size());
        }

        Integer[] expected = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        LinkedNode<Integer> iter = lsi.getHead();
        for (int j = 0; j < 11; j++) {
            assertEquals(expected[j], iter.getData());
            iter = iter.getNext();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testLSIPop() {
        assertTrue(lsi.isEmpty());
        for (int i = 0; i < 11; i++) {
            lsi.push(i);
            assertEquals(i + 1, lsi.size());
        }

        Integer[] expected = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        for (int j = 0; j < 11; j++) {
            assertEquals(expected[j], lsi.pop());
            assertEquals(10 - j, lsi.size());
        }

        assertNull(lsi.getHead());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLSIPushThrowsIAENullData() {
        lsi.push(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testLSIPopThrowsNSEEEmpty() {
        lsi.pop();
    }
}
