/**
 * @author Brian S. Borowski
 * Test cases for Programming Assignment 2 - Recursion exercises
 * COMS W3134
 * Last modified: 09/15/2022
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class RecursionTestCases {

    @Test
    public void testRecursiveMultiplication01() {
        assertEquals(0, Recursion.recursiveMultiplication(4, 0));
    }

    @Test
    public void testRecursiveMultiplication02() {
        assertEquals(0, Recursion.recursiveMultiplication(0, 3));
    }

    @Test
    public void testRecursiveMultiplication03() {
        assertEquals(3, Recursion.recursiveMultiplication(3, 1));
    }

    @Test
    public void testRecursiveMultiplication04() {
        assertEquals(48, Recursion.recursiveMultiplication(12, 4));
    }

    @Test
    public void testRecursiveMultiplication05() {
        assertEquals(1111, Recursion.recursiveMultiplication(101, 11));
    }

    @Test
    public void testReverse01() {
        assertEquals("", Recursion.reverse(""));
    }

    @Test
    public void testReverse02() {
        assertEquals("I", Recursion.reverse("I"));
    }

    @Test
    public void testReverse03() {
        assertEquals("123", Recursion.reverse("321"));
    }

    @Test
    public void testReverse04() {
        assertEquals(".erots eht ot tnew maS", Recursion.reverse("Sam went to the store."));
    }

    @Test
    public void testReverse05() {
        assertEquals("!raeY weN yppaH", Recursion.reverse("Happy New Year!"));
    }

    @Test
    public void testMax01() {
        assertEquals(12, Recursion.max(new int[] {12}));
    }

    @Test
    public void testMax02() {
        assertEquals(3, Recursion.max(new int[] {0, 1, 3, 2}));
    }

    @Test
    public void testMax03() {
        assertEquals(-1, Recursion.max(new int[] {-2, -3, -1, -12, -15}));
    }

    @Test
    public void testMax04() {
        assertEquals(Integer.MAX_VALUE, Recursion.max(new int[] {Integer.MAX_VALUE, 20000, Integer.MAX_VALUE, 9, Integer.MIN_VALUE}));
    }

    @Test
    public void testMax05() {
        assertEquals(10, Recursion.max(new int[] {1, 3, 4, 6, 5, 10, 9, 8, 7, 2}));
    }

    @Test
    public void testIsPalindrome01() {
        assertTrue(Recursion.isPalindrome(""));
    }

    @Test
    public void testIsPalindrome02() {
        assertTrue(Recursion.isPalindrome("a"));
    }

    @Test
    public void testIsPalindrome03() {
        assertTrue(Recursion.isPalindrome("racecar"));
    }

    @Test
    public void testIsPalindrome04() {
        assertFalse(Recursion.isPalindrome("racefcar"));
    }

    @Test
    public void testIsPalindrome05() {
        assertTrue(Recursion.isPalindrome("saippuakivikauppias"));
    }

    @Test
    public void testIsMember01() {
        assertFalse(Recursion.isMember(13, new int[] {12}));
    }

    @Test
    public void testIsMember02() {
        assertTrue(Recursion.isMember(3, new int[] {0, 1, 3, 2}));
    }

    @Test
    public void testIsMember03() {
        assertFalse(Recursion.isMember(-11, new int[] {-2, -3, -1, -12, -15}));
    }

    @Test
    public void testIsMember04() {
        assertTrue(Recursion.isMember(Integer.MIN_VALUE, new int[] {Integer.MAX_VALUE, 20000, Integer.MAX_VALUE, 9, Integer.MIN_VALUE}));
    }

    @Test
    public void testIsMember05() {
        assertTrue(Recursion.isMember(10, new int[] {1, 3, 4, 6, 5, 10, 9, 8, 7, 2}));
    }

    @Test
    public void testSeparateIdentical01() {
        assertEquals("", Recursion.separateIdentical(""));
    }

    @Test
    public void testSeparateIdentical02() {
        assertEquals("A", Recursion.separateIdentical("A"));
    }

    @Test
    public void testSeparateIdentical03() {
        assertEquals("noadjacentchars", Recursion.separateIdentical("noadjacentchars"));
    }

    @Test
    public void testSeparateIdentical04() {
        assertEquals("AB~BAC~C~CAB~BA", Recursion.separateIdentical("ABBACCCABBA"));
    }

    @Test
    public void testSeparateIdentical05() {
        assertEquals("~~~0~0123~~~4~~~3210~0~~~~~", Recursion.separateIdentical("~~00123~~4~~32100~~~"));
    }
}
