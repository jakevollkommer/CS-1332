import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Basic coverage of JUnit tests for Homework.
 *
 * @version 1.2
 * @author CS 1332 TAs and Jered Tupik
 */
public class BSTStudentTestsBasic {
    private BSTInterface<Integer> bst;
    public static final int TIMEOUT = 200000000;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);

        assertEquals(3, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testAddRepeated() {
        assertEquals(0, bst.size());

        int randNum = (int) Math.random();
        for (int i = 0; i < 1000; i++) {
            bst.add(randNum);
            assertEquals(1, bst.size());
            assertEquals(randNum, (int) bst.getRoot().getData());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        assertEquals(0, bst.size());

        Random rand = new Random();
        List<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            int num = rand.nextInt();
            bst.add(num);
            nums.add(num);
        }

        for (int num: nums) {
            assertEquals(true, bst.contains(num));
        }
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsIllegal() {
        assertEquals(0, bst.size());

        bst.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsEmpty() {
        assertEquals(0, bst.size());

        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            assertEquals(false, bst.contains(rand.nextInt()));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        assertEquals((Integer) 3, bst.remove(3));
        assertEquals(4, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight()
                .getRight().getData());
    }
    
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        assertEquals(0, bst.size());

        bst.remove(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        assertEquals(0, bst.size());

        bst.remove(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveRandom() {
        Random rand = new Random();
        List<Integer> listTree = new ArrayList<Integer>();

        assertEquals(0, bst.size());

        for (int i = 0; i < 1000; i++) {
            int num = rand.nextInt();
            bst.add(num);
            if (!listTree.contains(num)) {
                listTree.add(num);
            }
        }

        assertEquals(listTree.size(), bst.size());

        while (listTree.size() != 0) {
            int num = listTree.get(0);
            listTree.remove(0);
            Integer bstInt = bst.remove(num);
            assertEquals((Integer) num, bstInt);
            assertEquals(listTree.size(), bst.size());
        }
        assertEquals(0, bst.size());
        assertEquals(null, bst.getRoot());
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

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetIllegal() {
        assertEquals(0, bst.size());

        bst.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNoSuch() {
        assertEquals(0, bst.size());

        for (int i = 0; i < 5; i++) {
            bst.add(i);
            bst.add(-i);
        }
        bst.get(1000);
    }

    @Test(timeout = TIMEOUT)
    public void testpreorder() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(4);
        preorder.add(2);
        preorder.add(1);
        preorder.add(3);
        preorder.add(6);
        preorder.add(5);
        preorder.add(7);

        assertEquals(preorder, bst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testpreorderEmpty() {
        assertEquals(new ArrayList<Integer>(), bst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testpreorderWorst() {
        List<Integer> preorder = new ArrayList<>();
        for (int i = 1000; i >= 0; i--) {
            bst.add(i);
            preorder.add(i);
        }

        assertEquals(preorder, bst.preorder());

        bst.clear();
        preorder.clear();
        for (int i = 0; i <= 1000; i++) {
            bst.add(i);
            preorder.add(i);
        }
        assertEquals(preorder, bst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testpostorder() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(1);
        postorder.add(3);
        postorder.add(2);
        postorder.add(5);
        postorder.add(7);
        postorder.add(6);
        postorder.add(4);

        assertEquals(postorder, bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testpostorderEmpty() {
        assertEquals(new ArrayList<Integer>(), bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testpostorderWorst() {
        List<Integer> postorder = new ArrayList<>();
        for (int i = 1000; i >= 0; i--) {
            bst.add(i);
            postorder.add(1000 - i);
        }

        assertEquals(postorder, bst.postorder());

        bst.clear();
        postorder.clear();
        for (int i = 0; i <= 1000; i++) {
            bst.add(i);
            postorder.add(1000 - i);
        }
        assertEquals(postorder, bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testinorder() {
        bst.add(4);
        bst.add(2);
        bst.add(6);
        bst.add(1);
        bst.add(3);
        bst.add(5);
        bst.add(7);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);
        inorder.add(4);
        inorder.add(5);
        inorder.add(6);
        inorder.add(7);

        assertEquals(inorder, bst.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testinorderEmpty() {
        assertEquals(new ArrayList<Integer>(), bst.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testinorderRandom() {
        Random rand = new Random();
        List<Integer> inorder = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int next = rand.nextInt();
            bst.add(next);
            inorder.add(next);
        }
        Collections.sort(inorder);

        assertEquals(inorder, bst.inorder());
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
    public void testlevelorderEmpty() {
        assertEquals(new ArrayList<Integer>(), bst.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testlevelorderWorst() {
        List<Integer> levelorder = new ArrayList<>();
        for (int i = 1000; i >= 0; i--) {
            bst.add(i);
            levelorder.add(i);
        }

        assertEquals(levelorder, bst.levelorder());

        bst.clear();
        levelorder.clear();
        for (int i = 0; i <= 1000; i++) {
            bst.add(i);
            levelorder.add(i);
        }
        assertEquals(levelorder, bst.levelorder());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addExpectException() {
        bst.add(null);
    }


    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getEmpty() {
        bst.get(20);
    }

    @Test(timeout = TIMEOUT)
    public void testHeightEmpty() {
        assertEquals(bst.height(), -1);
    }

    @Test(timeout = TIMEOUT)
    public void testHeightBalanced() {
        bst.add(4);
        assertEquals(0, bst.height());

        bst.add(2);
        assertEquals(1, bst.height());

        bst.add(6);
        assertEquals(1, bst.height());

        bst.add(1);
        assertEquals(2, bst.height());

        bst.add(3);
        assertEquals(2, bst.height());

        bst.add(5);
        assertEquals(2, bst.height());
        
        bst.add(7);
        assertEquals(2, bst.height());
    }

    @Test(timeout = TIMEOUT)
    public void testHeightWorst() {
        for (int i = 0; i < 1000; i++) {
            bst.add(i);
            assertEquals(i, bst.height());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testEmptySize() {
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testSize() {
        for (int i = 0; i < 1000; i++) {
            bst.add(i);
            assertEquals(i + 1, bst.size());
        }
        bst.add(500);
        assertEquals(1000, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        for (int i = 0; i < 1000; i++) {
            bst.add(i);
        }
        bst.clear();
        assertEquals(0, bst.size());
        assertEquals(null, bst.getRoot());
    }
}