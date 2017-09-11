package com.nb.fun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolyMorphTest {
	
	static{
		System.out.println("in static initializer");
	}
	
	{
		System.out.println("in initializer");
	}
	
	public PolyMorphTest() {
		System.out.println("in constructor");
	}
	
	public static void main(String[] args) {
		new PolyMorphTest();
		
		List<String> list = new ArrayList<>();
		
		list.add("a");
		list.add("b");
		list.add("d");
		list.add("c");
		
		Iterator<String> itr = list.iterator();
		while (itr.hasNext()) {
			list.remove(1);
			
		}
	}

}
