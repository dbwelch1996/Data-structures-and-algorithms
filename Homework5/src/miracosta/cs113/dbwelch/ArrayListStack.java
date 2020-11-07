package miracosta.cs113.dbwelch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;

public class ArrayListStack<E> implements StackInterface<E> {

		private List<E> elements;
		
	
		public ArrayListStack() {
			elements = new ArrayList<E>();
		}
		
		@Override
		public E push(E obj) {
			elements.add(obj);
			return obj;
		}

		@Override
		public E peek() {
			if (elements.isEmpty()) {
				throw new EmptyStackException();
		}
			else {
				return elements.get(elements.size() - 1);
			}
		}
	
		@Override
		public E pop() {
			if(elements.isEmpty()) {
				throw new EmptyStackException();
			}
			E returnedObj = elements.get(elements.size() - 1);
			elements.remove(elements.size() - 1);
			return returnedObj;
		}

		@Override
		public boolean empty() {
			return elements.isEmpty();
		}

		public int size() {
			return elements.size();
		}
		
		public String toString() {
			String output = "";
			Iterator<List<E>> iterator = Arrays.asList(elements).iterator();
			while(iterator.hasNext()) {
			output += iterator.next();
			}
			return output;
		}
}
