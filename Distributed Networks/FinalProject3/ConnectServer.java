import java.rmi.*;

public class ConnectServer 
{
	private ServerInterface server;
	private PrimaryServerInterface primaryServer;
	private boolean checked = false;
	
	public ConnectServer(PrimaryServerInterface pServer)
	{
		primaryServer = pServer;
	}
	
	public String reserve(int seat, String name) throws RemoteException
	{
		String s = server.makeRes(seat, name);
		return s;
	}
	
	public void ping() throws RemoteException
	{
		server.ping();
	}
	
	public synchronized boolean[] getSeats() throws RemoteException
	{
		return server.getSeats();
	}
	
	public synchronized boolean connectAClient()
	{
		try
		{
			server = (ServerInterface) Naming.lookup(primaryServer.connectAClient());
		}
		catch(Exception ex)
		{
			return false;
		}
		return true;
	}
	
	public synchronized void connect()
	{
		while (checked)
		{
			try
			{
				server = (ServerInterface) Naming.lookup(primaryServer.connectAClient());
				checked = false;
			}
			catch(Exception ex)
			{
				checked = true;
			}
		}
	}
}
