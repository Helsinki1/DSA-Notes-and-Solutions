- Given a list of weights of individual people in "people" and the maximum amt of weight one boat can carry in "limit," what is the least number of boats to carry all the people if one boat can at most carry two people?
- Sort the array "people" to make it increasing order
- Implement the classic two-pointer scan to find optimal pairings of lightest + heaviest people together
```java
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0;
        int end = people.length-1;

        int count = 0;
        while(start <= end){
            if(start == end){
                count++;
                start++;
                end--;
            }else if(people[start]+people[end] <= limit){
                count++;
                start++;
                end--;
            }else{
                count++;
                end--;
            }
        }
        return count;
    }
}
```