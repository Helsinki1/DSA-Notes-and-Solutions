A red-black tree is a binary search tree that satisfies the following properties:
- Every node is either "red" or "black"
- The root is considered "black"
- Every null leaf is considered "black"
- If a node is "red," then both its children are "black"
- For each node, all simple paths from the node to descendant leaves contain the same number of "black" nodes
NOTE: the "height" of a red-black tree is the number of edges traveled from root to furthest leaf --> all the black NULL nodes on the bottom of the tree qualify as leaves

By constraining the node colors on any simple path from the root to a leaf, no path will be more than twice as long as any other --> ensures the tree is balanced, and that the height of *n* nodes is at most 2log_2(n+1)
![[RedBlackTree.png]]
"Black Height" --> the number of black nodes on the path between a node x and a leaf (including the leaf, but excluding node x)

"Internal nodes" on a red black tree are just the nodes that have a non-null key, since all leaves are null

![[RBT Rotations.png]]
Rotation operations are done to help balance the branches of red black trees while MAINTAINING the sorted output of in-order traversal
--> x's right must become y's left  OR  y's left must become x's right
--> x's parent must become y's parent  OR  y's parent must become x's parent
--> y's parent must become x   OR   x's parent must become y

For each insertion, there is potential for the tree to maintain balance with a rotation

Insertion Operations
- every inserted node always begins as "red"
- the "fix-up" function runs after every insertion to color each node correctly
Properties that could be violated during insertion: 
1. The root is black.  
2. If a node is red, then both its children are black.  

--> z & y are pointers, NOT NODES THEMSELVES
--> z initially points to the node just inserted, y points to inserted node's uncle

The Insertion Fix-up Routine:

While Z’s parent’s color is red:  applies to everything
**Case 1: z’s uncle y is red**  
parent[z].color = black  
y.color = black  
parent[ parent[z] ].color = red  
z = parent[ parent[z] ]  

z’s uncle y is black and ...  

z’s parent is a left child and  
**Case 2a: z is a right child**  
z = parent[z]  
left-rotate(z)  
**Case 3a: z is a left child**  
parent[z].color = black  
parent[ parent[z] ].color = red  
right-rotate(parent[ parent[z] ])  

z’s parent is a right child and  
**Case 2b: z is a left child**  
z = parent[z]  
right-rotate[z]  
**Case 3b: z is a right child**  
parent[z].color = black  
parent[ parent[z] ].color = red  
left-rotate(parent[ parent[z] ])  

Before completing the algorithm, make sure the root’s color is black.
* By instantiating every new inserted node as "red" first, it avoids messing with the "black height" of each node, and offers more consistent corrections