
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

/**
 * String searching student tests.
 *
 * @author Kar Shin Lin
 * @version 1.0
 */
public class StringSearchingStudentTestsCopy {
    private List<Integer> emptyList;


    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        emptyList = new ArrayList<>();
    }



    @Test(timeout = TIMEOUT)
    public void testKMPsimple() {
        SearchableString simple = new SearchableString("aaa");
        SearchableString simpleText = new SearchableString("baabaabaabaabaabaacaa");
        int[] failureTable = StringSearching.buildFailureTable(simple);
        int[] expected = {0, 1, 2};
        assertArrayEquals(expected, failureTable);
        List<Integer> answer = new ArrayList<>();
        assertEquals(emptyList, StringSearching.kmp(simple, simpleText));
        System.out.println("testKMPsimple");
        System.out.println("Pattern comparisons: " + simple.getCount());
        System.out.println("Text comparisons: " + simpleText.getCount());
        assertTrue("Pattern Comparisons made: " + simple.getCount(), simple.getCount() <= 41); //what I got
        assertTrue("Text Comparisons made: " + simpleText.getCount(), simpleText.getCount() <= 31); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testKMPsame() {
        SearchableString simple = new SearchableString("aaa");
        SearchableString simpleText = new SearchableString("aaa");
        int[] failureTable = StringSearching.buildFailureTable(simple);
        int[] expected = {0, 1, 2};
        assertArrayEquals(expected, failureTable);
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        assertEquals(answer, StringSearching.kmp(simple, simpleText));
        System.out.println("testKMPsame");
        System.out.println("Pattern comparisons: " + simple.getCount());
        System.out.println("Text comparisons: " + simpleText.getCount());
        assertTrue("Pattern Comparisons made: " + simple.getCount(), simple.getCount() <= 13); //what I got
        assertTrue("Text Comparisons made: " + simpleText.getCount(), simpleText.getCount() <= 3); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testKMPcomplex() {
        SearchableString simple = new SearchableString("aac");
        SearchableString simpleText = new SearchableString("aabaabaabaabaabaabaabaac");
        int[] failureTable = StringSearching.buildFailureTable(simple);
        int[] expected = {0, 1, 0};
        assertArrayEquals(expected, failureTable);
        List<Integer> answer = new ArrayList<>();
        answer.add(21);
        assertEquals(answer, StringSearching.kmp(simple, simpleText));
        System.out.println("testKMPcomplex");
        System.out.println("Pattern comparisons: " + simple.getCount());
        System.out.println("Text comparisons: " + simpleText.getCount());
        assertTrue("Pattern Comparisons made: " + simple.getCount(), simple.getCount() <= 50); //what I got
        assertTrue("Text Comparisons made: " + simpleText.getCount(), simpleText.getCount() <= 38); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testKMPmultiple() {
        SearchableString simple = new SearchableString("aac");
        SearchableString simpleText = new SearchableString("aacaacaacaacaacaacaacaac");
        int[] failureTable = StringSearching.buildFailureTable(simple);
        int[] expected = {0, 1, 0};
        assertArrayEquals(expected, failureTable);
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            answer.add(i*3);
        }
        assertEquals(answer, StringSearching.kmp(simple, simpleText));
        System.out.println("testKMPmultiple");
        System.out.println("Pattern comparisons: " + simple.getCount());
        System.out.println("Text comparisons: " + simpleText.getCount());
        assertTrue("Pattern Comparisons made: " + simple.getCount(), simple.getCount() <= 36); //what I got
        assertTrue("Text Comparisons made: " + simpleText.getCount(), simpleText.getCount() <= 24); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testKMPexception() {
        SearchableString simple = new SearchableString("aacaa");
        SearchableString simpleText = new SearchableString("aac");
        int[] failureTable = StringSearching.buildFailureTable(simple);
        int[] expected = {0, 1, 0, 1, 2};
        assertArrayEquals(expected, failureTable);
        List<Integer> answer = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        boolean expFound = false;
        try {
            actual = StringSearching.kmp(null, simpleText);
        } catch (IllegalArgumentException e) {
            expFound = true;
        }
        assertEquals(true, expFound);
        System.out.println("testKMPexception");
        System.out.println("Pattern comparisons: " + simple.getCount());
        assertTrue("Comparisons made: " + simple.getCount(), simple.getCount() <= 10); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testKMPexception2() {
        SearchableString simple = new SearchableString("aacaa");
        SearchableString simpleText = new SearchableString("aac");
        int[] failureTable = StringSearching.buildFailureTable(simple);
        int[] expected = {0, 1, 0, 1, 2};
        assertArrayEquals(expected, failureTable);
        List<Integer> answer = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        boolean expFound = false;
        try {
            actual = StringSearching.kmp(simple, null);
        } catch (IllegalArgumentException e) {
            expFound = true;
        }
        assertEquals(true, expFound);
        System.out.println("testKMPexception2");
        System.out.println("Pattern comparisons: " + simple.getCount());
        assertTrue("Pattern Comparisons made: " + simple.getCount(), simple.getCount() <= 10); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testKMPexception3() {
        SearchableString simple = new SearchableString("");
        SearchableString simpleText = new SearchableString("aac");
        int[] failureTable = StringSearching.buildFailureTable(simple);
        int[] expected = {};
        assertArrayEquals(expected, failureTable);
        List<Integer> answer = new ArrayList<>();
        List<Integer> actual = new ArrayList<>();
        boolean expFound = false;
        try {
            actual = StringSearching.kmp(simple, simpleText);
        } catch (IllegalArgumentException e) {
            expFound = true;
        }
        assertEquals(true, expFound);
        System.out.println("testKMPexception3");
        System.out.println("Pattern comparisons: " + simple.getCount());
        assertTrue(" Pattern Comparisons made: " + simple.getCount(), simple.getCount() <= 0); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testKMPloop() {
        String simpleText = "";
        for (int i = 0; i < 9000; i++) {
            if (i % 5 == 0) {
                simpleText += "g";
            } else {
                simpleText += "e";
            }
        }
        SearchableString simple = new SearchableString("eeeegeeee");
        SearchableString simpleText1 = new SearchableString(simpleText);
        int[] failureTable = StringSearching.buildFailureTable(simple);
        int[] expected = {0, 1, 2, 3, 0, 1, 2, 3, 4};
        assertArrayEquals(expected, failureTable);
        List<Integer> answer = new ArrayList<>();
        answer.add(1);
        for (int i = 2; i < 8995; i++) {
            if ((i - 1) % 5 == 0) {
                answer.add(i);
            }
        }
        List<Integer> actual = new ArrayList<>();
        assertEquals(answer, StringSearching.kmp(simple, simpleText1));
        System.out.println("testKMPloop");
        System.out.println("Pattern comparisons: " + simple.getCount());
        System.out.println("Text comparisons: " + simpleText1.getCount());
        assertTrue("Pattern Comparisons made: " + simple.getCount(), simple.getCount() <= 9044); //what I got
        assertTrue("Text Comparisons made: " + simpleText1.getCount(), simpleText1.getCount() <= 9000); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTableABC() {
        SearchableString pattern = new SearchableString("zyxwvutsrqponmlkjihgfedcba");
        Map<Character, Integer> lastTable = StringSearching
            .buildLastTable(pattern);

        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('a', 25);
        expectedLastTable.put('b', 24);
        expectedLastTable.put('c', 23);
        expectedLastTable.put('d', 22);
        expectedLastTable.put('e', 21);
        expectedLastTable.put('f', 20);
        expectedLastTable.put('g', 19);
        expectedLastTable.put('h', 18);
        expectedLastTable.put('i', 17);
        expectedLastTable.put('j', 16);
        expectedLastTable.put('k', 15);
        expectedLastTable.put('l', 14);
        expectedLastTable.put('m', 13);
        expectedLastTable.put('n', 12);
        expectedLastTable.put('o', 11);
        expectedLastTable.put('p', 10);
        expectedLastTable.put('q', 9);
        expectedLastTable.put('r', 8);
        expectedLastTable.put('s', 7);
        expectedLastTable.put('t', 6);
        expectedLastTable.put('u', 5);
        expectedLastTable.put('v', 4);
        expectedLastTable.put('w', 3);
        expectedLastTable.put('x', 2);
        expectedLastTable.put('y', 1);
        expectedLastTable.put('z', 0);
        assertEquals(expectedLastTable, lastTable);
        System.out.println("testBuildLastTableABC");
        System.out.println("Pattern comparisons: " + pattern.getCount());
        assertEquals(26, pattern.getCount()); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable1() {
        SearchableString pattern = new SearchableString("aaaaaaaab");
        Map<Character, Integer> lastTable = StringSearching
                .buildLastTable(pattern);

        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('a', 7);
        expectedLastTable.put('b', 8);

        assertEquals(expectedLastTable, lastTable);
        System.out.println("testBuildLastTable1");
        System.out.println("Pattern comparisons: " + pattern.getCount());
        assertEquals(true, pattern.getCount() <= 9); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTableEmpty() {
        SearchableString pattern = new SearchableString("");
        Map<Character, Integer> expected = new HashMap();
        boolean worked = true;
        try {
            Map<Character, Integer> lastTable = StringSearching
                    .buildLastTable(pattern);
        } catch (IllegalArgumentException e) {
            worked = false;
        }
        assertEquals(expected, StringSearching.buildLastTable(pattern));
        assertEquals(true, worked);
        List<Integer> actual = new ArrayList<>();
        try {
            actual = StringSearching.boyerMoore(pattern, new SearchableString("hello"));
        } catch (IllegalArgumentException e) {
            worked = false;
        }
        assertEquals(false, worked);
        System.out.println("testBuildLastTableEmpty");
        System.out.println("Pattern comparisons: " + pattern.getCount());
        assertEquals(0, pattern.getCount()); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore() {
        SearchableString pattern = new SearchableString("AABA");
        SearchableString text = new SearchableString("AABAACAADAABAABA");
        List<Integer> actual = new ArrayList<>();
        actual.add(0);
        actual.add(9);
        actual.add(12);
        assertEquals(actual, StringSearching.boyerMoore(pattern, text));
        System.out.println("testBoyerMoore");
        System.out.println("Pattern comparisons: " + pattern.getCount());
        assertEquals(true, pattern.getCount() <= 24); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreWorstCase() {
        SearchableString pattern = new SearchableString("abababab");
        SearchableString text = new SearchableString("bbabababbbabababbbabababbbababab");
        List<Integer> actual = new ArrayList<>();
        assertEquals(actual, StringSearching.boyerMoore(pattern, text));
        System.out.println("testBoyerMooreWorstCase");
        System.out.println("Text comparisons: " + text.getCount());
        assertEquals(true, text.getCount() <= 83); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMoore2() {
        SearchableString pattern = new SearchableString("abababab");
        SearchableString text = new SearchableString("bbabababbbabababbbababababababab");
        List<Integer> actual = new ArrayList<>();
        actual.add(18);
        actual.add(20);
        actual.add(22);
        actual.add(24);
        assertEquals(actual, StringSearching.boyerMoore(pattern, text));
        System.out.println("testBoyerMoore2");
        System.out.println("Text comparisons: " + text.getCount());
        assertEquals(true, text.getCount() <= 94); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testGenerateHash() {
        assertEquals(1986512908, StringSearching.generateHash(
                    "matt is my friend", 4));
    }

    @Test(timeout = TIMEOUT)
    public void testUpdateHash() {
        assertEquals(-1701295826, StringSearching.updateHash(99342732, 5, 'a',
                    'q'));
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarp1() {
        SearchableString pattern = new SearchableString("abababab");
        SearchableString text = new SearchableString("bbabababbbabababbbababababababab");
        List<Integer> actual = new ArrayList<>();
        actual.add(18);
        actual.add(20);
        actual.add(22);
        actual.add(24);
        assertEquals(actual, StringSearching.rabinKarp(pattern, text));
        System.out.println("testRabinKarp1");
        System.out.println("Text comparisons: " + text.getCount());
        assertEquals(true, text.getCount() <= 99); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpEmpty() {
        SearchableString pattern = new SearchableString("abababab");
        SearchableString text = new SearchableString("bbabababbbabababbbabababbbababab");
        List<Integer> actual = new ArrayList<>();
        assertEquals(actual, StringSearching.rabinKarp(pattern, text));
        System.out.println("testRabinKarpEmpty");
        System.out.println("Text comparisons: " + text.getCount());
        //assertEquals(true, text.getCount() <= 56); //what I got
    }

    @Test(timeout = TIMEOUT)
    public void otherTests() {
        SearchableString pattern1 = new SearchableString("abababab");
        SearchableString text1 = new SearchableString("bbabababbbabababbbabababbbababab");
        List<Integer> actual1 = new ArrayList<>();

        SearchableString pattern2 = new SearchableString("abababab");
        SearchableString text2 = new SearchableString("bbabababbbabababbbababababababab");
        List<Integer> actual2 = new ArrayList<>();
        actual2.add(18);
        actual2.add(20);
        actual2.add(22);
        actual2.add(24);

        SearchableString pattern3 = new SearchableString("AABA");
        SearchableString text3 = new SearchableString("AABAACAADAABAABA");
        List<Integer> actual3 = new ArrayList<>();
        actual3.add(0);
        actual3.add(9);
        actual3.add(12);

        SearchableString pattern4 = new SearchableString("a");
        SearchableString text4 = new SearchableString("zyxwvutsrqponmlkjihgfedcba");
        List<Integer> actual4 = new ArrayList<>();
        actual4.add(25);

        SearchableString pattern5 = new SearchableString("aaaaaaaaaaaaaaa");
        SearchableString text5 = new SearchableString("aaaaaaaaaaaaaaa");
        List<Integer> actual5 = new ArrayList<>();
        actual5.add(0);

        SearchableString pattern6 = new SearchableString("a");
        SearchableString text6 = new SearchableString("aaaaaaaaaaaaaaa");
        List<Integer> actual6 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            actual6.add(i);
        }
        System.out.println("otherTests");
        assertEquals(actual1, StringSearching.boyerMoore(pattern1, text1));
        assertEquals(actual1, StringSearching.kmp(pattern1, text1));
        assertEquals(actual1, StringSearching.rabinKarp(pattern1, text1));
        System.out.println("Text comparisons: " + text1.getCount());
        assertEquals(true, text1.getCount() <= 173); //what I got

        assertEquals(actual2, StringSearching.boyerMoore(pattern2, text2));
        assertEquals(actual2, StringSearching.kmp(pattern2, text2));
        assertEquals(actual2, StringSearching.rabinKarp(pattern2, text2));
        System.out.println("Text comparisons: " + text2.getCount());
        assertEquals(true, text2.getCount() <= 220); //what I got

        assertEquals(actual3, StringSearching.boyerMoore(pattern3, text3));
        assertEquals(actual3, StringSearching.kmp(pattern3, text3));
        assertEquals(actual3, StringSearching.rabinKarp(pattern3, text3));
        System.out.println("Text comparisons: " + text3.getCount());
        assertEquals(true, text3.getCount() <= 80); //what I got

        assertEquals(actual4, StringSearching.boyerMoore(pattern4, text4));
        assertEquals(actual4, StringSearching.kmp(pattern4, text4));
        assertEquals(actual4, StringSearching.rabinKarp(pattern4, text4));
        System.out.println("Text comparisons: " + text4.getCount());
        assertEquals(true, text4.getCount() <= 104); //what I got

        assertEquals(actual5, StringSearching.boyerMoore(pattern5, text5));
        assertEquals(actual5, StringSearching.kmp(pattern5, text5));
        assertEquals(actual5, StringSearching.rabinKarp(pattern5, text5));
        System.out.println("Text comparisons: " + text5.getCount());
        assertEquals(true, text5.getCount() <= 60); //what I got

        assertEquals(actual6, StringSearching.boyerMoore(pattern6, text6));
        assertEquals(actual6, StringSearching.kmp(pattern6, text6));
        assertEquals(actual6, StringSearching.rabinKarp(pattern6, text6));
        System.out.println("Text comparisons: " + text6.getCount());
        assertEquals(true, text6.getCount() <= 74); //what I got
    }
}
