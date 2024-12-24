```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0; i<nums.length-k+1; i++){
            if(i==0){
                for(int a=0; a<k; a++){
                    sum += nums[a];
                }
                if(sum>max) max = sum;
            } else {
                sum -= nums[i-1];
                sum += nums[i+k-1];
                if(sum>max) max = sum;
            }
        }
        return max/k;
    }
}
```
