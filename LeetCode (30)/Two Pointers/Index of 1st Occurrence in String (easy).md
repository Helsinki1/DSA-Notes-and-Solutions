- Basically, the first pointer is the only index that matters. It moves through the string looking for chars that are equal to the first char of the "needle" string. Every time it finds one, it invokes the second pointer which sweeps in front of it to check if it's a full match
- When the first pointer passes the end of the haystack string, it returns -1 as it didn't find anything
```java
class Solution {
    public int strStr(String haystack, String needle) {
        int start = 0;
        int index = 0;
        
        while(start < haystack.length()){
            while(haystack.charAt(start+index)==needle.charAt(index)){
                index++;
                if(index==needle.length()) return start;
                if(start+index > haystack.length()-1) return -1;
            }
            index = 0;
            start++;
        }

        return -1;
    }
}
```