for recursive functions like --> x(n) = x(n-1) + n runs in O(n) time
for recursive functions like --> x(n) = x(n/2) + 1 runs in O(lg n) time
for recursive functions like --> x(n) = x(n/2^k) + k runs in O(lg(n) / k) time


**Making Recursive Functions Finite**

example: x(n) = 4 * x(n-1), where n>1 and x(1) = 7
	x(n) = 4 * x(n-1)
	x(n-1) = 4 * x(n-2)
	x(n-2) = 4 * x(n-3)
	x(n-1) = 4(4 * x(n-3)) = 16 * x(n-3)
	x(n) = 64 * x(n-3)
	After k iterations --> x(n) = 4^k * x(n-k)
	Call n-k = 1 --> k=n-1 and x(n) = 4^n-1 * 7
	Final Answer --> x(n) = 7 * 4^n-1 

example: solve for n = 2^k in x(n) = x(n/2) + 1, where x(1) = 1
	Step 0: replace n with 2^k 
		x(2^k) = x(2^k / 2) + 1 = x(2^k-1) + 1
	Step 1: continue iterating recursively
		x(2^k-1) = x(2^k-2) + 1
		x(2^k) = x(2^k-2) + 2
	Step 2: continue iterating recursively, but leave the third function call in symbolic
		x(2^k-2) = x(2^k-3) + 1
		x(2^k) = x(2^k-1) + 1 = x(2^k-3) + 3
	Step 3: generalize assuming "i" iterations have been called
		x(2^k) = x(2^k-i) + i
	Step 4: since we know n = 2^k  --> k = lg(n)
			if we call 2^k-i = 1, then k-i=0 and k=i and x(2^k-i)=1
			x(2^k) = 1 + k = 1 + lg(n)

example: x(n) = 2x(n/2) + n, x(1) = 1
	step 0: x(2^k) = 2x(2^k-1) + 2^k 
	step 1: x(2^k-1) = 2x(2^k-2) + 2^k-1 
	step 2: x(2^k) = 2( 2x(2^k-2) + 2^k-1 ) + 2^k = 4x(2^k-2) + 2^k+1
		x(2^k-2) = 2x(2^k-3) + 2^k-2 
		x(2^k) = 4( 2x(2^k-3) + 2^k-2 ) + 2^k+1 = 8x(2^k-3) + 3 * 2^k 
	step 3: x(2^k) = 2^i x(2^k-i) + i 2^k 
	step 4: 2^k-i = 1
		k-i = 0
		k = i
	step 5: x(2^k) = 2^k x(2^k-k) + k 2^k 
		since n=2^k and k = lg(n)
		x(2^k) = 2^lg(n) + lg(n) (2^lg(n)) = n + n lg(n)