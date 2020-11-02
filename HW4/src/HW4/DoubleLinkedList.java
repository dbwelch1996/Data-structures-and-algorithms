package HW4;

import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    	private Node<E> head = null;   // points to the head of the list
    	private Node<E> tail = null;   //points to the tail of the list
    	private int size = 0;    // the number of items in the list
  
  public void add(int index, E obj)
   { // Fill Here 
	  ListIter iter = new ListIter(index);
	  iter.add(obj);
	 }
 
  public void addFirst(E obj) { // Fill Here 
	  ListIter iter = new ListIter(0);
	  iter.set(obj);
	  size ++;
      
  }
  public void addLast(E obj) { // Fill Here
	  Node<E> addedNode = new Node<E> (obj);
	  addedNode.prev = tail;
	  tail.next = addedNode;
	  addedNode = tail;
	  addedNode.next = null;
	  size++;
  }

  public E get(int index)  {
	   if (index < 0 || index > size) {     
  	throw new IndexOutOfBoundsException("Invalid index"); 
    }   
	ListIter iter = new ListIter(index);
	return iter.next();
  }  
  
  public E getFirst() {
	  return head.data;  
	  }
  public E getLast() {
	  return tail.data; 
	  }

  public int size() {
      return size;
      }
	  
  public E remove(int index)
  {     E returnValue = null;
  		ListIter iter = new ListIter(index);
        if (iter.hasNext()) {   
        	returnValue = iter.next();
            iter.remove();
        }
        else { 
        	throw new IndexOutOfBoundsException();  
        	}
        return returnValue;
  }
  
  public void clear() {
  	head = null;
  	tail = null;
  	size = 0;
  }
  public Iterator<E> iterator() { 
	  return new ListIter(0); 
	  }
  public ListIterator<E> listIterator() { 
	  return new ListIter(0); 
	  }
  public ListIterator<E> listIterator(int index){
	  return new ListIter(index);
  }
  public ListIterator<E> listIterator(ListIterator<E> iter)
  {     
	  return new ListIter( (ListIter) iter);  
	  
  }

  // Inner Classes
  private static class Node<E>
  {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
        { 
        	data = dataItem;  
        	}
  }  // end class Node

  
  
  public class ListIter implements ListIterator<E> 
  {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   // 

    public ListIter(int i)  // constructor for ListIter class
    {   if (i < 0 || i > size)
        {     
    	throw new IndexOutOfBoundsException("Invalid index " + i); 
        }
        lastItemReturned = null;
 
        if (i == size)     // Special case of last item
        {    
        	index = size;     
        	nextItem = null;    
        	}
        else          // start at the beginning
        {  
        	nextItem = head;
            for (index = 0; index < i; index++) {  
            	nextItem = nextItem.next;   
            }
        }// end else
    }  // end constructor

    public ListIter(ListIter other)
    {   
    	nextItem = other.nextItem;
        lastItemReturned = other.lastItemReturned;
    	index = other.index;  
    }

    public boolean hasNext() {  
    	return nextItem != null;
    	}
    	
    // Fill Here
    public boolean hasPrevious()
    {
    	if (size== 0) {
    	return false;
    	}
    	return (nextItem == null && size != 0) || nextItem.prev != null;
     }
    

    
    public int previousIndex() {  
    	return index-1;
    	} // Fill Here
    
    
    
    public int nextIndex() {
    	return index;  
    	} // Fill here
    
    
    
    public void set(E o)  { 
           if (lastItemReturned != null) 
            {
                lastItemReturned.data = o;
            } 
            else 
            {
                throw new IllegalStateException();
            }
        }  // not implemented
    	
   // not implemented
    public void remove(){
    if(lastItemReturned == null) {	
    throw new IllegalStateException();
    }
    
    if (lastItemReturned == head) {//if we are trying to remove the head
    		head = nextItem;
    		head.prev = null;
    		
    	}
    else if(lastItemReturned == tail) {//trying to remove the tail
    		lastItemReturned.prev.next = null;
    		tail = lastItemReturned.prev;
    		tail.next = null;

    	}
    else if (lastItemReturned != head && lastItemReturned != tail){
    		lastItemReturned.next.prev = lastItemReturned.prev;
    		lastItemReturned.prev.next = lastItemReturned.next;
    	}	
    		size--;
    		index--;
    }
    public E next()
    {  
        if(!(hasNext())) {//if there is no next item, this method is invalid
        	throw new NoSuchElementException();
        }
        else {

        	lastItemReturned = nextItem;//This moves the previous item up one spot
        	nextItem = nextItem.next;//this moves the next item up one spot
        	index ++;
        	return lastItemReturned.data;//Since the lastItemReturned changed to the next item(which we need to return), lastItemReturn is whats next when the method is called
        			
        }//good
    }

    public E previous() {  
    	if (!(hasPrevious())) {
    		 throw new NoSuchElementException();
    		 }
    		 if (nextItem == null) { // Iterator is past the last element
    		 nextItem = tail;
    		 } else {
    		 nextItem = nextItem.prev;
    		 }
    		 lastItemReturned = nextItem;
    		 index --;
    		 return lastItemReturned.data;
    }//good
    

    public void add(E obj) {
    	 if (head == null) { // Add to an empty list.
    		 head = new Node<E>(obj);
    		 tail = head;
    	}
    else if (nextItem == head){ // Insert at head.
    	 // Create a new node.
    	 Node<E> newNode = new Node<E>(obj);
    	 // Link it to the nextItem.
    	 newNode.next = nextItem;  // Step 1
    	 // Link nextItem to the new node.
    	 nextItem.prev = newNode;  // Step 2
    	 // The new node is now the head.
    	 head = newNode; // Step 3f
    	}
    else if (nextItem == null) { // Insert at tail.
    	Node<E> newNode = new Node<E>(obj);
    	tail.next = newNode;
    	newNode.prev = tail;
    	tail = newNode;
    }
    	
    else {	// Create a new node.
    	 Node<E> newNode = new Node<E>(obj);
    	 // Link the tail to the new node.
    	 newNode.prev = nextItem.prev; // Step 1
    	 nextItem.prev.next = newNode; // Step 2
    	 // The new node is the new tail.
    	 newNode.next = nextItem;
    	 nextItem.prev = newNode;
  }//good
    	 size++;
    	 index ++;
    	 lastItemReturned = null;
  }
  
  
  }// end of inner class ListIter
}// end of class DoubleLinkedList
