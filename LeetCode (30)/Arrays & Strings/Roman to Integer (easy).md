```java
class Solution {
    public int romanToInt(String s) {
        int accum = 0;
        for(int i=0; i<s.length(); i++){
            if(i == s.length()-1){
                if(s.charAt(i) == 'I') {
                    accum += 1;
                } else if(s.charAt(i) == 'V') {
                    accum += 5;
                } else if(s.charAt(i) == 'X') {
                    accum += 10;
                } else if(s.charAt(i) == 'L') {
                    accum += 50;
                } else if(s.charAt(i) == 'C') {
                    accum += 100;
                } else if(s.charAt(i) == 'D') {
                    accum += 500;
                } else if(s.charAt(i) == 'M') {
                    accum += 1000;
                }
                return accum;
            }
            if(s.charAt(i+1)=='V' && s.charAt(i)=='I'){
                accum += 4;
                i++;
            }
            else if(s.charAt(i+1)=='X' && s.charAt(i)=='I'){
                accum += 9;
                i++;
            }
            else if(s.charAt(i+1)=='L' && s.charAt(i)=='X'){
                accum += 40;
                i++;
            }
            else if(s.charAt(i+1)=='C' && s.charAt(i)=='X'){
                accum += 90;
                i++;
            }
            else if(s.charAt(i+1)=='D' && s.charAt(i)=='C'){
                accum += 400;
                i++;
            }
            else if(s.charAt(i+1)=='M' && s.charAt(i)=='C'){
                accum += 900;
                i++;
            }
            else {
                if(s.charAt(i) == 'I') {
                    accum += 1;
                } else if(s.charAt(i) == 'V') {
                    accum += 5;
                } else if(s.charAt(i) == 'X') {
                    accum += 10;
                } else if(s.charAt(i) == 'L') {
                    accum += 50;
                } else if(s.charAt(i) == 'C') {
                    accum += 100;
                } else if(s.charAt(i) == 'D') {
                    accum += 500;
                } else if(s.charAt(i) == 'M') {
                    accum += 1000;
                }
            }
        }
        return accum;
    }
}
```
