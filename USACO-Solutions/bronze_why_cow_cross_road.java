import java.io.*;
import java.util.StringTokenizer;

public class bronze_why_cow_cross_road{
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("circlecross.in"));
		PrintWriter pw = new PrintWriter("circlecross.out");

		String crossings = r.readLine();
	        // if two letters appear like A/B/A/B then they cross, if A/A/B/B then no
	        
	        String [] cows = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	        int count = 0;
	        for(int a=0; a<26; a++){
	            for(int b=0; b<26; b++){
	                boolean cowsCross = true;
	                String prev = " ";
	                for(int i=0; i<52; i++){
	                    if(crossings.substring(i,i+1).equals(cows[a]) || crossings.substring(i,i+1).equals(cows[b])){
	                        if(prev.equals(crossings.substring(i,i+1))){
	                            cowsCross = false;
	                        }
	                        prev = crossings.substring(i,i+1);
	                    }
	                }
	                if(cowsCross) count++;
	            }
	        }
	
	        pw.println(count/2);
	        pw.close();
	    }
}
	
