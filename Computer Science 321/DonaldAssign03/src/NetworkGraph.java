import java.util.Iterator;
import java.util.LinkedList;

public class NetworkGraph
{
	private int n;
	private boolean matrix[][];
	int discoveryTime = 0;

	public NetworkGraph(int n)
	{
		this.n = n;
		matrix = new boolean[n][n];
	}

	public void addEdge(int startV, int endV)
	{
		matrix[startV][endV] = true;
		matrix[endV][startV] = true;
	}
	
	public int min(int x, int y)
	{
		if (x < y)
			return x;
		else if (y < x)
			return y;
		else 
			return x;
	}

	// this will handle disconnected graphs
	public void PointsOfFailure()
	{
		// create an array to hold, for each vertex, whether
		// it has been visited or not.
		boolean visited[] = new boolean[n];
		int parent[] = new int[n];
		int discoveryTimeArray[] = new int[n];
		int lowVertexTime[] = new int[n];
        boolean printArray[] = new boolean[n];
        
        for (int i = 0; i < n; i++)
        {
            parent[i] = -1;
            visited[i] = false;
            printArray[i] = false;
        }

		// loop through each vertex in the graph. If it has not
		// been visited yet, call DFS for the vertex. Note that
		// by starting at 0, it will naturally pick up the
		// lowered number vertices first.
		for (int i = 0; i < n; i++)
		{
			if (!visited[i])
			{
				PointsOfFailure(i, visited, parent, discoveryTimeArray, lowVertexTime, printArray);
			}
		}
		
		for (int i = 0; i < n; i++)
		{
			if (printArray[i] == true)
			{
				System.out.print(i+" ");
			}
		}
	}


	private void PointsOfFailure(int v, boolean visited[], int parent[], int discoveryTimeArray[], int lowVertexTime[], boolean printArray[])
	{
		// visiting the node, set the appropriate array index to true
		// to indiciate the node has been visited
		int children = 0;
		
		visited[v] = true;
		discoveryTime++;
		discoveryTimeArray[v] = discoveryTime;
		lowVertexTime[v] = discoveryTime;
		
		
		//Perform the visit for the traversal. Here, we are just printing.
		
		//System.out.println(v);
		
        for (int i = 0; i < n; i++)
		{
			if (matrix[v][i] == true)
			{
				// loop through all the columns in the matrix for vertex v
				// to see if there is an edge. If so, make sure that vertex
				// hasn't already been visited.
				if (!visited[i])
				{
					children++;
					parent[i] = v;
					
					// if there is an edge the the vertex has not been visited
					// call DFS for that vertex
					PointsOfFailure(i, visited, parent, discoveryTimeArray, lowVertexTime, printArray);
					lowVertexTime[v] = min(lowVertexTime[v], lowVertexTime[i]);
					
					if (parent[v] == -1 && children > 1)
					{
						printArray[v] = true;
						//System.out.print(v + " ");
					}
					
					if (parent[v] != -1 && discoveryTimeArray[v] <= lowVertexTime[i])
					{
						printArray[v] = true;
						//System.out.print(v + " ");
					}
				}
				else /*if (i != parent[v])*/
				{
					lowVertexTime[v] = min(lowVertexTime[v], discoveryTimeArray[i]);
				}
			}
		}
	}
}


