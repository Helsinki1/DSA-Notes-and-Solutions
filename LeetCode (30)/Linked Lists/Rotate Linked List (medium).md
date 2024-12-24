
* This is for a singly LinkedList
* First count the number of nodes in the LinkedList (the length)
* Mod the shifting amt "k" by the length and store it in "shift"
* Connect the tail node with the head node
* Traverse the LinkedList again to find the spot to split it apart, then split it
Be careful with the math involved while traversing, always consider edge cases
```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) return null;

        int length = 1;
        ListNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
            length++;
        }

        int shift = k % length;
        if(shift == 0) return head;

        temp.next = head;

        ListNode pointer = head;
        for(int i=0; i<length-shift-1; i++) pointer = pointer.next;
        ListNode out = pointer.next;
        pointer.next = null;

        return out;
    }
}    
```