/**************************************************************************
*                                                                         *
*     Program Filename:  Donald05C.java                                   *
*     Author          :  Evan Donald                                      *
*     Date Written    :  February 6, 2019                                 *
*     Purpose         :  Prisoner Problem                                 *
*     Input from      :  User                                             *
*     Output to       :  Screen                                           *
*                                                                         *
**************************************************************************/
import java.util.Random;
import java.util.Scanner;

public class Donald05C
{
   public static void main(String[] args)
   {
	   
	  double trials;
	  Scanner Keyboard = new Scanner(System.in);
      System.out.print("Please enter the number of trials: ");
	  trials = Keyboard.nextInt();
	  
	  double totalSuccess = 0;
	  
	  for(int x = 0; x < trials; x++)
	  {
	
		  Random rand = new Random();
		  
		  int[] Boxes = new int[101];
		  for (int j = 1; j <= 100; j++)
		  {
			  Boxes[j] = j;
		  }
		  for (int j = 100; j > 1; j--)
		  {
			  int i = rand.nextInt(j) + 1;
			  int Temp = Boxes[j];
			  Boxes[j] = Boxes[i];
			  Boxes[i] = Temp;
		  }
		  
		  int counter = 0;
		  int box;
		  for (int i = 1; i <= 100; i++)
		  {
			  int fifty = 1;
			  box = Boxes[i];
			  
			  if (box == i)
			  {
				  counter++;
				  //break;
			  }
			  else
			  {
				  while (fifty <= 49)
				  {
					  box = Boxes[box];
					  if (box == i)
					  {
						  counter++;
						  break;
					  }
					 
					  fifty++;
				  }
			  }
				  
			  
		  }
		  //System.out.println(counter);
		  if (counter == 100)
			  totalSuccess++;
	  }
	  double percent = totalSuccess/trials;
	  System.out.println("The strategy was successful " + percent*100 + "% of the time.");
      
   }
   // end main method
}
// end class ThreeDoors
