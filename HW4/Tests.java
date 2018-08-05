import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Sample JUnit test cases for BST.
 *
 * @version 1.0
 * @author Vishal Vijayakumar
 */

public class Tests {
    private BSTInterface<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddOneArgConstructor() {
        ArrayList<Integer> testArr = new ArrayList<Integer>();
        testArr.add(10);
        testArr.add(8);
        testArr.add(12);
        testArr.add(3);
        testArr.add(11);
        testArr.add(13);
        testArr.add(2);
        testArr.add(5);

        bst = new BST<Integer>(testArr);

        assertEquals(testArr.size(), bst.size());

        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 8, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getData());
        assertEquals((Integer) 3, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 13, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 2, bst.getRoot().getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 5, bst.getRoot().getLeft().getLeft().getRight().getData());

    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddEdgeCases() {
        assertEquals(0, bst.size());

        /*
            test that adding null element throws IllegalArgumentException
            and that adding null element does not increment size
         */
        bst.add(2);
        assertEquals(1, bst.size());
        bst.add(null);
        assertEquals(1, bst.size());

        /*
            test that adding first element correctly assigns root label to that node
         */
        bst.clear();
        assertEquals(0, bst.size());
        bst.add(10);
        assertTrue(bst.getRoot() != null);
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals(1, bst.size());

        /*
            test that duplicates are not added in tree and that
            size is not incremented for attempting to add duplicates
         */
        bst.clear();
        assertEquals(0, bst.size());
        bst.add(10);
        bst.add(9);
        bst.add(11);
        assertEquals(3, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 9, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getData());
        bst.add(11);
        assertEquals(3, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 9, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getData());

        /*
            test that unbalanced tree is created correctly
         */
        bst.clear();
        assertEquals(0, bst.size());

        for (int i = 0; i < 5; i++) {
            bst.add(i);
        }
        assertEquals(5, bst.size());
        assertTrue(bst.getRoot().getLeft() == null);
        assertEquals((Integer) 0, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 2, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getLeft().getLeft().getLeft().getLeft().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(8);
        bst.add(3);
        bst.add(5);
        bst.add(12);
        bst.add(11);
        bst.add(13);

        assertEquals(7, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 8, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 5, bst.getRoot().getLeft().getLeft().getRight().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 13, bst.getRoot().getRight().getRight().getData());

        bst.add(2);
        bst.add(4);

        assertEquals(9, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getLeft().getLeft().getRight().getLeft().getData());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveIllegalArgumentException() {
        assertEquals(0, bst.size());

        bst.add(10);
        bst.add(0);
        bst.add(2);
        bst.add(1);
        assertEquals(4, bst.size());

        bst.remove(null);
        assertEquals(4, bst.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNoSuchElementException() {
        assertEquals(0, bst.size());

        bst.add(10);
        bst.add(0);
        bst.add(2);
        bst.add(1);
        assertEquals(4, bst.size());

        bst.remove(100);
        assertEquals(4, bst.size());

        bst.remove(1023);
        assertEquals(4, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNoChildren() {
        assertEquals(0, bst.size());
        assertEquals(bst.getRoot(), null);

        bst.add(10);
        bst.add(8);
        bst.add(3);
        bst.add(5);
        bst.add(12);
        bst.add(11);
        bst.add(13);
        bst.add(2);
        assertEquals(8, bst.size());

        assertEquals((Integer) 2, bst.remove(2));
        assertEquals(7, bst.size());
        assertEquals(bst.inorder().size(), bst.size());
        assertTrue(bst.getRoot().getLeft().getLeft().getLeft() == null);

        assertEquals((Integer) 5, bst.remove(5));
        assertEquals(6, bst.size());
        assertEquals(bst.inorder().size(), bst.size());
        assertTrue(bst.getRoot().getLeft().getLeft().getRight() == null);

        assertEquals((Integer) 11, bst.remove(11));
        assertEquals(5, bst.size());
        assertEquals(bst.inorder().size(), bst.size());
        assertTrue(bst.getRoot().getRight().getLeft() == null);

        assertEquals((Integer) 13, bst.remove(13));
        assertEquals(4, bst.size());
        assertEquals(bst.inorder().size(), bst.size());
        assertTrue(bst.getRoot().getRight().getRight() == null);

        assertEquals((Integer) 3, bst.remove(3));
        assertEquals(3, bst.size());
        assertEquals(bst.inorder().size(), bst.size());
        assertTrue(bst.getRoot().getLeft().getLeft() == null);

        assertEquals((Integer) 8, bst.remove(8));
        assertEquals(2, bst.size());
        assertEquals(bst.inorder().size(), bst.size());
        assertTrue(bst.getRoot().getLeft() == null);

        assertEquals((Integer) 12, bst.remove(12));
        assertEquals(1, bst.size());
        assertEquals(bst.inorder().size(), bst.size());
        assertTrue(bst.getRoot().getRight() == null);

        assertTrue(bst.getRoot() != null);
        assertEquals((Integer) 10, bst.getRoot().getData());

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals(bst.inorder().size(), bst.size());
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveOneChild() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(8);
        bst.add(15);
        bst.add(9);
        bst.add(17);
        bst.add(16);

        assertEquals(6, bst.size());

        assertEquals((Integer) 8, bst.remove(8));
        assertEquals(5, bst.size());
        assertEquals((Integer) 9, bst.getRoot().getLeft().getData());
        assertTrue(bst.getRoot().getLeft().getRight() == null);

        assertEquals((Integer) 17, bst.remove(17));
        assertEquals(4, bst.size());
        assertEquals((Integer) 16, bst.getRoot().getRight().getRight().getData());
        assertTrue(bst.getRoot().getRight().getRight().getLeft() == null);

        assertEquals((Integer) 15, bst.remove(15));
        assertEquals(3, bst.size());
        assertEquals((Integer) 16, bst.getRoot().getRight().getData());
        assertTrue(bst.getRoot().getRight().getRight() == null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveTwoChildren() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(8);
        bst.add(12);
        bst.add(3);
        bst.add(9);
        bst.add(11);
        bst.add(13);
        bst.add(2);
        bst.add(5);
        bst.add(4);

        assertEquals(10, bst.size());

        assertEquals((Integer) 3, bst.remove(3));
        assertEquals(9, bst.size());
        assertEquals(bst.levelorder().size(), bst.size());
        assertEquals((Integer) 4, bst.getRoot().getLeft().getLeft().getData());
        assertTrue(bst.getRoot().getLeft().getLeft().getRight().getLeft() == null);

        assertEquals((Integer) 4, bst.remove(4));
        assertEquals(8, bst.size());
        assertEquals(bst.levelorder().size(), bst.size());
        assertEquals((Integer) 5, bst.getRoot().getLeft().getLeft().getData());
        assertTrue(bst.getRoot().getLeft().getLeft().getRight() == null);

        assertEquals((Integer) 12, bst.remove(12));
        assertEquals(7, bst.size());
        assertEquals(bst.levelorder().size(), bst.size());
        assertEquals((Integer) 13, bst.getRoot().getRight().getData());
        assertTrue(bst.getRoot().getRight().getRight() == null);

        assertTrue(bst.getRoot() != null);
        assertEquals((Integer) 10, bst.getRoot().getData());


        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(8);
        bst.add(16);
        bst.add(7);
        bst.add(9);
        bst.add(15);
        bst.add(18);
        bst.add(14);
        bst.add(17);
        bst.add(13);

        assertEquals(10, bst.size());

        assertEquals((Integer) 16, bst.remove(16));
        assertEquals(9, bst.size());
        assertEquals(bst.levelorder().size(), bst.size());
        assertEquals((Integer) 17, bst.getRoot().getRight().getData());
        assertTrue(bst.getRoot().getRight().getRight().getLeft() == null);

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRootTwoChildren() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(8);
        bst.add(12);
        bst.add(3);
        bst.add(9);
        bst.add(11);
        bst.add(13);
        bst.add(2);
        bst.add(5);
        bst.add(4);

        assertEquals(10, bst.size());

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals(9, bst.size());
        assertEquals(bst.postorder().size(), bst.size());
        assertEquals((Integer) 11, bst.getRoot().getData());
        assertTrue(bst.getRoot().getRight().getLeft() == null);

        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(8);
        bst.add(16);
        bst.add(7);
        bst.add(9);
        bst.add(15);
        bst.add(18);
        bst.add(19);
        bst.add(17);
        bst.add(13);

        assertEquals(10, bst.size());

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals(9, bst.size());
        assertEquals(bst.preorder().size(), bst.size());
        assertEquals((Integer) 13, bst.getRoot().getData());
        assertTrue(bst.getRoot().getRight().getLeft().getLeft() == null);

        assertEquals((Integer) 13, bst.remove(13));
        assertEquals(8, bst.size());
        assertEquals(bst.preorder().size(), bst.size());
        assertEquals((Integer) 15, bst.getRoot().getData());
        assertTrue(bst.getRoot().getRight().getLeft() == null);
    }

    @Test(timeout =  TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.remove(12);
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(190);
        bst.add(1203);
        bst.add(23);

        assertEquals(4, bst.size());

        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNoSuchElementException() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(190);
        bst.add(1203);
        bst.add(23);

        assertEquals(4, bst.size());

        bst.get(0);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetEmpty() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.get(10);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(190);
        bst.add(1203);
        bst.add(23);

        assertEquals(4, bst.size());

        assertEquals((Integer) 10, bst.get(10));
        assertEquals(4, bst.size());
        assertEquals(4, bst.postorder().size());

        assertEquals((Integer) 1203, bst.get(1203));
        assertEquals(4, bst.size());
        assertEquals(4, bst.postorder().size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(190);
        bst.add(1203);
        bst.add(23);

        assertEquals(4, bst.size());

        bst.contains(null);
        assertEquals(4, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testContainsDoesNotExist() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(190);
        bst.add(1203);
        bst.add(23);

        assertEquals(4, bst.size());

        assertFalse(bst.contains(109));
        assertEquals(4, bst.size());

        assertFalse(bst.contains(0));
        assertEquals(4, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        bst.add(190);
        bst.add(1203);
        bst.add(23);

        assertEquals(4, bst.size());

        assertTrue(bst.contains(10));
        assertEquals(4, bst.size());

        assertTrue(bst.contains(190));
        assertEquals(4, bst.size());

        assertTrue(bst.contains(1203));
        assertEquals(4, bst.size());

        assertTrue(bst.contains(23));
        assertEquals(4, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSize() {
        assertEquals(0, bst.size());

        bst.add(10);
        bst.add(8);
        bst.add(16);
        bst.add(7);
        bst.add(9);
        bst.add(15);
        bst.add(18);
        bst.add(19);
        bst.add(17);
        bst.add(13);

        assertEquals(10, bst.size());

        bst.clear();
        assertEquals(0, bst.size());

        for (int i =0; i < 100; i++) {
            bst.add(i);
        }
        assertEquals(100, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testPreOrder() {
        bst.add(10);
        bst.add(8);
        bst.add(12);
        bst.add(3);
        bst.add(9);
        bst.add(11);
        bst.add(13);
        bst.add(2);
        bst.add(5);
        bst.add(4);

        assertArrayEquals(new Integer[] {10, 8, 3, 2, 5, 4, 9, 12, 11, 13}, bst.preorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPreOrderUnbalanced() {
        Integer[] arr = new Integer[100];
        for (int i = 100; i > 0; i--) {
            bst.add(i);
            arr[100 - i] = i;
        }

        assertArrayEquals(arr, bst.preorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostOrder() {
        bst.add(10);
        bst.add(8);
        bst.add(12);
        bst.add(3);
        bst.add(9);
        bst.add(11);
        bst.add(13);
        bst.add(2);
        bst.add(5);
        bst.add(4);

        assertArrayEquals(new Integer[] {2, 4, 5, 3, 9, 8, 11, 13, 12, 10}, bst.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostOrderUnbalanced() {
        Integer[] arr = new Integer[100];
        for (int i = 100; i > 0; i--) {
            bst.add(i);
            arr[100 - i] = 100 - i + 1;
        }

        assertArrayEquals(arr, bst.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInOrder() {
        bst.add(10);
        bst.add(8);
        bst.add(12);
        bst.add(3);
        bst.add(9);
        bst.add(11);
        bst.add(13);
        bst.add(2);
        bst.add(5);
        bst.add(4);

        assertArrayEquals(new Integer[] {2, 3, 4, 5, 8, 9, 10, 11, 12, 13}, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderUnbalanced() {
        Integer[] arr = new Integer[100];
        for (int i = 100; i > 0; i--) {
            bst.add(i);
            arr[100 - i] = 100 - i + 1;
        }

        assertArrayEquals(arr, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelOrder() {
        bst.add(10);
        bst.add(8);
        bst.add(12);
        bst.add(3);
        bst.add(9);
        bst.add(11);
        bst.add(13);
        bst.add(2);
        bst.add(5);
        bst.add(4);

        assertArrayEquals(new Integer[] {10, 8, 12, 3, 9, 11, 13, 2, 5, 4}, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelOrderUnbalanced() {
        Integer[] arr = new Integer[100];
        for (int i = 100; i > 0; i--) {
            bst.add(i);
            arr[100 - i] = i;
        }

        assertArrayEquals(arr, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testReturnEmptyListForTraversals() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        assertArrayEquals(new Integer[0], bst.preorder().toArray());
        assertArrayEquals(new Integer[0], bst.postorder().toArray());
        assertArrayEquals(new Integer[0], bst.inorder().toArray());
        assertArrayEquals(new Integer[0], bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        Integer[] arr = new Integer[100];
        for (int i = 100; i > 0; i--) {
            bst.add(i);
            arr[100 - i] = i;
        }

        assertEquals(100, bst.size());

        bst.clear();
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);
    }

    @Test(timeout = TIMEOUT)
    public void testHeightEmpty() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        assertEquals(-1, bst.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeightOnlyRoot() {
        assertEquals(0, bst.size());
        assertTrue(bst.getRoot() == null);

        bst.add(10);
        assertEquals((Integer) 10, bst.getRoot().getData());

        assertEquals(0, bst.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals(0, bst.size());
        assertEquals(-1, bst.height());

        bst.add(10);
        assertEquals(0, bst.height());

        bst.add(8);
        assertEquals(1, bst.height());

        bst.add(12);
        assertEquals(1, bst.height());

        bst.add(3);
        assertEquals(2, bst.height());

        bst.add(9);
        assertEquals(2, bst.height());

        bst.add(11);
        assertEquals(2, bst.height());

        bst.add(13);
        assertEquals(2, bst.height());

        bst.add(2);
        assertEquals(3, bst.height());

        bst.add(5);
        assertEquals(3, bst.height());

        bst.add(4);
        assertEquals(4, bst.height());
    }
}
