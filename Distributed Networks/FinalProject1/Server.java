
import java.net.*;
import java.io.*;

public class Server 
{
	public static void main(String[] args) throws IOException 
	{

		if (args.length != 1) 
		{
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		try 
		{
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			QuestionJar jar = new QuestionJar();
			while (true) 
			{
				Socket clientSocket = serverSocket.accept();
				Thread handle = new Thread(new ClientHandler(clientSocket, jar));
				handle.start();
			}
		} 
		catch (IOException e) 
		{
			System.out.println(
					"Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}

	private static class ClientHandler implements Runnable 
	{
		private Socket connection;
		private QuestionJar jar;
		private String role;

		PrintWriter out;

		BufferedReader in;

		public ClientHandler(Socket s, QuestionJar j) throws IOException 
		{
			connection = s;
			jar = j;

			out = new PrintWriter(connection.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		}

		public void answerer() throws Exception 
		{
			String question;
			String answer;
			String result;
			boolean empty;
			int score = 0;

			while (true) 
			{
				empty = jar.returnempty();
				if (empty)
					out.println("noq");
				
				if (!empty)
				{
					int num = 0;
					
					synchronized (jar) 
					{
						question = jar.returnquestion();
					}
					
					String q, a;
					int x = 0;
					for (int i = 0; i < question.length(); i++)
					{
						if (question.charAt(i) == '#')
						{
							x = i;
							break;
						}
					}
					q = question.substring(0, x);
					a = question.substring(x+3, question.length());

					while (true)
					{
						out.println(q);

						answer = in.readLine();
						System.out.println("Answer recieved");
						result = jar.getquestion(answer, a, score);
						if (result.equals("Wrong")) 
							out.println(result);
						else if (result.equals("Faster next time!"))
						{
							out.println(result);
							break;
						}
						else
						{
							score++;
							out.println(result);
							break;
						}
					}
				}
			}
		}

		public void questioner() throws IOException 
		{
			String question;
			while (true)
			{
				question = in.readLine();
				System.out.println(question);
				if (question.equals("Bye.")) 
				{
					quit();
					return;
				}
				jar.putquestion(question);
				
				boolean empty = jar.returnempty();
				while (!empty)
				{
					out.println("full");
					empty = jar.returnempty();
					
				}
				out.println("Done");
		
			}
		}

		public void quit() throws IOException 
		{
			connection.close();
		}

		public void run() 
		{
			try 
			{
				if ((role = in.readLine()) != null) 
				{
					System.out.println(role);
					if (role.equals("Q")) 
					{
						System.out.println("New questioning client.");
						questioner();
					} 
					else 
					{
						System.out.println("New answering client.");
						answerer();
					}
				}
			} 
			catch (IOException e) 
			{
				System.out.println("Exception caught when trying to listen on the port or listening for a connection");
				System.out.println(e.getMessage());
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}

		}

	}
}

