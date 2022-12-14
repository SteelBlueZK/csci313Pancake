Description of flipping pancakes:
The first look at the worst case scenario goes 
like this: N -1 -2 -3 .... -(N-1) -(N). for every 
element between 2 and N inclusive you need to 
preform 3 flips(j, 1, j) to sort it, and for element 
1 (the last element) it takes a single flip. Every 
stack of pancakes can be solved by finding the 
largest unsorted pancake, flipping it to the top
, potentially one flip to turn it burnt side up
, then a third(or second) flip to the bottom of 
the stack in a sorted position. This can then 
be optimized by removing unnessisary flips because 
pancakes are already sorted.

Looking at the problem the second time, you may 
find the insite that any grouping of two pancakes 
may be considered sorted if the pancake relative 
to the burnt side is adjacent to the next largest 
sized pancake. My solution uses this to my advantage 
by attempting to sort the top pancake of the current 
stack relative to the next largest pancake in 
the unsorted area of the stack. If the pancake 
on top is the largest in the sorted area, it can 
be safely sorted to the bottom. By attempting 
to find single-flip operations to locally sort 
the pancake on top to another pancake while not 
interfering with other potentially locally sorted 
pancakes, I may find potentially quicker solutions 
to the pancake problem.


Platform compilation:
I compiled and ran my code succesfully on my Windows 
10 machine. I've been testing with javac -d and 
java -cp.


Resources that Helped Me:
My AssertPancake.java was my initial implementation 
of a pancake solver. It was very hard to work 
with because it used a stack as it's data structure 
instead of an array. But it was built to first 
convert a pancake input into a sorted pancake 
stack with a submited solution order, and I then 
adapted and continued to adapt it to randomly 
generate pancake stacks and test an input proposed 
solution. It was easy to track down all the edge 
cases after I had this tool(and using breakpoint 
debugging in InteleJ ide). By the end I was using 
it to generate and solve 100,000 random pancake 
stacks of size 7 - 10, which I got a 100% success 
rate on.

InteleJ ide also would count as a resource, I suppose.
I also use java.util.Random for random pancake 
stack generation(testing) and java.util.Scanner 
to read numbers from my input/solution strings. 
If I had to create a better solution, I would 
create a linked list data model where every node 
represents a locally sorted grouping of pancakes 
of size n >= 1 and the nodes combine when they 
are locally sorted. I would attempt to preform 
the least number of flip operations nessisary 
to sort nodes and combine nodes, until there is 
a single node with the proper orientation left.


To run:
>java FlipPancakes <list|array> <N <1 2 3 .... N>>
To run random pancakes:

AssertPancake.class how to:

java AssertPancake [TEST_COUNT MIN_STACK_COUNT STACK_COUNT_VARIANCE]
	where TEST_COUNT is a positive integer for number of tests to run,
	MIN_STACK_COUNT is the minimum number of pankakes in a randomly generated stack
	STACK_COUNT_VARIANCE is a number greater than 1, to generate randomly stacks in the range
		(MIN_STACK_COUNT, MIN_STACK_COUNT + STACK_COUNT_VARIANCE]
	TEST_COUNT is default 10,
	MIN_STACK_COUNT  is default 4
	STACK_COUNT_VARIANCE is default 3
	no argument run is 10 random stacks of pancakes between 4 and 6 in length

java AssertPancake [0 N a1 a2 a3... aN-1 aN]
	By entering 0 for TEST_COUNT, the rest of the argument can be a properly formated input string.
	This will test the single case specified by user.

AssertPancake is modifiable to test for correctness of pancake algorithm by modifying the 
generateOutput(String input) command to run aplicable code, perhapse translating input string to
array of integers to pass to a static main function in another class.

AssertPancake.java is reliant on files:
Queue.java
LinkedQueue.java
Stack.java
ListStack.java

and may encounter bugs related to the change of details in these files. Besides that, 
Stack.java
ListStack.java
ArrayStack.java
FlipPankakes.java

can be replaced to hopefully test any in-progress soulution implementation.

You can view what my testing was looking like by running the following terminal commands below.

git clone https://github.com/SteelBlueZK/csci313Pancake.git
cd csci313Pancake
git checkout 0b85ac6a2d5601f2a7b007a2b6ac8127dd2256d9
javac *.java
java AssertPancake



