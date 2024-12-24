A **priority queue** is a collection of comparable elements that supports the following operations:
- insert(x) --> add an element to the queue
- deleteMin() --> return the minimum element in the queue and remove it

How would we implement a priority queue?
- Using a LinkedList
	- Either keep the LinkedList sorted, or traverse it every time to find the minimum
- Using a balanced binary search tree
	- Insert & deleteMin functions are both Theta(lg n) complexity
- But no, we use a heap

Storing a Complete Binary Tree in an Array (this is how to show a heap linearly)
- Simply store the elements of the tree in level-order (top to bottom, left to right)
- Since each node must have 2 children, the tree shape is fixed by the number of nodes

A **heap** is a completely binary tree stored in an array, with the **heap order property**:
- for every node N with value x --> the values of all nodes in the subtree with the root of  node N are less than or equal to x OR greater than or equal to x
- the root should be the maximum OR minimum value in the tree
Min Heap
- insert(x) --> Place the value "x" in the next available node of tree's lowest level, assuming the nodes of the lowest level are filled from left to right. Then compare "x" with the value of its parent node, swapping their values if the parent is greater than "x". Keep doing this until the parent is less than or equal to "x".
- delete(x) --> Set the value of the node with key "x" equal to the rightmost node on the lowest level of the tree. Delete this rightmost, lowest node. Now perform percolate operations on the node now in the position of "x" until both children are greater than it. Always swap with the child with the lowest key if both are less than the parent.
Max Heap
- insert(x) --> same operations as Min Heap except you swap when the parent node's value is less than "x". You stop swapping when the parent node's value is greater/equal.
- delete(x) --> the same operations as Min Heap except you percolate by swapping the child of greatest value greater than "x" until both children are less than the node with "x"

- Build a heap by using insert(x) for each element is called "iterative insertion," there are more efficient ways of doing this... like BuildHeap
BuildHeap --> runtime of O(n)
- in a perfect (full) binary tree --> height = log_2(N), given N number of nodes
- we initially construct the heap exactly like the array of the keys that is given, the leftmost key is the root and each key after that are nodes filled into each level in order
- then, we go from right to left in that array, percolating values if their child is less/greater than the current node (note how this is from a parent's perspective, you're checking if each node's subheap is valid or not)

Given an unordered sequence of N numbers S = (a1, a2, a3... an), what's the most efficient way to find the k-th largest number?
	Option 1
		- Build a Max Heap --> O(N)
		- Then call deleteMax() k number of times --> O(k lg N)
		- Return the root
		- complexity --> O(N + k lg N)
	Option 2
		- Build a Min Heap out of the first k elements in S --> O(k)
			- the root of this heap is the min of the first k elements
		- Iterate through the remaining N-k, if a number is larger than the root of the Min Heap created, remove the root and insert this number
		- At the end, the root of the Min Heap should be the k-th largest element
		- complexity --> O(k + (N-k)* lg k) = O(N lg k)

Sorting using a Heap
- first convert an unordered array into a heap, Theta(N) complexity
- call on deleteMin() repeatedly until the heap is empty, storing each output into another array containing the sorted elements of the original
Alternatively
- you can take the advantage of the freed up space after calling deleteMin() instead of creating a whole other array


NOTE: heap order property does NOT guarantee that the heap is perfectly sorted, it only guarantees that the root is the absolute min/max of the elements