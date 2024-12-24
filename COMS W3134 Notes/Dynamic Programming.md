**Coin Row Problem**
* Given an array of coin values, choose the coins that maximize the sum, given that NO two adjacent coins can be chosen
	If we have coins [0,2,10,6,2,1,5], we can either take "5" and then consider extracting coins from [0,2,10,6,2] OR we consider extracting coins from [0,2,10,6,2,1]

Recursive Implementation
```java
public int coinRow(int[] coins, int index){
	if(index <= 0) return 0; // base case
	else{
		// either take curr index and consider everything after index-2
		// OR ignore curr index and consider everything after index-1
		return Math.max(coins[index] + coinRow(coins, index-2),
							coinRow(coins, index-1)); 
	}
}
```
coinRow([0,2,10,6,2,1,5]) --> max(5 + coinRow[0,2,10,6,2],  coinRow[0,2,10,6,2,1])
coinRow([0,2,10,6,2]) --> max(2 + coinRow[0,2,10],  coinRow[0,2,10,6])
coinRow([0,2,10]) --> max(10 + coinRow[0],  coinRow[0,2])
coinRow([0]) = 0
coinRow([0,2,10,6,2,1]) --> max(1 + coinRow[0,2,10,6],  coinRow[0,2,10,6,2])
coinRow([0,2,10,6]) --> max(6 + coinRow[0,2],  coinRow[0,2,10])
etc...  as you can see, multiple identical function calls are made, simplify them using:

**Memoization** --> storing the results of unique recursive calls to decrease repetitiveness
0. Interpret the problem and design a simple recursive solution
1. Create a map with function inputs as keys and function outputs as values
2. If the current function call can be found in the map, directly fetch the value
3. If it cannot be found in the map, compute the function call and add it to the map
![[Pasted image 20241208013543.png]]
Doing Coin Row Problem by Hand  (L to R)
- C --> the coins at their respective index positions
- F --> the maximum amt of coins possible to be collected up to this index
- S --> the last index position in which you called the maxCoins() function
As you fill rows F and S from left to right, you consider two options at each index: to take or abstain. If you choose to take at index N, you think maxCoins(N-2) + (coins at N) is better than maxCoins(N-1). In other words, if F[N-2] + C[N] > F[N-1], then you take. Else, abstain.
- For row S, if you took a coin at index N, then record N-2 (you'd call maxCoins(N-2) prior). if you abstained at index N, then record N-1 (you'd call maxCoins(N-1))
	- If N-2 or N-1 is negative, just put 0
- To find which indices' coins were taken using row S, you back track.
	1. Start at the very last index position, if F[last] > F[last-1], then C[last] was taken
	*WHILE current index is greater than 0, LOOP:*
	2. Travel to index position i = S[current index]
	3. If F[i] > F[i-1] then the coins were taken at that index and we record the coin value
- Time Complexity of filling row F & S, Theta(n)
- Time Complexity of back-tracking, Theta(n)



**Robot Coin Collection Algorithm**
- Given a 2D array of positions and coin values, the goal is to pick up the maximum number coins with a moving robot. The robot starts at top left cell. Robot can move right or down only.

![[Pasted image 20241208123955.png]]
Solving by Hand
- Start at the top left, fill in each cell with the greater of these two: the cell to its left or the cell above it.
- If the current cell has a coin, add 1 to the cell value calculated above.
Back-Tracking
- Start at the bottom right, look at the left and upward neighbors, and travel to the cell that is the largest of the two. If there is a tie, note if there's a up/left preference.

- Time Complexity of Creating the 2D Matrix --> Theta(r * c)
- Time Complexity of Traversing it w/ DP --> Theta(r + c)
- Time Complexity of Back-Tracking --> Theta(r + c)


