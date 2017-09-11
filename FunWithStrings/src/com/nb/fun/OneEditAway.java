package com.nb.fun;

import java.util.HashMap;
import java.util.Iterator;

public class OneEditAway {
	
	public static void main(String[] args) {
		String s= "abcd";
		String s2= "abcde";
		
		char c[] = s.toCharArray();
		int i =0;
		
		for(char ch: c){
			if(!s2.contains(c.toString())){
				i++;
				if(i>1){
					break;
				}
			}else{
				
			}
		}
		if(i>1){
			System.out.println("more edit");
		}else{
			if(i==1){
				System.out.println("one edit");
			}
		}
	
		HashMap map = new HashMap();
		
		Iterator itr = map.entrySet().iterator();
		itr.re
				
		
		
	}
	

}
