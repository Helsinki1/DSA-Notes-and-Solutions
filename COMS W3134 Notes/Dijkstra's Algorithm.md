**Dijkstra's Algorithm** is for finding the shortest distance from a starting node to each node on a weighted-path undirected non-cyclic graph (all path weights must be positive)
- Use either an Adjacency Matrix or an Adjacency List to represent the graph
- Use a priority queue (min-heap) to get the next node to visit (an unvisited neighbor with the shortest distance from start), as well as store the distances of each node from start
- Optional: use a boolean array to track which nodes have been visited

1. Build your priority queue, inserting every node of the graph besides the starting node
	- For neighbors of the starting node, record their distFromStart. For all other nodes, assign Integer.MAX_VALUE
	- Start is marked as visited
*WHILE queue/heap is not empty LOOP:*
2. Pop the least distFromStart node N (guaranteed to be accessible through already visited nodes) from the min heap and visit it
3. Update the distFromStart of its neighbor nodes in the heap if a path through node N to a neighbor is shorter than what was previously recorded for that neighbor
	- If a node was already visited, it would have been popped from the priority queue, and you will not update its distFromStart as it's not in the heap

* **Time Complexity w/ Adjacency Matrix is Theta(V^2)** since you have to check the neighbors of every node you visit
* **Time Complexity w/ Adjacency List is O(lg(V) * (V+2E))** since again we have to check every node's neighbors. "E" represents the # of edges. Since it's an undirected graph, the # of trailing nodes from LinkedList heads in an Adjacency List = (2x the # of edges)
	* The "lg(V)" comes from checking (fetching) and updating (deletion & insertion) the distFromStart of nodes in the min heap
	* Because (# of Vertices) - 1 <= (# of Edges) --> Time Complexity is **O(lg(V) * E)** since V+2E <= 3E+1, which simplifies to E
* If you break from the algorithm once you found the shortest distance for a particular node, the time complexities will become upper bounds


![[Pasted image 20241201224310.png]]
1. Since A is our starting node, we build our min-heap with this information
	- B --> distFromStart = 3
	- C --> distFromStart = MAX VALUE
	- D --> distFromStart = 7
	- E --> distFromStart = MAX VALUE
2. We pop node B, which has the lowest distFromStart (and record this distance in the final output)
3. We calculate the distFromStart of each of B's unvisited neighbors, and we update the min-heap (delete & insert) if the path through B to a neighbor is shorter than what was previously recorded. 
	- C's distFromStart through B = 3+4
	- D's distFromStart through B = 3+2
4. We update C & D, and the heap looks like this
	- D --> distFromStart = 5
	- C --> distFromStart = 7
	- E --> distFromStart = MAX VALUE
5. We pop node D and record its distFromStart
6. We calculate the distFromStart of each of D's unvisited neighbors
	- C's distFromStart through D = 5+5
	- E's distFromStart through D = 5+4
7. We update E, and the heap looks like this
	- C --> distFromStart = 7
	- E --> distFromStart = 9
8. We pop node C and record its distFromStart
9. We calculate the distFromStart of each of C's unvisited neighbors
	- E's distFromStart through C = 7+6
10. We reject this alternative path for E. The distFromStart for E remains 9
11. We pop node E and record its distFromStart

Final Output:
- A's shortest distance from A = 0
- B's shortest distance from A = 3
- D's shortest distance from A = 5
- C's shortest distance from A = 7
- E's shortest distance from A = 9