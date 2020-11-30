package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {

		public static void main(String[] args) {
			try {
				File file = new File("C:/Users/Deedl/eclipse-workspace/Homework6/src/edu/miracosta/cs113/change/CoinCombinations.txt");//This is the file path where the Change combinations will be calculated
	    		PrintWriter printWriter = new PrintWriter(file);//Creates a FileWriter to write the coin combinations to the file

			
			    ArrayList<Integer> coins = new ArrayList<>();//This will be used to hold the coin combinations 
			    int[] amounts = { 1, 5, 10, 25};//An array of the Different Coin combinations; Penny, Nickle, Dime, and Quarters
			    calculateChange(coins, amounts, 0, 0, 75, printWriter);//Calls the recursive method to calculateChange Combinations. (Coins is an Arraylist to hold the combinations, amount is the currency combinations
			    //high is used in the recursive call , total is used in the recursive method to keep track of the total amount of value thats added, and the printWriter is needed to call the printCombinationsToFile 
			    printWriter.close();//Closes the PrintWriter when its done
			
			  
			}catch (FileNotFoundException ex){//Catches a file not found exception	
        	System.out.print("Error, cannot find file");
        	}
    			   
		}
		
    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    	  public static void calculateChange(ArrayList<Integer> coins, int[] amounts, int high, int total, int goal,PrintWriter keyboard) {
    		  if (total == goal) {//We reached our goal, now we can print the numbers into the file
    	        	printCombinationsToFile(coins, amounts,keyboard);//This calls the printCombinationsToFile method, which will format it neatly by its CoinCombanations
    	            return;//We will return nothing, since the printCombinationsToFile is actually writing the different coin combinations. This will still help exit the recursive call since it is a base case
    	        }
    	        if (total > goal) { //Returns if the Sum is greater than the goal, since you aren't able to have negative coins
    	            return; //This will return nothing, since it is not a correct CoinCombanation. It will still help exit the recursive call since it is a base case
    	        }
    	         
    	        for (int value : amounts) {//This will loop through every amount in the Array amounts, so 1,5,10,25. This will also call the recursive method multiple times
    	            if (value >= high) {//This means that there is room to take another coin from the total, so we need to call the recursive method again
    	                ArrayList<Integer> coins2 = new ArrayList<>();//Creates a new Array list for coins, This way we can hold the coin combinations for each recursive call
    	                coins2.addAll(coins); //This adds all the coin combinations from the previous recursion call, to keep track of the total number of combinations
    	                coins2.add(value);  //This adds another coin amount to the Arraylist, the one in which we are adding to the total value of coins in the Array List
    	                calculateChange(coins2, amounts, value, total + value, goal,keyboard);//Takes in the Coins2 array, which in the next recursive call be coins. Takes in amounts, which is the array of currency,
    	                //Value, which is the coin that we added during the recursive calls, total, which is the sum of the previous recursion calls plus the new value of this recurive call, goal, which is the total amount
    	                //we are trying to achieve, and last keyboard, which will be used for the printCombinationsToFile method
    	            }
    	        }
    	    }

    	    public static void printCombinationsToFile(ArrayList<Integer> coins, int[] amounts,PrintWriter keyboard) {
            		String[] names = {"Penny:", "Nickle:", "Dimes:", "Quarters:"};  //Used for Formatting the coins will the actual coin name next to it.     
            		keyboard.println(" "); //Since this method will be called multiple times during the recursive call, this will help seperate each of the calls 
            		for (int amount : amounts) {//For each integer in the amounts array, which is 1,5,10,25
    	    	         int count = 0;//We will need to count how many times a certain coin shows up in the coins Array list, that way we can print it 
    	    	            for (int coin : coins) {//This loop is used to count how many times a specific coin is in the Arraylist, so we can calculate the total amount of coins in the Coin Combanation
    	    	                if (coin == amount) {//If the coin matches 1,5,10,25 , then it will keep track of the total number of currency values that are in the coins Arraylist. Ex) [1,1,1,1,5] will be 4 pennies and 1 nickle
    	    	                    count++;
    	    	                }
    	    	            }
    	    	            if (amount == 1) {
    	    	            keyboard.print(names[0]);//Writes Pennies into the file
    	    	            keyboard.println(count);//Adds the total amount of pennies that showed up in the recursive call  
    	    	          }else if (amount == 5) {
    	    	        	  keyboard.print(names[1]);//Writes Nickles into the file
    	    	        	  keyboard.println(count);//Adds the total amount of nickles that showed up in the recursive call  
    	    	          }
    	    	          else if (amount == 10) {
    	    	        	  keyboard.print(names[2]);//Writes Dimes into the file
    	    	        	  keyboard.println(count);//Adds the total amount of dimes that showed up in the recursive call  
    	    	          }
    	    	          else if (amount == 25) {
    	    	        	  keyboard.print(names[3]);//Writes Quarters into the file
    	    	        	  keyboard.println(count);//Adds the total amount of quarters that showed up in the recursive call  
    	    	          }
    	    	        }
   	    	        	    	
    
 }
}

 // End of class ChangeCalculator