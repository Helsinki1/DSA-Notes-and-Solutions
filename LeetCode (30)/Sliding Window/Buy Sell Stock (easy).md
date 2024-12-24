* Learn the "two pointers" algorithm

``` java
class Solution {
    public int maxProfit(int[] prices) {
    
        int max = Integer.MIN_VALUE;
        int diff = 0;
        int start = 0;
        int end = 1;
        while(end < prices.length){
            diff = prices[end] - prices[start];
            if(prices[start] < prices[end]){
                if(max < diff) {
                    max = diff;
                }
            }else{
                start = end;
            }
            end++;
        }
        if(max <= 0) return 0;
        else return max;
    }
}
```