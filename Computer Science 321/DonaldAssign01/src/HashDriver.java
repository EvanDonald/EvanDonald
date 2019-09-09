import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class HashDriver
{
	public static void main(String[] args)
	{
		String inFilename = args[0];
		int tableSize = Integer.parseInt(args[1]);
		int bucketSize = Integer.parseInt(args[2]);
		Hashtable t = new Hashtable(tableSize, bucketSize);
		
		Scanner inputStream = null;
		
		try
		{
			inputStream = new Scanner(new File(inFilename));

			while (inputStream.hasNextLine())
			{
				String line = inputStream.nextLine();
				String word = line.substring(0, line.indexOf(";"));
				String def = line.substring(line.indexOf(";") + 1, line.length());
				t.put(word, def.trim());
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			inputStream.close();
		}
		
		System.out.println(t);

	}

}