public class Token{

	public static final int DECLARE = 0;
	public static final int VAR = 1;
	public static final int AS = 2;
	public static final int INTEGER = 3;
	public static final int ASSIGN = 4;
	public static final int NUMBER = 5;
	public static final int PRINT = 6;
	public static final int IF = 7;
	public static final int LT = 8;
	public static final int LTE = 9;
	public static final int EQ = 10;
	public static final int GT = 11;
	public static final int GTE = 12;
	public static final int NOTEQ = 13;
	public static final int THEN = 14;
	public static final int LOOP = 15;
	public static final int UNTIL = 16;
	public static final int LET = 17;
	public static final int PLUS = 18;
	public static final int MINUS = 19;
	public static final int TIMES = 20;	
	public static final int DIV = 21;
	public static final int MOD = 22;
	public static final int ENDIF = 23;
	public static final int EOF = 24;
	public static final int ERROR = 25;
	
   public int code;
   public int integer;
   public String string;

   public Token(int newCode){
      code = newCode;
      integer = 0;
      string = "";
   }
   
   public Token(int newCode, String s){
	      code = newCode;
	      integer = 0;
	      string = s;
	   }

   public String toString()
   {	   
      String s = "Code    = " + CODES[code];
      if (code == NUMBER)
          s += "\nInteger = " + integer;
      else if (code == VAR)     
      {
          s += "\nString = " + string;
      }
      return s;
   }

   private static final String CODES[] = {"DECLARE", "VAR", "AS", "INTEGER", "ASSIGN", "NUMBER", "PRINT",
		   									"IF", "LT", "LTE", "EQ", "GT", "GTE", "NOTEQ", "THEN",
		   									 "LOOP", "UNTIL", "LET", "PLUS", "MINUS", "TIMES", 
		   									"DIV", "MOD", "ENDIF", "EOF", "ERROR" };
}