import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Donald15C 
{

	public static void main(String[] args) 
	{
		Scanner Keyboard = new Scanner(System.in);
		String test;

		System.out.println("Please enter a string between 5 and 100 characters: ");
		test = Keyboard.nextLine();
		while (test.length() < 5 || test.length() > 100)
		{
			System.out.println("Please enter a valid string: ");
			test = Keyboard.nextLine();
		}
		
		test = test.toUpperCase();
		
		
		int[][] palindromes = new int[test.length()][test.length()];
	
		
		//find palindromes
		for (int i = test.length(); i >= 0; i--)
        {
            for (int j = i; j <= test.length()-1; j++)
            {
                if (i == j)
                {
                    palindromes[i][j] = 1;
                } 
                else if (test.charAt(i) != test.charAt(j))
                {
                    palindromes[i][j] = 0;
                }
                else
                {
                    if (j-i == 1)
                    	palindromes[i][j] = 1;
                    else
                    	palindromes[i][j] = palindromes[i+1][j-1];
                }
            }
        }
		
		//print palindromes array
		/*for (int i = 0; i <= test.length()-1; i++)
		{
			for (int j = 0; j <= test.length()-1; j++)
			{
				System.out.print(palindromes[i][j] + " ");
			}
			System.out.println();
		}*/
		
		
		//get number of cuts
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(test.length());
        
        for (int i = 0; i < test.length(); i++)
            list.add(new ArrayList<Integer>());
 
        list.get(test.length()-1).add(test.length()-1);
        
        for (int i = test.length()-2; i >= 0; i--)
        {
            if (palindromes[i][test.length()-1] == 1)
            {
                list.get(i).clear();
                list.get(i).add(i);
            }
            else
            {
                for (int j = test.length()-2; j >= i; j--)
                {
                    if (palindromes[i][j] == 1)
                    {
                        int count = 1 + list.get(j+1).size(); 
 
                        if (list.get(i).isEmpty() || count < list.get(i).size())
                        {
                            ArrayList<Integer> list2 = list.get(i);
                            list2.clear();
                            list2.add(0, i);
                            list2.addAll(list.get(j+1));
                        } 
                    }
                } 
            } 
        }
		
		
		System.out.println("The minimal number of cuts is " + (list.get(0).size() - 1));
		
		
		//print cuts
        List<Integer> l = list.get(0);
        int i;
        for (i = 0; i < l.size()-1; i++)
        { 
            int begin = l.get(i);
            int end = l.get(i+1);
            System.out.print(test.substring(begin, end) + "|");
        }
        int end = l.get(i);
        System.out.println(test.substring(end));
        
		
			
		
	}

}
