
public class ASTNum extends ASTSimpleExp
{
	String ASTnum = "";

	public ASTNum(String ASTnumber)
	{
		ASTnum = ASTnumber;
		super.varOrNum = ASTnumber;
	}
	
	public String convertToJava()
	{
		String number = ASTnum;
		return number;
	}
}
