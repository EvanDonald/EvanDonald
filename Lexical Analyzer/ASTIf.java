import java.util.ArrayList;

public class ASTIf extends ASTStatement
{
	
	ASTCondition cond = new ASTCondition();
	ArrayList<ASTStatement> statementlist = new ArrayList<>();
	
	
	public String convertToJava()
	{
		String tab = tablevel();
		
		String iff = "";
		iff = tab + "if (" + cond.convertToJava() + ")" + '\n';
		iff = iff + tab + "{" + '\n';
		ASTNode.tablevel++;
		for (int i = 0; i < statementlist.size(); i++)
		{
			iff = iff + statementlist.get(i).convertToJava();
		}
		ASTNode.tablevel--;
		iff = iff + tab + "}" + '\n';
		return iff;
	}
	
	public String tablevel()
	{
		String tab = "";
		for (int i = 0; i < ASTNode.tablevel; i++)
		{
			tab = tab + "\t";
		}
		return tab;
	}
	
}

