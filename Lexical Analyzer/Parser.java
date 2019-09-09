import java.util.ArrayList;

public class Parser extends Object
{

	private Chario chario;
	private Scanner scanner;
	private Token token;
	

	public Parser(Chario c, Scanner s)
	{
		chario = c;
		scanner = s;
		this.token = scanner.nextToken();
	}

	public void reset()
	{
		scanner.reset();
		this.token = scanner.nextToken();
	}

	private void accept(int expected, String errorMessage)
	{
		if (this.token.code != expected)
			fatalError(errorMessage);
		this.token = scanner.nextToken();
	}

	private void fatalError(String errorMessage)
	{
		chario.putError(errorMessage);
		throw new RuntimeException("Fatal error");
	}

	public void parse()
	{
		ASTProgram prg = new ASTProgram();
		while (this.token.code != Token.EOF)
		{
			prg.statementArray = statement_list();
		}
		System.out.println(prg.convertToJava());
		
	}

	/*---------------------------------------------------------------
	  statement_list ::= statement { statement }
	----------------------------------------------------------------*/
	private ArrayList<ASTStatement> statement_list()
	{
		ArrayList<ASTStatement> stateList = new ArrayList<ASTStatement>();
		stateList.add(statement());
		while (this.token.code == Token.DECLARE | this.token.code == Token.PRINT | this.token.code == Token.IF | 
															this.token.code == Token.LOOP | this.token.code == Token.LET)
		{
			stateList.add(statement());
		}
		return stateList;
	}

	/*---------------------------------------------------------------
	  statement ->  var-decl
					print-statement
	          		if-statement 
		   			loop-statement
		   			assign-statement
	----------------------------------------------------------------*/
	private ASTStatement statement()
	{
		ASTStatement state = null;
		if (this.token.code == Token.DECLARE)
		{
			this.token = scanner.nextToken();
			state = var_decl();
		}
		else if (this.token.code == Token.PRINT)
		{
			this.token = scanner.nextToken();
			state = print_statement();
		}
		else if (this.token.code == Token.IF)
		{
			this.token = scanner.nextToken();
			state = if_statement();
		}
		else if (this.token.code == Token.LOOP)
		{
			this.token = scanner.nextToken();
			state = loop_statement();
		}
		else if (this.token.code == Token.LET)
		{
			this.token = scanner.nextToken();
			state = assign_statement();
		}
		return state;
	}

	/*---------------------------------------------------------------
	  var-decl ::= DECLARE var AS type [= expression]
	----------------------------------------------------------------*/
	private ASTVarDecl var_decl()
	{
		ASTVarDecl dec = new ASTVarDecl();
		String var = this.token.string;
		ASTVar astvar = new ASTVar(var);
		dec.var = astvar;
		
		accept(Token.VAR, "a VAR is expected");
		accept(Token.AS, "'AS' expected");
		accept(Token.INTEGER, "an INTEGER is expected");
		if (this.token.code == Token.ASSIGN)
		{
			dec.eq = "=";
			accept(Token.ASSIGN, "'=' expected");
			dec.exp = expression();
		}
		return dec;
	}

	/*---------------------------------------------------------------
	  print-statement ::= PRINT expression
	----------------------------------------------------------------*/
	private ASTPrint print_statement()
	{
		ASTPrint print = new ASTPrint();
		ASTExpression exp;
		exp = expression();
		print.exp = exp;
		return print;
	}

	/*---------------------------------------------------------------------------
	  if-statement ::= IF condition THEN statement_list ENDIF
	-----------------------------------------------------------------------------*/
	private ASTIf if_statement()
	{
		ASTIf iff = new ASTIf();
		iff.cond = condition();
		accept(Token.THEN, "'THEN' expected");
		//ASTNode.tablevel++;
		iff.statementlist = statement_list();
		//ASTNode.tablevel--;
		accept(Token.ENDIF, "'ENDIF' expected");
		
		return iff;
	}
	
	/*-------------------------------------------------------------------
	  loop-statement ::= LOOP statement_list UNTIL condition
	---------------------------------------------------------------------*/
	private ASTLoop loop_statement()
	{
		ASTLoop loop = new ASTLoop();
		//ASTNode.tablevel++;
		loop.statementlist = statement_list();
		//ASTNode.tablevel--;
		accept(Token.UNTIL, "'UNTIL' expected");
		loop.cond = condition();
		
		
		return loop;
	}

	/*---------------------------------------------------------------
	  assign-statement ::= LET var = expression
	----------------------------------------------------------------*/
	private ASTAssign assign_statement()
	{
		ASTAssign assign = new ASTAssign();
		String var = "";
		var = this.token.string;
		ASTVar astvar = new ASTVar(var);
		astvar.ASTvar = var;
		assign.var = astvar;
		accept(Token.VAR, "a VAR is expected");
		accept(Token.ASSIGN, "'=' expected");
		assign.exp = expression();
		
		return assign;
	}
	
	/*---------------------------------------------------------------
	  condition ::= expression relop expression
	----------------------------------------------------------------*/
	private ASTCondition condition()
	{
		ASTCondition cond = new ASTCondition();
		ASTExpression exp1;
		ASTExpression exp2;
		ASTRelop relop;
		exp1 = expression();
		relop = relop();
		exp2 = expression();
		
		cond.exp1 = exp1;
		cond.relop = relop;
		cond.exp2 = exp2;
		
		
		return cond;
	}
	
	/*-----------------------------------------------------------------------------------
	  expression ::= simple-expression | simple-expression ( + | - | * | / ) expression 
	------------------------------------------------------------------------------------*/
	private ASTExpression expression()
	{
		ASTSimpleExp simpleexp = null;
		ASTMathExp mathexp = new ASTMathExp();
		
		simpleexp = simple_expression();
		if (this.token.code == Token.PLUS || this.token.code == Token.MINUS || this.token.code == Token.TIMES || this.token.code == Token.DIV || this.token.code == Token.MOD)
		{
			ASTExpression exp = null;
			mathexp.simpleexp = simpleexp;
			mathexp.operator = this.token.string;
			this.token = scanner.nextToken();
			exp = expression();
			mathexp.expression = exp;
			return mathexp;
		}
		return simpleexp;
	}

	/*---------------------------------------------------------------
	  simple-expression ::= var | number
	----------------------------------------------------------------*/
	
	private ASTSimpleExp simple_expression()
	{
		String varOrNum = "";
		int x;
		if (this.token.code == Token.VAR)
		{
			varOrNum = this.token.string;
			ASTVar simplevar = new ASTVar(varOrNum);
			simplevar.ASTvar = varOrNum;
			accept(Token.VAR, "a VAR is expected");
			return simplevar;
		}
		else if (this.token.code == Token.NUMBER)
		{
			x = this.token.integer;
			varOrNum = x + "";
			ASTNum simplenum = new ASTNum(varOrNum);
			simplenum.ASTnum = varOrNum;
			accept(Token.NUMBER, "a NUMBER is expected");
			return simplenum;
		}
		else
			return null;
	}
	
	
	/*---------------------------------------------------------------
	  relop ::= < [>|=] | > [ = ] | = =
	----------------------------------------------------------------*/
	public ASTRelop relop()
	{
		String relop = "";
		if (this.token.code == Token.LT)
		{
			ASTRelop ro = new ASTRelop();
			relop = this.token.string;
			accept(Token.LT, "'<' expected");
			ro.ASTrelop = relop;
			return ro;
		}
		else if (this.token.code == Token.NOTEQ)
		{
			ASTRelop ro = new ASTRelop();
			relop = this.token.string;
			accept(Token.NOTEQ, "'<>' expected");
			ro.ASTrelop = relop;
			return ro;
		}
		else if (this.token.code == Token.LTE)
		{
			ASTRelop ro = new ASTRelop();
			relop = this.token.string;
			accept(Token.LTE, "'<=' expected");
			ro.ASTrelop = relop;
			return ro;
		}
		else if (this.token.code == Token.GT)
		{
			ASTRelop ro = new ASTRelop();
			relop = this.token.string;
			accept(Token.GT, "'>' expected");
			ro.ASTrelop = relop;
			return ro;
		}
		else if (this.token.code == Token.GTE)
		{
			ASTRelop ro = new ASTRelop();
			relop = this.token.string;
			accept(Token.GTE, "'>=' expected");
			ro.ASTrelop = relop;
			return ro;
		}
		else if (this.token.code == Token.EQ)
		{
			ASTRelop ro = new ASTRelop();
			relop = this.token.string;
			accept(Token.EQ, "'=' expected");
			ro.ASTrelop = relop;
			return ro;
		}
		else 
			return null;
	}
	
	

}
