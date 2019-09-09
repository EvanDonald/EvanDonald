import java.util.ArrayList;

public class ASTProgram extends ASTNode
{
	
	ArrayList<ASTStatement> statementArray = new ArrayList<ASTStatement>();

	
	public String convertToJava()
	{
		String x = "";
		for (int i = 0; i < statementArray.size(); i++)
		{
			x = x + statementArray.get(i).convertToJava();
		}
		
		return x;
	}
}

