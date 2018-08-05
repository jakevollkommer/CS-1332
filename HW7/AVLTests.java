import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * AVL Tests by Caeden Meade
 */
public class AVLTests {
    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    private boolean printWholeNode = true;
    /**
    Convenience method for visualizing the AVL. Tests nothing
    AVLNodes appear in the form:
        (data, height, balanceFactor)->leftChild->leftGrandchild->etc.
                                                ->rightGrandChild->etc.
                                     ->rightChild->|
                                                 ->|

    ->| signifies a null child

    printWholeNode is true by default. When it's false, AVLNodes appear as:
        (data), rather than (data, height, balanceFactor). Setting its value
        within a single test changes its value for that test only. Adjust it
        here to change it for all tests.

    An empty AVL appears as "EMPTY AVL"
    */
    private void printAVL() {
        if (avlTree.size() == 0) {
            System.out.println("Empty AVL");
            return;
        }
        AVLNode<Integer> root = avlTree.getRoot();
        String prevLine = printWholeNode ? "(" + root.getData() + ", " + root.getHeight() + ", " + root.getBalanceFactor() + ")" : "(" + root.getData() + ")";
        System.out.print(prevLine);
        printRecurse(prevLine, avlTree.getRoot());
        System.out.println();
    }
    private void printRecurse(String prev, AVLNode<Integer> cur) {
        if (cur.getLeft() != null) {
            String thisLine = "->" + (printWholeNode ? "(" + cur.getLeft().getData() + ", " + cur.getLeft().getHeight() + ", " + cur.getLeft().getBalanceFactor() + ")" : "(" + cur.getLeft().getData() + ")");
            System.out.print(thisLine);
            printRecurse(prev + thisLine, cur.getLeft());
        } else {
            System.out.print("->|");
        }
        if (cur.getRight() != null) {
            System.out.print("\n" + new String(new char[prev.length()]).replace("\0", " "));
            String thisLine = "->" + (printWholeNode ? "(" + cur.getRight().getData() + ", " + cur.getRight().getHeight() + ", " + cur.getRight().getBalanceFactor() + ")" : "(" + cur.getRight().getData() + ")");
            System.out.print(thisLine);
            printRecurse(prev + thisLine, cur.getRight());
        } else {
            System.out.print("\n" + new String(new char[prev.length()]).replace("\0", " ") + "->|");
        }
    }

    // Depends on levelorder working
    private Integer[] toArray() { return this.avlTree.levelorder().toArray(new Integer[this.avlTree.size()]); }

    @Test
    public void testConstructor() {
        assertEquals(0, avlTree.size());

        avlTree = new AVL(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertEquals(7, avlTree.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExpectIAE() {
        avlTree = new AVL(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExpectIAE2() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 5, null, 6, 7, 8, 9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExpectIAE() {
        avlTree.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveExpectIAE() {
        avlTree.remove(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveExpectNSEE() {
        avlTree = new AVL();

        try {
            avlTree.remove(0);
            fail();
        } catch(NoSuchElementException ex) { }

        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 6, 7, 8, 9, 10));
        avlTree.remove(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetExpectIAE() {
        avlTree.get(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetExpectNSEE() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3));
        avlTree.get(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsExpectIAE() {
        avlTree.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testSize() {
        assertEquals(0, avlTree.size());

        avlTree.add(9);
        assertEquals(1, avlTree.size());

        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertEquals(11, avlTree.size());

        avlTree.remove(5);
        assertEquals(10, avlTree.size());

        avlTree.remove(3);
        assertEquals(9, avlTree.size());

        avlTree.remove(0);
        avlTree.remove(1);
        avlTree.remove(2);
        avlTree.remove(4);
        avlTree.remove(6);
        avlTree.remove(7);
        avlTree.remove(8);
        avlTree.remove(9);
        assertEquals(1, avlTree.size());
        avlTree.remove(10);
        assertEquals(0, avlTree.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertEquals(0, avlTree.size());
        assertNull(avlTree.getRoot());

        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4));
        assertEquals(5, avlTree.size());
        assertNotNull(avlTree.getRoot());

        avlTree.clear();
        assertEquals(0, avlTree.size());
        assertNull(avlTree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals(-1, avlTree.height());

        avlTree = new AVL(Arrays.asList(3, 4, 1, 5, 6, 7, 11, 20, 34, 22, 50, 9));
        assertEquals(3, avlTree.height());

        avlTree = new AVL(Arrays.asList(0));
        avlTree.getRoot().setHeight(99); // Intentionally broken
        assertEquals(99, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        assertArrayEquals(new Integer[] {7, 3, 1, 0, 2, 5, 4, 6, 9, 8, 10, 11}, avlTree.preorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        assertArrayEquals(new Integer[] {0, 2, 1, 4, 6, 5, 3, 8, 11, 10, 9, 7}, avlTree.postorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        assertArrayEquals(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, avlTree.inorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        assertArrayEquals(new Integer[] {7, 3, 9, 1, 5, 8, 10, 0, 2, 4, 6, 11}, avlTree.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddSimple() {
        assertEquals(0, avlTree.size());
        avlTree.add(0);
        assertEquals(1, avlTree.size());

        avlTree.add(0);
        assertEquals("Incremented size when adding same data", 1, avlTree.size());
        assertSame(0, avlTree.getRoot().getData()); // |Checking that 0 was not again added to the AVL
        assertNull(avlTree.getRoot().getLeft()); //    |
        assertNull(avlTree.getRoot().getRight()); //   |
    }

    @Test(timeout = TIMEOUT)
    public void testAddComplex() {
        avlTree = new AVL(Arrays.asList(0, 1));
        assertArrayEquals(new Integer[] {0, 1}, toArray());

        avlTree = new AVL(Arrays.asList(0, 1, 2));
        assertArrayEquals("Improper leftward rotation",new Integer[] {1, 0, 2}, toArray());

        avlTree = new AVL(Arrays.asList(0, -1, -2));
        assertArrayEquals("Improper rightward rotation", new Integer[] {-1, -2, 0}, toArray());

        avlTree = new AVL(Arrays.asList(0, 2, 1));
        assertArrayEquals("Improper right=left rotation", new Integer[] {1, 0, 2}, toArray());

        avlTree = new AVL(Arrays.asList(0, -2, -1));
        assertArrayEquals("Improper left-right rotation", new Integer[] {-1, -2, 0}, toArray());

        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4));
        AVLNode<Integer> cur = avlTree.getRoot().getRight().getRight();
        cur.setData(5);                       // | Intentionally broken. Not an AVL
        cur.setLeft(new AVLNode<Integer>(4));//  |
        avlTree.add(6);
        assertArrayEquals("Improper left rotation with spectator nodes", new Integer[] {3, 1, 5, 0, 2, 4, 6}, toArray());

        avlTree = new AVL(Arrays.asList(0, -1, -2, -3, -4));
        cur = avlTree.getRoot().getLeft().getLeft();
        cur.setData(-5);                       // | Intentionally broken. Not an AVL
        cur.setRight(new AVLNode<Integer>(-4));// |
        avlTree.add(-6);
        assertArrayEquals("Improper right rotation with spectator nodes", new Integer[] {-3, -5, -1, -6, -4, -2, 0}, toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAncestorUpdate() {
        avlTree = new AVL(Arrays.asList(0));
        assertSame(0, avlTree.getRoot().getData());
        assertSame(0, avlTree.getRoot().getHeight());
        assertSame(0, avlTree.getRoot().getBalanceFactor());

        avlTree.add(1);
        assertSame(0, avlTree.getRoot().getData());
        assertSame(1, avlTree.getRoot().getHeight());
        assertSame(-1, avlTree.getRoot().getBalanceFactor());
        assertSame(1, avlTree.getRoot().getRight().getData());
        assertSame(0, avlTree.getRoot().getRight().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getBalanceFactor());

        avlTree = new AVL(Arrays.asList(0));
        avlTree.add(-1);
        assertSame(0, avlTree.getRoot().getData());
        assertSame(1, avlTree.getRoot().getHeight());
        assertSame(1, avlTree.getRoot().getBalanceFactor());
        avlTree.add(1);
        assertSame(0, avlTree.getRoot().getData());
        assertSame(1, avlTree.getRoot().getHeight());
        assertSame(0, avlTree.getRoot().getBalanceFactor());

        avlTree = new AVL(Arrays.asList(0));
        avlTree.add(-1);
        assertSame(0, avlTree.getRoot().getData());
        assertSame(1, avlTree.getRoot().getHeight());
        assertSame(1, avlTree.getRoot().getBalanceFactor());
        avlTree.add(-2);
        assertSame(-1, avlTree.getRoot().getData());
        assertSame(1, avlTree.getRoot().getHeight());
        assertSame(0, avlTree.getRoot().getBalanceFactor());
        assertSame(-2, avlTree.getRoot().getLeft().getData());
        assertSame(0, avlTree.getRoot().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getData());
        assertSame(0, avlTree.getRoot().getRight().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getBalanceFactor());

        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        assertArrayEquals(new Integer[] {3, 1, 5, 0, 2, 4, 6}, toArray());
        assertSame(2, avlTree.getRoot().getHeight());
        assertSame(1, avlTree.getRoot().getRight().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getRight().getHeight());
        assertSame(1, avlTree.getRoot().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getRight().getHeight());

        assertSame(0, avlTree.getRoot().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
        avlTree.add(7);
        assertSame(3, avlTree.getRoot().getHeight());
        assertSame(2, avlTree.getRoot().getRight().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertSame(1, avlTree.getRoot().getRight().getRight().getHeight());
        assertSame(1, avlTree.getRoot().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getRight().getHeight());

        assertSame(-1, avlTree.getRoot().getBalanceFactor());
        assertSame(-1, avlTree.getRoot().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertSame(-1, avlTree.getRoot().getRight().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getRight().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 6, 7));
        assertArrayEquals(new Integer[] {3, 1, 6, 0, 2, 7}, toArray());

        assertSame(3, avlTree.get(3));
        assertArrayEquals(new Integer[] {3, 1, 6, 0, 2, 7}, toArray());

        assertSame(1, avlTree.get(1));
        assertSame(6, avlTree.get(6));
        assertSame(2, avlTree.get(2));
        assertSame(0, avlTree.get(0));
        assertSame(7, avlTree.get(7));
        assertArrayEquals(new Integer[] {3, 1, 6, 0, 2, 7}, toArray());

        try {
            avlTree.get(-1);
            fail();
        } catch(NoSuchElementException ex) { }
        try {
            avlTree.get(5);
            fail();
        } catch(NoSuchElementException ex) { }
        try {
            avlTree.get(8);
            fail();
        } catch(NoSuchElementException ex) { }
    }

    @Test(timeout = TIMEOUT)
    public void testGetUnequalReturn() {
        AVL<UnequalReturn> avl = new AVL(Arrays.asList(new UnequalReturn(true, true)));
        UnequalReturn inputDatum = new UnequalReturn(true, false);
        assertNotSame("Don't return the input datum.", inputDatum, avl.get(inputDatum));
    }

    private class UnequalReturn implements Comparable<Comparable> {
        boolean a, b;

        public UnequalReturn(boolean a, boolean b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Comparable o) { return Boolean.compare(this.a, ((UnequalReturn)o).a); }

        @Override
        public boolean equals(Object obj) { return this.a == ((UnequalReturn)obj).a; }
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 6, 7));
        assertArrayEquals(new Integer[] {3, 1, 6, 0, 2, 7}, toArray());

        assertTrue(avlTree.contains(3));
        assertArrayEquals(new Integer[] {3, 1, 6, 0, 2, 7}, toArray());

        assertTrue(avlTree.contains(1));
        assertTrue(avlTree.contains(6));
        assertTrue(avlTree.contains(2));
        assertTrue(avlTree.contains(0));
        assertTrue(avlTree.contains(7));
        assertArrayEquals(new Integer[] {3, 1, 6, 0, 2, 7}, toArray());

        assertFalse(avlTree.contains(-1));
        assertFalse(avlTree.contains(5));
        assertFalse(avlTree.contains(8));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveSimple() {
        try {
            avlTree.remove(0);
            fail();
        } catch (NoSuchElementException ex) { }

        avlTree = new AVL(Arrays.asList(1000));
        assertSame(1, avlTree.size());
        assertEquals(1000, (int)avlTree.remove(1000));
        assertSame(0, avlTree.size());

        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 5, 6, 7));
        assertSame(7, avlTree.size());
        assertEquals(2, (int)avlTree.remove(2));
        assertSame(6, avlTree.size());
        assertEquals(7, (int)avlTree.remove(7));
        assertSame(5, avlTree.size());
        assertEquals(0, (int)avlTree.remove(0));
        assertSame(4, avlTree.size());

        assertEquals(3, (int)avlTree.remove(3));
        assertArrayEquals(new Integer[] {5, 1, 6}, toArray());
        assertSame(3, avlTree.size());

        avlTree.remove(5);
        avlTree.remove(1);
        assertSame(1, avlTree.size());
        assertArrayEquals(new Integer[] {6}, toArray());

        assertEquals(6, (int)avlTree.remove(6));
        assertSame(0, avlTree.size());
        assertNull(avlTree.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveComplex() {
        avlTree = new AVL(Arrays.asList(0, 1, 3, 4, 5, 6, 7, 8, -1));
        avlTree.remove(3);
        assertArrayEquals("Failed to right rotate after removal of 3.", new Integer[] {4, 0, 6, -1, 1, 5, 7, 8}, toArray());
        avlTree.remove(4);
        assertArrayEquals("Failed to left rotate after removal of 4.", new Integer[] {5, 0, 7, -1, 1, 6, 8}, toArray());
        List<Integer> inorder = avlTree.inorder();
        avlTree.remove(5);
        assertArrayEquals(new Integer[] {6, 0, 7, -1, 1, 8}, toArray());
        avlTree.remove(-1);
        avlTree.remove(7);
        avlTree.remove(8);
        assertArrayEquals("Failed to left-right rotate after removal of 8.", new Integer[] {1, 0, 6}, toArray());
        avlTree = new AVL(inorder);
        avlTree.remove(8);
        avlTree.remove(-1);
        avlTree.remove(1);
        avlTree.remove(0);
        assertArrayEquals("Failed to right-left rotate after removal of 0.", new Integer[] {6, 5, 7}, toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAncestorUpdate() {
        avlTree = new AVL(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        assertArrayEquals(new Integer[] {7, 3, 9, 1, 5, 8, 10, 0, 2, 4, 6, 11}, toArray());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getRight().getHeight());
        assertSame(1, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertSame(1, avlTree.getRoot().getLeft().getRight().getHeight());
        assertSame(2, avlTree.getRoot().getLeft().getHeight());
        assertSame(2, avlTree.getRoot().getRight().getHeight());
        assertSame(1, avlTree.getRoot().getRight().getRight().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getRight().getRight().getHeight());
        assertSame(3, avlTree.getRoot().getHeight());

        assertSame(0, avlTree.getRoot().getLeft().getLeft().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertSame(-1, avlTree.getRoot().getRight().getBalanceFactor());
        assertSame(-1, avlTree.getRoot().getRight().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getRight().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getBalanceFactor());
        avlTree.remove(4);
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getRight().getHeight());
        assertSame(1, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertSame(1, avlTree.getRoot().getLeft().getRight().getHeight());
        assertSame(2, avlTree.getRoot().getLeft().getHeight());
        assertSame(2, avlTree.getRoot().getRight().getHeight());
        assertSame(1, avlTree.getRoot().getRight().getRight().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getRight().getRight().getHeight());
        assertSame(3, avlTree.getRoot().getHeight());

        assertSame(0, avlTree.getRoot().getLeft().getLeft().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertSame(-1, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertSame(-1, avlTree.getRoot().getRight().getBalanceFactor());
        assertSame(-1, avlTree.getRoot().getRight().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getRight().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getBalanceFactor());

        avlTree.remove(8);
        assertSame(3, avlTree.getRoot().getHeight());
        assertSame(1, avlTree.getRoot().getRight().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getRight().getRight().getHeight());

        assertSame(1, avlTree.getRoot().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getRight().getRight().getBalanceFactor());

        avlTree.remove(6);
        avlTree.remove(0);
        avlTree.remove(5);
        assertSame(2, avlTree.getRoot().getHeight());
        assertSame(1, avlTree.getRoot().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getHeight());
        assertSame(0, avlTree.getRoot().getLeft().getRight().getHeight());

        assertSame(0, avlTree.getRoot().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getLeft().getBalanceFactor());
        assertSame(0, avlTree.getRoot().getLeft().getRight().getBalanceFactor());
    }
}
