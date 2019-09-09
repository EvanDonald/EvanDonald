
import java.rmi.RemoteException;

public interface ChatWriter extends java.rmi.Remote 
{
    boolean login (ChatWriter ci, String name) throws RemoteException;
	void logout (ChatWriter ci, String name) throws RemoteException;
    void send(String name, String message) throws RemoteException;
    void display(String message) throws RemoteException;
	String returnName() throws RemoteException;
}