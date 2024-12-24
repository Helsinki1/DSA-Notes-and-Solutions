An strategy of solving a problem where the solution is found by repeatedly calling a function inside of itself until a base case is reached.
	**Base Case**: when the solution of the function can be immediately known
	**Recursive Calls**: when the function repeatedly calls itself with arguments closer and closer to the input that will lead to the base case
	**Pending Operations**: remaining instructions that must be computed after a recursive call is made but before its output is returned

Every time a function call is made, we **push** to a **stack frame**, a list of functions whose outputs are yet to be computed

After reaching the base case, we can **pop** each of the stack frames off from the bottom as the output of each function call leads to the output of the next sequential function call (each set of pending operations is resolved)

Factorial Example
	`public static int factorial(int n) {`
		`if (n == 0) {`
			`return 1;`
		`}`
		`return n * factorial (n-1);`
	`}`

Sometimes, when the output of the last function call leads to the answer, it is called a tail-end recursion.
Any recursion implementation where there exists pending operations between the first function call and the last function call is considered a linear recursion.

For Fibonacci problems, remember fib(0) = 0, fib(1) = 1, fib(2) = 1, these are your base cases  -->  runtime is O($n^2$) because each increase in $n$ will double the number of function calls needed

`str.substring(index)` has a runtime of O(`str.length - index`)
Essentially, the `.substring` function has a runtime equal to the length of the string outputted (it runs every time it collects another char)


Instead of using regular string concatenation with the "+" symbol (which creates a new string every time you run that operation), use the StringBuilder class like so:
	`StringBuilder stringBuilder = new StringBuilder();`
	`for(int i=0; i<100; i++){`
		`stringBuilder.append("some text");`
	`}`
	`String output = stringBuilder.toString();`