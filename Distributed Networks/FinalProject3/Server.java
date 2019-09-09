import java.rmi.*;
import java.util.Scanner;

public class Server 
{
	public static void main(String[] args) throws Exception 
	{
		PrimaryServerInterface primaryServer = (PrimaryServerInterface) Naming.lookup("rmi://127.0.0.1/pserver");
		ServerInterface tempserver = (ServerInterface) Naming.lookup("rmi://127.0.0.1/server");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the IP address.");
		String ip = scan.next();
		
		
		String RMIstring = primaryServer.connectAServer(ip);
		ServerInterface server = new ServerInterfaceImpl(tempserver, primaryServer, RMIstring);
		Naming.rebind(RMIstring, server);
		
		scan.close();
	}
}
