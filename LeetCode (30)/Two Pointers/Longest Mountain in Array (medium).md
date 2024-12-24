- Scan the array from left to right, finding sections of consecutively increasing values
- Scan the array from right to left, finding sections of consecutively decreasing values
	- Put data into hashmap --> key = index of section end, value = length of section
	- If the ending of a consecutively increasing section matches with the ending of a consecutively decreasing section, check their combined length and set it to "max" if it's greater than previous maximum
```java
class Solution {
    public int longestMountain(int[] arr) {
        if(arr.length < 3) return 0;
        HashMap<Integer,Integer> indexToLength = new HashMap<>();

        int count = 1;
        boolean valid = false;
        for(int i=1; i<arr.length; i++){
            if(arr[i]>arr[i-1]){
                valid = true;
                count++;
            }if((i==arr.length-1 || arr[i]<=arr[i-1]) && valid){
                indexToLength.put(i-1,count);
                count = 1;
                valid = false;
            }
        }

        int max = 0;
        count = 1;
        valid = false;
        for(int i=arr.length-2; i>=0; i--){
            if(arr[i]>arr[i+1]){
                valid = true;
                count++;
            }else if(arr[i]<=arr[i+1] && valid){
                if(indexToLength.containsKey(i+1)){
                    if(indexToLength.get(i+1)+count-1 > max){
                        max = indexToLength.get(i+1)+count-1;
                    }
                }
                count = 1;
                valid = false;
            }
        }
        return max;
    }
}
```