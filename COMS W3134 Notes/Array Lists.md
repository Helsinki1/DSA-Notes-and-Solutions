The List interface is found in the java.util package. It provides a way to store data in an ordered collection --> insertion order is preserved. It implements the ArrayList and LinkedList classes.

Primary Methods:
* `boolean add(E e)` --> appends the element to the end of the list, returns true always
* `void add(int index, E e)` --> inserts the element into the list at specified index
* `void clear()` --> removes all elements in the list
* `E get(int index)` --> returns the element at the index of the list
* `int indexOf(E e)` --> returns the index of the 1st occurrence of the element
* `boolean isEmpty()` --> returns true if list is empty
* `E remove(int index)` --> removes the element at specified index
* `E set(index, E e)` --> sets element at position to e, returns the old element
* `int size()` --> returns the number of elements in the list
* `Iterator<E> iterator` --> returns an iterator over the elements in a list

ArrayList is a class that supports **dynamic arrays**, arrays that increase in size as you add more elements. They continuously resize (usually 2x+1 the capacity of the original) when space is all used up. ArrayLists can grow as they gain elements, but they never shrink.
	Time Complexities of ArrayList
		- `add(element)` --> Theta(1), when array doesn't need to be resized
		- `add(element)` --> Theta(n), when array needs to be resized
		- `add(element)` --> Theta(1), amortized time    overall --> O(n)
		- `add(index, element)` --> O(n), you need to slide back all elements after index
		- `get(index)` --> Theta(1), fetching element at index is one step
		- `remove(index)` --> O(n), you need to slide forward all elements after index
		- `indexOf(element)` --> O(n), sequential search
	Disadvantages
		- Memory inefficient compared to array
		- Can't handle primitives without a wrapper class
		- Resizing & insertion & removal are linear operations

To copy an array's elements into another array, use System.arraycopy()

**Using the Iterator**
`public class iterTest {`
	`public static void main(String[] args) {`
		`MyList<String> list = new MyArrayList<>();`
		`list.add("Dr. B");`
		`list.add("Harry);`
		`list.add("Rich");`
		`// ALTERNATIVE EXAMPLE TO ITERATOR`
		`for(int i=0; i < list.size(); i++){`
			`System.out.println(list.get(i));`
		`}`
		
		// ALLOWS ITERATION OVER ANY DATA STRUCTURE
		Iterator<String> iter = list.Iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	 }
`}`