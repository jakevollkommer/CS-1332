import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Sample JUnit tests featuring Greek letters for Homework 1.
 * @version Omega
 */
public class GreekArrayListTests{

    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsAtIndex() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "alpha"); //alpha
        list.addAtIndex(1, "beta"); //alpha beta
        list.addAtIndex(2, "delta"); //alpha beta delta
        list.addAtIndex(3, "epsilon"); //alpha beta delta epsilon
        list.addAtIndex(2, "gamma"); //alpha beta gamma delta epsilon

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "alpha";
        expected[1] = "beta";
        expected[2] = "gamma";
        expected[3] = "delta";
        expected[4] = "epsilon";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("kappa");
        list.addToFront("iota");
        list.addToFront("theta");
        list.addToFront("eta");
        list.addToFront("zeta"); //zeta eta theta iota kappa

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "zeta";
        expected[1] = "eta";
        expected[2] = "theta";
        expected[3] = "iota";
        expected[4] = "kappa";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("lambda");
        list.addToBack("mu");
        list.addToBack("nu");
        list.addToBack("xi");
        list.addToBack("omicron"); //lambda mu nu xi omicron

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "lambda";
        expected[1] = "mu";
        expected[2] = "nu";
        expected[3] = "xi";
        expected[4] = "omicron";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "pi");
        list.addAtIndex(1, "rho");
        list.addAtIndex(2, "sigma");
        list.addAtIndex(3, "tau");
        list.addAtIndex(4, "upsilon");
        list.addAtIndex(5, "phi"); //pi rho sigma tau upsilon phi

        assertEquals(6, list.size());

        assertEquals("sigma", list.removeAtIndex(2)); //pi rho tau upsilon phi
        assertEquals("phi", list.removeFromBack()); //pi rho tau upsilon
        assertEquals("pi", list.removeFromFront()); //rho tau upsilon

        assertEquals(3, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "rho";
        expected[1] = "tau";
        expected[2] = "upsilon";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        assertTrue(list.isEmpty());
        list.addAtIndex(0, "upsilon");
        list.addAtIndex(1, "phi");
        list.addAtIndex(2, "chi");
        list.addAtIndex(3, "psi");
        list.addAtIndex(4, "omega"); //upsilon phi chi psi omega

        assertEquals("upsilon", list.get(0));
        assertEquals("phi", list.get(1));
        assertEquals("chi", list.get(2));
        assertEquals("psi", list.get(3));
        assertEquals("omega", list.get(4));
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }
}
