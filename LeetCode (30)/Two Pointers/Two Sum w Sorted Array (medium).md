* Your goal is to find two elements in a sorted integer array that sum to a target value
* Set the "start" pointer at index 0, set the "end" pointer at index length-1
* If the two pointers sum to less than target, push start forward
* If the two pointers sum to more than target, push end backward
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int length = numbers.length;
        int start = 0;
        int end = length-1;

        while(numbers[start] + numbers[end] != target){
            int sum = numbers[start] + numbers[end];
            if(sum > target) end--;
            else start++;
        }
        start++;
        end++;
        int[] out = {start,end};
        return out;
    }
}
```
classic 2-pointer scanning