The goal is to trap as much water in between walls as possible, given an array of the height of each wall at each position corresponding its index in the array. You have to take into account the height of the two boundaries as well as the distance between them.
- Set your start pointer at the very front & your end pointer at the very back
- Only move one pointer by one index per iteration of a while loop, moving the pointer of the boundary with the lesser height of the two boundaries
- Keep sweeping through and recording maximums until the distance between "start" and "end" pointers is 1
```java
class Solution {
    public int maxArea(int[] height) {
        int start = 0;
        int length = height.length;
        int end = length-1;
        int max = Math.min(height[start],height[end]) * (length-1);
        while(end - start > 1){
            if(height[start] > height[end]) end--;
            else start++;
            int temp = Math.min(height[start],height[end])*(end-start);
            if(temp > max) max = temp;
        }
        return max;
    }
}
```
![[Pasted image 20241031214557.png]]