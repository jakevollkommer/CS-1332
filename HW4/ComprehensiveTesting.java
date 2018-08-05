import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by samgilsonmac on 1/31/17.
 * Version: 1.2
 */

public class ComprehensiveTesting {
    private BSTInterface<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(5);
        bst.add(6);
        bst.add(4);
        bst.add(4);
        bst.add(1);
        bst.add(5);
        bst.add(0);
        bst.add(2);
        bst.add(1);
        bst.add(10);


        assertEquals(7, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight().getData());
        assertEquals((Integer) 6, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 0, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Integer) 10, bst.getRoot().getRight().getRight().getRight().getData());
    }


    @Test(timeout = TIMEOUT)
    public void testRemoveEdgeCases() {
        assertEquals(0, bst.size());

        /*
            tests removing a node that has two leaves but right node only has 1 leaf on the right
            START TEST
         */

        bst.add(10);
        bst.add(15);
        bst.add(22);
        bst.add(12);
        bst.add(13);
        bst.add(11);
        bst.add(23);

        assertEquals((Integer) 15, bst.remove(15));
        assertEquals(6, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 22, bst.getRoot().getRight().getData());
        assertEquals((Integer) 23, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 13, bst.getRoot().getRight().getLeft().getRight().getData());
        /*
            END TEST
         */

        bst.clear();

        /*
        tests removing a node that has two leaves but right node has 2 leaves
        START TEST
         */
        bst.add(10);
        bst.add(15);
        bst.add(22);
        bst.add(12);
        bst.add(13);
        bst.add(11);
        bst.add(23);
        bst.add(20);
        bst.add(21);

        assertEquals((Integer) 15, bst.remove(15));
        assertEquals(8, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 20, bst.getRoot().getRight().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 13, bst.getRoot().getRight().getLeft().getRight().getData());
        assertEquals((Integer) 21, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 22, bst.getRoot().getRight().getRight().getRight().getData());
        assertEquals((Integer) 23, bst.getRoot().getRight().getRight().getRight().getRight().getData());


        /*
        END TEST
         */

        /*
        tests the ability to remove two+ nodes
         */
        assertEquals((Integer) 20, bst.remove(20));
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 21, bst.getRoot().getRight().getData());
        assertEquals((Integer) 22, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 23, bst.getRoot().getRight().getRight().getRight().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 13, bst.getRoot().getRight().getLeft().getRight().getData());



        assertEquals((Integer) 21, bst.remove(21));
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 22, bst.getRoot().getRight().getData());
        assertEquals((Integer) 23, bst.getRoot().getRight().getRight().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getLeft().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 13, bst.getRoot().getRight().getLeft().getRight().getData());

        bst.clear();

        /*
        tests removing a node on the right if there is no left node
        START TEST
         */
        bst.add(10);
        bst.add(15);
        bst.add(22);

        assertEquals((Integer) 15, bst.remove(15));
        assertEquals(2, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 22, bst.getRoot().getRight().getData());
        /*
        END TEST
         */

        bst.clear();

        /*
        tests removing a node if there is only a left node and no right node
        START TEST
         */

        bst.add(10);
        bst.add(15);
        bst.add(12);
        bst.add(11);

        assertEquals((Integer) 15, bst.remove(15));
        assertEquals(3, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getData());
        assertEquals((Integer) 11, bst.getRoot().getRight().getLeft().getData());
        /*
        END TEST
         */

        bst.clear();



        /*
        tests removing a leaf node
        START TEST
         */

        bst.add(10);
        bst.add(15);
        bst.add(12);
        bst.add(17);
        bst.add(9);
        assertEquals((Integer) 17, bst.remove(17));
        assertEquals(4, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 15, bst.getRoot().getRight().getData());
        assertEquals((Integer) 9, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getLeft().getData());
        assertEquals(null, bst.getRoot().getRight().getRight());

        assertEquals((Integer) 9, bst.remove(9));
        assertEquals(3, bst.size());
        assertEquals((Integer) 10, bst.getRoot().getData());
        assertEquals((Integer) 15, bst.getRoot().getRight().getData());
        assertEquals((Integer) 12, bst.getRoot().getRight().getLeft().getData());
        assertEquals(null, bst.getRoot().getLeft());

        /*
        END TEST
         */
        bst.clear();

        /*
        tests removing the root node
        START TEST
         */
        bst.add(10);
        bst.add(9);
        bst.add(11);

        assertEquals((Integer) 10, bst.remove(10));
        assertEquals((Integer) 11, bst.getRoot().getData());
        assertEquals((Integer) 9, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 11, bst.remove(11));
        assertEquals((Integer) 9, bst.getRoot().getData());

        /*
        END TEST
         */

        bst.clear();

        /*
        tests remove when the data attempting to be removed is a new integer object
        START TEST
         */

        bst.add(10);
        bst.add(11);

        assertEquals((Integer) 11, bst.remove(new Integer(11)));
        /*
        END TEST
         */

    }
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeListIsEmpty() {

        bst.remove(5);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeNotFound() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.remove(100);

    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertTrue(bst.contains(58));
        assertEquals((Integer) 58, bst.get(58));
        assertTrue(bst.contains(12));
        assertEquals((Integer) 12, bst.get(12));
        assertTrue(bst.contains(94));
        assertEquals((Integer) 94, bst.get(94));
        assertTrue(bst.contains(24));
        assertEquals((Integer) 24, bst.get(24));
    }

    @Test(timeout = TIMEOUT)
    public void testGetDifferent() {
        Integer testingInteger = new Integer(12);

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(testingInteger);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertSame(testingInteger, bst.get(new Integer(12)));
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(24);
        levelorder.add(1);
        levelorder.add(94);
        levelorder.add(7);
        levelorder.add(58);
        levelorder.add(12);
        levelorder.add(73);
        levelorder.add(68);
        levelorder.add(77);

        assertEquals(levelorder, bst.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostOrder() {
        bst.add(7);
        bst.add(4);
        bst.add(5);
        bst.add(3);
        bst.add(10);
        bst.add(9);
        bst.add(11);
        bst.add(12);

        List<Integer> postOrder = new ArrayList<>();
        postOrder.add(3);
        postOrder.add(5);
        postOrder.add(4);
        postOrder.add(9);
        postOrder.add(12);
        postOrder.add(11);
        postOrder.add(10);
        postOrder.add(7);
        assertEquals(postOrder, bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInOrder() {
        bst.add(7);
        bst.add(4);
        bst.add(5);
        bst.add(3);
        bst.add(10);
        bst.add(9);
        bst.add(11);
        bst.add(12);

        List<Integer> inOrder = new ArrayList<>();
        inOrder.add(3);
        inOrder.add(4);
        inOrder.add(5);
        inOrder.add(7);
        inOrder.add(9);
        inOrder.add(10);
        inOrder.add(11);
        inOrder.add(12);

        assertEquals(inOrder, bst.inorder());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addExpectException() {
        bst.add(null);
    }


    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getEmpty() {
        bst.get(20);
    }
}
