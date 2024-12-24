**Indegree**: how many paths feed into this node? the number of directed paths / arrows pointing at this node

Topological sorts are for directed graphs, it calculates a naturally flowing / progression of nodes starting at origin nodes with indegree zero. The directed graph you're working with must be non-cyclic.

1. start with the vertices with indegree 0, add these to a Priority Queue (min heap)
* by storing the vertices with indegree 0 in a min heap, it guarantees that we work with the lexicographically-smallest nodes first
*WHILE this queue is not empty:*
2. pop from the queue and output this node (append it to the output list)
3. go to each of this node's neighbors and decrease each of their indegrees by 1
4. if any of its neighbors now has an indegree of 0, insert them into the min heap queue

* If you get to a point where all un-outputted nodes are not indegree 0, a topological sort of the directed graph is not possible --> the directed graph contains a cycle
* if a node is isolated, it'll still have indegree 0 and be visited at some point
![[Pasted image 20241118161801.png]]
Vertex:     2,3,5,7,8,9,10,11
Indegree: 1, 0,0,0,2,2,2,2
Output: 3,5,7,8,11,2,9,10

1. We start at 3, we decrement 8's indegree, then decrement 10
2. We start at 5, then decrement 11's indegree
3. We start at 7, then decrement 8 and add it to the queue, then 11 and add it to the queue
4. We start at 8, then decrement 9
5. We start at 11, then decrement 2 and add it to the queue, decrement 9 and add it to the queue, decrement 10 and add it to the queue
6. We start at 2
7. We start at 9
8. We start at 10
- We output numbers that we start with (0's from left to right at first)
	- Every time a vertex's indegree reaches 0, we add this to the queue of future starting points
- We only decrement the vertices adjacent to the starting point

```java
public static List<Integer> toposort(List<Integer>[] graph){
	int[] indegree = new int[graph.length];

	// determine the indegree of each vertex
	for(int vertex = 1; vertex < graph.length; vertex++){
		for(int node: graph[vertex]){
			indegree[node]++;
		}
	}

	// make a min heap for vertices with indegree 0 (starting points)
	PriorityQueue<Integer> q = new PriorityQueue<>();
	for(int vertex=1; vertex<graph.length; vertex++){
		if(indegree[vertex]==0) q.add(vertex);
	}

	// core algo
	List<Integer> out = new ArrayList<>();
	while(!q.isEmpty()){
		int vertex = q.poll();
		out.add(vertex);
		for(int adjacent: graph[vertex]){
			if(--indegree[adjacent]==0){
				q.add(adjacent);
			}
		}
	}

	if(out.size() != graph.length-1){
		return null;
	}else{
		return out;
	}
}
```