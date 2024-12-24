/**
 * @author Brian S. Borowski
 * @version 1.0
 * Various recursive methods
 * COMS W3134
 */
public class RecursionExercises {

    /**
     * Computes nth factorial starting with 0. 0!= 1.
     * @param n
     * @return the nth factorial
     */
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * Computes the length of a string.
     * @param s
     * @return the number of characters in the string.
     */
    public static int length(String s) {
        if (s.length() == 0) {
            return 0;
        }
        return 1 + length(s.substring(1));
    }

    /**
     * Computes x ^ y.
     * @param x
     * @param y
     * @return x raised to the y power
     */
    public static int power(int x, int y) {
        if (y == 0) {
            return 1;
        }
        return x * power(x, y - 1);
    }

    private static int powerTailHelper(int x, int y, int result) {
        if (y == 0) {
            return result;
        }
        return powerTailHelper(x, y - 1, x * result);
    }

    /**
     * Computes x ^ y with tail recursion.
     * @param x
     * @param y
     * @return x raised to the y power
     */
    public static int powerTail(int x, int y) {
        return powerTailHelper(x, y, 1);
    }

    private static int factorialTailHelper(int n, int result) {
        if (n == 0) {
            return result;
        }
        return factorialTailHelper(n - 1, n * result);
    }

    /**
     * Computes nth factorial with tail recursion starting with 0.
     * 0!= 1.
     * @param n
     * @return the nth factorial
     */
    public static int factorialTail(int n) {
        return factorialTailHelper(n, 1);
    }

    private static String removeVowelsHelper(
            String s, int index, StringBuilder builder) {
        if (index == s.length()) {
            return builder.toString();
        }
        char c = s.charAt(index);
        if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
            builder.append(c);
        }
        return removeVowelsHelper(s, index + 1, builder);
    }

    /**
     * Removes all vowels from a string with tail recursion.
     * @param s
     * @return a new string with no vowels
     */
    public static String removeVowelsTail(String s) {
        return removeVowelsHelper(s, 0, new StringBuilder());
    }

    /**
     * Removes all vowels from a string.
     * @param s
     * @return a new string with no vowels
     */
    public static String removeVowels(String s) {
        if (s.length() == 0) {
            return "";
        }
        char c = s.charAt(0);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return removeVowels(s.substring(1));
        }
        return c + removeVowels(s.substring(1));
    }

    /**
     * Computes the nth Fibonacci number according to the sequence:
     * 0, 1, 1, 2, 3, 4, 8, etc.
     * @param n
     * @return the nth Fibonacci number
     */
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(length("hello"));
        System.out.println(power(2, 4));
        System.out.println(factorialTail(5));
        System.out.println(removeVowels("helloworld"));
        System.out.println(removeVowelsTail("helloworld"));
    }
}
