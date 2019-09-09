
public class ASTAssign extends ASTStatement
{
	ASTVar var;
	ASTExpression exp;
	
	public String convertToJava()
	{
		String tab = tablevel();
		
		return tab + var.convertToJava() + " = " + exp.convertToJava() + ";" + '\n';
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
