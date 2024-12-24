```java
class Solution {
    public ListNode sortList(ListNode head) {
        if(head==null) return null;
        ArrayList<Integer> vals = new ArrayList<>();
        ListNode n = head;
        while(n != null){
            vals.add(n.val);
            n = n.next;
        }
        int[] arrVals = new int[vals.size()];
        for(int i=0; i<vals.size(); i++) arrVals[i] = vals.get(i);
        Arrays.sort(arrVals);

        ListNode out = new ListNode(arrVals[0]);
        n = out;
        out.next = n;
        for(int i=0; i<vals.size()-1; i++){
            n.next = new ListNode(arrVals[i+1]);
            n = n.next;
        }
        n.next = null;
        return out;
    }
}
```
