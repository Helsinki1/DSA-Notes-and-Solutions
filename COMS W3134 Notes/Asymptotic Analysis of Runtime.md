An algorithm is logarithmic if it takes constant time to cut the problem size by a fraction (usually by 1/2), it rushes to "n" at an exponential pace with a constant base (2^x)
	ex:  `for(int i=1; i<n; i=i*2) {}`,  binary search

An algorithm has a square root runtime if the increment rushes to "n" at a quadratic pace 
	ex:   `for(int i=1;  i * i <= n; i++) {}`
		`for(int i=1;  i<=n; i = (i+1)*(i+1)) {}`
		`for(int i=1;  i*i*i <= n; i++) {}`  -->  runtime of cube root of "n"
		As long as "i" reaches sqrt(n), the loop terminates

* Be careful, just because you see a break in an algorithm doesn't mean that Big O Notation is guaranteed to be the appropriate runtime expression

* If there is an oscillating condition in which the algorithm has a constant runtime rather than an N runtime (for ex with if n%2 == 0), then you must express your maximum runtime with Big O Notation --> O(N)
* However, if there is only a single or a couple conditions in which the algorithm has a constant runtime, your answer should still be expressed with Theta Notation
* When evaluating the runtime of while loops, it is very helpful to write out the values of each variable for each increment, then generalize the pattern

* CAREFUL: since we define $n_0$ to be a positive integer, if you compute it and it comes out to be negative, you must put down "1" as your answer instead
	* In the process of finding $n_0$ for an algorithm with a lower bound and upper bound, you must pick the larger $n_0$ that you calculate