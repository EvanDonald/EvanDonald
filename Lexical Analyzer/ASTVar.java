
public class ASTVar extends ASTSimpleExp
{
	String ASTvar = "";
	
	public ASTVar(String ASTvariable)
	{
		ASTvar = ASTvariable.toLowerCase(); 
		super.varOrNum = ASTvariable.toLowerCase();
	}
	
	public String convertToJava()
	{
		String variable = ASTvar;
		variable = variable.toLowerCase();
		return variable;
	}
}
