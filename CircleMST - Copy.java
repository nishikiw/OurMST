import java.util.Random;
import java.util.Scanner;

public class CircleMST {
	
	public static double getMSTWeight(int V){
			
		Random rd = new Random();
		
		// Initiate the variables to keep track of the minEdge and its index from index 0 to counter.
		int minVertexIndex = 0;
		
		// Initiate an array to keep track of edges in MST, which serves as the priority queue.
		// Note: the stored value is actually distance^2.
		double[] distances = new double[V - 1];
		
		// Generate x and y for starting vertex.
		double x0 = -1 + 2 * rd.nextDouble();
		double y0 = -Math.sqrt(1 - Math.pow(x0, 2)) + rd.nextDouble() * 2 * Math.sqrt(1 - Math.pow(x0, 2));
		
		// Initiate arrays for all other vertices' x and y value except for x0.
		double[] xArray = new double[V-1];
		double[] yArray = new double[V-1];
		
		// Calculate their distance individually and add to array edges.
		// Keep track of the minimum distance.
		for (int j = 0; j < V - 1; j++){
			xArray[j] = -1 + 2 * rd.nextDouble();
			yArray[j] = -Math.sqrt(1 - Math.pow(xArray[j], 2)) + rd.nextDouble() * 2 * Math.sqrt(1 - Math.pow(xArray[j], 2));
			distances[j] = Math.pow(xArray[j] - x0, 2)+Math.pow(yArray[j] - y0, 2);
			if (distances[j] < distances[minVertexIndex]){
				minVertexIndex = j;
			}
		}
		
		
		double totalWeight = 0;
		for (int counter = V - 2;counter > 0;counter--){
			
			// Add the cost of popped out vertex to total weight of MST. 
			totalWeight += Math.sqrt(distances[minVertexIndex]);

			// Pop out vertex with minimum cost, which is at edges[minVertexIndex].
			// Swap current vertex with the vertex with index counter.
			// Swap distance.
			double counterCost = distances[counter];
			distances[counter] = distances[minVertexIndex];
			distances[minVertexIndex] = counterCost;
			
			// Swap x.
			double counterX = xArray[counter];
			xArray[counter] = xArray[minVertexIndex];
			xArray[minVertexIndex] = counterX;
			
			// Swap y.
			double counterY = yArray[counter];
			yArray[counter] = yArray[minVertexIndex];
			yArray[minVertexIndex] = counterY;
			
			minVertexIndex = 0;
			// Generate edges connecting the vertex with minimum cost.
			for (int i = 0; i < counter; i++){
				double newDistance = Math.pow(xArray[i] - xArray[counter], 2)+Math.pow(yArray[i] - yArray[counter], 2);
				//System.out.println(newDistance);
				if (newDistance < distances[i]){
					distances[i] = newDistance;
				}
				if (distances[i] < distances[minVertexIndex]){
					minVertexIndex = i;
				}
			}
		}	
		
		totalWeight += Math.sqrt(distances[0]);
		return totalWeight;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int vNum = sc.nextInt();
		System.out.println(CircleMST.getMSTWeight(vNum));
		sc.close();
	}
}
