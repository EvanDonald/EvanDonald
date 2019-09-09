
public class ASTVarDecl extends ASTStatement
{
	ASTVar var;
	ASTExpression exp;
	String eq = "";
		
	public String convertToJava() 
	{
		String tab = tablevel();
		
		String vardecl;
		if (eq.equals(""))
		{
			vardecl = tab + "int" + " " + var.convertToJava() + ";" + '\n';
		}
		else 
			vardecl = tab + "int" + " " + var.convertToJava() + " " + eq + " " + exp.convertToJava() + ";" + '\n';
		return vardecl;
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

