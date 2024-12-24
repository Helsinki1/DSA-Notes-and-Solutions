This problem taught you a valuable lesson
- Even though the problem solving component was easy, you failed to think of your own test cases to spot careless errors in your code
- ALWAYS think of all the possible ways that your input could be formatted
- NEVER leave any possibility for indexing out of an array
	- Check if your index is in bounds always
	- Check if your array is not null
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        ArrayList<Integer> gaps = new ArrayList<>();
        ArrayList<Integer> full = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            if(nums[i]==val) gaps.add(i);
            else full.add(nums[i]);
        }
        int index = full.size()-1;
        if(index >= 0){
            for(int i=0; i<gaps.size(); i++){
                nums[gaps.get(i)] = full.get(index);
                if(--index < 0) break;
            }
        }
        return full.size();
    }
}
```