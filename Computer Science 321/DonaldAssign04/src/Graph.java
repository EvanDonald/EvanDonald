
public class Graph 
{
	int matrix[][];
	int noVertices = 4;
	int NNodes = 4;
	
	public Graph(int n[][])
	{
		matrix = n;
	}
	
	public int dijkstras(int s)
	{
		boolean visitedNode[] = new boolean[noVertices];
		int shortestPath[] = new int[noVertices];
		int pred[] = new int[noVertices];
		int shortestPathSum = 0;
		
		for (int i = 0; i < noVertices; i++)
		{
			if (i != s)
			{
				shortestPath[i] = Integer.MAX_VALUE;
			}
			pred[i] = -1;
		}
		
		int currV = s;
		int visitedNodeCount = 0;
		while (visitedNodeCount < noVertices)
		{
			for (int i = 0; i < noVertices; i++)
			{
				if (matrix[currV][i] > 0 && !visitedNode[i])
				{
					int weight = shortestPath[currV] + matrix[currV][i];
					if (weight < shortestPath[i])
					{
						shortestPathSum = shortestPathSum + weight;
						
						shortestPath[i] = weight;
						pred[i] = currV;
					}
				}
			}
			
			visitedNode[currV] = true;
			visitedNodeCount++;
			
			if (visitedNodeCount < noVertices)
			{
				int smallestValue = Integer.MAX_VALUE;
				for (int i = 0; i < noVertices; i++)
				{
					if (!visitedNode[i] && shortestPath[i] < smallestValue)
					{
						smallestValue = shortestPath[i];
						currV = i;
					}
				}
			}
		}
		return shortestPathSum;
	}
	
	public Graph Prim()
	{
		boolean[] Reached = new boolean[NNodes];
		int [] predNode = new int[NNodes];
		int MSTmatrix[][] = new int[4][4]; 
		
		
		Reached[0] = true;
		
		for (int i = 1; i < NNodes; i++)
		{
			Reached[i] = false;
		}
		
		predNode[0] = -1;
		
		for (int i = 1; i < NNodes; i++)
		{
			int lowEdgeCost = Integer.MAX_VALUE;
			int x, y;
			x = y = -1;
			
			for (int j = 0; j < NNodes; j++)
			{
				for (int k = 0; k < NNodes; k++)
				{
					if (Reached[j] && !Reached[k] && matrix[j][k] > 0 && matrix[j][k] < lowEdgeCost)
					{
						x = j;
						y = k;
						lowEdgeCost = matrix[x][y];
						//MSTmatrix[j][k] = lowEdgeCost;
					}
				}
			}
			
			predNode[y] = x;
			Reached[y] = true;
		}
		
		for (int i = 0; i < NNodes; i++)
		{
			if (predNode[i] != -1)
			{
			    MSTmatrix[predNode[i]][i] = matrix[predNode[i]][i];
			    MSTmatrix[i][predNode[i]] = matrix[i][predNode[i]];
			}
		}
		Graph MSTgraph = new Graph(MSTmatrix);
		return MSTgraph;
			
	}
	
	
	public int totalCost()
	{
		int total = 0;
		for (int i = 0; i < 4; i++)
		{
			for (int j = i; j < 4; j++)
			{
				total = total + matrix[i][j];
			}
		}
		return total;
	}
}














