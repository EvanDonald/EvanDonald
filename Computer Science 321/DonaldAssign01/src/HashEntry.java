
public class HashEntry<K,V> 
{
	private K key;
	private V value;
	
	HashEntry(K k, V v)
	{
		key = k;
		value = v;
	}

	public K getKey() 
	{
		return key;
	}

	public void setKey(K key) 
	{
		this.key = key;
	}

	public V getValue() 
	{
		return value;
	}

	public void setValue(V value) 
	{
		this.value = value;
	}
	
	public String toString()
	{
		String output = key + " => " + value;
		return output;
	}
	
}
