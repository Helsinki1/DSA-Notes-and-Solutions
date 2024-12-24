* Since all characters on the keyboard have an ASCII value from 0 to 127, you can create an array of 128 and input the char as an index to create your own map
* Since the window is fixed, you can just keep on adding new elements as "end" shifts and deleting trailing elements as "start" shifts
```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> output = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        int startInd = 0;
        int endInd = p.length()-1;
        boolean[] charsPresent = new boolean[p.length()];
        int[] map = new int[128];
        int[] reqs = new int[128];

        if(s.length() < p.length()) return output;

        temp.append(s.substring(0, endInd+1));  // initial window
        for(int i=0; i<p.length(); i++) {
            map[s.charAt(i)]++;
        }
        for(int i=0; i<p.length(); i++) {  // anagram requirements
            reqs[p.charAt(i)]++;
        }


        while(endInd < s.length()-1){
            if(Arrays.equals(reqs,map)) output.add(startInd);
            endInd++;
            map[s.charAt(endInd)]++;
            map[s.charAt(startInd)]--;
            startInd++;
        }
        if(Arrays.equals(reqs,map)) output.add(startInd);

        return output;
    }
}
```