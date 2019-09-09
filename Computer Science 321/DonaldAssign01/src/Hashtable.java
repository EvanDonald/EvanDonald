import java.util.ArrayList;
//import java.lang.StringBuilder;

public class Hashtable
{

	HashEntry<String,String>[] MyHashTable;
	ArrayList<HashEntry<String,String>> overflow;
	int tSize, bSize;
	int hash1, hash2;
	int index1, index2;
	int numOfBuckets;
	String Empty = "blank";
    HashEntry<String,String> Blank = new HashEntry<String,String>(Empty, Empty);
	
	Hashtable(int tableSize, int bucketSize)
	{
		tSize = tableSize;
		bSize = bucketSize;
		numOfBuckets = tableSize/bucketSize;
		MyHashTable = new HashEntry[tableSize];
		overflow = new ArrayList<HashEntry<String,String>>();
		for (int i = 0; i < tSize; i++)
		{
			MyHashTable[i] = Blank;
		}
	}
	
	public void put(String key, String value)
	{	
		HashEntry<String,String> KV = new HashEntry<String,String>(key, value);
		hash1 = (key.length()*17) % numOfBuckets;
		hash2 = (int)key.charAt(0) % numOfBuckets;
	    index1 = hash1*bSize;
		index2 = hash2*bSize;
		
		
		int counter1 = 0, counter2 = 0, jSize = index1 + bSize;
	    for (int j = index1; j < jSize; j++)
	    {
	    	if (MyHashTable[j] != Blank)
	    	{
	    		counter1++;
	    	}
	    }
	    int lSize = index2 + bSize;
	    for (int l = index2; l < lSize; l++)
	    {
	    	if (MyHashTable[l] != Blank)
	    	{
	    		counter2++;
	    	}
	    }
	    
	    if (counter1 == bSize && counter2 == bSize)
		{
			overflow.add(KV);
		}
		
	    else if (counter1 == counter2) 
	    {
	    	while (MyHashTable[index1] != Blank)
	    	{
	    		index1 = index1 + 1;
	    	}
	    	MyHashTable[index1] = KV;
        	return;
	    }
		
	    else if (counter1 < counter2) 
	    {
			while (MyHashTable[index1] != Blank)
	    	{
	    		index1 = index1 + 1;
	    	}
	    	MyHashTable[index1] = KV;
        	return;
        }
		
	    else if (counter1 > counter2) 
	    {
			while (MyHashTable[index2] != Blank)
	    	{
	    		index2 = index2 + 1;
	    	}
	    	MyHashTable[index2] = KV;
        	return;
        }
	}
	
	public String get(String key)
	{
		hash1 = (key.length()*17) % numOfBuckets;
		hash2 = (int)key.charAt(0) % numOfBuckets;
		int start1 = hash1*bSize;
		int start2 = hash2*bSize;
				
		if (bucketContains(key, hash1))
		{
			for (int i = start1; i < start1 + bSize; i++)
			{
				if (MyHashTable[i].getKey().equals(key))
				{
					return MyHashTable[i].getValue();
				}
			}
		}
		
		if (bucketContains(key, hash2))
		{
			for (int i = start2; i < start2 + bSize; i++)
			{
				if (MyHashTable[i].getKey().equals(key))
				{
					return MyHashTable[i].getValue();
				}
			}
		}
	
		if (bucketContains(key, -1))
		{
			for (int i = 0; i < overflow.size(); i++)
			{
				if(overflow.get(i).getKey().equals(key))
				{
					return overflow.get(i).getValue();
				}
			}
		}
		return "This key is not in the table";
	}
	
	public boolean bucketContains(String key, int BucketNumber)
	{
		//returns true if word is in bucket entered
		int start = BucketNumber*bSize;
		
		if (BucketNumber == -1)
		{
			for (int j = 0; j < overflow.size();)//j++)
			{
				if(overflow.get(j).getKey().equals(key))
				{
					return true;
				}
				else 
				{
					return false;
				}
			}
		}
		else 
		{
			for (int i = start; i < start + bSize; i++)
			{
				if (MyHashTable[i].getKey().equals(key))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public int getBucketSize(int BucketNumber)
	{
		//return how many items in bucket
		int start = BucketNumber*bSize;
		int NumberOfItems = 0;
		
		if (BucketNumber == -1)
		{
			NumberOfItems = overflow.size();
			return NumberOfItems;
		}
		
		for (int i = start; i < start + bSize; i++)
	    {
	    	if (MyHashTable[i] != Blank)
	    	{
	    		NumberOfItems++;
	    	}
	    }
		
		return NumberOfItems;
	}
	
	public String toString()
	{
		String t = " ";
		StringBuilder sb = new StringBuilder(t);
		
		int bucketCounter = 0;
		
	    for (int k = 0; k < tSize; k++)
		{
			if (MyHashTable[k] != Blank)
			{
				int kmodbSize = k%bSize;
			
				if (kmodbSize == 0)
				{
					sb.append('\n' + "------------------Bucket " + bucketCounter + ":-------------------" + '\n' + MyHashTable[k].toString());
					bucketCounter++;
				}
				else
				{
					sb.append('\n' + MyHashTable[k].toString());
				}
			}
		}
		
		sb.append('\n' + "------------------overflow:-------------------");
		
		for (int m = 0; m < overflow.size(); m++)
		{
			sb.append('\n');
			sb.append(overflow.get(m));
		}
		
		t = sb.toString();
		return t;
	}
	
	

}
