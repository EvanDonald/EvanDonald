import java.rmi.*;

public class PrimaryServer 
{
	public static void main(String[] args) throws Exception 
	{
		PrimaryServerInterface primaryServer = new PrimaryServerInterfaceImpl();
		Naming.rebind("pserver", primaryServer);
		
		ServerInterface server = new ServerInterfaceImpl();
		Naming.rebind("server", server);
	}
}
