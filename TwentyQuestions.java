import java.io.*;
import java.util.Scanner;

public class TwentyQuestions
{
	/* The BinaryTree that contains all the data. */
	private static BinaryTree<String> tree;

	/**
	* If the text file is empty it will create a default BinaryTree otherwise it will create a BinaryTree with the information
	* in the text file. Will play a game of twenty questions and will update both the BinaryTree and the text file with
	* any new information it learns
	* @param args	This is not used
	*/
	public static void main(String[] args)
	{
		if (read().equals(""))
		{
			tree = new BinaryTree<String>("Is it alive?", new BinaryTree<String>("rock"), new BinaryTree<String>("cat"));
		}
		else
		{
			tree = new BinaryTree<String>(read().substring(0, read().indexOf("(")));
			createTree(read().substring(read().indexOf("(") + 1, read().length() - 1), tree);
		}
		boolean done = false;
		Scanner keyboard = new Scanner(System.in).useDelimiter("\\n");
		BinaryTree<String> curr = tree;
		String userInput;
		while (!done)
		{
			if (!curr.isLeaf())
			{
				System.out.println(curr.value());
				userInput = keyboard.next();
				if (userInput.toUpperCase().equals("YES"))
					curr = curr.right();
				else
					curr = curr.left();
			}
			else
			{
				System.out.println("Were you thinking of a " + curr.value() + "?");
				userInput = keyboard.next();
				if (userInput.toUpperCase().equals("YES"))
				{
					System.out.println("YAY I WON!");
					done = true;
				}
				else
				{
					done = true;
					System.out.println("What were you thinking of?");
					String newAnswer = keyboard.next();
					if (newAnswer.charAt(1) == ' ')
						newAnswer = newAnswer.substring(2);
					else
						newAnswer = newAnswer.substring(3);
					System.out.println("What is a question that distinguishes your answer from what I guessed?");
					String newQuestion = keyboard.next();
					System.out.println("Is the answer to your question a yes or a no regarding the thing you were thinking of?");
					userInput = keyboard.next();
					String oldAnswer = curr.value();
					curr.setValue(newQuestion);
					if (userInput.toUpperCase().equals("YES"))
					{
						curr.setLeft(new BinaryTree<String>(oldAnswer));
						curr.setRight(new BinaryTree<String>(newAnswer));					
					}
					else
					{
						curr.setLeft(new BinaryTree<String>(newAnswer));
						curr.setRight(new BinaryTree<String>(oldAnswer));	
					}
				}
			}
		}
		write(tree);
	}
	
	/**
	* Reads the text from a file and converts it into a String.
	* @return	The text which is in the file.
	*/
	public static String read()
	{
		String data = "";
		String name = "helper.txt";
		File file = new File(name);	
		Scanner input = null;
		try
		{
			input = new Scanner(file);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(" Cannot open " + name );
			System.exit(1);
		}
		while (input.hasNextLine())
		{
			data += input.nextLine();
		}
		return data;
	}
	
	/**
	* Recursively takes a String of data and constructs a BinaryTree from the given information.
	* @param data	All the information about the BinaryTree
	* @param curr	The current node in the BinaryTree
	*/
	public static void createTree(String data, BinaryTree<String> curr)
	{
		if (data.charAt(commaFinder(data) - 1) != ')')
			curr.setLeft(new BinaryTree<String>(data.substring(0, commaFinder(data))));
		else
		{
			curr.setLeft(new BinaryTree<String>(data.substring(0, data.indexOf("("))));
		 	createTree(data.substring(data.indexOf("(") + 1, commaFinder(data) - 1), curr.left());
		}
		if (data.substring(commaFinder(data)).indexOf("(") == -1)
			curr.setRight(new BinaryTree<String>(data.substring(commaFinder(data) + 1)));
		else
		{
			curr.setRight(new BinaryTree<String>(data.substring(commaFinder(data) + 1, commaFinder(data) + data.substring(commaFinder(data)).indexOf("("))));
			createTree(data.substring(commaFinder(data) + data.substring(commaFinder(data)).indexOf("(") + 1, data.length() - 1), curr.right());
		}
	}
	
	/**
	* Takes in a String which contains two leaves and finds and returns the index of the comma that separates them.
	* @param data	The String that will be searched
	* @return	The index of the comma
	*/
	public static int commaFinder(String data)
	{
		int numOpen = 0;
		int numClose = 0;
		for (int i = 0; i < data.length(); i++)
		{
			if(data.charAt(i) == '(')
				numOpen++;
			if(data.charAt(i) == ')')
				numClose++;
			if (numOpen == numClose && numOpen != 0)
				return i + 1;
			if (data.charAt(i) == ',' && numOpen == 0)
				return i;
		}
		return data.indexOf(",");
	}
	
	/**
	* Writes all the data in the BinaryTree to the text file.
	* @param input	The BinaryTree that will be converted into a line of text.
	*/
	public static void write(BinaryTree<String> input)
	{
		String text = input.toString();
		String filename = "helper.txt";
		PrintWriter outputFile;
  		try
  		{
  			outputFile =
                 new PrintWriter(new FileWriter(filename));
            outputFile.println(text);
            outputFile.close();
    	}
    	catch(IOException e)
		{
			System.out.println("Error creating file");
			System.exit(1);
		}
	}	
	
	/** 
	* Clears all the data from the text file.
	*/
	public static void clear()
	{
		try
		{
			PrintWriter writer = new PrintWriter("helper.txt");
			writer.print("");
			writer.close();
		}
		catch(IOException e)
		{
			System.out.println("Error clearing file");
			System.exit(1);
		}
	}
}