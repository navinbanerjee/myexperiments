package com.nb.stringmanipulations;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class DistinctCharsInString {
	
	public static void main(String[] args) {
		DistinctCharsInString distinctChars = new DistinctCharsInString();
		
		distinctChars.smallestWindowOfDistinctChars("abccbefdjdaea");
	}
	
	private void smallestWindowOfDistinctChars1(String string){
		Set<Character> characters = new LinkedHashSet<Character>();
		
		char[] charArray = string.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {
		}
	}

	private void smallestWindowOfDistinctChars(String string) {
		Map<Character, Integer> uniqueChars = new LinkedHashMap<Character, Integer>();
		char[] charArray = string.toCharArray();
		String smallestWindow = null;
		char lastCharOfSmallestWindow = '*';
		for(int i = 0; i < charArray.length; i ++){
			if(uniqueChars.containsKey(charArray[i])){
				String temp = null;
				if(smallestWindow ==  null || uniqueChars.size() >= smallestWindow.length()){
					temp = getStringFromUniqueChars(uniqueChars);

					smallestWindow = temp.substring(0, temp.length());
					if(lastCharOfSmallestWindow != '*' && smallestWindow.indexOf(lastCharOfSmallestWindow) != 0 
							&& smallestWindow.indexOf(lastCharOfSmallestWindow) == -1){
						smallestWindow = lastCharOfSmallestWindow + smallestWindow;
					}
				}
			    lastCharOfSmallestWindow  = smallestWindow.charAt(smallestWindow.length()-1);
				uniqueChars.clear();
				if(lastCharOfSmallestWindow != charArray[i]){
					uniqueChars.put(lastCharOfSmallestWindow, i-1);
				}else{
					lastCharOfSmallestWindow = '*';
				}
				uniqueChars.put(charArray[i], i);
			}else{
				uniqueChars.put(charArray[i], i);
			}
			
		}
		if(uniqueChars.size() >= smallestWindow.length()){
			smallestWindow = getStringFromUniqueChars(uniqueChars);
		}
		System.out.println(smallestWindow);
	}

	private String getStringFromUniqueChars(Map<Character, Integer> uniqueChars) {
		String temp = null;
		char[] tempCharArray = new char[uniqueChars.size()];
		
	
		Iterator<Character> itr = uniqueChars.keySet().iterator();
		int i = 0;
		while(itr.hasNext()){
			tempCharArray[i] = itr.next().charValue();
			i++;
		}
		temp = new String(tempCharArray);
		return temp;
	}

}
