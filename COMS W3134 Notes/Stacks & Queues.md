**Stack**: a linear data structure where data can only be inserted and deleted from one side of the list. Stacks can be implemented using an ArrayList or LinkedList
* With a stack, we add elements to the top. We also remove elements from the top. This type of data structure is called a Last In First Out (LIFO) structure.

Stack Methods: -- all run in theta(1) time
1. boolean empty() --> tests if stack is empty
2. E peek() --> returns top element without deleting it
3. E pop() --> returns top element and deletes it
4. E push(E item) --> pushes element on the top of the stack

Ex: decide if a sequence of different brackets "(" "[" "{" ")" "]" "}" forms a palindrome of brackets --> add each new left sided bracket to a stack until you see a right sided bracket, then keep on popping and checking if the right sided brackets match each left sided one
* a trick to this question is to create a char array of length 128 so that you can input the ASCII value of a char as an index to check matching chars

`private static final char[] pairings = new char[128];`
`static {`
	`pairings['('] = ')';`
	`pairings['['] = ']';`
	`pairings['{'] = '}';`  // this uses ASCII to create a rudimentary map
`}`
`public static String isBalanced(String s) {`
	`Stack<Character> stack = new Stack<>();`
		`for(int i=0, length = s.length(); i<length; i++){`
			`char c = s.charAt(i);`
			`if(c == '{' || c =='(' || c == '[') {`
				`stack.push(c);`
			`} else {`
				`if (stack.empty() || stack.pop() != pairings[c]) {`
					`return "NO";`
				`}`
			`}`
		`}`
	`return stack.empty() ? "YES" : "NO";`   // ternary boolean operator
`}`


**Queues**: a linear data structure where elements are inserted at one end of the list, and elements are removed from the opposite end. Queues follows the First In First Out (FIFO) principle. Queues can be implemented using ArrayLists, but usually with LinkedLists.

Queue Interface: -- all run in theta(1) time
1. boolean add (E e) --> inserts element and returns if action was successful
2. E element() --> retrieves but does not remove the head of the queue
3. boolean offer(E e) --> inserts the element if it's possible immediately
4. E peek() --> retrieves but does not remove the head of the queue, returns null if queue empty
5. E poll() --> retrieves and removes the head of the queue, returns null if queue empty
6. E remove() --> retrieves and removes the head of the queue

Enqueue: the process of adding an element to the back of the queue
Dequeue: the process of removing an element from the front of the queue

Types of Queues:
1. Simple Queue
2. Circular Queue: the last element is connected to the first element of the queue
3. Priority Queue: queue elements have associated priority values, elements with higher priority are retrieved before elements with lower priority (usually done with a heap)
4. Deque: does not follow FIFO, insertion and deletion could be on either end