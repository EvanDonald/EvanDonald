public class CompressionTree
{
	TreeNode root;

	public CompressionTree(TreeNode root)
	{
		this.root = root;
	}

	public void printEncodings()
	{
		printEncodings(root);
	}
	
	private String removeLastChar(String A)
	{
		A = A.substring(0, A.length()-1);
		return A;
	}
	
	String newCode = "";
	
	private void printEncodings(TreeNode tn)
	{
		if (tn != null)
		{
	    	newCode = newCode + "0";
	    	printEncodings(tn.left);
	    	newCode = removeLastChar(newCode);
		    	
		   	newCode = newCode + "1";
			printEncodings(tn.right);
			newCode = removeLastChar(newCode);

			if (tn.c != ' ')
			{
				System.out.println(tn.c);
				System.out.println(newCode);
			}
		}
	}
}

