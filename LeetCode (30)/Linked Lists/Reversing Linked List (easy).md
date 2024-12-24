* The logic behind reversing a linked list is to traverse it from Head to Tail using three pointers: "prev" at null, "current" at head, and "temp" at head.next
* If you don't space it out between three pointers, you cannot set node.next of the middle pointer to the left pointer without cutting off your ability to traverse the rest of the list (as you manipulate the middle node's "next," you wont be able call on it to go rightward)
* Using the rightmost pointer to preserve your ability to traverse the list, you use the other two to continuously set the middle node's "next" to the last node -- all while shifting the three nodes to the right in each iteration
* The nodes stop shifting when "temp" becomes null after shifting right from the last element and "current" follows suit (temp = current = null, rightward of the last position)
* You output "prev" which is the last node in the list, which now points to each node behind it (you successfully reversed where each node is pointing)
```java
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;

        while(current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        return prev;
    }
}

// FAULTY SOLUTION
class Solution {
	public ListNode reverseList(ListNode head) {
		ListNode current = head;
		ListNode prev = null;
		ListNode temp = current.next;

		while(current != null) {
			current.next = prev;
			prev = current;
			current = temp;
			if(current != null) temp = current.next;
		}

		return prev;
	}
}
```
* This solution is faulty as the ListNode temp is declared outside the while loop, which makes an error possible if the linked list is empty