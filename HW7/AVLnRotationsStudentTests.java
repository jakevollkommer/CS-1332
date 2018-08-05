
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * These tests are all that I could think of
 * @author Alec
 * @version 1.0
 */

public class AVLnRotationsStudentTests {


    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testPreOrderTraversal() {
        Integer[] correct = {7, 3, 1, 0, 2, 5, 4, 6, 15, 11, 9, 8, 10,
            13, 12, 14, 17, 16, 18, 19};
        for (int i = 0; i < 20; i++) {
            avlTree.add(i);
        }
        assertEquals(20, avlTree.size());
        List<Integer> preorder = avlTree.preorder();
        assertArrayEquals(correct, preorder.toArray());

    }

    @Test(timeout = TIMEOUT)
    public void testInOrderTraversal() {
        Integer[] correct = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19};
        for (int i = 0; i < 20; i++) {
            avlTree.add(i);
        }
        assertEquals(20, avlTree.size());
        List<Integer> inorder = avlTree.inorder();
        assertArrayEquals(correct, inorder.toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testPostOrderTraversal() {
        Integer[] correct = {0, 2, 1, 4, 6, 5, 3, 8, 10, 9, 12, 14, 13,
            11, 16, 19, 18, 17, 15, 7};
        for (int i = 0; i < 20; i++) {
            avlTree.add(i);
        }
        assertEquals(20, avlTree.size());
        List<Integer> postorder = avlTree.postorder();
        assertArrayEquals(correct, postorder.toArray());


    }

    @Test(timeout = TIMEOUT)
    public void testLevelOrderTraversal() {
        Integer[] correct = {7, 3, 15, 1, 5, 11, 17, 0, 2, 4, 6, 9, 13,
            16, 18, 8, 10, 12, 14, 19};
        for (int i = 0; i < 20; i++) {
            avlTree.add(i);
        }
        assertEquals(20, avlTree.size());
        List<Integer> levelorder = avlTree.levelorder();
        assertArrayEquals(correct, levelorder.toArray());

    }

    @Test(timeout = TIMEOUT)
    public void testAddRightLeftRotationRoot() {
        avlTree.add(3);
        avlTree.add(5);
        avlTree.add(4);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 4, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals((Integer) 3,
                root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 5,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveGeneral() {
        Integer toBeRemoved = new Integer(526);
        avlTree.add(646);
        avlTree.add(386);
        avlTree.add(856);
        avlTree.add(toBeRemoved);
        avlTree.add(477);

        assertSame(toBeRemoved, avlTree.remove(new Integer(526)));

        assertEquals(4, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 646, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        assertEquals((Integer) 477,
                root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 856,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void removeRoot() {
        avlTree.add(50);
        avlTree.add(100);
        avlTree.add(25);
        avlTree.add(15);
        avlTree.add(30);
        avlTree.add(60);
        avlTree.add(125);
        avlTree.add(5);
        avlTree.add(55);
        avlTree.add(70);
        avlTree.add(110);
        avlTree.add(130);
        avlTree.add(80);
        avlTree.add(105);

        assertEquals(14, avlTree.size());

        avlTree.remove(50);
        assertEquals(13, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 55, root.getData());
        assertEquals(4, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        assertEquals((Integer) 100, root.getRight().getData());
        assertEquals((Integer) 70, root.getRight().getLeft().getData());
        assertEquals((Integer) 60, root.getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 80, root.getRight().getLeft().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals(-1, avlTree.height());

        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.add(4);
        avlTree.add(5);
        avlTree.add(6);
        avlTree.add(7);
        assertEquals(2, avlTree.height());

        avlTree.add(8);
        assertEquals(3, avlTree.height());

        for (int i = 9; i <= 1024; i++) {
            avlTree.add(i);
        }
        assertEquals(10, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(100);
        avlTree.add(90);
        avlTree.add(10);
        avlTree.add(75);
        avlTree.add(40);
        avlTree.add(15);
        avlTree.add(5);
        avlTree.add(1);


        Integer search1 = new Integer(50);
        Integer search2 = new Integer(75);
        Integer search3 = new Integer(1);

        assertEquals(search1, avlTree.get(new Integer(50)));
        assertEquals(search2, avlTree.get(new Integer(75)));
        assertEquals(search3, avlTree.get(new Integer(1)));

        //assertSame(falseSearch, avlTree.get(new Integer(27)));

    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(100);
        avlTree.add(75);
        avlTree.add(90);
        avlTree.add(10);
        avlTree.add(40);
        avlTree.add(15);
        avlTree.add(5);
        avlTree.add(1);

        Integer search1 = new Integer(50);
        Integer search2 = new Integer(75);
        Integer search3 = new Integer(1);


        assertTrue(avlTree.contains(search1));
        assertTrue(avlTree.contains(search2));
        assertTrue(avlTree.contains(search3));

        assertFalse(avlTree.contains(new Integer(7)));
    }

    //**********
    //Rotations without subtrees
    //**********
    @Test(timeout = TIMEOUT)
    public void testAddRightRotationNoSubtrees() {
        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(10);

        assertEquals(3, avlTree.size());
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 25, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        assertEquals((Integer) 50, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        assertEquals((Integer) 10, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void testAddLeftRotationNoSubtrees() {
        avlTree.add(50);
        avlTree.add(100);
        avlTree.add(150);

        assertEquals(3, avlTree.size());
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 100, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        assertEquals((Integer) 150, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        assertEquals((Integer) 50, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());


    }

    @Test(timeout = TIMEOUT)
    public void testAddLeftRightRotationNoSubtrees() {
        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(42);

        assertEquals(3, avlTree.size());
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 42, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        assertEquals((Integer) 50, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightLeftRotationNoSubtrees() {
        avlTree.add(50);
        avlTree.add(100);
        avlTree.add(75);

        assertEquals(3, avlTree.size());
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 75, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        assertEquals((Integer) 100, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        assertEquals((Integer) 50, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRightRotationNoSubtrees() {
        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(75);
        avlTree.add(10);

        assertEquals(4, avlTree.size());

        avlTree.remove(75);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();

        assertEquals((Integer) 25, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        assertEquals((Integer) 50, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        assertEquals((Integer) 10, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLeftRotationNoSubtrees() {
        avlTree.add(50);
        avlTree.add(100);
        avlTree.add(25);
        avlTree.add(150);

        assertEquals(4, avlTree.size());

        avlTree.remove(25);
        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();

        assertEquals((Integer) 100, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        assertEquals((Integer) 150, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        assertEquals((Integer) 50, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());


    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLeftRightRotationNoSubtrees() {
        avlTree.add(50);
        avlTree.add(100);
        avlTree.add(25);
        avlTree.add(42);

        assertEquals(4, avlTree.size());

        avlTree.remove(100);
        assertEquals(3, avlTree.size());


        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 42, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        assertEquals((Integer) 50, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRightLeftRotationNoSubtrees() {
        avlTree.add(50);
        avlTree.add(100);
        avlTree.add(25);
        avlTree.add(75);

        assertEquals(4, avlTree.size());

        avlTree.remove(25);
        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 75, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());

        assertEquals((Integer) 100, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        assertEquals((Integer) 50, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
    }

    //**********
    //Rotations with subtrees
    //**********
    @Test(timeout = TIMEOUT)
    public void testAddRightRotationWithSubtrees() {
        avlTree.add(50);
        avlTree.add(25);

        avlTree.add(100);
        avlTree.add(90);

        avlTree.add(10);
        avlTree.add(40);
        avlTree.add(15);
        avlTree.add(5);
        avlTree.add(1);

        assertEquals(9, avlTree.size());
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals(3, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        assertEquals((Integer) 10,
                root.getLeft().getData());
        assertEquals(2, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 100,
                root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(1, root.getRight().getBalanceFactor());

        //subtrees maintained?
        AVLNode<Integer> rotatedTop = avlTree.getRoot().getLeft();

        assertEquals((Integer) 25,
                rotatedTop.getRight().getData());
        assertEquals(1, rotatedTop.getRight().getHeight());
        assertEquals(0, rotatedTop.getRight().getBalanceFactor());

        assertEquals((Integer) 15,
                rotatedTop.getRight().getLeft().getData());
        assertEquals(0, rotatedTop.getRight().getLeft().getHeight());
        assertEquals(0, rotatedTop.getRight()
                .getLeft().getBalanceFactor());

        assertEquals((Integer) 40,
                rotatedTop.getRight().getRight().getData());
        assertEquals(0, rotatedTop.getRight().getRight().getHeight());
        assertEquals(0, rotatedTop.getRight()
                .getRight().getBalanceFactor());

        assertNull(rotatedTop.getLeft().getRight());

        assertEquals((Integer) 1,
                rotatedTop.getLeft().getLeft().getData());
        assertEquals(0, rotatedTop.getLeft().getLeft().getHeight());
        assertEquals(0, rotatedTop.getLeft()
                .getLeft().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void testAddLeftRotationWithSubtrees() {
        avlTree.add(50);
        avlTree.add(25);

        avlTree.add(100);
        avlTree.add(15);

        avlTree.add(60);
        avlTree.add(125);
        avlTree.add(110);
        avlTree.add(130);
        avlTree.add(131);

        assertEquals(9, avlTree.size());
        AVLNode<Integer> root = avlTree.getRoot();

        assertEquals((Integer) 50, root.getData());
        assertEquals(3, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());

        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());

        assertEquals((Integer) 125, root.getRight().getData());
        assertEquals(2, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        //subtrees maintained?
        AVLNode<Integer> rotatedTop = avlTree.getRoot().getRight();

        assertEquals((Integer) 130,
                rotatedTop.getRight().getData());
        assertEquals(1, rotatedTop.getRight().getHeight());
        assertEquals(-1, rotatedTop.getRight().getBalanceFactor());

        assertEquals((Integer) 131,
                rotatedTop.getRight().getRight().getData());
        assertEquals(0, rotatedTop.getRight().getRight().getHeight());
        assertEquals(0, rotatedTop.getRight()
                .getRight().getBalanceFactor());

        assertEquals((Integer) 100,
                rotatedTop.getLeft().getData());
        assertEquals(1, rotatedTop.getLeft().getHeight());
        assertEquals(0, rotatedTop.getLeft().getBalanceFactor());

        assertNull(rotatedTop.getRight().getLeft());


        assertEquals((Integer) 110,
                rotatedTop.getLeft().getRight().getData());
        assertEquals(0, rotatedTop.getLeft().getRight().getHeight());
        assertEquals(0, rotatedTop.getLeft()
                .getRight().getBalanceFactor());




    }

    @Test(timeout = TIMEOUT)
    public void testAddLeftRightRotationWithSubtrees() {
        avlTree.add(50);
        avlTree.add(25);

        avlTree.add(100);
        avlTree.add(90);

        avlTree.add(10);
        avlTree.add(40);
        avlTree.add(5);
        //8
        avlTree.add(8);

        assertEquals(8, avlTree.size());
        AVLNode<Integer> root = avlTree.getRoot();

        assertEquals((Integer) 50, root.getData());
        assertEquals(3, root.getHeight());
        assertEquals(1, root.getBalanceFactor());

        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals(2, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());

        assertEquals((Integer) 100, root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(1, root.getRight().getBalanceFactor());

        //subtrees maintained?
        AVLNode<Integer> rotatedTop = avlTree.getRoot().getLeft();

        assertEquals((Integer) 40,
                rotatedTop.getRight().getData());
        assertEquals(0, rotatedTop.getRight().getHeight());
        assertEquals(0, rotatedTop.getRight().getBalanceFactor());

        assertEquals((Integer) 5,
                rotatedTop.getLeft().getLeft().getData());
        assertEquals(0, rotatedTop.getLeft().getLeft().getHeight());
        assertEquals(0, rotatedTop.getLeft()
                .getLeft().getBalanceFactor());

        assertEquals((Integer) 8,
                rotatedTop.getLeft().getData());
        assertEquals(1, rotatedTop.getLeft().getHeight());
        assertEquals(0, rotatedTop.getLeft().getBalanceFactor());

        assertEquals((Integer) 10,
                rotatedTop.getLeft().getRight().getData());
        assertEquals(0, rotatedTop.getLeft().getRight().getHeight());
        assertEquals(0, rotatedTop.getLeft()
                .getRight().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void testAddRightLeftRotationWithSubtrees() {
        avlTree.add(50);
        avlTree.add(25);

        avlTree.add(100);
        avlTree.add(15);

        avlTree.add(60);
        avlTree.add(125);
        avlTree.add(110);
        avlTree.add(130);
        avlTree.add(105);

        assertEquals(9, avlTree.size());
        AVLNode<Integer> root = avlTree.getRoot();

        assertEquals((Integer) 50, root.getData());
        assertEquals(3, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());

        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());

        assertEquals((Integer) 110, root.getRight().getData());
        assertEquals(2, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        //subtrees maintained?
        AVLNode<Integer> rotatedTop = avlTree.getRoot().getRight();

        assertEquals((Integer) 125,
                rotatedTop.getRight().getData());
        assertEquals(1, rotatedTop.getRight().getHeight());
        assertEquals(-1, rotatedTop.getRight().getBalanceFactor());

        assertEquals((Integer) 130,
                rotatedTop.getRight().getRight().getData());
        assertEquals(0, rotatedTop.getRight().getRight().getHeight());
        assertEquals(0, rotatedTop.getRight()
                .getRight().getBalanceFactor());

        assertEquals((Integer) 100,
                rotatedTop.getLeft().getData());
        assertEquals(1, rotatedTop.getLeft().getHeight());
        assertEquals(0, rotatedTop.getLeft().getBalanceFactor());

        assertNull(rotatedTop.getRight().getLeft());

        assertEquals((Integer) 105,
                rotatedTop.getLeft().getRight().getData());
        assertEquals(0, rotatedTop.getLeft().getRight().getHeight());
        assertEquals(0, rotatedTop.getLeft()
                .getRight().getBalanceFactor());


    }

    //**********
    //Exceptions
    //**********
    @Test(expected = IllegalArgumentException.class)
    public void collectionExceptionTester() throws IllegalArgumentException {
        avlTree = new AVL<>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void collectionExceptionTester2() throws IllegalArgumentException {
        List<Integer> list = new ArrayList<>();
        list.add(7);
        list.add(null);
        list.add(12);
        avlTree = new AVL<>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAdd() throws IllegalArgumentException {
        avlTree.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() throws IllegalArgumentException {
        avlTree.remove(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveNotInTree() throws java.util.NoSuchElementException {
        avlTree.add(8);
        avlTree.add(6);
        avlTree.add(5);
        avlTree.remove(7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNull() throws IllegalArgumentException {
        avlTree.get(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testGetNotInTree() throws java.util.NoSuchElementException {
        avlTree.add(8);
        avlTree.add(6);
        avlTree.add(5);
        avlTree.get(7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void contains() throws IllegalArgumentException {
        avlTree.contains(null);
    }

}
