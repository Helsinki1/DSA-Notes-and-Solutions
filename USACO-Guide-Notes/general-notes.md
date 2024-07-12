**Data Types**
Int --> 4 bytes, $±2 \cdot 10^9$
long long --> 8 bytes, $±9 \cdot 10^{18}$
double --> 8 bytes, used to store decimals (do not == them)
bool --> 1 bit, true or false
char --> 1 byte, any character on ASCII

**Input & Output (C++)**
`include <iostream>`
`using namespace std;`
`int main() {`
	`int a;`
	`int b;`
	`int c;`
	`cin >> a >> b >> c;`
	`cout << a+b+c << endl;`
`}`
**Input & Output (Java Ex1)**
`import java.util.Scanner;`
`public class Main {`
	`public static void main(String[] args) {`
		`Scanner sc = new Scanner(System.in);`
		`int a = sc.nextInt();`
		`int b = sc.nextInt();`
		`int c = sc.nextInt();`
		`System.out.print("The sum of these three numbers is ");`
		`System.out.println(a + b + c);`
	`}`
`}`
**Input & Output (Java Ex2)**
`import java.io.*;`
`import java.util.StringTokenizer;`
`public class Main {`
	`public static void main(String[] args) throws IOException {`
		`BufferedReader r = new BufferedReader(new InputStreamReader(System.in));`
		`PrintWriter pw = new PrintWriter(System.out);`
		`StringTokenizer st = new StringTokenizer(r.readLine());`
		`int a = Integer.parseInt(st.nextToken());`
		`int b = Integer.parseInt(st.nextToken());`
		`int c = Integer.parseInt(st.nextToken());`
		`pw.print("The sum of these three numbers is ");`
		`pw.println(a + b + c);`

		* flushes and closes the output stream.
		pw.close();
	}
`}`

[Debugging & Troubleshooting](https://usaco.guide/general/debugging-checklist?lang=java#wrong-answer-or-runtime-error)

**General Rules of Thumb in terms of Practicing**
1. Thoroughly understand each type of algorithm before moving onto a new one
2. Problem solving = correctly implementing the appropriate algorithm
3. Problem solving $\ne$ hurriedly writing code & bashing a solution
4. Reading the solution after ~20 min is okay if you've exhausted your ideas
5. Don't read the full solution; read the first part and try the problem again
