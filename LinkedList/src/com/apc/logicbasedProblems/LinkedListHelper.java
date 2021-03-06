
package com.apc.logicbasedProblems;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListHelper<T> {

	private Node first;
	private Node last;
	private int count;
	private class Node{
		T item;
		Node next;
	}
	
	
	public boolean isEmpty(){
		return getFirst()==last && getFirst()==null;
	}
	
	public boolean removeItem(T item,boolean removeFromFirst){
		if(!isEmpty()){
			return false;
		}
		
		if(removeFromFirst){
			Node temp = getFirst().next;
			getFirst().next = null;
			setFirst(temp);
		}else{
			

		}
		
		count--;
		return true;
	}
	
	public void printList( Node node){
		Node temp =node==null?getFirst():node;
		
		while(temp != null){
			System.out.print(temp.item+" ");
			temp=temp.next;
		}
	}
	
	public Iterator<T> iterator(){
		return new LLIterator();
	}
	
	
	private class LLIterator implements Iterator<T>{

		Node head=first;
		private int expectedCount = count;
		
		@Override
		public boolean hasNext() {
			return head!=null;
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			checkConcurrentModification();
			T item = head.item;
			head = head.next;
			return item;
		}

		private void checkConcurrentModification() {
			if(expectedCount != count)
				throw new ConcurrentModificationException();
		}

		@Override
		public void remove() {
		}
		
	}

	public void makeCircularAt(int position){
		Node temp = first;
		for(int i=1;i<position;i++){
			temp =temp.next;
		}
		last.next=temp;
	}
	
	public void addItem(T item,boolean addToFirst){
		Node node = new Node();
		node.item = item;
		if(isEmpty()){
			setFirst(last=node);
		}else if(addToFirst){
			node.next = getFirst();
			setFirst(node);
		}else{
			Node temp = last;
			temp.next = node;
			last = node;
		}
		count++;
	}

	public int getSize(){
		return count;
	}
	
	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

}
