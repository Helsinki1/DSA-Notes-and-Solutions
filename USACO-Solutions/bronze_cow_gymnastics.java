import java.io.*;
import java.util.StringTokenizer;

public class bronze_cow_gymnastics{
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("gymnastics.in"));
		PrintWriter pw = new PrintWriter("gymnastics.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int numLines = Integer.parseInt(st.nextToken());
		int numCows = Integer.parseInt(st.nextToken());  // cows will be labeled from 1 to N (subtract 1 to index them)

        	int [][] cowRankings = new int [numLines][numCows];
	        for(int a=0; a<numLines; a++){
	            String [] line = (r.readLine()).split(" ");
	            for(int b=0; b<numCows; b++){
	                int cowIdentity = Integer.parseInt(line[b]);
	                cowRankings[a][cowIdentity-1] = b;
	            }
	        }
	
	        int count = 0;
	        for(int a=0; a<numCows; a++){
	            for(int b=0; b<numCows; b++){
	                boolean consistent = true;
	                for(int i=0; i<numLines; i++){
	                    if(cowRankings[i][a] >= cowRankings[i][b]){
	                        consistent = false;
	                    }
	                }
	                if(consistent) count++;
	            }
	        }
	        
	        pw.println(count);
	        pw.close();

    	}
}
