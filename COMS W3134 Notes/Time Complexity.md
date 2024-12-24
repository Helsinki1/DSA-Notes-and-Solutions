Algorithm: a step-by-step set of operations that produces an output for a valid set of inputs
* Will the algorithm always terminate?
* How much time / resources does the algorithm require as input size increases?
We always consider the "worst case" runtime when analyzing T(n)


Defining Big O Notation:
	O(g(n)) = a generalized function such that we disregard scalar shifts in terms of constant multiplication and addition
	O(g(n)) = a set of functions such that 0 <= f(n) <= c * g(n) for all n >= $n_0$
		g(n) = the exact algorithm that is being implemented
		ex: g(n) = 5n --> O(g(n)) = n
		Big O gives the upper bound for evaluating run time

Defining Big Omega Notation:
	Omega(g(n)) = the set of all functions f(n) such that  0 <= $c_1$ * g(n) <= f(n) for all n >= $n_0$
		$c_1$, $n_0$  are positive integer constants
		Big Omega gives the lowest bound for evaluating run time

Defining Big Theta Notation:
	Theta(g(n)) = the set of all functions f(n) so that 0 <= $c_1$ * g(n) <= f(n) <= $c_2$ * g(n) for all n >= $n_0$
		$c_1$, $c_2$, $n_0$  are positive integer constants and $c_1 <= c_2$
		Theta gives both the lower and upper bounds, it's the most restrictive and the most informational way to evaluate run time

With an algorithm, if the best case runtime is equal to the worst case runtime, you must express your runtime in terms of Theta notation, since Big O = Big Omega


A great way to quickly analyze time complexity is --> **if I double "n," what happens to O(n)?**


Comparing Time Complexities
	(Best)   O(1), O(log n), O($\sqrt{n}$), O(n), O($n^2$), O($n^3$), O($n^n$)    (Worst)

1. If T(N) is a polynomial with degree k, then it must equal Theta($N^k$)
2. $\log{(N)}^k$ = O(N) for any "k"
3. $\log_{a}{(N)}$ = Theta(lg(N)) for any "a"

Since $\ln(n)$, $\log(n)$, $\log_{2}{(n)}$ are all constant factors apart, they are all considered identically, along with logarithms of any base
	also: $lg(n)$ = $\log_{2}{(n)}$

When two algorithms are summed, their time complexity equals the time complexity of the more intensive algorithm:  $O(n^2 + log(n))$ = $O(n^2)$
When two algorithms are nested together, their time complexity equals the product of the time complexities of each algorithm:  $T_1(n)$ * $T_2(n)$ = $O(f(n) * g(n))$

When calculating the upper bound for O(n) notation, you can find "n naught" by simply solving the inequality:   given function <= (minimized c) * g(n)   and minimizing "n"
	ex: $2n^2 + 5n$ --> $n^2$,  so   $2n^2 + 5n <= 3n^2$   -->  $5<=n$   -->  $c = 3$,  $n_0 = 5$

When calculating the lower bound for Omega(n) notation, we maximize c:  c * g(n) <= f(n)
	ex: $2n^2 - 5n$ --> $n^2$,  so   $n^2 <= 2n^2 - 5n$   -->   $c = 1$,  $n_0 = 5$


For all problems for evaluating time complexity, always give the tightest bound possible
For many problems, they will give you a length of time, and you can find the maximum possible input size that can be computed in that time frame (using the time needed for the principal operation)


To calculate the largest input size "n" that can be ran in a given amount of time:
- Assume an algorithm of O(n) takes "n" milliseconds to run
1. Convert the given amount of time into milliseconds
2. Set the number of milliseconds equal to the time complexity expression in terms of "n"
3. Solve for "n" --> take the floor (round down) if you get a decimal