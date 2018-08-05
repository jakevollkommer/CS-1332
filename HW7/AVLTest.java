import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * These tests ONLY check efficiency of balancing/rotations
 * @author Animesh Fatehpuria
 * @version 1.0
 */
public class AVLTest {
    private static final int TIMEOUT = 3000;
    private AVL<Integer> tree;

    @Before
    public void setup() {
        tree = new AVL<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedList() {
        // A Timeout here would imply that balancing is not done properly, i.e.
        // some function is O(n) when it needs to be O(log N) / O(1).
        int n = 500000;
        for (int i = 1; i <= n; i++) {
            tree.add(i);
        }
        // Note that a simple BST would be O(n^2) in this case and definitely time out
        assertEquals(n, tree.size());
        for (int i = n; i >= 1; i -= 2) {
            tree.remove(i);
        }
        assertEquals(n / 2, tree.size());
        for (int i = 1; i <= n; i++) {
            assertEquals(tree.contains(i), (i % 2 == 1));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testCactusTree() {
        // A Timeout here would imply that balancing is not done properly, i.e.
        // some function is O(n) when it needs to be O(log N) / O(1).
        int n = 500000;
        tree.add(1);
        for (int i = 3; i <= n; i += 2) {
            tree.add(i);
            tree.add(i - 1);
        }
        tree.add(n);
        for (int i = 1; i <= n; i++) {
            boolean ok = tree.contains(i);
            assertEquals(ok, true);
        }
        for (int i = 1; i <= n; i += 2) {
            tree.remove(i);
        }
        for (int i = 1; i <= n; i++) {
            assertEquals(tree.contains(i), (i % 2 == 0));
        }
    }
}
