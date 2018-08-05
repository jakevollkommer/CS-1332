import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Extra JUnit tests
 * @version 1
 */
public class ArrayListStudentTests2 {

    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }



    @Test(timeout = TIMEOUT)
    public void testAddStringsMiddle() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a
        list.addAtIndex(1, "hi");
        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "hi";
        expected[2] = "1a";
        expected[3] = "2a";
        expected[4] = "3a";
        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testDouble() {
        assertEquals(0, list.size());
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a");
        list.addToFront("6a");
        list.addToFront("7a");
        list.addToFront("8a");
        list.addToFront("9a");
        list.addToFront("10a");
        assertEquals(11, list.size());

        Object[] expected = new Object[20]; //this is what the size of the array should be when resizing if doubled
        expected[0] = "10a";
        expected[1] = "9a";
        expected[2] = "8a";
        expected[3] = "7a";
        expected[4] = "6a";
        expected[5] = "5a";
        expected[6] = "4a";
        expected[7] = "3a";
        expected[8] = "2a";
        expected[9] = "1a";
        expected[10] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testAddStringsIndexDouble() {
        assertEquals(0, list.size());

        list.addAtIndex(0,"0a");
        list.addAtIndex(0,"1a");
        list.addAtIndex(0,"2a");
        list.addAtIndex(0,"3a");
        list.addAtIndex(0,"4a");
        list.addAtIndex(0,"5a");
        list.addAtIndex(0,"6a");
        list.addAtIndex(0,"7a");
        list.addAtIndex(0,"8a");
        list.addAtIndex(0,"9a");
        list.addAtIndex(0, "10a");
        assertEquals(11, list.size());

        Object[] expected = new Object[20];
        expected[0] = "10a";
        expected[1] = "9a";
        expected[2] = "8a";
        expected[3] = "7a";
        expected[4] = "6a";
        expected[5] = "5a";
        expected[6] = "4a";
        expected[7] = "3a";
        expected[8] = "2a";
        expected[9] = "1a";
        expected[10] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testDoubleBack() {
        assertEquals(0, list.size());
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a");
        list.addToBack("6a");
        list.addToBack("7a");
        list.addToBack("8a");
        list.addToBack("9a");
        list.addToBack("10a");
        assertEquals(11, list.size());

        Object[] expected = new Object[20]; //this is what the size of the array should be when resizing if doubled
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        expected[10] = "10a";
        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testAddStringsFrontIndex() {
        assertEquals(0, list.size());

        list.addAtIndex(0,"0a");
        list.addAtIndex(0,"1a");
        list.addAtIndex(0,"2a");
        list.addAtIndex(0,"3a");
        list.addAtIndex(0,"4a"); //4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[4] = "4a";
        expected[3] = "3a";
        expected[2] = "2a";
        expected[1] = "1a";
        expected[0] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        list.clear();
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        assertArrayEquals(expected, list.getBackingArray());
        assertEquals(0, list.size());

    }
    @Test(timeout = TIMEOUT)
    public void testRemoveBack() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");

        list.removeFromBack();
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveMix() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");

        list.removeFromBack();
        list.removeAtIndex(1);
        list.removeFromFront();
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "2a";
        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        assertEquals(3, list.size());
        list.removeFromFront();
        list.removeFromFront();
        list.removeFromFront();
        assertEquals(0, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];

        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveEmpty() {
        assertEquals(0, list.size());
        list.removeFromBack();
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        assertArrayEquals(expected, list.getBackingArray());
    }
    @Test (timeout = TIMEOUT)
    public void testRemoveFromLarge() {
        assertEquals(0, list.size());
        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a");
        list.addToBack("6a");
        list.addToBack("7a");
        list.addToBack("8a");
        list.addToBack("9a");
        list.addToBack("10a");
        assertEquals(11, list.size());

        list.removeFromBack();
        list.removeFromBack();
        Object[] expected = new Object[20];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        assertArrayEquals(expected, list.getBackingArray());
    }

}
