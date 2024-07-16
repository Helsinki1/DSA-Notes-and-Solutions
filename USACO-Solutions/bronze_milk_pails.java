
import java.io.*;
import java.util.StringTokenizer;

public class bronze_milk_pails {
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader("pails.in"));
		PrintWriter pw = new PrintWriter("pails.out");

		StringTokenizer st = new StringTokenizer(r.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int Xmax = m/x;
		int Ymax = m/y;
		int out = 0;

		for(int a=0; a<=Xmax; a++){
			for(int b=0; b<=Ymax; b++){
				int combo = (a*x)+(b*y);
				if((combo > out) && (combo <= m)){
					out = combo;
				}
			}
		}

		pw.println(out);
		pw.close();
	}
}