#course 
[[Computer Science]]

# Core Data Structures

## Limitations of arrays:
- Fixed number of elements
- Cannot easily insert an element in the middle
- Some operations are very slow (have to loop through all of them)

## Linked Lists
- Array stored as one element --> [28,14,17,30]
- In linked list each element stored in memory separately and points to the others --> [28] -> [14] -> [17] -> [30]
- `Node(value, next)`
- `LinkedList(head, tail)`

### Adding nodes
- At front
		- Create new node
		- Point to head
		- Update head to new node
- At end
		- Create new node
		- Point tail to new node
		- Point new node to null
		- Update tail
- Before node n
		- Create new node
		- Point new node to node n
		- Point node n-1 to new node

### Get node at index n
- Start at head
- Move forward n times
- Return that value
- This is actually worse than an array, where you can just get the element

### Removing nodes
- At head
		- Set `head = head.next`
- At tail
		- No way to check for second to last, so need to loop forward
		- Loop through until `node.next = tail` 
		- Set that node as the tail and point to null
- At index n
		- Go to node n-1
		- Point it to n+1 --> `current.next = current.next.next`

### Linked Lists java API
- `java.util.LinkedList<E>`
		- `add(index, Element)` / `add(Element)` / `addFirst`
		- `set(index, Element)`
		- `.contains`
		- `get` / `getFirst` / `getLast`
		- `remove` / `remove(index)` / `removeLast`
- ArrayLists
		- Adjustable size array
		- Wrapper around a normal array
- Both ArrayList and LinkedList implement `List<E>`, so you can use this as a common factor (eg. as a return type)
- LinkedList <-- List interface <-- Collection interface

## Abstract Data Types

- Data types that are based on linked lists

### Queue (FIFO)
- Linked list with front and rear
- Can only
		- `add` to the front
		- `remove` from the rear
- `java.util.Queue<E>`


### Stack (LIFO)
- Linked list with a "top"
- Can only
		- `push` to the top
		- `pop` (remove) from the top
- Eg. Undo function of computer
- `java.util.Stack<E>`

## Hash Sets

> Hash sets are just a way of organising your data so its easier (faster) to find!

### Big-O Notation
- Worst case scenario for doing some operation
- Accessing elements
		- Linked list = O(n)
		- Array List = O(1)
- Checking if list contains an element
		- Linked list = O(n)
		- Array List = O(n)

### Hash Sets Intro
- Sets are an unordered, non-repeating array of values
- A way of making the `contains` functionality O(1)
- For integers, you have an array of booleans, and the value says if you have seen it before
- Eg. If you want to check `contains(3)` you just check array[3] and see if it is true or false
- This only works with ints, to use it on other data types you need to transform the data into an int
- You transform the data via a **hashing function**
		- data in --> hashfunction --> hashcode out --> `array[hashcode] = data`
		- Hash function tells you where the data would be if it existed
		- The array doesnt necessarily contain true/false
		- Eg. Put "dog" in array[3] because `len(dog)=3`
		- Common hashing function: `hashcode = len(myString) % len(myArray)`

### Collision Handling
- Each entry in the array is a linked list
- Each spot in the array is a "bucket"
- If two items have the same hashcode they go in the same bucket

### Self Resizing Hash Sets
- Add more buckets when the current buckets become too full
- **Load Factor** = The average number of items in a bucket in the array
- When load factor goes over a certain threshold --> Automatically resize
- **Resiziing**
		- Double the number of buckets
		- Rerun hash function to put items in correct buckets
		- eg. `string % 6 = 2` might now be in bucket 8 not bucket 2 (double buckets from 6->12)

### HashSets & HashMaps Java API
	- `java.util.HashSet<E>`
- Self resizing
- Has standard functions: add/contains/remove/size
- **HashMaps**
		- A mapping of a set of keys to values
		- A hashmap is a map where the keys are stored in a hashset
		- Eg. Count how many times each word has occurred
		- `wordCount.put(word, count + 1` = I have seen word count+1 times
		- `entrySet` simplifies iterating over all the key-value pairs in the map


# Advanced Data Structures

## Binary Search Trees
- Type of tree
	- Root node
	- Links to other nodes
- Binary tree
	- Nodes have 2 child nodes
	- Sorted, so traversing the tree gives the elements in order
	- Smaller values on left, larger on right
	- A set (no duplicates)
- Traversal
	1. Traverse left subtree
	2. Visit node
	3. Traverse right subtree

### Operations on BSTs
- Commonly use recursion
- `.contains()` - Compare to each node and follow the correct side
- `.remove()` - When you remove a node, you replace it with the **largest value in its left subtree** (rearrange the subtree)
- All O(log_2(n)) operations when the tree is balanced

### Self-balancing BST (red-black tree)
- Goal = Minimise height of tree
- Leaf nodes all black
- Other nodes red or black
- Red nodes must have two black child nodes
- Keep "black height" the same
- Rotate around a number

### Java Tree API
- Part of the Set<> interface
- `TreeSet<E>`
- Any java Set API has a iterator method: `Iterator<String> iter = myTree.iterator()`
- Also have TreeMap the same way there is HashSet and HashMap (key:value pairs)
- vs HashSet
	- In sorted order (better than hashset)
	- HashSet can do better at item retreival if the load factor is small enough

## Heaps
- Useful when you need to get the largest value really fast
- Binary tree where any node is larger than its two children ("heap property")
- So root of the tree is largest value (can get largest in O(1))
- MinHeap same where minimum at the top

### Operations
- `.add()` - put new number at next open location and keep swapping upward until heap property satisfied
- O(log(n)) adding
- `.remove()`
	- Remove largest
	- Put last value to root of heap
	- Swap root with largest child until its in place again
- MinHeap -> MaxHeap by changing compareTo function

### Implementation 
- `java.util.PriorityQueue<E>`
- Actually same as a minheap

## Graphs

### Characteristics
- Model relationships
- Nodes connnected by edges
- **Degree** = Number of edges
- Weighted graph = Edges have values
- Cycle = Path with first and last node the same (graphs with cycles are called cyclic)
- Directed graph 
	- = Edges have direction (connecting source and destination node)
	- Directed graphs have "In degree" and "out degree"

### Graphs in Java
1. Adjacency matrix
	- Matrix of values with nodes on x and y axis
	- Symmetric for undirected graphs
	- if a 1 at (1,3) then nodes 1 and 3 are connected
	- Can do the same with weighted graphs by changing 1 to another number
2. Edge Set
	- Set of edges that make up the graph, containing references to the two nodes it connects
	- Undirected: (A, B)
	- Weighted: (A, B, 10)
	- Directed: (A, B)
3. Adjacency set
	- Each node is mapped to its own set of outgoing edges (for directed graph)
		A: (A, B), (A, C)
		B: (B, C)
		C: (C, B)
	- Can add weights in too
	- Set contains mapping (A,B) and (B,A) if graphs is undirected

### Breadth First Search
- To find out if there is a path between two nodes
- A systematic way of traversing a graph
- `public boolean BreadthFirstSearch(String toFind, Node start)`
	0. Create "marked" set and "to check" que
	1. Take starting node
	2. Check its "children" (nodes it connects to)
		a. Mark as checked/visited
		b. Add node to list of nodes to explore (to look at its children)
		c. Remove the node you have been checking from que once fully checked
	3. Check children's children (2nd level out)
		a. Mark as checked/visited
		b. Add node to list of nodes to explore (to look at its children)
		c. Remove the node you have been checking from que once fully checked
	4. Repeat until you find the final node

### Depth First Search
- To find out if there is a path between two nodes
- A recursive way of traversing a graph
- `public boolean DepthFirstSearch(String toFind, Node start)`
		1. Create "marked" set
		2. Mark the element
		3. Loop through children
		4. Recursively run DFS function, return true if recursive function returns true

### Depth vs Breadth First Search
- BFS needs que and marked set (DFS just marked set) --> More data in memory
- DFS likely to be more efficient
- BFS guarantees to find the mininum number of edges between nodes