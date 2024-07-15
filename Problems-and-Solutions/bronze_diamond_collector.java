import java.io.*;
import java.util.StringTokenizer;

public class bronze_diamond_collector {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter pw = new PrintWriter("diamond.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int numInputs = Integer.parseInt(st.nextToken());
		int gap = Integer.parseInt(st.nextToken());

		int [] sizes = new int [numInputs];
        sizes[0] = Integer.parseInt(r.readLine());
        int max = sizes[0];
        int min = sizes[0];

        for(int i=1; i<numInputs; i++){
            sizes[i] = Integer.parseInt(r.readLine());
            if(sizes[i] > max){
                max = sizes[i];
            } if(sizes[i] < min){
                min = sizes[i];
            }
        }

        int maxCount = 0;
        int count = 0;
        if(max-gap <= 0){  // in case the range of given numbers is smaller than the specified range (gap)
            max = max+gap;
        }
        for(int a=min; a<=max-gap; a++){
            for(int i=0; i<numInputs; i++){
                if((sizes[i] >= a) && (sizes[i] <= a+gap)){
                    count++;
                }
            }
            if(count > maxCount){
                maxCount = count;
            }
            count = 0;
        }

        pw.println(maxCount);

		pw.close();
	}
}