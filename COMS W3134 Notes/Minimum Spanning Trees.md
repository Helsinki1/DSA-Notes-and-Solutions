A **Minimum Spanning Tree** is a non-cyclic graph that makes every vertex reachable from any other vertex, but the tree's sum of edge weights is minimal
- a Minimum Spanning Tree is the result of connecting all the nodes of a given graph together, while keeping the sum of path-weights a minimum
- Prim's & Kruskal's algorithms below are different methods of finding a MST

**Prim's Algorithm**
- Almost identical to Dijkstra's, but instead of outputting shortest distFromStart, we output each connection we make between two neighbors AND the total sum of path-weights
1. Represent your graph with an Adjacency Matrix or List, store your unvisited nodes in a priority queue (min-heap)
2. Build a heap with every node besides your starting, giving them Integer.MAX_VALUE dist
*WHILE heap not empty LOOP:*
3. pop the next cheapest unvisited node N from the queue, visit it, then update the prices of the paths through node N to each of its neighbors if it's cheaper than what is stored
4. Each new node your visit, output what node you came from and the connection's price
- Note that we do NOT sum the cost to first arrive at node N as we do in Djikstra's

![[Pasted image 20241202134117.png]]
- For Dijkstra's and Prim's, you want to draw out the information like this ^^
- Record the current node being visited on the left, and the updated heap on the right
- Time Complexity, same as Dijkstra's --> **Theta(V^2)** for Matrix, **O(E lg V)** for List
- Starting vertex "a(-,-)" for Prim's, but "a(-,0)" for Djikstra's bc it's cumulative


**Kruskal's Algorithm**
1. sort edges in order of increasing path-weight in array A. If weights are equal, sort lexicographically
2. given V vertices in a graph, you know the MST will have V-1 edges
*WHILE you have less than V-1 edges in your MST, LOOP:*
3. If the next cheapest edge in list L does not create a cycle in your MST, add this edge (in order of increasing path-weight) to your MST
	- else, discard it and move on to the next cheapest edge in list L
- Time Complexity is **O(E lg E)** since sorting edges by path-weight is the slowest operation

**Union-Find Algorithm**
- This is a sub-component of Kruskal's that allows us to determine if a cycle exists in our MST thus far
- For this algorithm, we will create an auxiliary diagram with the vertices of the graph. We will position every node lexicographically, linearly, and disconnected. For each connection between two nodes we add to our MST, we will draw an arrow pointing from the newly visited neighbor back to the node we came from.
- Let's call a node's **root** the node we reach if we follow its pointers to the final destination
- If a node has no pointers to other nodes, its root is itself
- **union(x,y)** --> sets the root of y's subtree to the root of x's subtree, O(1)
	- make sure to FULLY traverse down all the arrows to get y's root, then make it point to x's root
- **find(x)** --> follows the pointer chain from x to its root, O(V)

1. Make sure all the edges are sorted in increasing-order for their path weights --> O(E lgE)
	- make sure you write the nodes of the edges in alphabetical order (AD, not DA)
1. Position every node on the graph linearly and disconnected at first
	- each node will point to itself at first, this can be stored in an array [0,1,2,3,4...]
*WHILE there are still unconnected nodes, LOOP:*
3. Grab the edge with the next lowest path-weight. If this edge does not cause a cycle (use find function) in the MST you're making, add it to the MST
	- if find(A) == find(B), aka if A's root the same as B's root, then a cycle exists
- when all nodes are connected, you should have V-1 edges


**Compressing Function Calls w/ Union Find** (capstone project)
- If node A points to node B, which then points to node C, you can compress this by updating node A to point directly to node C
	- When we check a connection AD, and we see that A.next stores B and B.next stores C, we should write our code to update A.next to become C
	- The implication here is that if we never check a connection involving a node X, then a possible compression for X will not occur
- Compression helps fasten the Time Complexity of find(x) close to O(1), otherwise it's more like O(V)