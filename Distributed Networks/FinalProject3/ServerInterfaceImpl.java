import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerInterfaceImpl extends UnicastRemoteObject implements ServerInterface 
{
	
	boolean[] seatArray;
	
	HashMap<Integer,String> seatMap;
	
	int[] locked;
	
	ServerInterface server;
	
	PrimaryServerInterface primaryServer;
	
	String RMIstring;
	
	public ServerInterfaceImpl() throws RemoteException 
	{
		
		seatArray = new boolean[50];
		seatMap  = new HashMap<Integer,String>();
		locked = new int[50];
		
		for(int i=0;i<50;i++)
		{
			locked[i] = -1;
		}
		
	}
	
	public ServerInterfaceImpl(ServerInterface s) throws RemoteException
	{
		server = s;
		
		UpdateInfo inform = server.sync();
		
		seatMap = inform.getSeatMap();
		
		locked = inform.getLock();
		
		seatArray = inform.getSeats();
		
	}
	
	public ServerInterfaceImpl(ServerInterface s, PrimaryServerInterface ps, String rmi) throws RemoteException
	{
		this.primaryServer = ps;
		
		server = s;
		
		this.RMIstring = rmi;
		
		UpdateInfo inform = server.sync();
		
		seatMap = inform.getSeatMap();
		
		locked = inform.getLock();
		
		seatArray = inform.getSeats();
		
	}
	
	public synchronized void primaryRes(int s, String name) throws RemoteException
	{
		Integer seat = new Integer(s);
		
		if(seatMap.containsKey(seat)){}
		else
		{
			seatMap.put(seat,name);
			seatArray[s-1] = true;
			complete(s);
		}
	}
	
	public synchronized String makeRes(int s, String name) throws RemoteException
	{
		ArrayList<String> serverList = primaryServer.getServers();
		
		if(locked[s-1] == 1)
		{
			return "That seat is already reserved.";
		}
		else if(locked[s-1] == 0)
		{
			return "Cannot reserve that seat at this time.";
		}
		else
		{
			put(s);
			int visited = 0;
			boolean reset = false;
			
			for (String connection : serverList)
			{
				if(!RMIstring.equals(connection))
				{
					visited++;
					try
					{
						ServerInterface server = (ServerInterface) Naming.lookup(connection);
						
						if(server.check(s) == 0 || server.check(s) == 1)
						{
							reset = true;
							break;
						}
						else
						{
							server.put(s);
						}	
					}
					catch(Exception e)
					{
						
					}
				}
			}
			
			if(reset)
			{
				for(int i = 0; i < visited; i++)
				{
					if(!RMIstring.equals(serverList.get(i)))
					{
						try
						{
							ServerInterface server = (ServerInterface) Naming.lookup(serverList.get(i));
							server.remove(s);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				remove(s);
				
				return "That seat is already reserved.";
			}
			complete(s);
			primaryRes(s, name);
			server.primaryRes(s, name);
			for(String connection : serverList)
			{
				if(!RMIstring.equals(connection))
				{
					visited++;
					try
					{
						ServerInterface server = (ServerInterface) Naming.lookup(connection);
						server.primaryRes(s, name);
							
					}
					catch(Exception e)
					{
						
					}
				}
			}
			
			return name + " successfully reserved seat " + s + ".";
			
		}
	}
	
	public synchronized boolean[] getSeats()
	{
		return seatArray;
	}

	public int ping()
	{
		return 1;
	}
	
	public synchronized UpdateInfo sync()
	{
		UpdateInfo inform = new UpdateInfo(seatMap, locked, seatArray);
		
		return inform;
	}
	
	public synchronized int check(int seatnumber)
	{
		return locked[seatnumber-1];
	}
	
	public synchronized void put(int seatnumber)
	{
		locked[seatnumber-1] = 0;
	}
	
	public synchronized void remove(int seatnumber)
	{
		locked[seatnumber-1] = -1; 
	}
	
	public synchronized void complete(int seatnumber)
	{
		locked[seatnumber-1] = 1;
	}

}
