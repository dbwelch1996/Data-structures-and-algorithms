package edu.miracosta.cs113.hw3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Menu {
		public static void main(String[] args) {
			Polynomial polynomialA = new Polynomial();
			Polynomial polynomialB = new Polynomial();
			boolean exit = false;
			Scanner keyboard = new Scanner (System.in);
			int coefficient, exponent, mainMenu, firstChoice,amount;

			
			
			System.out.println("Welcome to the polynomial program!");
			
		do {
			System.out.println("Please select a choice from the following options");
			System.out.println("1.Edit first polynomial");
			System.out.println("2.Edit second polynomial");
			System.out.println("3.Add both polynomials");
			System.out.println("4.Exit Program");
			mainMenu = keyboard.nextInt();
			
			
			switch(mainMenu) {
			case 1:
				System.out.println("Please select a choice from the following options for the first polynomial: ");
				System.out.println("1.Create new polynomials");
				System.out.println("2.Clear the Polynomial ");
				System.out.println("3.Back to main menu ");
				firstChoice = keyboard.nextInt();
				switch(firstChoice) {
				case 1:
					System.out.println("How many terms would you like to add?");
					amount = keyboard.nextInt();
						for (int i = 0; i<=amount; i++) {
							System.out.println("What coefficient would you like to add?");
							coefficient = keyboard.nextInt();
							System.out.println("What exponent would you like to add?");
							exponent = keyboard.nextInt();
							Term nTerm = new Term(coefficient,exponent);
							//polynomialA.add(nTerm);
					}
						break;
				case 2:
					System.out.println("Clearing polynomial");
					polynomialA.clear();
					break;
				case 3:
					System.out.println("Returning to main menu");
					break;
				}
				break;
			case 2:
				System.out.println("Please select a choice from the following options for the second polynomial: ");
				System.out.println("1.Create a new polynomials");
				System.out.println("2.Clear the Polynomial");
				System.out.println("3.Back to main menu ");
				firstChoice = keyboard.nextInt();
				switch(firstChoice) {
				case 1:
					System.out.println("How many terms would you like to add?");
					amount = keyboard.nextInt();
						for (int i = 0; i<=amount; i++) {
							System.out.println("What coefficient would you like to add?");
							coefficient = keyboard.nextInt();
							System.out.println("What exponent would you like to add?");
							exponent = keyboard.nextInt();
							Term nTerm = new Term(coefficient,exponent);
							//polynomialB.add(nTerm);
					}
						break;
				case 2:
					System.out.println("Clearing polynomial");
					polynomialB.clear();
					break;
				case 3:
					System.out.println("Returning to main menu");
					break;
				}
				break;
			case 3:
				System.out.println("Adding both polynomials");
				polynomialA.add(polynomialB);
				break;
			case 4:
				System.out.println("Exiting, thank you for using Polynomial express");
				exit = true;
				break;
			}
			}while(exit !=true);

		}
}
