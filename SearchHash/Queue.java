package search.hash;

import java.util.Iterator;
/**
 * 链表实现的队列  无须考虑扩大链表容量大小问题  链表没有这方便面性质
 */
public class Queue<Item> implements Iterable<Item>{
	
	private Node first; //指向首结点
	private Node last;  //指向尾结点
	private int N;
	
	//结点  内部类
	private class Node{
		Item item;
		Node next;
		public Node(Item item, Node next){
			this.item = item;
			this.next = next;
		}
	}
	
	public boolean isEmpty(){
		return first == null;
	  //return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	//向队列末尾添加元素
	public void enqueue(Item item){
		Node oldlast = last;
		last = new Node(item, null);
		if(isEmpty())
			first = last;
		else
			oldlast.next = last;
		N++;
	}
	
	//从表头删除元素
	public Item dequeue(){
		if(isEmpty())
			return null;
		Item item = first.item;
		first = first.next;
		if(isEmpty())
			last = null;
		N--;
		return item;
	}
	
	// 获取队列的 迭代器
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	// 内部类  链表实现的队列  的 迭代器
	public class ListIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext(){
			return current != null;
		}
		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
		}
		public void remove(){}
	}
	/**
	 * 接口Iterable<?> 中只有一个方法 即 iterator()  
	 * 此方法仅仅是返回一个对象(实现了Iterator<?> 接口的类的对象)
	 * 实现Iterator<?> 接口的类  就需要实现 此接口中三个方法 
	 * hasNext()   Next()  remove() 
	 * */
}
