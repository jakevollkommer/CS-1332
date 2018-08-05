import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Have fun! :D
 */
public class BstTests {

    private BSTInterface<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(2);
        bst.add(0);
        bst.add(6);
        bst.add(10);
        bst.add(-2);

        assertEquals(5, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 0, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 6, bst.getRoot().getRight().getData());
        assertEquals((Integer) 10, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) (int) -2, bst.getRoot().getLeft().getLeft().getData());

        bst.add(1);
        assertEquals(6, bst.size());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getRight().getData());

        bst.add(4);
        assertEquals(7, bst.size());
        assertEquals((Integer) 4, bst.getRoot().getRight().getLeft().getData());

        bst.add(5);
        assertEquals(8, bst.size());
        assertEquals((Integer) 5, bst.getRoot().getRight().getLeft().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddCustomConstructor() {
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5)));

        assertEquals(8, bst.size());

        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 0, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 6, bst.getRoot().getRight().getData());
        assertEquals((Integer) 10, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) (int) -2, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight().getLeft().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddDuplicate() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);
        assertEquals(3, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);
        assertEquals(3, bst.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);
        assertEquals(3, bst.size());

        bst.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddRandom() {  // tests creating a deep tree
        assertEquals(0, bst.size());

        for (int i = 0; i < 100; i++) {
            bst.add((int) (Math.random() * Integer.MAX_VALUE));
        }
        assertEquals(100, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5)));
        assertEquals(8, bst.size());

        assertEquals((Integer) 2, bst.get(2));
        assertEquals((Integer) 0, bst.get(0));
        assertEquals((Integer) 6, bst.get(6));
        assertEquals((Integer) 10, bst.get(10));
        assertEquals((Integer) (int) -2, bst.get(-2));
        assertEquals((Integer) 1, bst.get(1));
        assertEquals((Integer) 4, bst.get(4));
        assertEquals((Integer) 5, bst.get(5));

        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 0, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 6, bst.getRoot().getRight().getData());
        assertEquals((Integer) 10, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) (int) -2, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight().getLeft().getRight().getData());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5)));
        assertEquals(8, bst.size());

        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNoSuchElement() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5)));
        assertEquals(8, bst.size());

        assertEquals((Integer) 5, bst.get(5));
        bst.get(1000);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetEmpty() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst.get(0);
    }

    @Test(timeout = TIMEOUT)
    public void testGetMany() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        for (int i = 500; i >= 0; i--) {
            bst.add(i);
        }
        for (int i = 501; i < 1000; i++) {
            bst.add(i);
        }
        assertEquals(1000, bst.size());
        assertEquals((Integer) 500, bst.getRoot().getData());

        for (int i = 0; i < 1000; i++) {
            assertEquals((Integer) i, bst.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5)));
        assertEquals(8, bst.size());

        assertTrue(bst.contains(2));
        assertTrue(bst.contains(0));
        assertTrue(bst.contains(6));
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(-2));
        assertTrue(bst.contains(1));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(5));

        assertFalse(bst.contains(100));
        assertFalse(bst.contains(101));
        assertFalse(bst.contains(102));
        assertFalse(bst.contains(103));
        assertFalse(bst.contains(104));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsMany() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        for (int i = 500; i >= 0; i--) {
            if (i % 2 == 0) {
                bst.add(i);
            }
        }
        for (int i = 501; i < 1000; i++) {
            if (i % 2 == 0) {
                bst.add(i);
            }
        }
        assertEquals(500, bst.size());
        assertEquals((Integer) 500, bst.getRoot().getData());

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                assertTrue(bst.contains(i));
            } else {
                assertFalse(bst.contains(i));
            }
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5)));
        assertEquals(8, bst.size());

        bst.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));
        assertEquals(12, bst.size());

        List<Integer> list = bst.preorder();
        assertArrayEquals(new Integer[] {2,0,-2,-3,-1,1,6,4,5,10,8,7}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorderIncreasing() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        Integer[] arr = new Integer[100];
        for (int i = 0; i < 100; i++) {
            bst.add(i);
            arr[i] = i;
        }
        assertArrayEquals(arr, bst.preorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));
        assertEquals(12, bst.size());

        List<Integer> list = bst.postorder();
        assertArrayEquals(new Integer[] {-3,-1,-2,1,0,5,4,7,8,10,6,2}, list.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorderMany() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        Integer[] arr = new Integer[100];
        for (int i = 0; i < 100; i++) {
            bst.add(i);
            arr[i] = 99 - i;
        }
        assertArrayEquals(arr, bst.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));
        assertEquals(12, bst.size());

        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,6,7,8,10}, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorderMany() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        ArrayList<Integer> list = new ArrayList<Integer>();
        Integer[] arr = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            list.add(i);
            arr[i] = i;
        }
        Collections.shuffle(list);
        for (Integer i : list) {
            bst.add(i);
        }
        assertArrayEquals(arr, bst.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(2);
        assertEquals(0, bst.height());
        assertEquals(1, bst.size());

        bst.add(0);
        assertEquals(1, bst.height());
        assertEquals(2, bst.size());

        bst.add(6);
        assertEquals(1, bst.height());
        assertEquals(3, bst.size());

        bst.add(10);
        assertEquals(2, bst.height());
        assertEquals(4, bst.size());

        bst.add(-2);
        assertEquals(2, bst.height());
        assertEquals(5, bst.size());

        bst.add(1);
        assertEquals(2, bst.height());
        assertEquals(6, bst.size());

        bst.add(4);
        assertEquals(2, bst.height());
        assertEquals(7, bst.size());

        bst.add(5);
        assertEquals(3, bst.height());
        assertEquals(8, bst.size());

        bst.add(8);
        assertEquals(3, bst.height());
        assertEquals(9, bst.size());

        bst.add(-1);
        assertEquals(3, bst.height());
        assertEquals(10, bst.size());

        bst.add(-3);
        assertEquals(3, bst.height());
        assertEquals(11, bst.size());

        bst.add(7);
        assertEquals(4, bst.height());
        assertEquals(12, bst.size());

        bst.add(9);
        assertEquals(4, bst.height());
        assertEquals(13, bst.size());

        bst.add(11);
        assertEquals(4, bst.height());
        assertEquals(14, bst.size());

        bst.add(12);
        assertEquals(4, bst.height());
        bst.add(13);
        assertEquals(5, bst.height());
        bst.add(14);
        assertEquals(6, bst.height());
        bst.add(15);
        assertEquals(7, bst.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeightMany() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        for (int i = 0; i < 100; i++) {
            bst.add(i);
            assertEquals(i + 1, bst.size());
            assertEquals(i, bst.height());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));

        assertArrayEquals(new Integer[] {2,0,6,-2,1,4,10,-3,-1,5,8,7}, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorderMany() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        for (int i = 500; i < 1000; i++) {
            bst.add(i);
        }

        for (int i = 499; i > 0; i--) {
            bst.add(i);
        }
        assertEquals(999, bst.size());
        assertEquals(499, bst.height());

        List<Integer> list = new ArrayList<Integer>();
        list.add(500);
        for (int i = 1; i < 500; i++) {
            list.add(500 - i);
            list.add(500 + i);
        }
        assertArrayEquals(list.toArray(), bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLeaf() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));

        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,6,7,8,10}, bst.inorder().toArray());
        assertEquals(12, bst.size());

        assertEquals((Integer) (int) -3, bst.remove(-3));
        assertEquals(11, bst.size());
        assertArrayEquals(new Integer[] {-2,-1,0,1,2,4,5,6,7,8,10}, bst.inorder().toArray());

        assertEquals((Integer) 7, bst.remove(7));
        assertEquals(10, bst.size());
        assertArrayEquals(new Integer[] {-2,-1,0,1,2,4,5,6,8,10}, bst.inorder().toArray());

        assertEquals((Integer) 8, bst.remove(8));
        assertEquals(9, bst.size());
        assertArrayEquals(new Integer[] {-2,-1,0,1,2,4,5,6,10}, bst.inorder().toArray());

        assertEquals((Integer) 1, bst.remove(1));
        assertEquals(8, bst.size());
        assertArrayEquals(new Integer[] {-2,-1,0,2,4,5,6,10}, bst.inorder().toArray());

        assertEquals((Integer) (int) -1, bst.remove(-1));
        assertEquals(7, bst.size());
        assertArrayEquals(new Integer[] {-2,0,2,4,5,6,10}, bst.inorder().toArray());

        assertEquals((Integer) (int) -2, bst.remove(-2));
        assertEquals(6, bst.size());
        assertArrayEquals(new Integer[] {0,2,4,5,6,10}, bst.inorder().toArray());

        assertEquals((Integer) 0, bst.remove(0));
        assertEquals(5, bst.size());
        assertArrayEquals(new Integer[] {2,4,5,6,10}, bst.inorder().toArray());

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals(4, bst.size());
        assertArrayEquals(new Integer[] {2,4,5,6}, bst.inorder().toArray());

        assertEquals((Integer) 5, bst.remove(5));
        assertEquals(3, bst.size());
        assertArrayEquals(new Integer[] {2,4,6}, bst.inorder().toArray());

        assertEquals((Integer) 4, bst.remove(4));
        assertEquals(2, bst.size());
        assertArrayEquals(new Integer[] {2,6}, bst.inorder().toArray());

        assertEquals((Integer) 6, bst.remove(6));
        assertEquals(1, bst.size());
        assertArrayEquals(new Integer[] {2}, bst.inorder().toArray());

        assertEquals((Integer) 2, bst.getRoot().getData());

        assertEquals((Integer) 2, bst.remove(2));
        assertEquals(0, bst.size());
        assertArrayEquals(new Integer[] {}, bst.inorder().toArray());

        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSingleChild() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));

        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,6,7,8,10}, bst.inorder().toArray());
        assertEquals(12, bst.size());

        assertEquals((Integer) 4, bst.remove(4));
        assertEquals(11, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,5,6,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,6,-2,1,5,10,-3,-1,8,7}, bst.levelorder().toArray());

        assertEquals((Integer) (int) -1, bst.remove(-1));
        assertEquals(10, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,0,1,2,5,6,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,6,-2,1,5,10,-3,8,7}, bst.levelorder().toArray());

        assertEquals((Integer) (int) -2, bst.remove(-2));
        assertEquals(9, bst.size());
        assertArrayEquals(new Integer[] {-3,0,1,2,5,6,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,6,-3,1,5,10,8,7}, bst.levelorder().toArray());

        assertEquals((Integer) (int) 1, bst.remove(1));
        assertEquals(8, bst.size());
        assertArrayEquals(new Integer[] {-3,0,2,5,6,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,6,-3,5,10,8,7}, bst.levelorder().toArray());

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals(7, bst.size());
        assertArrayEquals(new Integer[] {-3,0,2,5,6,7,8}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,6,-3,5,8,7}, bst.levelorder().toArray());

        assertEquals((Integer) 8, bst.remove(8));
        assertEquals(6, bst.size());
        assertArrayEquals(new Integer[] {-3,0,2,5,6,7}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,6,-3,5,7}, bst.levelorder().toArray());

        assertEquals((Integer) 0, bst.remove(0));
        assertEquals(5, bst.size());
        assertArrayEquals(new Integer[] {-3,2,5,6,7}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,-3,6,5,7}, bst.levelorder().toArray());

        assertEquals((Integer) 5, bst.remove(5));
        assertEquals(4, bst.size());
        assertArrayEquals(new Integer[] {-3,2,6,7}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,-3,6,7}, bst.levelorder().toArray());

        assertEquals((Integer) 6, bst.remove(6));
        assertEquals(3, bst.size());
        assertArrayEquals(new Integer[] {-3,2,7}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,-3,7}, bst.levelorder().toArray());

        assertEquals((Integer) (int) -3, bst.remove(-3));
        assertEquals(2, bst.size());
        assertArrayEquals(new Integer[] {2,7}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,7}, bst.levelorder().toArray());

        assertEquals((Integer) 7, bst.remove(7));
        assertEquals(1, bst.size());
        assertArrayEquals(new Integer[] {2}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2}, bst.levelorder().toArray());

        assertEquals((Integer) 2, bst.getRoot().getData());

        assertEquals((Integer) 2, bst.remove(2));
        assertEquals(0, bst.size());
        assertArrayEquals(new Integer[] {}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {}, bst.levelorder().toArray());

        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveTwoChildren() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));

        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,6,7,8,10}, bst.inorder().toArray());
        assertEquals(12, bst.size());

        assertEquals((Integer) 6, bst.remove(6));
        assertEquals(11, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,7,-2,1,4,10,-3,-1,5,8}, bst.levelorder().toArray());

        assertEquals((Integer) 7, bst.remove(7));
        assertEquals(10, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,8,-2,1,4,10,-3,-1,5}, bst.levelorder().toArray());

        assertEquals((Integer) 8, bst.remove(8));
        assertEquals(9, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,10,-2,1,4,-3,-1,5}, bst.levelorder().toArray());

        assertEquals((Integer) (int) -2, bst.remove(-2));
        assertEquals(8, bst.size());
        assertArrayEquals(new Integer[] {-3,-1,0,1,2,4,5,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,0,10,-1,1,4,-3,5}, bst.levelorder().toArray());

        assertEquals((Integer) (int) 0, bst.remove(0));
        assertEquals(7, bst.size());
        assertArrayEquals(new Integer[] {-3,-1,1,2,4,5,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,1,10,-1,4,-3,5}, bst.levelorder().toArray());

        assertEquals((Integer) 1, bst.remove(1));
        assertEquals(6, bst.size());
        assertArrayEquals(new Integer[] {-3,-1,2,4,5,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,-1,10,-3,4,5}, bst.levelorder().toArray());

        assertEquals((Integer) (int) -1, bst.remove(-1));
        assertEquals(5, bst.size());
        assertArrayEquals(new Integer[] {-3,2,4,5,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,-3,10,4,5}, bst.levelorder().toArray());

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals(4, bst.size());
        assertArrayEquals(new Integer[] {-3,2,4,5}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,-3,4,5}, bst.levelorder().toArray());

        assertEquals((Integer) 4, bst.remove(4));
        assertEquals(3, bst.size());
        assertArrayEquals(new Integer[] {-3,2,5}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,-3,5}, bst.levelorder().toArray());

        assertEquals((Integer) (int) -3, bst.remove(-3));
        assertEquals(2, bst.size());
        assertEquals((Integer) 5, bst.remove(5));
        assertEquals(1, bst.size());
        assertEquals((Integer) 2, bst.remove(2));
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRoot() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));

        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,6,7,8,10}, bst.inorder().toArray());
        assertEquals(12, bst.size());

        assertEquals((Integer) 2, bst.remove(2));
        assertEquals(11, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,4,5,6,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {4,0,6,-2,1,5,10,-3,-1,8,7}, bst.levelorder().toArray());
        assertEquals((Integer) 4, bst.getRoot().getData());

        assertEquals((Integer) 4, bst.remove(4));
        assertEquals(10, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,5,6,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {5,0,6,-2,1,10,-3,-1,8,7}, bst.levelorder().toArray());
        assertEquals((Integer) 5, bst.getRoot().getData());

        assertEquals((Integer) 5, bst.remove(5));
        assertEquals(9, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,6,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {6,0,10,-2,1,8,-3,-1,7}, bst.levelorder().toArray());
        assertEquals((Integer) 6, bst.getRoot().getData());

        assertEquals((Integer) 6, bst.remove(6));
        assertEquals(8, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,7,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {7,0,10,-2,1,8,-3,-1}, bst.levelorder().toArray());
        assertEquals((Integer) 7, bst.getRoot().getData());

        assertEquals((Integer) 7, bst.remove(7));
        assertEquals(7, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,8,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {8,0,10,-2,1,-3,-1}, bst.levelorder().toArray());
        assertEquals((Integer) 8, bst.getRoot().getData());

        assertEquals((Integer) 8, bst.remove(8));
        assertEquals(6, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,10}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {10,0,-2,1,-3,-1}, bst.levelorder().toArray());
        assertEquals((Integer) 10, bst.getRoot().getData());

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals(5, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,0,1}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {0,-2,1,-3,-1}, bst.levelorder().toArray());
        assertEquals((Integer) 0, bst.getRoot().getData());

        assertEquals((Integer) 0, bst.remove(0));
        assertEquals(4, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1,1}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {1,-2,-3,-1}, bst.levelorder().toArray());
        assertEquals((Integer) 1, bst.getRoot().getData());

        assertEquals((Integer) 1, bst.remove(1));
        assertEquals(3, bst.size());
        assertArrayEquals(new Integer[] {-3,-2,-1}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {-2,-3,-1}, bst.levelorder().toArray());
        assertEquals((Integer) (int) -2, bst.getRoot().getData());

        assertEquals((Integer) (int) -2, bst.remove(-2));
        assertEquals(2, bst.size());
        assertArrayEquals(new Integer[] {-3,-1}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {-1,-3}, bst.levelorder().toArray());
        assertEquals((Integer) (int) -1, bst.getRoot().getData());

        assertEquals((Integer) (int) -1, bst.remove(-1));
        assertEquals(1, bst.size());
        assertArrayEquals(new Integer[] {-3}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {-3}, bst.levelorder().toArray());
        assertEquals((Integer) (int) -3, bst.getRoot().getData());

        assertEquals((Integer) (int) -3, bst.remove(-3));
        assertEquals(0, bst.size());
        assertArrayEquals(new Integer[] {}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {}, bst.levelorder().toArray());

        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));

        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,6,7,8,10}, bst.inorder().toArray());
        assertEquals(12, bst.size());

        bst.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNoSuchElement() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
        bst = new BST<Integer>(new ArrayList<Integer>(
                Arrays.asList(2, 0, 6, 10, -2, 1, 4, 5, 8, -1, -3, 7)));

        assertArrayEquals(new Integer[] {-3,-2,-1,0,1,2,4,5,6,7,8,10}, bst.inorder().toArray());
        assertEquals(12, bst.size());

        bst.remove(111);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.remove(10);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSuccessor() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        bst.add(2);
        bst.add(1);
        bst.add(100);
        bst.add(8);
        bst.add(9);
        bst.add(6);
        bst.add(7);
        bst.add(4);
        bst.add(5);
        bst.add(10);
        bst.add(11);
        bst.add(12);

        assertArrayEquals(new Integer[] {1,2,4,5,6,7,8,9,10,11,12,100}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {2,1,100,8,6,9,4,7,10,5,11,12}, bst.levelorder().toArray());

        assertEquals((Integer) 2, bst.remove(2));
        assertEquals((Integer) 4, bst.getRoot().getData());
        assertArrayEquals(new Integer[] {1,4,5,6,7,8,9,10,11,12,100}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {4,1,100,8,6,9,5,7,10,11,12}, bst.levelorder().toArray());

        assertEquals((Integer) 4, bst.remove(4));
        assertEquals((Integer) 5, bst.getRoot().getData());
        assertArrayEquals(new Integer[] {1,5,6,7,8,9,10,11,12,100}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {5,1,100,8,6,9,7,10,11,12}, bst.levelorder().toArray());

        assertEquals((Integer) 5, bst.remove(5));
        assertEquals((Integer) 6, bst.getRoot().getData());
        assertArrayEquals(new Integer[] {1,6,7,8,9,10,11,12,100}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {6,1,100,8,7,9,10,11,12}, bst.levelorder().toArray());

        assertEquals((Integer) 6, bst.remove(6));
        assertEquals((Integer) 7, bst.getRoot().getData());
        assertArrayEquals(new Integer[] {1,7,8,9,10,11,12,100}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {7,1,100,8,9,10,11,12}, bst.levelorder().toArray());

        assertEquals((Integer) 7, bst.remove(7));
        assertEquals((Integer) 8, bst.getRoot().getData());
        assertArrayEquals(new Integer[] {1,8,9,10,11,12,100}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {8,1,100,9,10,11,12}, bst.preorder().toArray());
        assertEquals((Integer) 10, bst.getRoot().getRight().getLeft().getRight().getData());

        assertEquals((Integer) 8, bst.remove(8));
        assertEquals((Integer) 9, bst.getRoot().getData());
        assertArrayEquals(new Integer[] {1,9,10,11,12,100}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {9,1,100,10,11,12}, bst.preorder().toArray());

        assertEquals((Integer) 9, bst.remove(9));
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertArrayEquals(new Integer[] {1,10,11,12,100}, bst.inorder().toArray());
        assertArrayEquals(new Integer[] {10,1,100,11,12}, bst.preorder().toArray());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 100, bst.getRoot().getRight().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getLeft().getRight().getData());

        assertNull(bst.getRoot().getRight().getLeft().getRight().getRight());
    }

}