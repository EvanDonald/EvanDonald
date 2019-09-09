
import java.util.Random;
import java.util.Scanner;

public class Donald11C 
{
	public static void main(String[] args) 
	{
		int n, m;
		Scanner Keyboard = new Scanner(System.in);
		Random generator = new Random();
		double[] birds;
		int[] checkedArray;
		double total;
		double sum = 0;
		double totalAverage = 0;

		System.out.println("Please enter the number of birds and size of wire you would like: ");
		n = Keyboard.nextInt();
		System.out.println("Please enter the number of iterations you would like to perform: ");
		m = Keyboard.nextInt();
		
		for (int j = 1; j <= m; j++) 
		{

			checkedArray = new int[n + 1];
			for (int i = 1; i < n; i++)
			{
				checkedArray[i] = 0;
			}
			
			birds = new double[n + 1];
			double A;

			for (int i = 1; i <= n; i++) 
			{
				A = n * generator.nextDouble();
				birds[i] = A;
			}

			QuickSort(birds, 1, n);

			total = birds[2] - birds[1];
			total += birds[n] - birds[n - 1];
			checkedArray[1] = 1;

			for (int i = 2; i < n; i++) 
			{
				if (birds[i] - birds[i - 1] < birds[i + 1] - birds[i] && checkedArray[i - 1] != 1) 
				{
					total += birds[i] - birds[i - 1];
				} 
				else if (birds[i + 1] - birds[i] < birds[i] - birds[i - 1]) 
				{
					total += birds[i + 1] - birds[i];
					checkedArray[i] = 1;
				}
			}

			sum += total / n;
		}
		// end for iterations

		totalAverage = sum / m;
		System.out.println(totalAverage);
	}
	// end main

	public static void QuickSort(double A[], int Left, int Right) 
	{
		double v, temp;
		int i, j;

		if (Left >= Right)
			return;
		// end if

		v = A[Right];
		i = Left;
		j = Right - 1;

		for (;;) 
		{
			while (A[i] < v)
				i++;
			// end while

			while (j > Left && A[j] > v)
				j--;
			// end while

			if (i >= j)
				break;
			// end if

			temp = A[i];
			A[i] = A[j];
			A[j] = temp;
			i++;
			j--;
		}
		// end for

		temp = A[i];
		A[i] = A[Right];
		A[Right] = temp;

		if (Left < i - 1)
			QuickSort(A, Left, i - 1);
		// end if
		if (Right > i + 1)
			QuickSort(A, i + 1, Right);
		// end if
	}
	// end method QuickSort
}
// end class QuickSort
