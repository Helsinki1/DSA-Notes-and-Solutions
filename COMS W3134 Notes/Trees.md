**Trees** are hierarchical and nested data structures (Ex: file systems, program structure). They are very useful in speeding up search/sort algorithms, making it O(lg n) instead of O(n)

![[Pasted image 20241009162146.png]]
Vocabulary
- Every tree consists of the top "root node" and branches beneath called "sub-trees"
- A **leaf** is a node that has no children, it's a terminal node
- B, C, D are "children" of A, meanwhile E, F are "grandchildren" of A
- Each line in between two nodes is called an "edge"
- The path from A to E is **length** 2, which is the number of edges traveled
	- length = (level of start node) - (level of end node)
- n_1 is an **ancestor** of n_2 if n_1 is contained in the path from the root to n_2
	- the root is the ancestor to every node; every node is the ancestor to itself
- The **subtree** of a node n_k is the set of all nodes for which n_k is an ancestor
	- ex: the subtree of node D includes D, E, F, G
- The **depth** of a node is the length between it and the root --> depth(E) = 2
- The **height** of a tree is the length between the root and the farthest node (3 for above)
- The **diameter** is the number of nodes on the longest path on the tree (5: B & G for above)
- The **maximum width** of a tree is the number of nodes on the level of the tree with the most nodes (3: level one on the diagram above)
* A tree is considered full if every node has two children
* A tree is considered complete if every node besides the terminal level has a child

Tree Traversals
* Preorder --> process the root, then work your way leftward, then the closest right nodes
* Inorder --> process everything that's leftmost, then the root, then consider the right
* Postorder --> process everything that's leftmost, then consider the right, then the root



Preorder Traversal w/ Recursion
```java
public static void preOrder(Node root) {
	if(root != null) {
		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
}
```
Inorder Traversal w/ Recursion
```java
public static void inOrder(Node root) {
	if(root != null) {
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}
}
```
Postorder Traversal w/ Recursion
```java
public static void postOrder(Node root) {
	if(root != null) {
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}
}
```
Finding Height of a Binary Tree w/ Recursion
```java
public static int height(Node root) {
	if(root==null) return 1;
	return 1 + Math.max(height(root.left),height(root.right));
}
```