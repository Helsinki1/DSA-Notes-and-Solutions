A **graph** is a set of vertices & edges. Edges represent connections between vertices. Edges can be directed or undirected, weighted or unweighted. 
- **Adjacency Matrices** are extremely useful for computing the number of paths of a certain length --> columns & rows: all nodes, write a 1 if there is an edge between two nodes
	- if the graph is undirected, the adjacency matrix should be symmetrical about the major diagonal
	- to check if you can go from one node to another (indexing a 2D array), it's Theta(1)\
	- to count the number of other nodes you can visit from one node, you check its respective row which has "v" number of entries, so it's Theta(v)
	- the space complexity is clearly v^2 as it's a square with v x v entries
- **Adjacency Lists** are an array of each node in the graph, each element is a LinkedList of other nodes that its adjacent to
	- to check if you can go from one node to another (indexing an array of LinkedList heads is Theta(1), then traversing through the LinkedList is O(d)), it's O(d) where d=the number of edges emerging from that vertex
	- to count the number of other nodes you can visit from one node, you iterate through its respective LinkedList which has "d" entries, so it's Theta(d)
	- the space complexity is "v" for the vertex-heads in the array of LinkedLists, then another "2 x edges" (for an undirected graph) for the number of nodes attached to the vertex-heads

Runtimes for Adjacency Matrices:  call "v" the # of vertices
- Determining if there's an edge between two nodes --> Theta(1)
- Counting all vertices adjacent to a given vertex --> Theta(v)
Runtimes for Adjacency Lists:  call "d" the number of edges from that vertex
- Determining if there's an edge between two nodes --> O(d)
- Counting all vertices adjacent to a given vertex --> Theta(d)
	- where "E" is the # of edges
When to use:
- Matrices --> small graphs & dense graphs
- Lists --> large graphs & sparse graphs

**Breadth-First Search (BFS)** --> matrix Theta(v^2), list Theta(v+E)
1. Put the starting node into a simple queue
*WHILE queue is not empty, LOOP:*
2. pop the next unvisited node from the queue 
3. put all the adjacent, non-visited nodes into the queue in lexicographical order
4. mark the current node as visited
(you also have a set of already-visited nodes)
* BFS can be used to find the shortest path to every vertex on an unweighted graph from a specified starting point
* Time Complexity is v^2 for adjacency matrix because every node's array of neighbors needs to be checked, regardless if they were visited before or not
* Time Complexity is v+E for adjacency list because, again, every node's LinkedList of neighbors needs to be checked

**Depth-First Search (DFS)** --> matrix Theta(v^2), list Theta(v+E)
* From your starting node, execute a recursive function on each of its children, which will then execute the same recursive function on their children
* The order in which nodes are visited depends on the compiler's stack of recursive calls
* The difference between DFS & BFS is that BFS strictly uses a queue to guarantee that all nodes length N away are visited *before* nodes N+1 away. For DFS, nodes are visited whenever their recursive call is ran
(you also have a set of already-visited nodes)
* Time Complexity of BFS & DFS are both equal to the space complexities of adjacency matrices and adjacency lists because they both involve checking every node's array/list of neighbors
* Preorder Traversal --> if we output a node as soon as it is visited in DFS
* Postorder Traversal --> if we output a node when all its neighbors are visited in DFS (we output a node as soon as we realize it's a "dead end")

```java
public class BFS {  
	private static final Scanner scanner = new Scanner(System.in);  
	
	static class Pair {  
		public int vertex, distFromStart; 
		public Pair(int vertex, int distFromStart) {  
			this.vertex = vertex;  
			this.distFromStart = distFromStart;  
		}  
	}  
	
	static int[] getAdjacentTo(int vertex, int[][] edges) {  
		List<Integer> adjacentVerticesList = new ArrayList<>();
		for(int[] edge: edges){
			if(edge[0]==vertex){
				adjacentVerticesList.add(edge[i]);
			} else if(edge[i]==vertex){
				adjacentVerticesList.add(edge[0]);
			}
		} 
		int[] adjacentVerticesArray = new int[adjacentVerticesList.size()];
		int j=0;
		for(int adjacent: adjacentVerticesList){
			adjacentVerticesArray[i++] = adjacent;
		}
		return adjacentVerticesArray;
	}  
	static int[] bfsHelper(int vertex, int numVertices, int[][] edges) {  
		int[] visited = new int[numVertices + 1];
		for(int i=0; i<visited.length; i++){
			visited[i] = -1;
		}
		visited[vertex] = 0;
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(vertex, 0));
		while(!queue.isEmpty()){
			Pair front = queue.poll();
			for(int adjacent: getAdjacentTo(front.vertex, edges)){
				if(visited[adjacent] == -1){
					int newDist = 6 + front.distFromStart;
					visited[adjacent] = newDist;
					queue.add(new Pair(adjacent, newDist));
				}
			}
		}
		return visited;
	}  
	public static int[] bfs(int n, int m, int[][] edges, int s) {  
		int[] visited = bfsHelper(s,n,edges);
		int[] expectedAnswer = new int[n-1];
		for(int i=1, j=0; i<visited.length; i++){
			if(i != s){
				expectedAnswer[j++] = visited[i];
			}
		}
		return expectedAnswer;
	}  

	public static void main(String[] args) throws IOException {  
		BufferedWriter bufferedWriter =  
		new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));  
		String skipRegex = "(\r\n|[\n\r\u2028\u2029\u0085])?";  
		int q = scanner.nextInt();  
		scanner.skip(skipRegex);  
		for (int qItr = 0; qItr < q; qItr++) {  
			String[] nm = scanner.nextLine().split(" ");  
			int n = Integer.parseInt(nm[0]),  
			m = Integer.parseInt(nm[1]);  
			int[][] edges = new int[m][2];  
			for (int i = 0; i < m; i++) {  
				String[] edgesRowItems = scanner.nextLine().split(" ");  
				scanner.skip(skipRegex);  
				for (int j = 0; j < 2; j++) {  
					int edgesItem = Integer.parseInt(edgesRowItems[j]);  
					edges[i][j] = edgesItem;  
				}  
			}  
			int s = scanner.nextInt();  
			scanner.skip(skipRegex);  
			int[] result = bfs(n, m, edges, s);  
			if (result.length > 0) {  
				bufferedWriter.write(String.valueOf(result[0]));  
			}

			for (int i = 1; i < result.length; i++) {  
				bufferedWriter.write(" ");  
				bufferedWriter.write(String.valueOf(result[i]));  
			}  
			bufferedWriter.newLine();  
		}  
		bufferedWriter.close();  
		scanner.close();  
	}  
}
```