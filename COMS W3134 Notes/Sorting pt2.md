[**Merge Sort**](https://www.w3schools.com/dsa/dsa_algo_mergesort.php) --> overall Theta(N lg N)
- Divide & conquer strategy through recursion --> split the array into two parts by the middle index repeatedly until you end up with arrays of length 2 or 1
- Each time you combine two arrays together, sort them in increasing order by iterating through both arrays, picking whichever value is largest --> array1[i] or array2[j] then do i++/j++
- When the pointer for array1 or array2 reaches the end (all elements of that array are exhausted), just fill in the rest of the spots with the leftover array
![[Pasted image 20241111164724.png]]
```java
MergeSort(array, low, high){
	if(low < high){
		mid = (low+high)/i
		MergeSort(array, low, mid)
		MergeSort(array, mid+1, high)
		L = low
		H = mid+1
		for(int i=low; i<=high; i++){
			if(L<=mid && (H>high || array[L] <= A[H])){
				scratch[i] = A[L]
				L = L+1
			}else{
				scratch[k] = A[H]
				H = H+1
			}
		}
		for(int i=low; i<=high; i++){
			A[i] = scratch[i]
		}
	}
	
}
```

**Quick Sort** --> best Theta(N lgN), worst Theta(N^2), average Theta(N log N), overall O(N^2)
- You take some number in the array, and you partition the array so that all elements to the left are less than the pivot, and all elements to the right are greater than the pivot
- The pivot is the element located at the index of the partition
- Treating "lomutoPartition" as a helper, you need to use it within another function that strategically picks the partition index --> pick three random elements from the array and select the median of those three to act as the partition for each helper call
```java
public static int lomutoPartition(int[] array, int left, int right){
	int x = array[left], s - left;
	for(int j=left+1; j<=right; j++){
		if(array[j] < x){
			s++;
			if(s != j){
				swap(array, s, j);
			}
		}
	}
	if(s!=left){
		swap(array, s, left);
	}
	return s;
}
public static void quickSortHelper(int[] array, int p, int r){
	if(p < r){
		int q = lomutoPartition(array, p, r);
		quickSortHelper(p, q-1);
		quickSortHelper(q+1, r);
	}
}
```

**Heap Sort** **-->** Theta(N lg N)
- Build a min/max binary heap with the given list of numbers, then pop them all out to get your sorted results
- It takes lg(N) to percolate during insertion & removal

**Counting Sort** --> Theta(largest value in the array)
1. find the max value of the array
2. make a second array of length "max value" to track how many of each integer you see
3. iterate through the array again, adding 1 to the index you're using to track each value
4. iterate through the second array, setting values of the original array to match info recorded
- Best used when range of values is small OR the same 


**Radix Sort** --> Theta(d * n),  d=length of longest number & n=# of numbers, Theta(n)
1. start by sorting each number only with respect to their units digit
2. make a second pass, sorting each number by their tens digit, if nothing is there call it 0
	- If two numbers have the same second digit, maintain their original order from the previous pass through
3. keep making passes for each set of digits until you run out of digits
	- remember to keep the order of numbers of the same digit from the previous pass

What should the sorting function be for the digits during each pass through?
- a parallelized bubble sort --> alternating pairwise swaps with consecutive numbers
- 012345 --> check 12&34, then check 01&23&45, then check 12&34 until two consecutive passes results in no swaps being made