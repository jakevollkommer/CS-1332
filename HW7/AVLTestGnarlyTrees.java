
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


import org.junit.Before;
import org.junit.Test;

/**
 * These tests are not exhaustive.
 * @author CS 1332 TAs + Sam Gilson
 * @version 1.0
 */
public class AVLTestGnarlyTrees {
    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightRotation() {
        avlTree.add(5);
        avlTree.add(4);
        avlTree.add(3);

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
    public void testAdd() {
        avlTree.add(15);
        avlTree.add(20);
        avlTree.add(18);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 18, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals((Integer) 15,
                root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 20,
                root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());

        avlTree.add(22);
        root = avlTree.getRoot();
        avlTree.add(21);

        root = avlTree.getRoot();
        assertEquals((Integer) 18, root.getData());
        assertEquals((Integer) 15, root.getLeft().getData());
        assertEquals((Integer) 21, root.getRight().getData());
        assertEquals((Integer) 22, root.getRight().getRight().getData());
        assertEquals((Integer) 20, root.getRight().getLeft().getData());
        avlTree.add(17);
        root = avlTree.getRoot();
        assertEquals((Integer) 18, root.getData());
        assertEquals((Integer) 15, root.getLeft().getData());
        assertEquals((Integer) 21, root.getRight().getData());
        assertEquals((Integer) 22, root.getRight().getRight().getData());
        assertEquals((Integer) 20, root.getRight().getLeft().getData());
        assertEquals((Integer) 17, root.getLeft().getRight().getData());
        avlTree.add(23);
        root = avlTree.getRoot();
        assertEquals((Integer) 18, root.getData());
        assertEquals((Integer) 15, root.getLeft().getData());
        assertEquals((Integer) 21, root.getRight().getData());
        assertEquals((Integer) 22, root.getRight().getRight().getData());
        assertEquals((Integer) 20, root.getRight().getLeft().getData());
        assertEquals((Integer) 17, root.getLeft().getRight().getData());
        assertEquals((Integer) 23, root.getRight().getRight().getRight().getData());
        avlTree.add(24);
        assertEquals((Integer) 18, root.getData());
        assertEquals((Integer) 15, root.getLeft().getData());
        assertEquals((Integer) 21, root.getRight().getData());
        assertEquals((Integer) 20, root.getRight().getLeft().getData());
        assertEquals((Integer) 17, root.getLeft().getRight().getData());
        assertEquals((Integer) 23, root.getRight().getRight().getData());
        assertEquals((Integer) 22, root.getRight().getRight().getLeft().getData());
        assertEquals((Integer) 24, root.getRight().getRight().getRight().getData());
        avlTree.add(16);
        assertEquals((Integer) 18, root.getData());
        assertEquals((Integer) 16, root.getLeft().getData());
        assertEquals((Integer) 15, root.getLeft().getLeft().getData());
        assertEquals((Integer) 21, root.getRight().getData());
        assertEquals((Integer) 20, root.getRight().getLeft().getData());
        assertEquals((Integer) 17, root.getLeft().getRight().getData());
        assertEquals((Integer) 23, root.getRight().getRight().getData());
        assertEquals((Integer) 22, root.getRight().getRight().getLeft().getData());
        assertEquals((Integer) 24, root.getRight().getRight().getRight().getData());

        assertEquals(3, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());




    }

    @Test(timeout = TIMEOUT)
    public void testAddGnarlyTrees() {
        avlTree.add(874);
        avlTree.add(559);
        avlTree.add(714);
        avlTree.add(631);
        avlTree.add(433);
        avlTree.add(122);
        avlTree.add(206);
        avlTree.add(397);
        avlTree.add(237);
        avlTree.add(195);
        avlTree.add(678);
        avlTree.add(715);
        avlTree.add(364);
        avlTree.add(78);
        avlTree.add(680);
        avlTree.add(908);
        avlTree.add(593);

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 559, root.getData());
        assertEquals((Integer) 206, root.getLeft().getData());
        assertEquals((Integer) 714, root.getRight().getData());
        assertEquals((Integer) 122, root.getLeft().getLeft().getData());
        assertEquals((Integer) 195, root.getLeft().getLeft().getRight().getData());
        assertEquals((Integer) 78, root.getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 397, root.getLeft().getRight().getData());
        assertEquals((Integer) 433, root.getLeft().getRight().getRight().getData());
        assertEquals((Integer) 237, root.getLeft().getRight().getLeft().getData());
        assertEquals((Integer) 364, root.getLeft().getRight().getLeft().getRight().getData());
        assertEquals((Integer) 678, root.getRight().getLeft().getData());
        assertEquals((Integer) 680, root.getRight().getLeft().getRight().getData());
        assertEquals((Integer) 631, root.getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 593, root.getRight().getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 874, root.getRight().getRight().getData());
        assertEquals((Integer) 715, root.getRight().getRight().getLeft().getData());
        assertEquals((Integer) 908, root.getRight().getRight().getRight().getData());






    }

    @Test(timeout = TIMEOUT)
    public void testRemoveGnarlyTrees() {
        avlTree.add(874);
        avlTree.add(559);
        avlTree.add(714);
        avlTree.add(631);
        avlTree.add(433);
        avlTree.add(122);
        avlTree.add(206);
        avlTree.add(397);
        avlTree.add(237);
        avlTree.add(195);
        avlTree.add(678);
        avlTree.add(715);
        avlTree.add(364);
        avlTree.add(78);
        avlTree.add(680);
        avlTree.add(908);
        avlTree.add(593);

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 559, root.getData());
        assertEquals((Integer) 206, root.getLeft().getData());
        assertEquals((Integer) 714, root.getRight().getData());
        assertEquals((Integer) 122, root.getLeft().getLeft().getData());
        assertEquals((Integer) 195, root.getLeft().getLeft().getRight().getData());
        assertEquals((Integer) 78, root.getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 397, root.getLeft().getRight().getData());
        assertEquals((Integer) 433, root.getLeft().getRight().getRight().getData());
        assertEquals((Integer) 237, root.getLeft().getRight().getLeft().getData());
        assertEquals((Integer) 364, root.getLeft().getRight().getLeft().getRight().getData());
        assertEquals((Integer) 678, root.getRight().getLeft().getData());
        assertEquals((Integer) 680, root.getRight().getLeft().getRight().getData());
        assertEquals((Integer) 631, root.getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 593, root.getRight().getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 874, root.getRight().getRight().getData());
        assertEquals((Integer) 715, root.getRight().getRight().getLeft().getData());
        assertEquals((Integer) 908, root.getRight().getRight().getRight().getData());

        assertEquals(new Integer(714), avlTree.remove(new Integer(714)));

        root = avlTree.getRoot();
        assertEquals((Integer) 559, root.getData());
        assertEquals((Integer) 206, root.getLeft().getData());
        assertEquals((Integer) 715, root.getRight().getData());
        assertEquals((Integer) 122, root.getLeft().getLeft().getData());
        assertEquals((Integer) 195, root.getLeft().getLeft().getRight().getData());
        assertEquals((Integer) 78, root.getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 397, root.getLeft().getRight().getData());
        assertEquals((Integer) 433, root.getLeft().getRight().getRight().getData());
        assertEquals((Integer) 237, root.getLeft().getRight().getLeft().getData());
        assertEquals((Integer) 364, root.getLeft().getRight().getLeft().getRight().getData());
        assertEquals((Integer) 678, root.getRight().getLeft().getData());
        assertEquals((Integer) 680, root.getRight().getLeft().getRight().getData());
        assertEquals((Integer) 631, root.getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 593, root.getRight().getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 874, root.getRight().getRight().getData());
        assertEquals((Integer) 908, root.getRight().getRight().getRight().getData());

        assertEquals(new Integer(559), avlTree.remove(new Integer(559)));

        root = avlTree.getRoot();
        assertEquals((Integer) 593, root.getData());
        assertEquals((Integer) 206, root.getLeft().getData());
        assertEquals((Integer) 715, root.getRight().getData());
        assertEquals((Integer) 122, root.getLeft().getLeft().getData());
        assertEquals((Integer) 195, root.getLeft().getLeft().getRight().getData());
        assertEquals((Integer) 78, root.getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 397, root.getLeft().getRight().getData());
        assertEquals((Integer) 433, root.getLeft().getRight().getRight().getData());
        assertEquals((Integer) 237, root.getLeft().getRight().getLeft().getData());
        assertEquals((Integer) 364, root.getLeft().getRight().getLeft().getRight().getData());
        assertEquals((Integer) 678, root.getRight().getLeft().getData());
        assertEquals((Integer) 680, root.getRight().getLeft().getRight().getData());
        assertEquals((Integer) 631, root.getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 874, root.getRight().getRight().getData());
        assertEquals((Integer) 908, root.getRight().getRight().getRight().getData());

        assertEquals(new Integer(433), avlTree.remove(new Integer(433)));

        assertEquals((Integer) 593, root.getData());
        assertEquals((Integer) 206, root.getLeft().getData());
        assertEquals((Integer) 715, root.getRight().getData());
        assertEquals((Integer) 122, root.getLeft().getLeft().getData());
        assertEquals((Integer) 195, root.getLeft().getLeft().getRight().getData());
        assertEquals((Integer) 78, root.getLeft().getLeft().getLeft().getData());
        assertEquals((Integer) 364, root.getLeft().getRight().getData());
        assertEquals((Integer) 237, root.getLeft().getRight().getLeft().getData());
        assertEquals((Integer) 397, root.getLeft().getRight().getRight().getData());
        assertEquals((Integer) 678, root.getRight().getLeft().getData());
        assertEquals((Integer) 680, root.getRight().getLeft().getRight().getData());
        assertEquals((Integer) 631, root.getRight().getLeft().getLeft().getData());
        assertEquals((Integer) 874, root.getRight().getRight().getData());
        assertEquals((Integer) 908, root.getRight().getRight().getRight().getData());

        assertEquals(3, root.getHeight());





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
    public void testRemove() {
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
    public void testHeight() {
        avlTree.add(646);
        avlTree.add(386);
        avlTree.add(856);
        avlTree.add(526);
        avlTree.add(477);

        assertEquals(2, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        Integer maximum = new Integer(646);
        avlTree.add(526);
        avlTree.add(386);
        avlTree.add(477);
        avlTree.add(maximum);
        avlTree.add(856);

        assertSame(maximum, avlTree.get(new Integer(646)));
    }
}
