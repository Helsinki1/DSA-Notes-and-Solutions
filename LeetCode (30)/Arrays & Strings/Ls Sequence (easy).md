* oftentimes, it is advantageous to use a while-loop in place of a for-loop in a traditional for-loop question to avoid indexing out of bounds

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        
        if(s.equals("")) return true;

        int keyChar = 0;
        for(int i=0; i<t.length(); i++){
            if(t.charAt(i) == s.charAt(keyChar)) keyChar++;
            if(keyChar == s.length()) i=t.length();
        }
        return keyChar == s.length();

    }
}
```