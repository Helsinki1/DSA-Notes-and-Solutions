- BE CAREFUL TO NOT INDEX OUT OF THE ARRAY --> use break statements and if conditions as needed to prevent this
- BE CAREFUL OF EDGE CASES AT FRONT & END OF ARRAY --> think: do my checking routines still run for the first/last elements?
- Since valid numbers are -100 to 100, I use -101 as null
- By using two pointers and skipping around (pointer1 = pointer2), we can reduce complexity to O(n)
- Let pointer1 jump to pointer2's position after all consecutive equivalent values have been scanned by pointer2 (the nums array is in non-decreasing order (increasing or equal consecutive integers))
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if(length == 0 || length == 1) return length;
        int count = length;
        int pointer1 = 0;
        int pointer2 = 1;
        int index = 0;
        while(pointer2 <= length-1){
            while(nums[pointer1] == nums[pointer2] && nums[pointer1] != -101){
                count--;
                nums[pointer2] = -101;
                if(pointer2+1 > length-1) break;
                pointer2++;
            }
            nums[index] = nums[pointer1];
            index++;
            pointer1 = pointer2;
            pointer2++;
        }
        if(pointer1 == length-1) nums[index] = nums[pointer1];
        return count;
    }
}
```