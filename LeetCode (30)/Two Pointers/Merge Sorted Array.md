* You CANNOT declare one array equal to another. Although it is syntactically correct, all it does is let one array point to the other array in your computer memory. If you want to actually change the contents of array1 into the contents of array2, you must use 
		`System.arraycopy(from, 0, to, 0, from.length);`
		
* To solve this problem, go through both arrays using one pointer for each, iterate through and add the lowest value of the two pointers each time until you reach the end of both arrays. Once you reach the end of one array, just keep adding the leftover contents from the other array
```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(m<1){
            System.arraycopy(nums2, 0, nums1, 0, nums2.length);
            return;
        }
        if(n<1){
            return;
        }
        int[] out = new int[m+n];
        int index1 = 0;
        int index2 = 0;
        int indOut = 0;
        while((index1<m || index2<n) && indOut < n+m){
            if(index1 >= m){
                out[indOut] = nums2[index2];
                index2++;
            }else if(index2 >= n){
                out[indOut] = nums1[index1];
                index1++;
            }
            else if(nums1[index1]<=nums2[index2]){
                out[indOut] = nums1[index1];
                index1++;
            }
            else{ 
                out[indOut] = nums2[index2];
                index2++;
            }
            indOut++;
        }
        System.arraycopy(out, 0, nums1, 0, out.length);

    }
}
```