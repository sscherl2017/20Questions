import java.util.Iterator;

/** 
 *  This class creates a BinaryTree which will allow storage of an Object of type E
 * and allows many operations to be done upon the BinaryTree such as adding, printing, modifying and iterating.
 * @author Sam Scherl
 * @version 12/14/15
 */
public class BinaryTree<E> implements Iterable<E>
{
	/** The value of the Binary Tree. */
	protected E value;
	
	/** A pointer to the BinaryTree to the left of this BinaryTree. */
	protected BinaryTree<E> left;
	
	/** A pointer to the BinaryTree to the right of this BinaryTree. */
	protected BinaryTree<E> right;
	
	/**
	* A Constructor which initializes the BinaryTree with the given value and the given BinaryTrees to the left
	* and right of it. 
	* @param v	The value of the BinaryTree
	* @param l	The pointer to the BinaryTree to the left
	* @param r	The pointer to the BinaryTree to the right
	*/
	public BinaryTree(E v, BinaryTree<E> l, BinaryTree<E> r)
	{
		this(v);
		setLeft(l);
		setRight(r);
	}
	
	/**
	* A constructor which initializes the BinaryTree with the given value.
	* @param v	The value of the BinaryTree
	*/
	public BinaryTree(E v)
	{
		this();
		value = v;
	}
	
	/**
	* A constructor which initializes the BinaryTree with all class fields as null.
	*/
	public BinaryTree()
	{
		value = null;
		left = null;
		right = null;
	}
	
	/**
	* Returns the BinaryTree to the left.
	* @return	The BinaryTree to the left
	*/
	public BinaryTree<E> left()
	{
		return left;
	}
	
	/**
	* Returns the BinaryTree to the right.
	* @return	The BinaryTree to the right 
	*/
	public BinaryTree<E> right()
	{
		return right;
	}
	
	/**
	* Returns the value of the BinaryTree.
	* @return	The value of the BinaryTree
	*/
	public E value()
	{
		return value;
	}
	
	/**
	* Sets BinaryTree to the left to the given BinaryTree.
	* @param node	The BinaryTree which will be set as the left BinaryTree
	*/
	public void setLeft(BinaryTree<E> node)
	{
		left = node;
	}
	
	/**
	* Sets BinaryTree to the right to the given BinaryTree.
	* @param node	The BinaryTree which will be set as the right BinaryTree
	*/	
	public void setRight(BinaryTree<E> node)
	{
		right = node;
	}
	
	/**
	* Sets the value of the BinaryTree to the given value.
	* @param val	The value which will be set to the value of the BinaryTree
	*/
	public void setValue(E val)
	{
		value = val;
	}
	
	/**
	* Returns true if the BinaryTree is a leaf, false if it is not.
	* @return	True if the BinaryTree is a leaf, false if it is not
	*/
	public boolean isLeaf()
	{
		return (left == null && right == null);
	}
	
	/**
	* Returns the size of the BinaryTree.
	* @return	The size of the BinaryTree
	*/
	public int size()
	{
		if (isLeaf())
			return 1;
		else
		{
			if (left == null)
				return 1 + right.size();
			else if (right == null)
				return 1 + left.size();
			return 1 + left.size() + right.size();
		}
	}
	
	/**
	* Returns an Iterator of the BinaryTree.
	* @return	An Iterator of the BinaryTree
	*/
	public InOrderIterator<E> iterator()
	{
		return inorderIterator();
	}
	
	/**
	* Returns an in-order Iterator of the BinaryTree.
	* @return	An in-order Iterator of the BinaryTree
	*/
	public InOrderIterator<E> inorderIterator()
	{
		return new InOrderIterator<E>(this);
	}
	
	/**
	* Returns an pre-order Iterator of the BinaryTree.
	* @return	An pre-order Iterator of the BinaryTree
	*/
	public PreOrderIterator<E> preorderIterator()
	{
		return new PreOrderIterator<E>(this);
	}
	
	/**
	* Returns an post-order Iterator of the BinaryTree.
	* @return	An post-order Iterator of the BinaryTree
	*/
	public PostOrderIterator<E> postorderIterator()
	{
		return new PostOrderIterator<E>(this);
	}
	
	/**
	* Returns a String representation of the BinaryTree.
	* @return	A String representation of the BinaryTree
	*/
	public String toString()
	{
		if (isLeaf())
			return value + "";
		else
		{
			if (right == null)
				return value + "(" + left.toString() + ",)";
			else if (left == null)
				return value + "(," + right.toString() + ")";
			return value + "(" + left.toString() + "," + right.toString() + ")";
		}
	}
	
	/**
	* Returns the height of the BinaryTree.
	* @return	The height of the BinaryTree
	*/
	public int height()
	{
		if (isLeaf())
			return 0;
		else
		{
			if (left == null || (right != null && right.height() >= left.height()))
				return 1 + right.height();
			return 1 + left.height();
		}
	}
	
	/**
	* Returns true if the BinaryTree is full, false if it is not.
	* @return	True if the BinaryTree is full, false if it is not
	*/
	public boolean isFull()
	{
		if (isLeaf())
			return true;
		else
		{
			if (left == null || right == null)
				return false;
			return (left.height() == right.height() && left.isFull() && right.isFull());
		}
	}
	
	/**
	* Returns true if the BinaryTree is complete, false if it is not.
	* @return	True if the BinaryTree is complete, false if it is not
	*/
	public boolean isComplete()
	{
		if (isLeaf())
			return true;
		else
		{
			if (left == null)
				return false;
			else if (right == null)
				return left.height() == 0;
			else if (left.height() - right.height() == 1)
				return left.isComplete() && right.isFull();
			else if (left.height() == right.height())
				return left.isFull() && right.isComplete();
			return false;
		}
	}
	
	/**
	* Returns true if the BinaryTree is balanced, false if it is not.
	* @return	True if the BinaryTree is balanced, false if it is not
	*/
	public boolean isBalanced()
	{
		if (isLeaf())
			return true;
		else
		{
			if (right == null)
				return (left.height() == 0);
			else if (left == null)
				return (right.height() == 0);
			return (Math.abs(right.height() - left.height()) <= 1);
		}
	}
}