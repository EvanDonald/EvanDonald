
public class ASTCondition extends ASTNode
{
	ASTExpression exp1;
	ASTRelop relop;
	ASTExpression exp2;
	
	public String convertToJava()
	{
		return exp1.convertToJava() + " " + relop.convertToJava() + " " + exp2.convertToJava();
	}
}
