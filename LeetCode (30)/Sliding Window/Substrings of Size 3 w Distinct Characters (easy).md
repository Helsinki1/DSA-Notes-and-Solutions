
```java
class Solution {
    public int countGoodSubstrings(String s) {
        if(s.length() < 3) return 0;

        char one = s.charAt(0);
        char two = s.charAt(1);
        char three = s.charAt(2);
        int start = 0;
        int end = 2;
        int count = 0;

        while(end < s.length()-1){
            if((one!=two) && (two!=three) && (one!=three)) count++;
            end++;
            start++;
            two = three;
            three = s.charAt(end);
            one = s.charAt(start);
        }
        if((one!=two) && (two!=three) && (one!=three)) count++;
        return count;
    }
}
```