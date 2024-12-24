I found the trick to this question to be very very hard to come up with, so I first did it one way by sorting a 2D array of {element value, element index} of the original array "nums," then pushing "start" and "end" pointers incrementally:
	push "end" backward if sum > target
	push "start" forward if sum < target
Then finally returning the element indices when the values added up to target
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int start = 0;
        int end = length-1;

        int[][] sorted = new int[length][2];
        for(int i=0; i<length; i++){
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, Comparator.comparingInt(row -> row[0]));

        int sum = Integer.MIN_VALUE;
        while(sum != target){
            sum = sorted[start][0] + sorted[end][0];
            if(sum > target) end--;
            else if(sum < target) start++;
        }
        int[] out = {sorted[start][1], sorted[end][1]};
        return out;
    }
}```
Useful method I learned:  `Arrays.sort(array, Comparator.comparingInt(row -> row[0]));`
- This allows me to sort a 2D array with respect to integers from only one specified column

However, this solution's complexity was O(n * log(n)) instead of O(n).
- `Arrays.sort()` has a complexity of O(n* log(n))

As I read other solutions, I see that there is no trick at all involving 2 pointers... This really is purely a map/table problem... Improvements in time complexity mainly come from:
- Using a hash table instead of a sorted array
	- `hashTable.get(key)` & `hashTable.containsKey(key)` are both O(1) complexity, both simply input a key and calculates the hash index and fetches the value / checks for a collision
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }

        return new int[]{}; // No solution found
    }
}
```
This solution passes through "nums" just once, stopping at each index to calculate the complement needed to make the sum = target, then searching the hash table for the complement's existence