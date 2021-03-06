import java.util.NoSuchElementException;

import java.util.Iterator;

/**
* An post-order Iterator that acts upon a BinaryTree.
*/
public class PostOrderIterator<E> implements Iterator<E>
{
	/** A Queue that holds all Objects within the BinaryTree in post-order. */
	private Queue<E> helper;
	
	/**
	* A constructor that initializes the Queue and calls the function to populate the Queue
	* in the correct order.
	* @param bt	The BinaryTree that will be iterated
	*/
	public PostOrderIterator(BinaryTree<E> bt)
	{
		helper = new LinkedList<E>();
		creator(bt);
	}
	
	/**
	* Populates the Queue with all of the values of the BinaryTree in the correct order
	* @param input	The BinaryTree that will made into a Queue
	*/
	public void creator(BinaryTree<E> input)
	{
		if (input.isLeaf())
			helper.offer(input.value());
		else
		{
			if (input.right() == null)
			{
				creator(input.left());
				helper.offer(input.value());
			}
			else if (input.left() == null)
			{
				creator(input.right());
				helper.offer(input.value());
			}
			else
			{
				creator(input.left());
				creator(input.right());
				helper.offer(input.value());
			}
		}
	}
	
	/**
	* Returns true if there is another E within the Queue, false if there is not another E.
	* @return		True if the there is another E within the Queue, false if there is not one
	*/
	public boolean hasNext()
	{
		return (!helper.isEmpty());
	}
	
	/**
	* Returns the next E within the Queue, and throws a NoSuchElementException if there is not another E in
	* the Queue.
	* @return		The next E within the Queue
	*/
	public E next()
	{
		if (!hasNext())
			throw new NoSuchElementException();
		return helper.poll();
	}
}