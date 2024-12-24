```java
class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] out = new int[n];
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(s.charAt(i)==c) arr.add(i);
        }

        int ind = arr.get(0);
        for(int i=ind; i>=0; i--){
            out[i] = ind-i;
        }
        ind = arr.get(arr.size()-1);
        for(int i=ind; i<n; i++){
            out[i] = i-ind;
        }

        if(arr.size()>1){
            for(int a=0; a<arr.size()-1; a++){
                int start = arr.get(a);
                int end = arr.get(a+1);
                int dist = 0;
                while(start<=end){
                    out[start] = dist;
                    out[end] = dist;
                    start++;
                    end--;
                    dist++;
                }
            }
        }

        return out;
    }
}
```
