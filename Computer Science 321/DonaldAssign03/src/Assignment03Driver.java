
public class Assignment03Driver
{

	public static void main(String[] args)
	{
		// Create graphs given in above diagrams
		System.out.println("Points of Failure Graph 1: ");
		NetworkGraph g1 = new NetworkGraph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		g1.PointsOfFailure();
		System.out.println();

		System.out.println("Points of Failure Graph 2: ");
		NetworkGraph g2 = new NetworkGraph(7);
		g2.addEdge(0, 1);
		g2.addEdge(0, 3);
		g2.addEdge(1, 3);
		g2.addEdge(1, 4);
		g2.addEdge(2, 3);
		g2.addEdge(2, 5);
		g2.addEdge(3, 5);
		g2.addEdge(5, 6);
		g2.PointsOfFailure();
		System.out.println();

		System.out.println("Points of Failure Graph 3: ");
		NetworkGraph g3 = new NetworkGraph(7);
		g3.addEdge(0, 1);
		g3.addEdge(1, 2);
		g3.addEdge(2, 0);
		g3.addEdge(1, 3);
		g3.addEdge(1, 4);
		g3.addEdge(1, 6);
		g3.addEdge(3, 5);
		g3.addEdge(4, 5);
		g3.PointsOfFailure();
	}

}