package edu.miracosta.cs113;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Driver {

	public static void main(String[] args) throws Exception {
		int mainMenu = 0;
		boolean exit = false;
		Scanner keyboard = new Scanner(System.in);
		MorseCodeTree mct = new MorseCodeTree();
		System.out.println("Welcome to the Morse Code Program!");
		
		do {
			System.out.println("Please select a choice from the following options");
			System.out.println("1.Output all Morse code with their respective letters ");
			System.out.println("2.Enter Test Output file to be translated");
			System.out.println("3.Decode Morse Code");
			System.out.println("4.Exit Program");
			mainMenu = keyboard.nextInt();
			
			switch(mainMenu) {
			case 1:
				System.out.println(mct);
				System.out.println("a: *-\nb: -***\nc: -*-*\nd: -**\ne: *\nf: **-*\ng: --*\nh: ****\ni: **\nj: *---\nk: -*-\nl: *-**\nm: --\nn: -*\no: ---\np: *--*\nq: --*-\nr: *-*\ns: ***\nt: -\nu: **-\nv: ***-\nw: *--\nx: -**-\ny: -*--\nz: --**");
				break;
			case 2:				
				Scanner file = null;
				String fileName = "";
				StringBuilder sb = new StringBuilder();
		    	try {
		    	System.out.println("Please enter the file you want translated");
		    	fileName = keyboard.nextLine();
		    	File text = new File(fileName);
		    	file = new Scanner(text);
		    	} catch (FileNotFoundException o) {
		    		System.out.print("Error");
		    	}
		    	while(file.hasNext()) {
		    		String seq = file.nextLine();
		    		sb.append(mct.translateFromMorseCode(seq));
		    	}
		    	System.out.print(sb);
			case 3://Good
				System.out.println("What String would you like translated?");
				Scanner input = new Scanner(System.in);
				String output = input.nextLine();
				String results = mct.translateFromMorseCode(output);
				System.out.println(results);
				break;
			
			case 4:
				System.out.println("Thank you for using my Morse Code Program!");
				exit = true;
				break;
		
			}
	}while(exit==false);
		keyboard.close();
	}
}

