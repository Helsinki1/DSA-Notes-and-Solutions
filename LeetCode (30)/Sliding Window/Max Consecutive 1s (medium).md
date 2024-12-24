* Keep on pushing "endInd" until your window contains more than the permitted number of zeros, then at that point shift back your "endInd" by one and calculate the new max of continuous ones after flipping "k" zeros
* For sliding window questions, it is IMPERATIVE that you process the information in your initial window frame rigorously before it starts sliding
* Also, since you want to avoid indexing out of the array as the "endInd" reaches the last index, you want to do the last round of processing right after the while loop
```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        int numOnes = (nums[0]==1)? 1:0;
        int numZeros = (nums[0]==0)? 1:0;
        int startInd = 0;
        int endInd = 0;
        int max = 0;
        
        while(endInd <= nums.length-1){
            if(numZeros <= k){
                if(endInd==nums.length-1) break;
                endInd++;
                if(nums[endInd]==0) numZeros++;
                else numOnes++;
            } else{
                endInd--;
                numZeros--;
                if(numZeros + numOnes > max) max = numZeros + numOnes;
                if(nums[startInd]==0) numZeros--;
                else numOnes--;
                startInd++;
            }
        }

        if(numZeros > k) numZeros--;
        if(numZeros + numOnes > max) max = numZeros + numOnes;

        return max;
    }
}
```
