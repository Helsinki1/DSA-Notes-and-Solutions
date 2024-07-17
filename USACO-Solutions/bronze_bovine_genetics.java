import java.io.*;
import java.util.StringTokenizer;

public class bronze_bovine_genetics{
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("cownomics.in"));
		PrintWriter pw = new PrintWriter("cownomics.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int numCows = Integer.parseInt(st.nextToken());
		int numGenes = Integer.parseInt(st.nextToken());  // cows will be labeled from 1 to N (subtract 1 to index them)

        String [] spottyGenes = new String [numCows];
        String [] plainGenes = new String [numCows];
        
        for(int i=0; i<numCows; i++){
            spottyGenes[i] = r.readLine();
        }
        for(int i=0; i<numCows; i++){
            plainGenes[i] = r.readLine();
        }

        int count = 0;
        for(int a=0; a<numGenes; a++){
            boolean valid = true;
            for(int b=0; b<numCows; b++){
                for(int c=0; c<numCows; c++){
                    if(plainGenes[c].substring(a,a+1).equals(spottyGenes[b].substring(a,a+1))){
                        valid=false;
                    }
                }
                if(!valid) b=numCows;
            }
            if(valid) count++;
        }

        pw.println(count);
        pw.close();
    }
}