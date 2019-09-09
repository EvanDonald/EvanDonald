import java.util.PriorityQueue;

public class Assign02Driver
{

	public static void main(String[] args)
	{
		OrderedLL l1 = new OrderedLL();
		l1.add(new TreeNode('S', 2));
		l1.add(new TreeNode('E', 4));
		l1.add(new TreeNode('I', 4));
		l1.add(new TreeNode('N', 2));
		l1.add(new TreeNode('G', 2));
		l1.add(new TreeNode('B', 1));
		l1.add(new TreeNode('L', 1));
		l1.add(new TreeNode('V', 1));
		
		/*l1.add(new TreeNode('D', 1));
		l1.add(new TreeNode('E', 1));
		l1.add(new TreeNode('H', 1));
		l1.add(new TreeNode('L', 3));
		l1.add(new TreeNode('O', 2));
		l1.add(new TreeNode('R', 1));
		l1.add(new TreeNode('W', 1));*/
		
		
		
		//Complete Step 3 from your assignment directions. You will need to loop
		//until there is only one item remaining in l1. Each iteration, you will
		//remove two from the queue, build the new Tree node, and add back to l1.
	
		while (l1.getSize() > 1)
		{
			TreeNode Temp1 = l1.remove();
			TreeNode Temp2 = l1.remove();
			TreeNode NewNode = new TreeNode(' ', 0);
			NewNode.count = Temp1.count + Temp2.count;
			NewNode.left = Temp1;
			NewNode.right = Temp2;
			l1.add(NewNode);
		}
		
		
		//This is Step 4 which takes the last node from the queue and prints
		//the encodings for each of the characters by traversing the tree.
		
		CompressionTree t = new CompressionTree(l1.remove());
		t.printEncodings(); 
	}

}
