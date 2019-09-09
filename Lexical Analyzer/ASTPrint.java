
public class ASTPrint extends ASTStatement
{
	ASTExpression exp;
	
	public String convertToJava()
	{
		String tab = tablevel();
		
		return tab + "System.out.println(" + exp.convertToJava() + ");" + '\n';
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
