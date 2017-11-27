import java.util.ArrayList;
import java.util.Stack;


public class GraphAdjMatrix implements Graph {
	
	// Class variables to represent the Graph
	private int[][] matrix;
	private int size;
	
	// Constructor
	public GraphAdjMatrix(int size) {
		this.size = size;
		this.matrix = new int[size][size];
	}
	
	@Override
	// Indicates a directed edge pointing from v1 to v2
	public void addEdge(int v1, int v2) {
		matrix[v1][v2] = 1;
	}
	
	// Prints to the console an ordering of directed edges
	@Override
	public void topologicalSort() {
		
		// Initialize a boolean array to indicate that each vertex has not been visited
		boolean[] visited = new boolean[matrix.length];
		
		// Step through the array, calling the explore() function if the vertex has not been marked visited
		for(int i = 0; i < visited.length; i++) {
			if(!visited[i]) {
				explore(i, visited);
			}
		}

	}

	// Helper function called by topologicalSort()
	private void explore(int v, boolean[] visited) {
		
		// Stacks allows for proper functionality.  Begin by pushing the vertex to explore to the stack.
		Stack<Integer> s = new Stack<Integer>();
		s.push(new Integer(v));
		
		while(!s.empty()) {
			
			// Pop the top element, print it, and mark it as visited.
			int pop = s.pop();
			System.out.println(pop);
			visited[v] = true;
			
			// Find the neighbors, if any, of the just visited vertex.
			int[] neighbors = neighbors(pop);
			
			// Each neighbor that has not been visited gets pushed to the stack, repeating the process.
			for(int n : neighbors) {
				if(!visited[n]) {
					s.push(new Integer(n));
					visited[n] = true;
				}
			}
		}

		
	}

	@Override
	public int[] neighbors(int vertex) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		// Iterate horizontally to find all directed edges out of vertex
		for(int i = 0; i < size; i++) {
			if(matrix[vertex][i] == 1) {
				temp.add(i);
			}
		}
		
		// Calls the helper function on the temp ArrayList
		int[] neighbors = convertIntegers(temp);
		return neighbors;
	}
	
	// Helper function to convert an ArrayList of Integer objects to a primitive int array
	private static int[] convertIntegers(ArrayList<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    for (int i=0; i < ret.length; i++)
	    {
	        ret[i] = integers.get(i).intValue();
	    }
	    return ret;
	} 

}
