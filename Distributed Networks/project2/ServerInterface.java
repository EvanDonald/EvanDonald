import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class ServerInterface extends UnicastRemoteObject implements ChatWriter 
{
    private ArrayList<ChatWriter> clientList;
	String currentClient = "";
 
    protected ServerInterface() throws RemoteException 
	{
        clientList = new ArrayList<ChatWriter>();
    }

    public synchronized boolean login(ChatWriter cw, String clientname) throws RemoteException 
	{
		for (int i = 0; i < clientList.size(); i++)
		{
			if (clientList.get(i).returnName().equals(clientname))
			{
				cw.display("Sorry, that username is taken.");
				return false;
			}
		}
		this.clientList.add(cw);
		return true;
    }
	
	public synchronized void logout(ChatWriter cw, String clientname) throws RemoteException
	{
		for (int i = 0; i < clientList.size(); i++)
		{
			if (clientList.get(i).returnName().equals(clientname))
				clientList.remove(i);
		}
	}
	
	public String returnName() throws RemoteException {return "";}
 
    public synchronized void send(String clientname , String message) throws RemoteException 
	{
        for (int i = 0; i < clientList.size(); i++) 
		{
			if (!(clientList.get(i).returnName().equals(clientname)))
				clientList.get(i).display(clientname + ": " + message);
        }
    }
 
    public synchronized void display(String message) throws RemoteException{}

    public static void main(String[] arg) throws RemoteException, MalformedURLException 
	{
        Naming.rebind("messageservice", new ServerInterface());
    }
 
}