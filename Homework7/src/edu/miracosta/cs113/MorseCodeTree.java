package edu.miracosta.cs113;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;
/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

	
	public MorseCodeTree() {
		//Creates a root node with a character that isn't in the Tree, This way we have a foundation when creating the tree
		super(new Node<Character> ('#'));
		//The ReadTextFile method is used to actually create the MorseCodeTree from a file.
		readTextFile();
		
	}
    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws Exception {
    	StringBuilder sb = new StringBuilder();//The String builder is what im using to append each of the data from the Nodes
        Scanner scan = new Scanner(morseCode);//Used to take in the String's and break them up by spaces
    	while(scan.hasNext()) {//While the morseCode String has an input, since it may be broken up by whitespace
            Node<Character> current = root;//Node used to traverse the MorseCodeTree
    		String temp = scan.next();//This parses the Morse code by white space, since the characters are broken up by whitespace 
    		for(char c : temp.toCharArray()) {//I changed the temp to an array of character, using a for each method to go through each individual character in the morseCode
    			
    			if(temp.length()>4)//There is no way for a MorseCode String to have more than 4 characters, since that will be out of the Binary Tree Bounds
    				throw new Exception();
    			if(c != '*' && c != '-') //These are the only two characters that can used to translate morseCode, so we need to catch an exception if the user puts in anything else
    				throw new Exception();
    			
    			if(c == '*') {//If the character in the temp array is a star, we move the traversing node to the left
    			current = current.left;
    			}
    			if(c == '-') {//If the character in the temp array is a dash, we move the traversing node to the right
    			current = current.right;	
    			}
    		}
    		sb.append(current.data);//Once all the characters in the Character array are gone through, this means that the traversing node is on the desired node. we can take the data from the node and add it to the String builder
    		
    	}
    	return sb.toString();//Returns the String builder with the translated Morsecode
    }
   
    
    public BinaryTree<Character> readTextFile() {
    	Scanner file = null;//Creates a new Scanner named file, initialize it to null so its in the scope of the method
    	try {//Put in a try/catch to check if the file is in the working directory
    	File text = new File("C:/Users/Deedl/eclipse-workspace/Homework7/src/edu/miracosta/cs113/MorseCode.txt");//My personal filePath with the file 
    	file = new Scanner(text);//Assigned the Scanner to the text file, that way we can parse through the Text File
    	} catch (FileNotFoundException o) {//If the file is not found in the directory, throw FileNotFound exception
    		System.out.print("Error");
    	}
    	while (file.hasNext()) {//While the File has input
    		String seq = file.nextLine();//Takes in the line from the file, an example would be e * . Its important to note that the file is created in a way to have the tree built from top to bottom, left to right
    		char node = seq.charAt(0);//Takes in the letter from the text file, this way we can assign it to the data of a node 
    		seq = seq.substring(2);//This parses the Morse Code translation, so we know how to create the tree. This replaces the old Seq String since we already created a char to hold the data
    		Node<Character> current = root;//Creates a traversing node starting at the root. 
    		for(int i = 0; i < seq.length(); i++) {//This for loop will travel the length of the seq string, since this will help figure out where to create the Node with the data of node
    			if(seq.charAt(i) == '-') {//If the seq is a -, we will traverse the node to the right
    				if(current.right == null) {//If the node does not have any children, We need to create a child node with the data from node
    					current.right = new Node<Character>(node);//Creates the node with Child data in the correct place if the Parent node doesnt not have any children
    				}
    				current = current.right;//This mean that the Parent code already has the child node, so we just move the traversing node	
    			}
    			if(seq.charAt(i) == '*') {//Same as right, but this traverses left instead
    				if(current.left == null) {
    					current.left = new Node<Character>(node);
    				}
    				current = current.left;
    			}
    		}
    	}
    	
    	return new BinaryTree<Character>(root);//This returns the completed MorseCodeBinaryTree
    }
    

} // End of class MorseCodeTree