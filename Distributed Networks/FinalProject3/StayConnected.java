import java.rmi.Naming;

public class StayConnected extends Thread implements Runnable 
{
	ConnectServer Connection;
	private boolean connected = true;
	private boolean checked = false;
	
	public StayConnected(ConnectServer server)
	{
		this.Connection = server;
	}
	
	public void run()
	{
		
		while (connected)
		{
			try
			{
				Connection.ping();
			}
			catch (Exception e)
			{
				Connection.connectAClient();
			}
		}
	}
	
	public void quit()
	{
		connected = false;
	}
	
	private synchronized void connect()
	{
		while (connected)
		{
			try
			{
				Connection.connectAClient();
				checked = false;
			}
			catch (Exception e)
			{
				checked = true;
			}
		}
	}

}
