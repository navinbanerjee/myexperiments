package com.nb;

public class RecursiveFactorial {
	
	public static void main(String[] args) {
		RecursiveFactorial recurFact = new RecursiveFactorial();
		
		System.out.println(recurFact.factorial(5));
	}

	private int factorial(int i) {
		int fact = 0;
		
		if(i == 1)
			return i;
		
		fact = i * factorial(i-1);
		
		return fact;
	}

}
