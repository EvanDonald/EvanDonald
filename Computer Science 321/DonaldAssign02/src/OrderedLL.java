public class OrderedLL
{
	public class Node
	{
		TreeNode data;
		Node next;

		public Node(TreeNode tn)
		{
			this.data = tn;
		}
	}

	private Node head;
	private int size = 0;
	

	//Complete the code necessary to add a TreeNode in the correct order
	//according to the TreeNode's count property. TreeNode's with 
	//lower counts should be first in the list. If a node already exists
	//in the list with the same count, put the new one in front.
	public void add(TreeNode tn)
	{
		Node newNode = new Node(tn);
		if (head == null)
		{
			head = newNode;
			size++;
		}
		else if (newNode.data.compareTo(head.data) < 0 || newNode.data.compareTo(head.data) == 0)
		{
			newNode.next = head;
			head = newNode;
			size++;
		}
		else 
		{
			//Availability: https://www.cs.ucsb.edu/~franklin/20/assigns/prog2files/MySortedLinkedList.java
			Node after = head.next;
			Node before = head;
			while (after != null)
			{
				if (newNode.data.compareTo(after.data) < 0 || newNode.data.compareTo(after.data) == 0)
				{
					break;
				}
				before = after;
				after = after.next;
			}
			newNode.next = before.next;
			before.next = newNode;
			size++;
			//end of cited code
		}

	}
	
	//Remove one from the front of the queue
	public TreeNode remove()
	{
		TreeNode RemovedTN;
		RemovedTN = head.data;
		head = head.next;
		setSize(getSize() - 1);
		return RemovedTN;
	}
	
	public void Print()
	{
		Node PrintNode = head;
		while (PrintNode != null)
		{
			System.out.print(PrintNode.data.count);
			System.out.print(PrintNode.data.c + " ");
			PrintNode = PrintNode.next;
		}
	}

	public int getSize() 
	{
		return size;
	}

	public void setSize(int size) 
	{
		this.size = size;
	}
	
	
	
}
