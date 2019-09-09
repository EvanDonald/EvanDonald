
public class TreeNode implements Comparable<TreeNode>
{
	char c;
	int count;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(char c, int count)
	{
		this.c = c;
		this.count = count;
	}
	
	public TreeNode(int count, TreeNode left, TreeNode right)
	{
		this.count = count;
		this.left = left;
		this.right = right;
	}

	public int compareTo(TreeNode n2)
	{
		if (count < n2.count)
		{
			return -1;
		}
		if (count > n2.count)
		{
			return 1;
		}
		return 0;
		
	}

	
	
}
