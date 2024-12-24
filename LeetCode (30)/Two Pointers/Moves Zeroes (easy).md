- problem solving using two moving pointers to store information
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int inc = 1;
        int zerosCount = 0;
        for(int i=0; i<nums.length; i++){
            if(i+inc-1<nums.length && nums[i+inc-1]==0){
                int ind = i;
                while(ind+inc<nums.length && nums[ind+inc]!=0){
                    nums[ind] = nums[ind+inc];
                    ind++;
                }
                inc++;
                zerosCount++;
                i=ind-1;
            }
        }
        for(int i=nums.length-1; i>=nums.length-zerosCount; i--){
            nums[i] = 0;
        }

    }
}
```