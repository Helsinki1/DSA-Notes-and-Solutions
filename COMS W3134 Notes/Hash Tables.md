A **set** is a collection of data that does not allow for duplicates
Supported Operations:
1. insert(x), remove(x), contains(x), isEmpty(), size() ...

**Ordered sets** are sets with a condition defined on the items (ex: all consecutive elements are > or < each other)

Implementing Sets
- We CANNOT use LinkedList or ArrayList --> operations will be O(n) time
- We CAN use a **map**, a dictionary/collection of (key, value) pairs
	- Keys are unique, but values don't need to be
	- Time for "get" and "put" is O(1)

A **hash table** is an array of some length *TableSize*
- "chaining hash table" --> hash table with LinkedList buckets
A **hash function** maps key objects to an integer index in the range
- if we want to store (key, value), then we turn the key into an index in our array to store it
- It is possible for two different keys to result in the same index from the hash function, in this case, we want to resolve these collisions by making an effective hash function

How do we make a good hash function?
- It all depends on the type of keys we expect (Strings, integers...)
- You can also make a hash function that is the combination of other hash functions to create better ones, this is particularly useful for objects whose constructors take multiple primitives --> combine the preferred hash functions for each primitive to hash your object
- Good practices: 
	- keep TableSize a prime number 
	- when combining hash functions, make the factors prime numbers
	- random number generators are NOT good hash functions because you can't consistently compute the same hash index for the same key

What can be a key?
- Anything can be a key as long as there exists a good hash function
- Keys have to be immutable objects 
- If `Key1.equals(Key2)` is true, both Key1 & Key2 must produce the same hash value

Dealing with Collisions
- You can keep all pairs whose key hashes to the same hash index in a list
- You can make each hash value a "bucket" where all these collisions are stored, after hashing to this "bucket," we search the list sequentially
Load Factor = (number of keys to store or stored) / (table size)
	the average length of a "bucket" is the Load Factor, used as a metric to know when to resize the array
- We can also resolve collisions through probing --> when a collision happens, just probe around for an open spot nearby
	- hash value = (hash(x) + f(i)) % *TableSize* for the i-th collision, but it's not constant time lookup
	- f(i) = i^2 is much better than f(i) = i as it ensures no large section of consecutive indexes are taken by numerous collisions of the same hash value
	- NOTE: each consecutive shift calculated by f(i) **does not** build on each other

Quadratic Probing Theorem (when f(i)%*TableSize* becomes cyclic): If *TableSize* is prime,  the first *TableSize*/2 indices visited by quadratic probing will always result in distinct indices, after that, no guarantee
![[Pasted image 20241028165859.png]]
Double Hashing
- Use another dedicated hash function to calculate the offset amount to place the collision
- hash_1(key) = where the data is supposed to go, hash_2(key) = the amount to offset from where the data is supposed to go in the case of a collision

Rehashing
- Since all the types of probing and setting up "buckets" for each index becomes really inefficient when we fill in the data beyond *TableSize*/2
- To make all our processes more efficient, we make another array with double the length of the original
- We CANNOT simply copy the data from the original to the new array (since modulo hashing operations will lead to different indices), we have to take all the data of the original and re-insert it using the new hash functions into the new array
	- This rehashing process is O(n) time complexity

Deletion
- Lazy deletion: simply mark a cell as deleted, the ignore it on subsequent look-ups and allow it to be written over during insertions