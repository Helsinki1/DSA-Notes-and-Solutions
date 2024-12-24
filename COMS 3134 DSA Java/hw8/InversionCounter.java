import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class with two different methods to count inversions in an array of integers.
 * @author David Xiong yx2948
 * @version 1.0.0 November 17, 2022
 */
public class InversionCounter {

    /**
     * Returns the number of inversions in an array of integers.
     * This method uses nested loops to run in Theta(n^2) time.
     * @param array the array to process
     * @return the number of inversions in an array of integers
     */
    public static long countInversionsSlow(int[] array) {
        long count = 0;
        int length = array.length;

        int min = Integer.MAX_VALUE;
        for(int i=0; i<length; i++){
            min = Math.min(min, array[i]);
        }
        int [] arrayCopy = new int[length];
        for(int i=0; i<length; i++){
            arrayCopy[i] = array[i] - min;
        }
        for(int i=0; i<length-1; i++){
            long val = 0;
            int inc = i+1;
            while(inc<length){
                if(arrayCopy[inc]<arrayCopy[i]) val++;
                inc++;
            }
            count+=val;
            while(i<length-1 && arrayCopy[i+1]==arrayCopy[i]){
                count += val;
                i++;
            }
            val = 0;
        }
        return count;

        // for(int i=0; i<length-1; i++){
        //     long val = 0;
        //     int inc = i+1;
        //     while(inc<length){
        //         if(array[inc]<array[i]) val++;
        //         inc++;
        //     }
        //     count += val;
        //     while(i<length-1 && array[i+1]==array[i]){
        //         count += val;
        //         i++;
        //     }
        //     val = 0;
        // }
        // return count;
    }

    /**
     * Returns the number of inversions in an array of integers.
     * This method uses mergesort to run in Theta(n lg n) time.
     * @param array the array to process
     * @return the number of inversions in an array of integers
     */
    public static long fastHelper(int[] array, int[] scratch, int low, int high){
        if(low < high){
            long count = 0;
            int mid = low + (high-low)/2;
            long out = fastHelper(array, scratch, low, mid) + fastHelper(array, scratch, mid+1, high);

            int i=low, j=mid+1;
            for(int k=low; k<=high; k++){
                if(i<=mid && (j>high || array[i]<=array[j])){
                    scratch[k] = array[i++];
                }else{
                    if(i<=mid && j<=high && array[i]>array[j]) count += mid-i+1;
                    scratch[k] = array[j++];
                }
            }
            for(int k=low; k<=high; k++){
                array[k] = scratch[k];
            }
            return out + count;
        }else{
            return 0;
        }
    }
    public static long countInversionsFast(int[] array) {        
        int[] arrayCopy = new int[array.length],
              scratch =  new int[array.length];
        System.arraycopy(array, 0, arrayCopy, 0, array.length);

        return fastHelper(arrayCopy, scratch, 0, arrayCopy.length-1);
    }

    /**
     * Reads an array of integers from stdin.
     * @return an array of integers
     * @throws IOException if data cannot be read from stdin
     * @throws NumberFormatException if there is an invalid character in the
     * input stream
     */
    private static int[] readArrayFromStdin() throws IOException,
                                                     NumberFormatException {
        List<Integer> intList = new LinkedList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int value = 0, index = 0, ch;
        boolean valueFound = false;
        while ((ch = reader.read()) != -1) {
            if (ch >= '0' && ch <= '9') {
                valueFound = true;
                value = value * 10 + (ch - 48);
            } else if (ch == ' ' || ch == '\n' || ch == '\r') {
                if (valueFound) {
                    intList.add(value);
                    value = 0;
                }
                valueFound = false;
                if (ch != ' ') {
                    break;
                }
            } else {
                throw new NumberFormatException(
                        "Invalid character '" + (char)ch +
                        "' found at index " + index + " in input stream.");
            }
            index++;
        }

        int[] array = new int[intList.size()];
        Iterator<Integer> iterator = intList.iterator();
        index = 0;
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }
        return array;
    }

    public static void main(String[] args) {

        if(args.length>0 && args[0].equals("lots")){
            System.out.print("Usage: java InversionCounter [slow]");
            System.exit(1);
        }
        else if(args.length>0 && !args[0].equals("slow")){
            System.out.print("Error: Unrecognized option '" + args[0] + "'.");
            System.exit(1);
        }
        else{
            try{
                int[] inputArray = readArrayFromStdin();
                if(inputArray.length==0){
                    System.out.print("Enter sequence of integers, each followed by a space: Error: Sequence of integers not received.");
                    System.exit(1);
                }
                else{
                    long num = (args.length>0 && args[0].equals("slow"))? countInversionsSlow(inputArray):countInversionsFast(inputArray);
                    System.out.print("Enter sequence of integers, each followed by a space: Number of inversions: " + Long.toString(num));
                    System.exit(0);
                }
            }catch(Exception e){
                System.out.print("Enter sequence of integers, each followed by a space: Error: " + e.getMessage());
                System.exit(1);
            }
        }
        
        

    }
}