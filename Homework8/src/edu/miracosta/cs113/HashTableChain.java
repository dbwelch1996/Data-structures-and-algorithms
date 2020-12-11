package edu.miracosta.cs113;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


public class HashTableChain<K,V> implements Map<K,V> {
		
	private LinkedList<Entry<K, V>>[] table; //An Array of linked list, the data type is Entry, which has a key and a value
	private static int numKeys;
	private static final int CAPACITY = 101;
	private static final double LOAD_THRESHOLD = 3.0;
		 // Constructor
	
	public HashTableChain() {
		table = new LinkedList[CAPACITY];
		numKeys = 0;
		 }
	
		 public V get(Object key) {
			 int index = (key.hashCode()) % table.length;
			 if (index < 0)
			 index += table.length;
			 if (table[index] == null)
			 return null; // key is not in the table.
			 // Search the list at table[index] to find the key.
			 for (Entry<K, V> nextItem : table[index]) {
			 if (nextItem.getKey().equals(key))
			 return nextItem.getValue();
			 }
			 // assert: key is not in the table.
			 return null;
			}

		 public V put(K key, V value) {
			 int index = (key.hashCode()) % table.length;
			 	if (index < 0)
			 		index += table.length;//If the mod is 0, that means it evenly divides into the the length of the table, which means that it is the last index of the table.
			 	if (table[index] == null) {
			 // Create a new linked list at table[index].
			 		table[index] = new LinkedList<>();
	            }

			 // Search the list at table[index] to find the key.
			 	for (Entry<K, V> nextItem : table[index]) {
			 // If the search is successful, replace the old value.
			 		if (nextItem.getKey().equals(key)) {
			 // Replace value for this key.
			 V oldVal = nextItem.getValue();
			 nextItem.setValue(value);
			 return oldVal;
			 		}
			 }
			 	
			 // assert: key is not in the table, add new item.
			 table[index].add(new Entry<K, V>(key, value));
			 numKeys++;
			 if (numKeys > (LOAD_THRESHOLD * table.length))
			 rehash();
			 return null;
			}

		 private void rehash() {//Reallocates size
			 //Add all of the Previous tables into an old table, just to save them for when we reallocate	
			 LinkedList<Entry<K, V>>[] oldTable = table;		 		 
			 table = new LinkedList[(int) (table.length * LOAD_THRESHOLD)];		
			 numKeys= 0;
			 for(LinkedList<Entry<K,V>> list: oldTable) {
				 if(list != null) {			 
				for(Entry<K,V> ent : list) {
					put(ent.getKey(), ent.getValue());
					}
				 }
			   }		 	 		 		
			}	 
	 		 
		 
		 public int size() {
			 return numKeys;
			 }
	
		@Override
		public boolean isEmpty() {
			return (numKeys == 0);
		}

		@Override
			public boolean containsKey(Object key) {
				int index = key.hashCode() % table.length;
				if(table[index] == null) {
					return false;
				}
				else {
				for(int i = 0; i<table[index].size(); i++) {
					if(table[index].get(i).getKey().equals(key)) {
						return true;
					}
				}
			}
				return false;
			}

		
		
		@Override
		public boolean containsValue(Object value) {
			boolean flag = false;//Keep track if the key is inside of the Linked List's Entries
			for (LinkedList<Entry<K,V>> l : table) {//For each LinkedList inside of the Table Array
				if(l != null) {//If the LinkedList is null, we skip it
				for(Entry<K,V> e : l) {//For each entry inside of the Linked List
					if(e.getValue().equals(value)) {//If the Entry's key is the same as the parameter key
						flag = true;//The entry contains the key, we need to return true
					}
				}
			}
		}	
			return flag;//Returns if it has the key or not
		}
		@Override
		public V remove(Object key) {//Hashcode is method that is inherited 
			int index = (key.hashCode()) % table.length;//Gets the hashcode Value
			Entry<K,V> entry = new Entry<K, V>();//Creates default entry so we have it inside of the scope of the method
			if(table[index] == null) {
				return null;
			}
			for(int i = 0; i<table[index].size(); i++) {
				if (table[index].get(i).getKey().equals(key)) {
					entry = new Entry<K, V>(table[index].get(i).getKey(),table[index].get(i).getValue());
					table[index].remove(i);
				}
			}
			return entry.getValue();
		}			

		//Javas implementation of hashcode, Calculates total hashcode of each of the entrys, rather than just the hashcode of the Entry's key value
		public int hashCode() {
			int total = 0;
			for(LinkedList<Entry<K,V>> list : table) {
				if (list != null) {
					for (Entry<K,V> entry : list) {
						total += entry.hashCode();
					}
				}
			}
			return total;
		}
		
		public boolean equals(Object o) {//Since it has to use HashTables equals method, need a object parameter. If it does not contain a value, it is false
			Map<String, Integer> other = (Map<String, Integer>) o;
			for (LinkedList<Entry<K,V>> list: table) {
				if(list != null) {
					for(Entry<K,V> entry : list) {
						if (!entry.getValue().equals(other.get(entry.getKey()))){
						return false;
					}
				}
				}
			}
			return true;
		}
		
		@Override
		public void putAll(Map<? extends K, ? extends V> m) {
		 //NOT NEEDED
		}
		@Override
		public void clear() {//Basically goes through each of the linked list in the table and clearing them.
			for (int i = 0; i<table.length; i++) {
				if(table[i] != null)
				table[i].clear();
			}	
			numKeys = 0;
		}
		@Override
		public Set<K> keySet() {
			Set<K> set = new HashSet<K>();
			for (LinkedList<Entry<K,V>> l : table) {
				if(l != null) {
				for(Entry<K,V> e : l) {
					set.add(e.getKey());
				}
			}
		   }
			return set;
		}
		@Override
		public Collection<V> values() {
			//Dont need to do
			return null;
		}
		@Override
		public Set<Map.Entry<K, V>> entrySet() {
			 return new EntrySet();
			}
		
		
		@Override
		public String toString() {//For testing purpose
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (LinkedList<Entry<K,V>> l : table) {
				if(l != null) {
				for(Entry<K,V> e : l) {
					if(e == null) {
						
					}
					else
					sb.append(" "+ e + ",");
				}
			}
		}
			sb.deleteCharAt(1);//Deletes the first empty space
			sb.deleteCharAt(sb.length() -1);//Deletes the last comma in the Stringbuilder
			sb.append("]");
			return sb.toString();
		}

		private static class Entry<K, V> implements Map.Entry<K, V> {
			 private K key;
			 /** The value */
			 private V value;
			
			 public Entry(K key, V value) {
			 this.key = key;
			 this.value = value;
			 }
		
			 public Entry() {
				 key = null;
				 value = null;
			 }
			 public K getKey() {
			 return key;
			 }
		
			 public V getValue() {
			 return value;
			 }

			 public V setValue(V val) {
			 V oldVal = value;
			 value = val;
			 return oldVal;
			 }
			 
			 public int hashCode() {
				return (key.hashCode() ^ value.hashCode());//Implemented from java's own hash table, uses Xor, which will create a new int. Uses Binary "boolean" operands to create a new binary int 110 and 011 will become 101, which translate to new int
			 }
			 public String toString() {//For testing purpose
				 return  key + "=" + value ;
			 }
	}
	
		private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

			 @Override
			 public Iterator<Map.Entry<K, V>> iterator() {
			 return new SetIterator(); //Using Javas default constructor 
			 }

			@Override
			public int size() {
				return numKeys;
			}

		}
		private class SetIterator implements Iterator<Map.Entry<K, V>>{

		private int index = 0;
		private Entry<K, V> lastItemReturned = null; 
		private Iterator<Entry<K, V>> itr = null;
		
		public boolean hasNext() {
			if (itr != null && itr.hasNext()) {
				return true;
			}
			do {
				index++;
				if(index >= table.length) {
					return false;
				}
			}while(table[index] == null); 
				itr = table[index].iterator();
				index ++;
				return itr.hasNext();		
		}
		

		public Map.Entry<K, V> next() {
			if(hasNext()) {
				lastItemReturned = itr.next();
				return lastItemReturned;
			}
			else {
				throw new NoSuchElementException();
			}
	}
}


}
