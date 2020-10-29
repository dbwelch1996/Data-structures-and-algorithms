package edu.miracosta.cs113.hw3;

public class Term implements Comparable<Term>{
	private int coefficient;
	private int exponent;
	
//Full constructor
	public Term(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
//Default Constructor
	public Term() {
		coefficient = 1;
		exponent = 1;
	}
	
//Copy constructor. Creates a default object if the Term o is null, Copies if it has values	
	public Term(Term o) {
		if (o == null) {
			coefficient = 0;
			exponent = 0;
		}
		else {
			coefficient = o.coefficient;
			exponent = o.exponent;
		}
	}
	public Term(String term) {
		int indexOfX = term.indexOf("x");
		int indexOfPower = term.indexOf("^");
		if(term.equals("")) {
			coefficient = 0;
			exponent = 0;
		}
		
		else if (term.contains("x") && !term.contains("^") && indexOfX == 1) {
			if(term.charAt(0) == '-') {
				coefficient = -1;
				exponent = 1;
			}
			else {
				coefficient = 1;
				exponent = 1;
			}
		}
		else if(term.contains("x") && !term.contains("^") && indexOfX != 1) {
			if(term.charAt(0) == '-') {
				coefficient = Integer.parseInt(term.substring(0,indexOfX));
				exponent = 1;
			}
			else {
			coefficient = Integer.parseInt(term.substring(1,indexOfX));
			exponent = 1;
			}
		}	
		else if (indexOfX == 1  && term.contains("^")) {
			if(term.charAt(0) == '-') {
				coefficient = -1;
				exponent = Integer.parseInt(term.substring(indexOfPower + 1));
			}
			else {
			coefficient = 1;
			exponent = Integer.parseInt(term.substring(indexOfPower + 1));
			}
		}
		
		else if(!term.contains("x") && !term.contains("^")) {
			coefficient = Integer.parseInt(term.substring(0));
			exponent = 0;
		}
		
		else {
			coefficient = Integer.parseInt(term.substring(0,indexOfX));
			exponent = Integer.parseInt(term.substring(indexOfPower + 1));
		}
	}
		
//Clone method
	public Term clone() {
		return new Term(this);
	}
	
//Setters/Getter	
	public void setCoefficient(int coefficient) {
		this.coefficient = coefficient;
	}
	
	public void setExponent(int exponent) {
		this.exponent = exponent;
	}
	
	public void setAll(int coefficient, int exponent) {
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	
	public int getCoefficient() {
		return coefficient;
	}
	public int getExponent() {
		return exponent;
	}
//CompareTo method	
	public int compareTo(Term o) {
		int results = 0;
	 if(exponent == o.exponent) {
		 results = 0;
	 }
	 else if (exponent > o.exponent) {
		 results = -1;
	 }
	 else if (exponent < o.exponent) {
		 results = 1;
	 }
		return results;
		}
	
//toString
	public String toString(){
		String output = "";
		//positive coefficient, positive/negitive exponent
		if (coefficient > 1 && exponent != 0 && exponent != 1 ) {
			output =  "+" + coefficient + "x^" + exponent;
		}
		//Negative coefficient, positive/negitive exponent
		else if(coefficient < 1 && coefficient != 0 && coefficient != -1 && exponent != 0 && exponent != 1 ) {
			output = coefficient + "x^" + exponent;
		}
		//positive coefficient, exponent not 1
		else if (coefficient > 1 && exponent == 1) {
			output = "+" + coefficient + "x";
		}
		//coefficient 1, exponenet not 1
		else if (coefficient == 1 && exponent != 1) {
			output = "+x^" + exponent;
		}
		//coefficient -1, exponenet not 1
		else if (coefficient == -1 && exponent != 1) {
			output = "-x^" + exponent;
				}
		//negitive coefficient, exponent not 1
		else if (coefficient < -1 && exponent == 1) {
			output = coefficient + "x";
		}
		//coefficient 1, exponent 1
		else if (coefficient == 1 && exponent == 1) {
			output =  "+x";
		}
		//coefficient -1, exponent 1
		else if (coefficient == -1 && exponent == 1) {
			output =  "-x";
		}
		//Positive Constant
		else if (coefficient > 1 && exponent == 0) {
			output = "+" + coefficient;
		}
		//negitive constant
		else if (coefficient < 1 && exponent == 0) {
			output = coefficient + "";
		}
		
		//negitive constant
		else if (coefficient == 0) {
					output = "";
				}
		return output;
	}
	
//Equals method
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return exponent == term.exponent &&
                coefficient == term.coefficient;
    }
 
}

	
	

