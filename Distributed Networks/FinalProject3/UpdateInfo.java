import java.io.Serializable;
import java.util.HashMap;

public class UpdateInfo implements Serializable
{
	private HashMap<Integer,String> seatMap;
	
	private int[] locked;
	private boolean[] seatArray;
	
	public UpdateInfo(HashMap<Integer,String> seatMap, int[] locked, boolean[] seatArray)
	{
		this.seatMap = seatMap;
		this.locked = locked;
		this.seatArray = seatArray;
	}
	
	public HashMap<Integer,String> getSeatMap()
	{
		return seatMap;
	}
	
	public int[] getLock()
	{
		return locked;
	}
	
	public boolean[] getSeats()
	{
		return seatArray;
	}
}
