import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.rmi.*;

public class PrimaryServerInterfaceImpl extends UnicastRemoteObject implements PrimaryServerInterface 
{
	
	ArrayList<String> servers;
	
	
	public PrimaryServerInterfaceImpl() throws RemoteException
	{
		servers = new ArrayList<String>();	
	}
	
	public synchronized String connectAClient()
	{
		Random rand  = new Random();
		
		int num = rand.nextInt(servers.size());
		
		return servers.get(num);
	}
	
	public synchronized String connectAServer(String ip)
	{
		String RMIstring = "rmi://"+ip+"/server"+ servers.size();
		servers.add(RMIstring);
		return RMIstring;
	}
	
	public synchronized ArrayList<String> getServers()
	{
		return servers;
	}
}
