/*
 * Name: Kevin Dalle
 * Copyright 9-20-16 by Kevin Dalle
 */

/**
 *
 * @author kwdalle
 */

import java.util.StringTokenizer;

public class LL1 {

	public static StringTokenizer st;
	public static String token;

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {
	        String expression = args[0];
        	
		expression = expression + "$";
	        System.out.println(expression);

		st = new StringTokenizer(expression,"+-*/()$", true);
		token = st.nextToken();
		E(); // Begins the process of parsing the string.
		if(token.equals("$")){
			System.out.println("Yes");
		}
		else
		{
			fail();
		}		 // If the logic below does not exit the 
					// program then it must be valid if its at the
					//the end of file.
    	}
	// There is a rule for every non terminal symbol
	public static void E(){
		if (isANumber(token)){
			T();
			ePrime(); // This is calling rule 1
		}else{
			switch (token){ // The switch statements are designed based on the 
					// parse table
				case "+":
					fail(); // Fails occur when there is not a rule to 
						// handle this situation, meaning the exp-
						// ression is not valid.
					break;
				case "-":
					fail();
					break;
				case "*":
					fail();
					break;
				case "/":
					fail();
					break;
				case "(":
					T();
					ePrime(); // Again calling rule 1
					break;
				case ")":
					fail();
					break;
				case "$":
					fail();
					break;
				}
					 
		}
	}

	public static void ePrime(){
		if (isANumber(token)){
			fail();
		}else{
			switch (token){
				case "+": // Calling rule 2
					token = st.nextToken();
					T();
					ePrime();
					break;
				case "-": // Calling rule 3
					token = st.nextToken();
					T();
					ePrime();
					break;
				case "*":
					fail();
					break;
				case "/":
					fail();
					break;
				case "(":
					fail();
					break;
				case ")": // Rule 4
					break;
				case "$": // Rule 4
					break;
				}
			}
	}

	public static void T(){
		if (isANumber(token)){
			F(); // Rule 5
			tPrime();
		}else{
			switch (token){
				case "+":
					fail();
					break;
				case "-":
					fail();
					break;
				case "*":
					fail();
					break;
				case "/":
					fail();
					break;
				case "(": // Rule 5
					F();
					tPrime();
					break;
				case ")":
					fail();
					break;
				case "$":
					fail();
					break;
				}	
		}
	}

	public static void tPrime(){
		if (isANumber(token)){
			fail();
		}else{
			switch (token){
				case "+": // Rule 8
					break;
				case "-": // Rule 8
					break; 
				case "*": // Rule 6
					token = st.nextToken();
					F();
					tPrime();
					break;
				case "/": // Rule 7
					token = st.nextToken();
					F();
					tPrime();
					break;
				case "(":
					fail();
					break;
				case ")": // Rule 8
					break;
				case "$": // Rule 8
					break;
				}	
		}
	}

	public static void F(){
		if (isANumber(token)){ // Rule 10
			token = st.nextToken();
		}else{
			switch (token){
				case "+":
					fail();
					break;
				case "-":
					fail();
					break;
				case "*":
					fail();
					break;
				case "/":
					fail();
					break;
				case "(": // Rule 9
					token = st.nextToken();
					E();
					if(!token.equals(")")){
						fail();
					}
					else{
						token = st.nextToken();
					}
					break;
				case ")":
					fail();
					break;
				case "$":
					fail();
					break;
				}	
		}
	}

	public static boolean isANumber(String num){
		// Determines whether or not the given string is a number
		try{
			int n = Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	public static void fail(){
		// Prints false and ends the program if the expression is invalid
		System.out.println("No");
		System.exit(1);
	}
}
    
    
