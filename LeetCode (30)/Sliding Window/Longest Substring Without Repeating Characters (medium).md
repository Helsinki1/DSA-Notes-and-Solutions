* use an ASCII char map to record which characters are present in the sliding window
* if the next character is present in the window, pause the "end" pointer from moving and keep on sliding "start" forward until the repeat no longer exists
* account for edge cases when s.length() == 0
* account for the fact that max does not update when "end" reaches the last index, so update it again right after the while loop

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0) return 0;
        if(s.length()==1) return 1;

        int start = 0;
        int end = 0;
        int max = 0;
        boolean[] map = new boolean[128];
        map[s.charAt(0)] = true;

        while(end < s.length()-1) {
            end++;
            if(map[s.charAt(end)] == true){
                end--;
                if(end-start+1 > max) max = end-start+1;
                map[s.charAt(start)] = false;
                start++;
            } else {
                map[s.charAt(end)] = true;
            }
        }
        if(end-start+1 > max) max = end-start+1;

        return max;
    }
}
```