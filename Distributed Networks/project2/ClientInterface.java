import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.util.Scanner;

public class ClientInterface extends UnicastRemoteObject implements ChatWriter, Runnable 
{
    private ChatWriter server;
    private String ClientName;
	boolean login = false;
 
    protected ClientInterface(ChatWriter cw, String cn) throws RemoteException 
	{
		Scanner scanner = new Scanner(System.in);
		String name = "";
        this.server = cw;
        this.ClientName = cn;
		login = server.login(this, cn);
		while (!login)
		{
			System.out.println("Please enter a new username: ");
			name = scanner.nextLine();
			this.ClientName = name;
			System.out.println();
			login = server.login(this, name);
		}
    }

    public void display(String message) throws RemoteException 
	{
        System.out.println(message); 
    }
	
	public String returnName() throws RemoteException 
	{
        return ClientName; 
    }
 
    public void send(String cn, String message) throws RemoteException {}
 
    public boolean login(ChatWriter cw, String cn) throws RemoteException {return true;}
	
	public void logout(ChatWriter cw, String cn) throws RemoteException {}
	
    public void run() 
	{
        System.out.println("Connected to chat room. Enter quit to logout.");
        Scanner scanner = new Scanner(System.in);
        String message;
            
        while(true) 
		{
            message = scanner.nextLine();
            if(message.equals("QUIT") || message.equals("Quit") || message.equals("quit")) 
			{
                try 
				{
					server.send(ClientName, "quits");
					server.logout(this, ClientName);
					System.out.println("You have been logged out.");
					System.exit(0);
				}
				catch(RemoteException e) 
				{
                    e.printStackTrace();
                }
            }
            else 
			{
                try 
				{
                    server.send(ClientName , message);
                }
                catch(RemoteException e) 
				{
                    e.printStackTrace();
                }
            }  
        } 
          
    }
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException 
	{
        Scanner scanner = new Scanner(System.in);
        String clientName = "";
		String serveraddress = "";
		System.out.println("Please enter the server's address (ip or host name): ");
		serveraddress = scanner.nextLine();
		System.out.println("Please enter your username: ");
        clientName = scanner.nextLine();
        System.out.println();
		
		String lookupString = "rmi://" + serveraddress + "/messageservice";

		ChatWriter chatinterface = (ChatWriter)Naming.lookup(lookupString);
        new Thread(new ClientInterface(chatinterface , clientName)).start();
    }
 
}