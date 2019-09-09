public class Assignment04Test
{

	public static void main(String[] args)
	{
		int costMat[][] =	{	{ 0, 20, 10, 0 },
								{ 20, 0, 19, 5 },
								{ 10, 19, 0, 0 },
								{ 0, 5, 0, 0 } };

		Graph costGraph = new Graph(costMat);
		
		//=================================================================
		// This is for #1. 
		// The cost of building all network links 
		//=================================================================
		int totalCost = costGraph.totalCost(); 
		System.out.println("Total cost of building all links: $" + totalCost + " million");
		
		//=================================================================
		// This is for #2. 
		// The cost of building the MST  
		//=================================================================
		//costGraph.Prim();
		Graph mstGraph = costGraph.Prim(); 
		int mstCost = mstGraph.totalCost();
		System.out.println("Cost of building MST links: $" + mstCost+ " million");
	
		//=================================================================
		// This is for #3. 
		// The cost of all network links (#1) - cost of MST links (#2)
		//=================================================================
		System.out.println("Amount Saved with MST: $" + (totalCost - mstCost) + " million"); 
																				

		//=================================================================
		// The weighted latency graph
		//=================================================================
		int latencyMatrix[][] = {	{ 0, 33, 17, 0 },
									{ 33, 0, 40, 10 },
									{ 17, 40, 0, 0 },
									{ 0, 10, 0, 0 } };

		Graph latGraph = new Graph(latencyMatrix);
		
		//=================================================================
		// Calculate dijkstras for each vertex, and sum the total (#4)
		//=================================================================	
		int totalLatCost = 0;
		for (int i = 0; i < 4; ++i)
		{
			totalLatCost += latGraph.dijkstras(i);
		}
		System.out.println("Total transit time for all links: " + totalLatCost + " milliseconds");
		
		//This will translate the MST from above to use the same tree structure,
		//but include the latency costs instead of building costs
		int mstLatMatrix[][] = new int[4][4];
		for (int i=0; i<4; ++i)
		{
			for (int j=0; j<4; ++j)
			{
				if (mstGraph.matrix[i][j] > 0)
				{
					mstLatMatrix[i][j] = latGraph.matrix[i][j];
				}
			}
		}
		
		//=================================================================
		// Calculate dijkstras for each vertex of MST, and sum the total (#5)
		//=================================================================	s		
		Graph mstLatGraph = new Graph(mstLatMatrix); //MST with latency values
		int mstLatCost = 0;
		for (int i = 0; i < 4; ++i)
		{
			mstLatCost += mstLatGraph.dijkstras(i);
		}
		System.out.println("Total transit time for mst links: " + mstLatCost + " milliseconds");
		
		//=================================================================
		// Take #5 - #4 to determine how much more time the MST requires (#6)
		//=================================================================
		System.out.println("Extra time required for MST: " + (mstLatCost - totalLatCost) + " milliseconds");
		
		
		
	}
}