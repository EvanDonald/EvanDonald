import java.util.ArrayList;

public class ASTLoop extends ASTStatement
{
	ArrayList<ASTStatement> statementlist = new ArrayList<>();
	ASTCondition cond = new ASTCondition();
	
	public String convertToJava()
	{
		String tab = tablevel();
	
		String loop = "";
		loop = tab + "do" + '\n';
		loop = loop + tab + "{" + '\n';
		ASTNode.tablevel++;
		for (int i = 0; i < statementlist.size(); i++)
		{
			loop = loop + statementlist.get(i).convertToJava();
		}
		ASTNode.tablevel--;
		loop = loop + tab + "}" + '\n';
		loop = loop + tab + "while (" + cond.convertToJava() + ");" + '\n';
		return loop;
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
