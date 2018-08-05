import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Nice String Searching Tests ( ͡° ͜ʖ ͡°)
 *
 * @author Sam Gilson
 * @version 1.3
 */
public class NiceTests {

    private List<Integer> squempsAnswer;
    private List<Integer> emptyList;
    private SearchableString squemps;
    private SearchableString squempsNotThere;
    private SearchableString squempsText;
    private SearchableString squempsTextMultiple;
    private SearchableString squempsTextTooShort;
    private SearchableString squempsTextLorem;
    private List<Integer> squempsAnswerMultiple;
    private List<Integer> squempsAnswerLorem;
    private SearchableString terribleHash;
    private SearchableString terribleHashText;
    private List<Integer> terribleHashAnswer;


    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        squemps = new SearchableString("squemps");
        squempsText = new SearchableString("i really love cs1332 it is my squempsiest class" +
        " its way better than cs2340");

        squempsTextMultiple = new SearchableString("squemps hello squemp mchasdjsquemp" +
                "sanlkndasjn woeirer squempsquempssquemps");
        squempsNotThere = new SearchableString("one day there was a big farm and nobody lived on this farm"
         + " and in all honesty it was a pretty terrible farm. The end.");
        squempsTextTooShort = new SearchableString("emps");

        squempsTextLorem = new SearchableString("Lorem ipsum dolor sit amet, oblique intesquempsis vix id. Ea feugait repudiandae comprehensam sea, habeo corpora assentior cu duo. Id dolorem offsquemps disputando ius. An vix nulla lobortis dignissim. Cu congue mentitum maiestatis quo, te nullam saperet eum, adhuc aliquid apeirian an cum." +

                "Ut pri tollit melius laboramus. Ei vix splendide abhorreant, erant clita gloriatur pro et, has cu quot sanctus omittam. At iisque petentium imperdiet cum. Te natum laoreet vel. Vix homero aeterno ea, minim partiendo conceptam an vix." +

                "Eu est omnes nusquam percipit. Et facilis squemps qui, has epicuri deseruisse ex, at tale consul sea. No vim maiorum tacimates urbanitas. Ne suscipit volutpat comprehensam mea, mei equidem scriptorem at. Laudem volumus qui no, at habemus omnesquemps invenire eum. Qui vero elit forensibus no, et summo postea accommodare quo, malis mollis ei pro." +

                "Ancillae nominavi aliquando vix id. No illum asquempsi squempria nam, nec at adhuc iusto. Eu elitr ceteros ius, te vix mollis pericula. At audiam admodum mei, in elit nominati interpretaris eum, no laudem regione accusata duo. Id sint primis volutpat qui, decore quaestio squempsratoribusquemps ne mea." +

                "Et homero recusabo temporibus duo. Has cu deleniti consulatu interesset, et prima reque squemps cum. Mundi consul eum no. Eam te malis ullum docendi. Et expetenda vituperatoribus quo, per te suas omnis facilisi.");

        // there are 8 squemps hidden in the lorem

        squempsAnswer = new ArrayList<>();
        squempsAnswer.add(30);
        squempsAnswerMultiple = new ArrayList<>();
        squempsAnswerMultiple.add(0);
        squempsAnswerMultiple.add(28);
        squempsAnswerMultiple.add(54);
        squempsAnswerMultiple.add(60);
        squempsAnswerMultiple.add(67);

        squempsAnswerLorem = new ArrayList<>();
        squempsAnswerLorem.add(40);
        squempsAnswerLorem.add(145);
        squempsAnswerLorem.add(565);
        squempsAnswerLorem.add(765);
        squempsAnswerLorem.add(915);
        squempsAnswerLorem.add(1141);
        squempsAnswerLorem.add(1156);
        squempsAnswerLorem.add(1259);





        terribleHash = new SearchableString("00000");
        terribleHashText = new SearchableString("0000000000000000");

        terribleHashAnswer = new ArrayList<>();

        for (int i = 0; i <= terribleHashText.length() - terribleHash.length(); i++) {
            terribleHashAnswer.add(i);
        }


        emptyList = new ArrayList<>();

    }

    @Test (timeout = TIMEOUT)
    public void testBuildLastTableSquemps() {
        Map<Character, Integer> lastTable = StringSearching
                .buildLastTable(squemps);
        Map<Character, Integer> expectedLastTable = new HashMap<>();
        expectedLastTable.put('s', 6);
        expectedLastTable.put('p', 5);
        expectedLastTable.put('m', 4);
        expectedLastTable.put('e', 3);
        expectedLastTable.put('u', 2);
        expectedLastTable.put('q', 1);
        assertEquals(expectedLastTable, lastTable);
        assertTrue(squemps.getCount() <= 7);
    }



    @Test(timeout = TIMEOUT)
    public void testBoyerMooreThereSquemps() {
        assertEquals(squempsAnswer, StringSearching.boyerMoore(squemps, squempsText));
        assertTrue("squempsText count was " + squempsText.getCount()
                + ". Should be <= 18.", squempsText.getCount() <= 18); //this is what i got

    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreThereSquempsMultiple() {
        assertEquals(squempsAnswerMultiple, StringSearching.boyerMoore(squemps, squempsTextMultiple));
        assertTrue("squempsTextMultiple count was " + squempsTextMultiple.getCount()
                + ". Should be <= 51.", squempsTextMultiple.getCount() <= 51); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreThereSquempsLorem() {
        assertEquals(squempsAnswerLorem, StringSearching.boyerMoore(squemps, squempsTextLorem));
        assertTrue("squempsTextMultiple count was " + squempsTextLorem.getCount()
                + ". Should be <= 303.", squempsTextLorem.getCount() <= 303); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreNotThereSquemps() {
        assertEquals(emptyList, StringSearching.boyerMoore(squemps, squempsNotThere));
        assertTrue("squempsNotThere count was " + squempsNotThere.getCount()
                + ". Should be <= 22.", squempsNotThere.getCount() <= 22); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreTooShortSquemps() {
        assertEquals(emptyList, StringSearching.boyerMoore(squemps, squempsTextTooShort));
        assertTrue("squempsTextTooShort count was " + squempsTextTooShort.getCount()
                + ". Should be <= 0.", squempsTextTooShort.getCount() <= 0); //this is what i got


    }

    @Test (timeout = TIMEOUT)
    public void testBuildFailureSquemps() {
        int[] failureTable = StringSearching
                .buildFailureTable(squemps);
        int[] expectedfailureTable = new int[failureTable.length];
        expectedfailureTable[0] = 0;
        expectedfailureTable[1] = 0;
        expectedfailureTable[2] = 0;
        expectedfailureTable[3] = 0;
        expectedfailureTable[4] = 0;
        expectedfailureTable[5] = 0;
        expectedfailureTable[6] = 1;
        for (int i = 0; i < failureTable.length; i++) {
            assertEquals(expectedfailureTable[i], failureTable[i]);
        }
        assertTrue(squemps.getCount() <= 8);
    }

    @Test(timeout = TIMEOUT)
    public void testKMPThereSquemps() {
        assertEquals(squempsAnswer, StringSearching.kmp(squemps, squempsText));
        assertTrue("squempsText count was " + squempsText.getCount()
                + ". Should be <= 77.", squempsText.getCount() <= 77); //this is what i got

    }

    @Test(timeout = TIMEOUT)
    public void testKMPThereSquempsMultiple() {
        assertEquals(squempsAnswerMultiple, StringSearching.kmp(squemps, squempsTextMultiple));
        assertTrue("squempsTextMultiple count was " + squempsTextMultiple.getCount()
                + ". Should be <= 84.", squempsTextMultiple.getCount() <= 84); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testKMPThereSquempsLorem() {
        assertEquals(squempsAnswerLorem, StringSearching.kmp(squemps, squempsTextLorem));
        assertTrue("squempsTextMultiple count was " + squempsTextLorem.getCount()
                + ". Should be <= 1461.", squempsTextLorem.getCount() <= 1461); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testKMPNotThereSquemps() {
        assertEquals(emptyList, StringSearching.kmp(squemps, squempsNotThere));
        assertTrue("squempsNotThere count was " + squempsNotThere.getCount()
                + ". Should be <= 116.", squempsNotThere.getCount() <= 116); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testKMPTooShortSquemps() {
        assertEquals(emptyList, StringSearching.kmp(squemps, squempsTextTooShort));
        assertTrue("squempsTextTooShort count was " + squempsTextTooShort.getCount()
                + ". Should be <= 0.", squempsTextTooShort.getCount() <= 0); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testRKThereSquemps() {
        assertEquals(squempsAnswer, StringSearching.rabinKarp(squemps, squempsText));
        assertTrue("squempsText count was " + squempsText.getCount()
                + ". Should be <= 77.", squempsText.getCount() <= 148); //this is what i got

    }

    @Test(timeout = TIMEOUT)
    public void testRKThereSquempsMultiple() {
        assertEquals(squempsAnswerMultiple, StringSearching.rabinKarp(squemps, squempsTextMultiple));
        assertTrue("squempsTextMultiple count was " + squempsTextMultiple.getCount()
                + ". Should be <= 84.", squempsTextMultiple.getCount() <= 176); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testRKThereSquempsLorem() {
        assertEquals(squempsAnswerLorem, StringSearching.rabinKarp(squemps, squempsTextLorem));
        assertTrue("squempsTextMultiple count was " + squempsTextLorem.getCount()
                + ". Should be <= 1461.", squempsTextLorem.getCount() <= 2813); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testRKNotThereSquemps() {
        assertEquals(emptyList, StringSearching.rabinKarp(squemps, squempsNotThere));
        assertTrue("squempsNotThere count was " + squempsNotThere.getCount()
                + ". Should be <= 116.", squempsNotThere.getCount() <= 227); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testRKTooShortSquemps() {
        assertEquals(emptyList, StringSearching.rabinKarp(squemps, squempsTextTooShort));
        assertTrue("squempsTextTooShort count was " + squempsTextTooShort.getCount()
                + ". Should be <= 0.", squempsTextTooShort.getCount() <= 0); //this is what i got


    }

    @Test(timeout = TIMEOUT)
    public void testRKTerribleHash() {
        int terribleHashLength = terribleHash.length();
        int terribleHashTextLength = terribleHashText.length();
        System.out.println("TerribleHashLength: " + terribleHashLength);
        System.out.println("TerribleHashTextLength: " + terribleHashTextLength);
        //System.out.println(StringSearching.generateHash(terribleHashText, terribleHashTextLength));
        //System.out.println(terribleHashText.getCount());
        System.out.println("TerribleHashLength * TerribleHashTextLength: " + (terribleHashLength * terribleHashTextLength));

        assertEquals(terribleHashAnswer, StringSearching.rabinKarp(terribleHash, terribleHashText));
        assertTrue("terribleHashText count was " + terribleHashText.getCount()
                + ". Should be <= 80.", terribleHashText.getCount() <= 87); //this is what i got
        // in reality, your algorithm should be O(mn), but in this case the pre processing (generating the hash) adds about 16
        // extra comparisons. If you manually go into your code and have it sout the amount of
        // times it calls text.charAt() outside of the pre processing it should be somewhere around 70. As long as that
        // number is below m*n you're fine.


    }




}
