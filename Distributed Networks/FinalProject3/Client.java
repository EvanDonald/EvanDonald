import java.rmi.*;
import java.util.Scanner;

public class Client 
{
	public static void main(String[] args) throws Exception 
	{
		
		PrimaryServerInterface PSInterface = (PrimaryServerInterface) Naming.lookup("rmi://127.0.0.1/pserver");
		ConnectServer Connection = new ConnectServer(PSInterface);
		Connection.connect();
		StayConnected thread = new StayConnected(Connection);
		thread.start();
		
		Scanner scan = new Scanner(System.in);
		boolean connected = true;
		
		System.out.println("You are now connected to the seat reservation system.");
		
		System.out.println("Please enter your username.");
		String name = scan.nextLine();
		
		while (connected)
		{
			System.out.println("The available seats are listed below.");
			
			printSeats(Connection.getSeats());
			
			System.out.println("Please enter the seat you would like to reserve or enter \"Quit\" to quit.");
			String seat = scan.nextLine();
			
			if (seat.equals("quit") || seat.equals("Quit") || seat.equals("QUIT"))
			{
				connected = false;
			}
			else
			{
				int s = Integer.parseInt(seat);
				
				if (s < 1 || s > 50)
				{
					System.out.println("Please enter a valid seat or enter \"Quit\" to quit.");
					seat = scan.nextLine();
					s = Integer.parseInt(seat);
				}
				
				System.out.println(Connection.reserve(s, name));
				
			}
			
			
			
		}
		
		scan.close();
		thread.quit();
	}
	
	public static void printSeats(boolean[] seats)
	{
		for (int i = 0; i < seats.length; i++)
		{
			
			if (seats[i])
			{
				System.out.println("Reserved");
			}
			else
			{
				System.out.println("Seat "+ (i+1));
			}
	
		}
		
	}
}
