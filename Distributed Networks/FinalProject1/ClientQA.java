

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;
        
public class ClientQA 
{
    private String role;
    private Socket clientsocket;
    private BufferedReader stdIn;
    private PrintWriter out;
    private BufferedReader in;
	
    public ClientQA(Socket s) throws IOException
    {
        clientsocket = s;
        stdIn = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(clientsocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        askuser();
    }
    
    private void askuser() throws IOException
    {
        System.out.println("Would you like to be a Questioner (Q) or Answerer (A)? Enter \"Bye.\" to quit.");
        String userInput = stdIn.readLine();
        while (!(userInput.equals("Q") || userInput.equals("A")))
		{
            if (userInput.equals("Bye."))
                quit();
            
            System.out.println("Please enter a valid command.");
            userInput = stdIn.readLine();
        }
        role = userInput;
    }
    
    public void handler() throws Exception
    {
        if (role.equals("Q"))
		{
			out.println("Q");	
            questioner();
		}	
        else if (role.equals("A"))
		{
			out.println("A");	
            answerer();
		}
            
    }
    
    public void questioner() throws IOException
    {
        String question;
		String answer;
		String QandA;
        String feedback;
		int total = 0;
        while (true)
        {
            System.out.println("Please enter your question. Enter \"Bye.\" to quit.");
			question = stdIn.readLine();
			if (question.equals("Bye."))
			{
                out.println(question);
				quitQ(total);
			}
			System.out.println("Please enter the answer.");
			answer = stdIn.readLine();
			QandA = question + "###" + answer;
            out.println(QandA);
			System.out.println("Please wait while the server processes your question.");
			feedback = in.readLine();
			while (!(feedback.equals("Done")))
			{
				feedback = in.readLine();
			}
			if (feedback.equals("Done"))
			{
				total++;
				System.out.println("Done. Your question was accepted by the server.");
			}
			
        }
    }
	
	public void quit() throws IOException
    {
        clientsocket.close();
        System.exit(1);
    }
    
    public void quitQ(int total) throws IOException
    {
		System.out.println("You sent " + total + " questions.");
        clientsocket.close();
        System.exit(1);
    }
	
	public void quitA(int total) throws IOException
    {
		System.out.println("You're final score is " + total + ".");
        clientsocket.close();
        System.exit(1);
    }
    
    public void answerer()throws Exception
    {
        String question;
        String answer;
        String feedback;
		int total = 0;
        while (true)
        {
			question = in.readLine();
			System.out.println("Please wait for a question");
			while (question.equals("noq"))
			{
				question = in.readLine();
			}
			if (!(question.equals("noq")))
			{
                System.out.println("Please answer the following question or enter \"Bye.\" to quit.");
                System.out.println(question);
                answer = stdIn.readLine();
                if(answer.equals("Bye."))
                    quitA(total);
				out.println(answer);
                feedback = in.readLine();
				System.out.println(feedback);
                while(feedback.equals("Wrong"))
                {
					TimeUnit.SECONDS.sleep(2);
                    System.out.println("Your answer is not correct, please try again or enter \"Bye.\" to quit.");
					System.out.println(question);
                    answer = stdIn.readLine();
					if(answer.equals("Bye."))
						quitA(total);
                    out.println(answer);
					question = in.readLine();
                    feedback = in.readLine();
					if (feedback.equals("Faster next time!"))
						System.out.println(feedback);
					else 
					{
						total++;
						System.out.println(feedback);
					}
				}
			}
        }
    }
}
