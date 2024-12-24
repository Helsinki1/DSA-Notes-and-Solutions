**Bubble Sort** --> O(n^2) if array isn't sorted already, best case Theta(n)
```java
public static void bubbleSort(int[] array) {
	int len = array.length;
	while(len > 0){
		int n = 0;
		for(int i=0; i<len; ++i){
			if(array[i-1] > array[i]){
				swap(array, i-1, i);
				n = i;
			}
		}
		len = n;
	}
} 
```
- Keep on scanning through the array from index 0 to index "len"
- "n" tracks the last index where a swap was made in the previous scan, elements after this index are all in increasing order & don't need to be scanned again
- Keep doing this until the entire array is non-decreasing --> n=0 & len=0
- CORE CONCEPT: Continuously swapping

**Selection Sort** --> Theta(n^2) always
```java
public static void selectionSort(int[] array){
	for(int i=0, i_bound=array.length-1; i<i_bound; ++i){
		int min_index = i, min = array[i];
		for(int j=i+1; j<array.length; ++j){
			if(array[j] < min){
				min_index = j;
				min = array[j];
			}
		}
		if(min_index != i){
			swap(array, i, min_index);
		}
	}
}
```
- Even though it complexity seems comparable between Selection Sort & Bubble Sort, selection sort is significantly faster because there are less calls to "swap." In reality, indexing an array repeatedly takes up a lot of time
- Make passes through the array, keeping track of the minimum value each time and moving that value to the front of the array. Each consecutive pass starts at an index one spot further along the array.
- CORE CONCEPT: Finding Extrema & Moving It Up

**Insertion Sort** --> Theta(n), worst case O(n^2)
```java
public static void insertionSort(int[] array){
	for(int i=1; i<array.length; ++i){
		int k, current = array[i];
		for(k=i-1; k>=0 && array[k] > current; --k){
			array[k+1] = array[k];
		}
		array[k+1] = current;
	}
}
```
- Start your array of values and an empty array of the same length
- With each insertion of a new element into the empty array, find where it's supposed to go then put it there
- Performs better than the other two sorting algorithms above
- CORE CONCEPT: Strategically Inserting Each Value