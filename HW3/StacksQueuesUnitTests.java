import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Basic tests for array-based and linked-based queues and stacks
 */
public class StacksQueuesUnitTests {
    private StackInterface<String> arrayStack;
    private StackInterface<String> linkedStack;
    private QueueInterface<String> arrayQueue;
    private QueueInterface<String> linkedQueue;

    public static final int TIMEOUT = 200;

    @Before
    public void SetUp() {
        arrayStack = new ArrayStack<>();
        linkedStack = new LinkedStack<>();
        arrayQueue = new ArrayQueue<>();
        linkedQueue = new LinkedQueue<>();
    }

    /*
    Array Stack Tests
     */
    @Test(timeout = TIMEOUT)
    public void testArrayStack() {
        arrayStack.push("1a");
        arrayStack.push("2a");
        arrayStack.push("3a");
        arrayStack.push("4a"); // 1a 2a 3a 4a
        assertFalse(arrayStack.isEmpty());
        assertEquals(4, arrayStack.size());

        assertEquals("4a", arrayStack.pop());

        assertEquals(3, arrayStack.size());
        assertEquals("3a", arrayStack.pop());

        assertEquals(2, arrayStack.size());
        assertEquals("2a", arrayStack.pop());

        assertEquals(1, arrayStack.size());
        assertEquals("1a", arrayStack.pop());

        assertEquals(0, arrayStack.size());
        assertTrue(arrayStack.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackGrowArray() {
        for (int i = 0; i < StackInterface.INITIAL_CAPACITY; i++) {
            assertEquals(i, arrayStack.size());
            arrayStack.push(i + "a");
        }
        arrayStack.push("11a");
        assertEquals(12, arrayStack.size());

        Object[] arr = new Object[StackInterface.INITIAL_CAPACITY * 2 + 1];
        for (int i = 0; i < StackInterface.INITIAL_CAPACITY + 1; i++) {
            arr[i] = i + "a";
        }
        assertArrayEquals(arr, ((ArrayStack<String>) arrayStack).getBackingArray());
        assertEquals("11a", arrayStack.pop());
        assertEquals(11, arrayStack.size());
        arr[11] = null;
        assertArrayEquals(arr, ((ArrayStack<String>) arrayStack).getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackTime() {
        assertEquals(0, arrayStack.size());
        assertTrue(arrayStack.isEmpty());

        for (int i = 0; i < 500000; i++) {
            assertEquals(i, arrayStack.size());
            arrayStack.push(i + "a");
        }

        for (int i = 500000 - 1; i >= 0; i--) {
            assertEquals(i + "a", arrayStack.pop());
            assertEquals(i, arrayStack.size());
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayStackPushNull() {
        arrayStack.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayStackPopEmpty() {
        assertEquals(0, arrayStack.size());
        assertTrue(arrayStack.isEmpty());
        arrayStack.push("1a");
        assertEquals("1a", arrayStack.pop());
        arrayStack.pop();
    }

    /*
    Linked Stack Tests
     */
    @Test(timeout = TIMEOUT)
    public void testLinkedStack() {
        assertEquals(0, linkedStack.size());
        assertNull(((LinkedStack) linkedStack).getHead());

        linkedStack.push("1a");
        assertEquals(1, linkedStack.size());
        assertEquals("1a", ((LinkedStack) linkedStack).getHead().getData());

        linkedStack.push("2a");
        assertEquals(2, linkedStack.size());
        assertEquals("2a", ((LinkedStack) linkedStack).getHead().getData());

        linkedStack.push("3a");
        assertEquals(3, linkedStack.size());
        assertEquals("3a", ((LinkedStack) linkedStack).getHead().getData());

        linkedStack.push("4a");
        assertEquals(4, linkedStack.size());
        assertEquals("4a", ((LinkedStack) linkedStack).getHead().getData());

        assertFalse(linkedStack.isEmpty());

        assertEquals("4a", linkedStack.pop());
        assertEquals(3, linkedStack.size());
        assertEquals("3a", ((LinkedStack) linkedStack).getHead().getData());

        assertEquals("3a", linkedStack.pop());
        assertEquals(2, linkedStack.size());
        assertEquals("2a", ((LinkedStack) linkedStack).getHead().getData());

        assertEquals("2a", linkedStack.pop());
        assertEquals(1, linkedStack.size());
        assertEquals("1a", ((LinkedStack) linkedStack).getHead().getData());

        assertEquals("1a", linkedStack.pop());
        assertEquals(0, linkedStack.size());
        assertNull(((LinkedStack) linkedStack).getHead());
        assertTrue(linkedStack.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackTime() {
        assertEquals(0, linkedStack.size());
        assertNull(((LinkedStack) linkedStack).getHead());

        for (int i = 0; i < 500000; i++) {
            linkedStack.push(i + "a");
            assertEquals(i + "a", ((LinkedStack) linkedStack).getHead().getData());
            assertEquals(i + 1, linkedStack.size());
        }

        for (int i = 500000 - 1; i >= 0; i--) {
            assertEquals(i + "a", linkedStack.pop());
            assertEquals(i, linkedStack.size());
        }

        assertNull(((LinkedStack) linkedStack).getHead());
        assertTrue(linkedStack.isEmpty());
        assertEquals(0, linkedStack.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedStackPushNull() {
        for (int i = 0; i < 100; i++) {
            linkedStack.push(i + "a");
        }
        assertEquals(100, linkedStack.size());
        for (int i = 0; i < 100; i++) {
            linkedStack.pop();
        }
        assertEquals(0, linkedStack.size());
        assertTrue(linkedStack.isEmpty());
        linkedStack.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedStackPopEmpty() {
        for (int i = 0; i < 100; i++) {
            linkedStack.push(i + "a");
        }
        assertEquals(100, linkedStack.size());
        for (int i = 0; i < 100; i++) {
            linkedStack.pop();
        }
        assertEquals(0, linkedStack.size());
        assertTrue(linkedStack.isEmpty());
        linkedStack.pop();
    }

    /*
    Array Queue Tests
     */
    @Test(timeout = TIMEOUT)
    public void testArrayQueue() {
        assertTrue(arrayQueue.isEmpty());
        assertEquals(0, arrayQueue.size());
        assertArrayEquals(new Object[StackInterface.INITIAL_CAPACITY], ((ArrayQueue) arrayQueue).getBackingArray());

        arrayQueue.enqueue("1a"); //1a
        assertEquals(1, arrayQueue.size());

        arrayQueue.enqueue("2a"); //1a 2a
        assertEquals(2, arrayQueue.size());

        arrayQueue.enqueue("3a"); //1a 2a 3a
        assertEquals(3, arrayQueue.size());

        arrayQueue.enqueue("4a"); //1a 2a 3a 4a
        assertEquals(4, arrayQueue.size());

        assertFalse(arrayQueue.isEmpty());

        assertEquals("1a", arrayQueue.dequeue()); //2a 3a 4a
        assertEquals(3, arrayQueue.size());

        assertEquals("2a", arrayQueue.dequeue()); //3a 4a
        assertEquals(2, arrayQueue.size());

        assertEquals("3a", arrayQueue.dequeue()); //4a
        assertEquals(1, arrayQueue.size());

        assertEquals("4a", arrayQueue.dequeue()); //
        assertEquals(0, arrayQueue.size());
        assertTrue(arrayQueue.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueGrowArray() {
        assertEquals(0, arrayQueue.size());
        assertTrue(arrayQueue.isEmpty());
        Object[] arr = new Object[QueueInterface.INITIAL_CAPACITY * 2 + 1];
        for (int i = 0; i < 23; i++) {
            assertEquals(i, arrayQueue.size());
            arrayQueue.enqueue(i + "a");
            arr[i] = i + "a";
        }
        assertEquals(QueueInterface.INITIAL_CAPACITY * 2 + 1, arrayQueue.size());
        assertArrayEquals(arr, ((ArrayQueue) arrayQueue).getBackingArray());

        assertEquals("0a", arrayQueue.dequeue());
        arr[0] = null;
        assertArrayEquals(arr, ((ArrayQueue) arrayQueue).getBackingArray());

        for (int i = 1; i < 23; i++) {
            assertEquals(i + "a", arrayQueue.dequeue());
        }
        assertTrue(arrayQueue.isEmpty());

        for (int i = 0; i < 23; i++) {
            assertEquals(i, arrayQueue.size());
            arrayQueue.enqueue(i + "a");
            arr[i] = i + "a";
        }
        assertEquals(23, ((ArrayQueue) arrayQueue).getBackingArray().length);
        arrayQueue.enqueue("23a");
        assertEquals(23 * 2 + 1, ((ArrayQueue) arrayQueue).getBackingArray().length);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueCycle() {
        arrayQueue = new ArrayQueue();
        for (int i = 0; i < 10; i++) {
            arrayQueue.enqueue(i + "a");
        }
        assertEquals(10, arrayQueue.size());
        for (int i = 10; i < 100000; i++) {
            arrayQueue.enqueue(i + "a");
            assertEquals((i - 10) + "a", arrayQueue.dequeue());
        }
        assertEquals(10, arrayQueue.size());
        assertEquals(QueueInterface.INITIAL_CAPACITY, ((ArrayQueue) arrayQueue).getBackingArray().length);
        assertNull(((ArrayQueue) arrayQueue).getBackingArray()[10]);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayQueueDequeueEmpty() {
        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY; i++) {
            arrayQueue.enqueue(i + "a");
        }
        assertEquals(QueueInterface.INITIAL_CAPACITY, arrayQueue.size());
        for (int i = 0; i < QueueInterface.INITIAL_CAPACITY; i++) {
            assertEquals(i + "a", arrayQueue.dequeue());
        }
        assertEquals(0, arrayQueue.size());
        assertTrue(arrayQueue.isEmpty());
        arrayQueue.dequeue();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayQueueEnqueueNull() {
        for (int i = 0; i < 100; i++) {
            arrayQueue.enqueue(i + "a");
        }
        arrayQueue.enqueue(null);
    }

    /*
    Linked Queue Tests
     */
    @Test(timeout = TIMEOUT)
    public void testLinkedQueue() {
        assertEquals(0, linkedQueue.size());
        assertTrue(linkedQueue.isEmpty());
        assertNull(((LinkedQueue) linkedQueue).getHead());
        assertNull(((LinkedQueue) linkedQueue).getTail());

        linkedQueue.enqueue("1a"); //1a
        assertEquals(1, linkedQueue.size());
        assertEquals("1a", ((LinkedQueue) linkedQueue).getHead().getData());
        assertEquals("1a", ((LinkedQueue) linkedQueue).getTail().getData());

        linkedQueue.enqueue("2a"); //1a 2a
        assertEquals(2, linkedQueue.size());
        assertEquals("1a", ((LinkedQueue) linkedQueue).getHead().getData());
        assertEquals("2a", ((LinkedQueue) linkedQueue).getTail().getData());

        linkedQueue.enqueue("3a"); //1a 2a 3a
        assertEquals(3, linkedQueue.size());
        assertEquals("1a", ((LinkedQueue) linkedQueue).getHead().getData());
        assertEquals("3a", ((LinkedQueue) linkedQueue).getTail().getData());

        linkedQueue.enqueue("4a"); //1a 2a 3a 4a
        assertEquals(4, linkedQueue.size());
        assertEquals("1a", ((LinkedQueue) linkedQueue).getHead().getData());
        assertEquals("4a", ((LinkedQueue) linkedQueue).getTail().getData());

        assertFalse(linkedQueue.isEmpty());

        assertEquals("1a", linkedQueue.dequeue()); //2a 3a 4a
        assertEquals(3, linkedQueue.size());
        assertEquals("2a", ((LinkedQueue) linkedQueue).getHead().getData());
        assertEquals("4a", ((LinkedQueue) linkedQueue).getTail().getData());

        assertEquals("2a", linkedQueue.dequeue()); //3a 4a
        assertEquals(2, linkedQueue.size());
        assertEquals("3a", ((LinkedQueue) linkedQueue).getHead().getData());
        assertEquals("4a", ((LinkedQueue) linkedQueue).getTail().getData());

        assertEquals("3a", linkedQueue.dequeue()); //4a
        assertEquals(1, linkedQueue.size());
        assertEquals("4a", ((LinkedQueue) linkedQueue).getHead().getData());
        assertEquals("4a", ((LinkedQueue) linkedQueue).getTail().getData());

        assertEquals("4a", linkedQueue.dequeue()); //
        assertEquals(0, linkedQueue.size());
        assertNull(((LinkedQueue) linkedQueue).getHead());
        assertNull(((LinkedQueue) linkedQueue).getTail());

        assertTrue(linkedQueue.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueTime() {
        assertEquals(0, linkedQueue.size());
        assertTrue(linkedQueue.isEmpty());
        assertNull(((LinkedQueue) linkedQueue).getHead());
        assertNull(((LinkedQueue) linkedQueue).getTail());

        linkedQueue.enqueue("0a");
        for (int i = 1; i < 100000; i++) {
            assertEquals(i, linkedQueue.size());
            linkedQueue.enqueue(i + "a");
            assertEquals("0a", ((LinkedQueue) linkedQueue).getHead().getData());
            assertEquals(i + "a", ((LinkedQueue) linkedQueue).getTail().getData());
        }

        for (int i = 0; i < 100000; i++) {
            assertEquals(i + "a", ((LinkedQueue) linkedQueue).getHead().getData());
            assertEquals("99999a", ((LinkedQueue) linkedQueue).getTail().getData());
            assertEquals(i + "a", linkedQueue.dequeue());
        }

        assertEquals(0, linkedQueue.size());
        assertTrue(linkedQueue.isEmpty());
        assertNull(((LinkedQueue) linkedQueue).getHead());
        assertNull(((LinkedQueue) linkedQueue).getTail());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedQueueDequeueEmpty() {
        for (int i = 0; i < 1000; i++) {
            linkedQueue.enqueue(i + "a");
        }
        assertEquals(1000, linkedQueue.size());
        for (int i = 0; i < 1000; i++) {
            assertEquals(i + "a", linkedQueue.dequeue());
        }
        assertEquals(0, linkedQueue.size());
        assertTrue(linkedQueue.isEmpty());
        assertNull(((LinkedQueue) linkedQueue).getHead());
        assertNull(((LinkedQueue) linkedQueue).getTail());

        linkedQueue.dequeue();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedQueueEnqueueNull() {
        for (int i = 0; i < 100; i++) {
            linkedQueue.enqueue(i + "a");
        }
        linkedQueue.enqueue(null);
    }
}
