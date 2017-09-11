package com.nb;

import java.util.Scanner;

public class RecursiveFibonacci {
	
	
	
	public static void main(String[] args) {
		RecursiveFibonacci  recurFibo = new RecursiveFibonacci();
//		for (int i = 0; i < 10; i++) {
//			System.out.println(recurFibo.printFiboRecursive(i));
//		}
		Scanner input = new Scanner(System.in);
        int number = input.nextInt();
		recurFibo.printFibo(number);
		input.close();
	}

	private int printFiboRecursive(int i) {
		if(i == 0){
			return 0;
		}
		
		if(i == 1){
			return 1;
		}
		
		int num= printFiboRecursive(i - 1) + printFiboRecursive(i-2);
		return num;
	}
	
	private void printFibo(int i){
		int prev = 0; int next = 1; int result = 0;
		System.out.println(prev);
		System.out.println(next);
		for (int j = 0 ; j < 10; j ++){
			result = prev + next;
			System.out.println(result);
			prev = next;
			next = result;
		}
	}

}
