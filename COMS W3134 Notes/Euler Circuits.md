The steps taken by DFS can be illustrated as a (directed) spanning tree
Add a "**tree edge**" for every graph edge taken by DFS
Add a "**back edge**" for every skipped edge
- For example, when you get to a node and check its neighbors, if a neighbor has already been visited, we signify this by drawing a "back edge" to it. We always ignore the node we came from, we never draw back edges to them.

A **biconnected graph** is a graph such that there is no single vertex V such that disconnecting it would make traveling to certain nodes impossible. Such critical nodes are called **articulation points**.
- A graph G is a biconnected graph if everything is connected to each other, and there does not exist any articulation points


**Euler Circuits** 
- An **Euler Path** is a path through an undirected graph that visits every edge exactly once. Basically, imagine trying to go along every edge without 1) lifting your pencil 2) going backwards on an edge you've already drawn
- An **Euler Circuit** is an Euler Path that ends at the vertex where it started

For an Euler Circuit to exist, the starting vertex must have an even indegree. If a vertex has an odd indegree, then you will get stuck when you visit it the 2nd time --> come, leave, come, ?

For an Euler Path to exist, you must have either 0 or 2 vertices with an odd indegree. If you have 2 vertices with an odd indegree, you MUST start and end on those vertices.

To Find the Euler Circuit of a Graph:
1. First check that every node has an even indegree (guarantee that a circuit exists)
2. Start at the lowest lexicographical node and run DFS. Every time you run yourself into a dead end where a node has no more edges to travel to, pick out the lowest lexicographical node that still has non-traveled outbound edges and run DFS again. This will produce component Euler Circuits (these sub-traversals will be guaranteed to start and end at the same node as they have even degrees)
3. We want to merge each component Euler Circuit with the first DFS traversal, inserting each component Euler Circuit at the first occurrence of the start/end point in the first DFS traversal

