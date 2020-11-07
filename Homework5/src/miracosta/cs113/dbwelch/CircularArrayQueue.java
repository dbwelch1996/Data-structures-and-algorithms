package miracosta.cs113.dbwelch;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Queue;

public class CircularArrayQueue<E> extends AbstractQueue<E> implements Queue<E>{

private int front;
private int rear;
private int size;
private int capacity;
private static final int DEFAULT_CAPACITY = 10;
private E[] theData;

public CircularArrayQueue() {
	this(DEFAULT_CAPACITY);
}

@SuppressWarnings("unchecked")
public CircularArrayQueue(int initCapacity) {
capacity = initCapacity;
theData = (E[]) new Object[capacity];
front = 0;
rear = capacity - 1;
size = 0;
}

public boolean offer(E item) {
if (size == capacity) {
reallocate();
}
size++;
rear = (rear + 1) % capacity; theData[rear] = item;
return true;
}

public E peek() {
if (size == 0)
return null;
else
return theData[front];
}


public E poll() {
if (size == 0) {
return null;
}
E result = theData[front];
front = (front + 1) % capacity;
size--;
return result;
}


private void reallocate() {
int newCapacity = 2 * capacity;
@SuppressWarnings("unchecked")
E[] newData = (E[]) new Object[newCapacity];
int j = front;
for (int i = 0; i < size; i++) {
newData[i] = theData[j];
j = (j + 1) % capacity;
}
front = 0;
rear = size - 1;
capacity = newCapacity;
theData = newData;
}

public Iterator<E> iterator() {
	Iterator<E> iterator = Arrays.asList(theData).iterator();
	return iterator;
}

public int size() {
	return size;
}
}
