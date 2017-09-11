package com.nb.reverse.linkedlist;

import java.util.LinkedList;

public class ReverseLinkedList {
	
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		
		list = reverseListStartingFromAndEndingAt(list, 2);
		System.out.println(list);
	}
	
	public reverseListIteratively (Node head)
	{
	if (head == NULL || head.next == NULL)
	return;  //empty or just one node in list

	Node Second = head.next;

	//store third node before we change 
	Node Third = Second.next;  

	//Second's next pointer
	Second.next = head;  //second now points to head
	head.next = NULL;  //change head pointer to NULL

	//only two nodes, which we already reversed
	if (Third == NULL)
	return;  

	Node CurrentNode = Third;

	Node PreviousNode = Second;

	while (CurrentNode != NULL)
	{ 
	Node NextNode = CurrentNode.next;

	CurrentNode.next = PreviousNode;

	/*  repeat the process, but have to reset
	     the PreviousNode and CurrentNode
	*/

	PreviousNode = CurrentNode;
	CurrentNode = NextNode;  
	}

	head  = PreviousNode; //reset the head node
	}

	private static LinkedList<String> reverseListStartingFromAndEndingAt(LinkedList<String> list, int i) {
		LinkedList<String> subListToReverse = null;
		if(i > 1){
			subListToReverse = new LinkedList<String>(list.subList(i-1, (list.size() - i + 1)));
		}
		if(subListToReverse.size() > 0){
			
			for(int i1 = 0; i1 < subListToReverse.size() ; i1++){
				if(subListToReverse.size() % 2 == 0 && i1 < subListToReverse.size() / 2){
					String element = subListToReverse.get(i1);
					String elementToBeSwapped = subListToReverse.get(subListToReverse.size() - (i1+1));
					subListToReverse.add(i1, elementToBeSwapped);
					subListToReverse.add(subListToReverse.size() - (i1+1), element);
				}
			}
			
		}
		
		 subListToReverse.addFirst(list.getFirst());
		 subListToReverse.addLast(list.getLast());
		 return subListToReverse;
	}	

}
