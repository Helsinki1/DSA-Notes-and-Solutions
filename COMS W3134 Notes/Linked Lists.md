A linked list is a linear data structure that dynamically stores elements using nodes, something that stores both a value and a reference (pointer) to the next node. Node traversal must follow the path dictated by the collective pointers to future nodes. The last node is "null," making it easy to compare other nodes to null.
- Nodes are continuously added & removed as data is added & removed
- The first node is called the Head, the last node is called the Tail
	- Head and Tail must always point to something; if there's only one node, they both point to it
- When implementing a linked list, you are really just keeping track (pointing to) the Head
- Each node can only point to one other node
- Use `[node].data` to return the element stored at the node

Appending a Value
	`tail.next = [value being appended];`
	`tail = [value being appended];`
Traversing a Linked List
	`Node p = head;`
	`while (p != null) {`
		`print(p.data);`
		`p = p.next;`
	`}`
Maintaining Order in a Linked List (two pointers p&q)
	check if p points to a node greater than the value to be inserted
	set q.next equal to the value you want to insert


Time Complexities of Linked Lists
	Given "n" elements in a linked list
- `add(element)` --> Theta(1)    if there is a tail pointer
- `add(element)` --> Theta(n)    if there is no tail pointer
- `add(index, element)` --> O(n)
- `get(index)` --> O(n)
- `remove(index)` --> O(n)
- `indexOf(element)` --> O(n)

A doubly linked list stores pointers to both the previous node and the next node

Inserting an Element into a Linked List
`public SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {`
	`SinglyLinkedListNode p = llist, q = null;`
	`for(int i=0; i<position; i++){`
		`q = p;`
		`p = p.next;`
	`}`
	`SinglyLinkedListNode insert = new SinglyLinkedListNode(data);`
	`if (q==llist){`
		`llist.next = insert;`
	`} else {`
		`q.next = insert;`
	`}`
	`insert.next = p;`
`}`