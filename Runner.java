public class Runner
{
	public static void main(String[] args)
	{
	/**
	       7
	      /  \
	     B     A
	    / \   / \
	   4   6  E  2
	  /	\    / 
	 5 	 C   9
	   7(B(4(5, C), 6), A(E(9, blank), 2)
	   */
		BinaryTree<String> tester = new BinaryTree<String>("7", new BinaryTree<String>("B"), new BinaryTree<String>("A"));
		tester.left().setLeft(new BinaryTree<String>("4"));
		tester.left().setRight(new BinaryTree<String>("6"));
		tester.right().setLeft(new BinaryTree<String>("E"));
		tester.right().setRight(new BinaryTree<String>("2"));
		tester.left().left().setLeft(new BinaryTree<String>("5"));
		tester.left().left().setRight(new BinaryTree<String>("C"));
		tester.right().left().setLeft(new BinaryTree<String>("9"));
		System.out.println(tester);
		System.out.println(tester.isFull());
		System.out.println(tester.isComplete());
		System.out.println(tester.isBalanced());
		for(String s: tester)
			System.out.print(s);
		System.out.println("");
	}
}