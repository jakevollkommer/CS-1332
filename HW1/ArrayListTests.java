import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayListTests {
    private ArrayList<Integer> arr;

    @Before
    public void setUp() {
        arr = new ArrayList<>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(arr.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, arr.size());
    }

    @Test
    public void testClear() {
        arr.clear();
        assertArrayEquals(arr.getBackingArray(), new Object[10]);
        assertEquals(0, arr.size());
    }

    @Test
    public void testGet() {
        arr.addToFront(1);
        assertEquals(1, arr.size());
        arr.addToFront(2);
        assertEquals(2, arr.size());
        arr.addToFront(3);
        assertEquals(3, arr.size());
        arr.addToFront(4);
        assertEquals(4, arr.size());
        arr.addToFront(5);
        assertEquals(5, arr.size());
        arr.addToFront(6);
        assertEquals(6, arr.size());
        arr.addToFront(7);
        assertEquals(7, arr.size());
        arr.addToFront(8);
        assertEquals(8, arr.size());
        arr.addToFront(9);
        assertEquals(9, arr.size());
        arr.addToFront(10);
        assertEquals(10, arr.size());
        arr.addToFront(11);
        assertEquals(11, arr.size());

        Integer a = arr.get(0);
        assertEquals(new Integer(11), a); // needed to make IDE accept
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetThrowsIOOBENegativeIndex() {
        arr.addToFront(1);
        Integer a = arr.get(-1);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetThrowsIOOBEIndexGreaterThanOrEqualToSize() {
        arr.addToFront(1);
        Integer a = arr.get(1); // change to >= 2 to test the other scenario
    }

    /*************************************************************************
     * Tests the addToFront method - incl. size incrementing, backing array
     * expansion, and compares to a pre-made Integer array
     *************************************************************************/
    @Test
    public void testAddToFront() {
        arr.addToFront(1);
        assertEquals(1, arr.size());
        arr.addToFront(2);
        assertEquals(2, arr.size());
        arr.addToFront(3);
        assertEquals(3, arr.size());
        arr.addToFront(4);
        assertEquals(4, arr.size());
        arr.addToFront(5);
        assertEquals(5, arr.size());
        arr.addToFront(6);
        assertEquals(6, arr.size());
        arr.addToFront(7);
        assertEquals(7, arr.size());
        arr.addToFront(8);
        assertEquals(8, arr.size());
        arr.addToFront(9);
        assertEquals(9, arr.size());
        arr.addToFront(10);
        assertEquals(10, arr.size());
        arr.addToFront(11);
        assertEquals(11, arr.size());

        Integer[] expected = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1,
                null, null, null, null, null, null, null, null, null};

        assertFalse(arr.isEmpty());
        assertArrayEquals(expected, arr.getBackingArray());
    }

    /************************************************************************
     * Tests that addToFront throws an IAE for null data as specified in the
     * instructions.
     ************************************************************************/
    @Test(expected=IllegalArgumentException.class)
    public void testAddToFrontThrowsIAENullData() {
        arr.addToFront(null);
    }

    /************************************************************************
     * Tests the addToBack method - incl. size incrementing, backing array
     * expansion, and compares to a pre-made Integer array
     ************************************************************************/
    @Test
    public void testAddToBack() {
        arr.addToFront(1);
        assertEquals(1, arr.size());
        arr.addToFront(2);
        assertEquals(2, arr.size());
        arr.addToFront(3);
        assertEquals(3, arr.size());
        arr.addToFront(4);
        assertEquals(4, arr.size());
        arr.addToFront(5);
        assertEquals(5, arr.size());
        arr.addToFront(6);
        assertEquals(6, arr.size());
        arr.addToFront(7);
        assertEquals(7, arr.size());
        arr.addToFront(8);
        assertEquals(8, arr.size());
        arr.addToFront(9);
        assertEquals(9, arr.size());
        arr.addToFront(10);
        assertEquals(10, arr.size());

        arr.addToBack(12);
        assertEquals(11, arr.size());

        Integer[] expected = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 12,
                null, null, null, null, null, null, null, null, null};

        assertArrayEquals(expected, arr.getBackingArray());
    }

    /************************************************************************
     * Tests that addToBack throws an IAE for null data as specified in the
     * instructions.
     ************************************************************************/
    @Test(expected=IllegalArgumentException.class)
    public void testAddToBackThrowsIAENullData() {
        arr.addToBack(null);
    }

    /**************************************************************************
     * Tests the addAtIndex method - incl. size incrementing, backing array
     * expansion, and compares to a pre-made Integer array
     **************************************************************************/
    @Test
    public void testAddAtIndex() {
        arr.addAtIndex(0,1);
        assertEquals(1, arr.size());
        arr.addAtIndex(1,2);
        assertEquals(2, arr.size());
        arr.addAtIndex(2, 3);
        assertEquals(3, arr.size());
        arr.addAtIndex(3,4);
        assertEquals(4, arr.size());
        arr.addAtIndex(4,5);
        assertEquals(5, arr.size());
        arr.addAtIndex(5,6);
        assertEquals(6, arr.size());
        arr.addAtIndex(6,7);
        assertEquals(7, arr.size());
        arr.addAtIndex(7,8);
        assertEquals(8, arr.size());
        arr.addAtIndex(8,9);
        assertEquals(9, arr.size());
        arr.addAtIndex(9,10);
        assertEquals(10, arr.size());

        arr.addAtIndex(5, 15);
        arr.addAtIndex(9, 19);
        arr.addAtIndex(11, 111);

        Integer[] expected = {1, 2, 3, 4, 5, 15, 6, 7, 8, 19, 9, 111, 10,
                null, null, null, null, null, null, null};

        assertArrayEquals(expected, arr.getBackingArray());
    }

    /*******************************************************************************
     * Tests that addAtIndex throws an IOOBE for index > size as specified in the
     * instructions.
     *******************************************************************************/
    @Test(expected=IndexOutOfBoundsException.class)
    public void testAddAtIndexThrowsIOOBEIndexGreaterThanSize() {
        arr.addAtIndex(1, 1);
    }

    /************************************************************************
     * Tests that addAtIndex throws an IOOBE for index < 0 as specified
     * in the instructions.
     ************************************************************************/
    @Test(expected=IndexOutOfBoundsException.class)
    public void testAddAtIndexThrowsIOOBENegativeIndex() {
        arr.addAtIndex(-1, 1);
    }

    /************************************************************************
     * Tests that addAtIndex throws an IAE for null data as specified in the
     * instructions.
     ************************************************************************/
    @Test(expected=IllegalArgumentException.class)
    public void testAddAtIndexThrowsIAENullData() {
        arr.addAtIndex(0, null);
    }

    @Test
    public void testRemoveFromFront() {
        arr.addAtIndex(0,1);
        assertEquals(1, arr.size());
        arr.addAtIndex(1,2);
        assertEquals(2, arr.size());
        arr.addAtIndex(2, 3);
        assertEquals(3, arr.size());
        arr.addAtIndex(3,4);
        assertEquals(4, arr.size());
        arr.addAtIndex(4,5);
        assertEquals(5, arr.size());
        arr.addAtIndex(5,6);
        assertEquals(6, arr.size());
        arr.addAtIndex(6,7);
        assertEquals(7, arr.size());
        arr.addAtIndex(7,8);
        assertEquals(8, arr.size());
        arr.addAtIndex(8,9);
        assertEquals(9, arr.size());
        arr.addAtIndex(9,10);
        assertEquals(10, arr.size());

        Integer rem = arr.removeFromFront();
        assertEquals(new Integer(1), rem);
        assertEquals(9, arr.size());
        rem = arr.removeFromFront();
        assertEquals(new Integer(2), rem);
        assertEquals(8, arr.size());

        Integer[] expected = {3, 4, 5, 6, 7, 8, 9, 10, null, null};
        assertArrayEquals(expected, arr.getBackingArray());
    }

    @Test
    public void testRemoveFromFrontIfEmpty() {
        assertNull(arr.removeFromFront());
    }

    @Test
    public void testRemoveFromBack() {
        arr.addAtIndex(0,1);
        assertEquals(1, arr.size());
        arr.addAtIndex(1,2);
        assertEquals(2, arr.size());
        arr.addAtIndex(2, 3);
        assertEquals(3, arr.size());
        arr.addAtIndex(3,4);
        assertEquals(4, arr.size());
        arr.addAtIndex(4,5);
        assertEquals(5, arr.size());
        arr.addAtIndex(5,6);
        assertEquals(6, arr.size());
        arr.addAtIndex(6,7);
        assertEquals(7, arr.size());
        arr.addAtIndex(7,8);
        assertEquals(8, arr.size());
        arr.addAtIndex(8,9);
        assertEquals(9, arr.size());
        arr.addAtIndex(9,10);
        assertEquals(10, arr.size());

        Integer rem = arr.removeFromBack();
        assertEquals(new Integer(10), rem);
        assertEquals(9, arr.size());
        rem = arr.removeFromBack();
        assertEquals(new Integer(9), rem);
        assertEquals(8, arr.size());

        Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, null, null};
        assertArrayEquals(expected, arr.getBackingArray());
    }

    @Test
    public void testRemoveFromBackIfEmpty() {
        assertNull(arr.removeFromBack());
    }

    @Test
    public void testRemoveAtIndex() {
        arr.addAtIndex(0,1);
        assertEquals(1, arr.size());
        arr.addAtIndex(1,2);
        assertEquals(2, arr.size());
        arr.addAtIndex(2, 3);
        assertEquals(3, arr.size());
        arr.addAtIndex(3,4);
        assertEquals(4, arr.size());
        arr.addAtIndex(4,5);
        assertEquals(5, arr.size());
        arr.addAtIndex(5,6);
        assertEquals(6, arr.size());
        arr.addAtIndex(6, 7);
        assertEquals(7, arr.size());
        arr.addAtIndex(7,8);
        assertEquals(8, arr.size());
        arr.addAtIndex(8,9);
        assertEquals(9, arr.size());
        arr.addAtIndex(9,10);
        assertEquals(10, arr.size());

        Integer rem = arr.removeAtIndex(2);
        assertEquals(new Integer(3), rem);
        assertEquals(9, arr.size());
        rem = arr.removeAtIndex(7);
        assertEquals(new Integer(9), rem);
        assertEquals(8, arr.size());
        rem = arr.removeAtIndex(3);
        assertEquals(new Integer(5), rem);
        assertEquals(7, arr.size());

        Integer[] expected = {1, 2, 4, 6, 7, 8, 10, null, null, null};
        assertArrayEquals(expected, arr.getBackingArray());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIOOBEIndexGreaterThanOrEqualToSize() {
        arr.addAtIndex(0,1);
        arr.addAtIndex(1,2);
        arr.removeAtIndex(3);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testRemoveAtIndexThrowsIOOBENegativeIndex() {
        arr.addAtIndex(0,1);
        arr.addAtIndex(1,2);
        arr.removeAtIndex(-1);
    }
}
