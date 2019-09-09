import java.rmi.*;

public interface ServerInterface extends Remote 
{
	void primaryRes(int s, String n) throws RemoteException;
	
	String makeRes(int s, String n) throws RemoteException;
	
	boolean[] getSeats() throws RemoteException;
	
	int ping() throws RemoteException;
	
	UpdateInfo sync() throws RemoteException;
	
	int check(int seatnumber) throws RemoteException;
	
	void put(int seatnumber) throws RemoteException;
	
	void remove(int seatnumber) throws RemoteException;
	
	void complete(int seatnumber) throws RemoteException;
	
}
