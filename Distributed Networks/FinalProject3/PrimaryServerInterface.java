import java.rmi.*;
import java.util.ArrayList;

public interface PrimaryServerInterface extends Remote 
{
	
	String connectAClient() throws RemoteException;

	String connectAServer(String ip) throws RemoteException;
	
	ArrayList<String> getServers() throws RemoteException;
	
}
