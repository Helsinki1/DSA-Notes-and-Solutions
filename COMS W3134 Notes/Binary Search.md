To find the target number within a certain range, it's most efficient to repeatedly guess the middle of each (gradually more restrictive) range the number occupies.

After we know the target is larger/smaller than each guess, we can adopt a new, more restrictive range that is exactly half the size of the previous range.

The stop condition for a binary search algorithm is when the lower bound is >= to the upper bound. If the target is not found, it is best practice for the algorithm to return (lower - upper) so we know the index of where the target number should have been.

`public static <E extends Comparable<E>> int binarySearch(E key, E[] array) {`
	`System.out.println("Iterative binary search [generics]");`
	`int low = 0, high = array.length - 1;`
	
	while (low <= high) {
		int mid = low + (high - low) / 2;      // this helps avoid overflow
		result = key.compareTo(array[mid]);
		if (result == 0) {
			return mid;
		}
		if (result < 0) {
			high = mid - 1;
		} else {
			low = mid + 1;
		}
	}
	// Returns a negative value, indicating the element is not present.
	// Assume the return value is stored in a variable called index. If you take
	// -index - 1, that value becomes the index in the array the value should be inserted, if you were to do so.

	return -low - 1;
`}`

Sequential Search --> search through every element in an array, worst possible method, the only benefit is that the data does not need to be sorted beforehand
	The expected number of attempts needed to for a successful match to be found is:
			 $\frac{p(n+1)}{2}+n(1-p)$
		p = probability that searching the given range will be successful
		n = number of elements needed to be searched in the range

Encapsulation: bundling data and functionality into separate objects
Polymorphism: 
Inheritance: 

Built-in compareTo method
	if *this* object is greater than *other*, 1 is returned
	if *this* object is smaller than *other*, -1 is returned
	if *this* object is equal to *other*, 0 is returned
* for strings/chars, the ASCII values of the initial chars are compared
		A = 65 in ASCII
		a = 97 in ASCII
		0 = 48 in ASCII


Generics in Java
	In java, you can enable a function to accept multiple data types if you define the function multiple times; for example
			`public int Add(int a, int b)`
			`public double Add(int a, double b)
			`public double Add(double a, int b)`
			`public double Add(double a, double b)`
			
Instead of declaring all those functions, you can just use a generic like so
			`public <T> T Add(T a, T b)`
			`public <T extends <Comparable T>> Add(T a, T b)`