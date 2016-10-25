package OurMST;

import java.util.*;

public class RandomMST {

	public static double getMSTWeight(int V){
		
		Random rd = new Random();
		
		// Initiate the variables to keep track of the minEdge and its index from index 0 to counter.
		int minVertexIndex = 0;
		
		// Initiate an array to keep track of edges in MST, which serves as the priority queue.
		// Generate all edges connecting starting vertex and other vertices.
		double[] edges = new double[V - 1];
		for (int j = 0; j < V - 1; j++){
			edges[j] = rd.nextDouble();
			if (edges[j] < edges[minVertexIndex]){
				minVertexIndex = j;
			}
		}
		
		double totalWeight = 0;
		for (int counter = V - 2;counter > 0;counter--){
			// Add the cost of popped out vertex to total weight of MST. 
			totalWeight += edges[minVertexIndex];
			
			// Pop out vertex with minimum cost, which is at edges[minVertexIndex].
			// Swap current vertex with the vertex with index counter.
			double counterCost = edges[counter];
			edges[counter] = edges[minVertexIndex];
			edges[minVertexIndex] = counterCost;
			
			minVertexIndex = 0;
			// Generate edges connecting the vertex with minimum cost.
			for (int i = 0; i < counter; i++){
				double newEdge = rd.nextDouble();
				if (newEdge < edges[i]){
					edges[i] = newEdge;
				}
				if (edges[i] < edges[minVertexIndex]){
					minVertexIndex = i;
				}
			}				
		}
		
		totalWeight += edges[0];
		return totalWeight;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int vNum = sc.nextInt();
		System.out.println(RandomMST.getMSTWeight(vNum));
		sc.close();
	}
}
