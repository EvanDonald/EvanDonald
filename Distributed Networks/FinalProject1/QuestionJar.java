public class QuestionJar 
{  
    private String question;
    private boolean empty = true;
	private boolean first = true;
	

    public synchronized String getquestion(String answer, String correct, int score) 
	{ 
		if (!first)
			return "Faster next time!";
		
        while (empty) 
		{ 
            try 
			{ 
                wait(); 
            } 
			catch (InterruptedException e) { } 
        } 
		
		
		if (!answer.equals(correct))
			return "Wrong";
		else
		{
			first = false;
			score = score + 1;
			empty = true;
			notifyAll();
			String newScore = Integer.toString(score);
			String returnString = "Correct, Your score is: " + newScore;
			return returnString;
		}
        
    }
	
    public synchronized void putquestion(String q) 
	{ 
        while (!empty) 
		{ 
            try 
			{ 
                wait(); 
            } 
			catch (InterruptedException e) { } 
        } 
        empty = false; 
		first = true;
		question = q;
		System.out.println("Put a new question into pool.");
		notifyAll();
    } 
	
    public synchronized String returnquestion() 
	{
		return question;
	}
	public synchronized boolean returnempty() 
	{
		return empty;
	}
		
} 

