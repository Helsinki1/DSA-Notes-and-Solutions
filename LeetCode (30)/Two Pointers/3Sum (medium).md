I found this question to be incredibly difficult. I just couldn't think of a O(n) solution so I first implemented a O(n^2) solution first:
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();  

        int length = nums.length;
        int[] sorted = nums;
        Arrays.sort(sorted);

        int index = 0;
        while(sorted[index] < 0){
            index++;
            if(index>=length) return out;
        }

        for(int i=0; i<=index; i++){
            int pointer2 = (i==0)? 1:0;
            int pointer3 = (i==length-1)? length-2:length-1;
            int target = -1 * sorted[i];
            int complement = Integer.MIN_VALUE;
            while(pointer2 < pointer3){
                complement = sorted[pointer2] + sorted[pointer3];
                if(complement > target){
                    if(--pointer3 == i) pointer3--;
                }else if(complement < target){
                    if(++pointer2 == i) pointer2++;
                }else{
                    int ans1 = sorted[i];
                    int ans2 = sorted[pointer2];
                    int ans3 = sorted[pointer3];
                    int[] entry = {ans1,ans2,ans3};
                    Arrays.sort(entry);
                    set.add(Arrays.asList(entry[0], entry[1], entry[2]));
                    if(--pointer3 == i) pointer3--;
                }
            }
        }
        for(List<Integer> x:set){
            out.add(x);
        }
        return out;        
    }
}
```
(269ms 22.61%, 52MB 24.22%)
- Basically pointer1 iterates through each index of the sorted version of "nums" incrementally, while pointer2 & pointer3 perform the classic 2-pointer scan for two elements that sum to the complement value needed to sum to 0 with pointer1's element.
- Each time a valid triplet is found, it's organized and added to a set, where duplicates are automatically disregarded
- Finally, transfer the arrays of the set into nested Lists of Integers.

Finding an O(n) solution...
- You had the right idea: you want to sort "nums," then iterate through with pointer1 then conduct the classic two-pointer search for the complement
- To further shrink the search space, you can stop after pointer1 passes the first zero because all checked triplets will be repeats from then on

Concepts you must remember
- You can directly use `Arrays.sort(nums)` and the changes to "nums" will be permanent
- `for(int i=0; i<0 && nums[i]<=0; i++){}` --> for loops can have multiple conditions
- Helper methods are a staple of insanely efficient code

Now... how do you avoid repeats? --> include a condition where if the current element of pointer1 is equal to the one just before, skip the scan
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            if (i != 0 && nums[i] == nums[i - 1])
                continue;
            twoSum(-nums[i], nums, i + 1, result);
        }
        return result;
    }

    private void twoSum(int target, int[] nums, int startIndex, List<List<Integer>> result) {
        int i = startIndex;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] < target) {
                i++;
                continue;
            }
            if (nums[i] + nums[j] > target) {
                j--;
                continue;
            }
            result.add(Arrays.asList(-target, nums[i], nums[j]));
            i++;
            j--;
            while (j > i && nums[j] == nums[j + 1])
                j--;
        }
    }
```