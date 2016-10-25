Group Member:
- Shentao Yang
- Yijin Wang


RandomMST:

	- 	Since there is no way to store the whole complete graph inside the memory. I will generate 
		the graph step-by-step, which is as if we have already generated that graph but seeing the 
		(edges in) graph in a step-by-step fashion. We use the Prim’s algorithm with vertices named 
		as 0, 1, …, v-1, where v is the total number of vertices. Since the starting node in the 
		Prim’s algorithm is arbitrary, without loss of generality, we choose it to be node v-1. 
		Since the graph is complete, we will implement the priority queue as unsorted array. For 
		initialization step in the Prim’s, I just initialize a random array A[0,…,v-2] of size v-1, 
		which is the result after exploring all edges adjacent to node v-1 (really, we do not need 
		to consider the starting node). Since each step of Prim’s algorithm, we only consider edges 
		connecting the removed vertex (say, u) to all the unexplored vertices. And update the value 
		in the priority queue based on the comparison of the weight of those edges with the 
		old-value in the priority queue. So I only need to generate the weight of the edges 
		connecting u to the unexplored vertices and do the usual update as in the Prim’s algorithm 
		for every u removed. Since the weights of those “new” edges are needed only once, we do not 
		need to store them. Definitely, we need a variable, say totalWeight, to store the total cost 
		of the removed vertices, which will finally become the cost of the whole MST. After 
		initialization, in the next step, suppose the Prim’s algorithm choose to remove node i. Then 
		I can just swap the content in A[i] and A[v-2] so that A[i] actually store the information 
		with respect to node v-2 and A[v-2] contain the information with respect to node i. Then our 
		algorithm will actually remove the “content” of A[v-2] (really, I just use the rear of the 
		array to store the set of nodes in the sub-MST built). Then we just referred to step 4 & 5 
		and generate v-2 random number and compare them, one-by-one, with A[0] up to A[v-3] and do 
		the usual update. In the kth iteration of the prim’s algorithm, just create v-k random 
		number and compare them, one-by-one, with A[0] up to A[v-1-k] and do the usual update and 
		swap the min-cost one, say, A[k’] with A[v-1-k]. After v-1 iteration, we can read the cost 
		of the whole MST (which is the only required result) from the variable totalWeight.
	
	- 	+-------+--------------------+--------------+
		|   V   |       Output       | Runtime (ms) |
		+-------+--------------------+--------------+
		|    10 | 1.5078117587023903 |            3 |
		|   100 |  1.161313644528088 |            4 |
		|  1000 |  1.144120085672689 |           21 |
		| 10000 | 1.2101092514674674 |         1326 |
		+-------+--------------------+--------------+
		
	-	The output of RandomMST is floating around 1.2 for any V. As V increases, the output is more 
		stable. The runtime of RandomMST increases in polynomial time as V increases.


CircleMST

	-	We again use the prim’s algorithm and unsorted array distance as priority queue. First we 
		initialize the coordinate of initial point (stored in x0, y0) and all other points (stored 
		in two arrays: xArray, yArray) by the formula: 
		-1 <= x <= 1, -sqrt(1 - x^2) <= y <= sqrt(1 - x^2). 
		Next we update the distance array according to the Euclidian distance between initial point 
		and all other point. Then we iteratively do the usual removeMin, swap and update priority 
		queue as in the previous problem in total v - 1 times. Note that in this case, swap should 
		be done in all the three arrays concurrently. After v - 1 iteration, we can read the cost of 
		the whole MST (which is the only required result) from the variable totalWeight.
	
	- 	+-------+--------------------+--------------+
		|   V   |       Output       | Runtime (ms) |
		+-------+--------------------+--------------+
		|    10 | 4.1866972335563215 |            4 |
		|   100 |  12.35201683615479 |            7 |
		|  1000 |  36.82057306965032 |           50 |
		| 10000 | 113.56867798359542 |          147 |
		+-------+--------------------+--------------+
		
	-	The output of CircleMST increases as log(n). The runtime of CircleMST also increases in 
		polynomial time as V increases but increases much slower than RandomMST. CircleMST is much 
		faster than RandomMST because Random.nextDouble() takes longer time than double arithmetic 
		operations.