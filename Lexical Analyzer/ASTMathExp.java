
public class ASTMathExp extends ASTExpression
{
	ASTSimpleExp simpleexp;
	String operator;
	ASTExpression expression;
	
	public String convertToJava()
	{
		String mathexp = "";
		mathexp = simpleexp.convertToJava() + " " + operator + " " + expression.convertToJava();
		return mathexp;
	}
}
