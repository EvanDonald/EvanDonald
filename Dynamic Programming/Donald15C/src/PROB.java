import java.util.Random;

public class PROB {

	public static void main(String[] args) 
	{
		int[] x = {1,3,5,6,7,9,11,12};
		Random generator = new Random();
		
		double mean = (1 + 3 + 5 + 6 + 7 + 9 + 11 + 12)/8.0;
		double variance = (Math.pow(1-mean, 2) + Math.pow(3-mean, 2) + Math.pow(5-mean, 2) + Math.pow(6-mean, 2)
			+ Math.pow(7-mean, 2) +Math.pow(9-mean, 2) + Math.pow(11-mean, 2) + Math.pow(12-mean, 2))/8.0;
		
		System.out.println(mean + " " + variance);
		
		for (int i = 0; i < 1000; i++) 
		{
			int a = generator.nextInt(8);
			int b = generator.nextInt(8);
			int c = generator.nextInt(8);
			//System.out.println(x[a] + " " + x[b] + " " + x[c]);
			double samplemean = (x[a] + x[b] + x[c])/3.0;
			double samplevar = (Math.pow(x[a]-samplemean, 2) + Math.pow(x[b]-samplemean, 2) + Math.pow(x[c]-samplemean, 2))/2.0;
			
			double stat = ((8-1)*samplevar)/variance;
			System.out.println(stat);
		}

	}

}
