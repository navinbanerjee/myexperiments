package com.nb.exp;

public class ExpTest1 {
	
	public boolean oneEditAway(String one, String two){
		char[] oneArray = one.toCharArray();
		char[] twoArray = two.toCharArray();
		int countOfDiff = 0;
		if(one.length() == two.length()){
			for (int i = 0; i < twoArray.length; i++) {
				if(twoArray[i] != oneArray[i]){
					countOfDiff++;
				}
				if(countOfDiff > 1){
					return false;
				}
			}
		}else if(one.length()-1 == two.length()){
			return oneEditAwayForStringsOfInequalLength(one, two);
		}else if(one.length()+1 == two.length()){
			return oneEditAwayForStringsOfInequalLength(two, one);
		}
		return true;
	}
	
	private boolean oneEditAwayForStringsOfInequalLength(String longer, String shorter){
		char[] longerArray = longer.toCharArray();
		char[] shorterArray = shorter.toCharArray();
		if(!longer.contains(shorter)){
			for (int i = 0; i < longerArray.length; i++) {
				if(i != longerArray.length -1){
					if(longerArray[i] != shorterArray[i]){
						String longerTillEnd = longer.substring(i+1);
						if(!shorter.endsWith(longerTillEnd)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		ExpTest1 test = new ExpTest1();
		System.out.println(test.oneEditAway("abc", "abcd"));
		
		System.out.println("hello world... ");
	}

}
