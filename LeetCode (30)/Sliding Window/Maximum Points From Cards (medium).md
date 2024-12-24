* You can only pick "k" cards, and you can only pick cards consecutively from the right/left of the array of positive integers. Your goal is to maximize the sum of values from your cards.
* The trick here is to use complementary counting and iterate through the array with a fixed-size sliding window for the numbers you want to exclude, finding the minimum value of this as you go

```java
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int arraySum = 0;
        for(int i=0; i<cardPoints.length; i++){
            arraySum += cardPoints[i];
        }

        if(k == cardPoints.length) return arraySum;
        
        int start = 0; // complementary strategy
        int sum = 0;
        int complength = cardPoints.length - k;
        int end = complength - 1;
        for(int i=0; i<complength; i++){
            sum += cardPoints[i];
        }
        int min = sum;

        while(end < cardPoints.length-1){
            sum -= cardPoints[start];
            start++;
            end++;
            sum += cardPoints[end];
            if(sum<min) min = sum;
        }

        return arraySum - min;
    }
}
```