package njupt.search.linkedlist;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;


/**
 * 顺序查找   
 * 基于 无序 链表
 */

public class SequentialSearchST<Key, Value> {
	
	private Node first;
	private int N;
	
	private class Node{ // 链表结点定义
		Key key;
		Value val;
		Node next;
		public Node(Key key, Value val, Node next){
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	public SequentialSearchST(){
		this.first = null;
		this.N = 0;
	}
	
	public Value get(Key key){
		//查找给定的键  返回相关的值
		for(Node x = first; x != null; x = x.next){
			if(key.equals(x.key))
				return x.val;
		}
		return null;
	}
	
	public void put(Key key, Value val){
		//查找给定的键 ，找到则更新其值，否则在链表中新建结点
		for(Node x = first; x != null; x = x.next)
			if(key.equals(x.key)){
				x.val = val; //找到 更新结点
				return;
			}
		first = new Node(key, val, first); //未找到 新建结点 插入表头
		N++;
	}
	
	public void delete(Key key){
		//删除首结点
		if(key.equals(first.key))
			first = first.next;
		//删除中间结点
		Node x;
		for(x = first; x.next != null; x = x.next){
			if(key.equals(x.next.key))
				x.next = x.next.next;
		}
		//删除尾结点
		if(key.equals(x.key))
			x = null;
		N--;
	}
	
	//返回链表中结点数
	public int size(){
		return N;
	}
	
	//是否包含指定结点
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	//链表迭代器
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(Node x = first ; x != null; x = x.next){
			queue.enqueue(x.key);
		}
		return queue;
	}
	
	public void print(){
		Queue<Key> qu = (Queue<Key>) keys();
		while(!qu.isEmpty()){
			Key keys = qu.dequeue();
			System.out.println("key: "+keys+"  val:"+get(keys));
		}
		System.out.println("总键值对数： "+ N);
	}
	
	
	public static void main(String[] args){
		// key: String  Value: Integer 表示每个String出现的频率
		SequentialSearchST<String, Integer> lst = new SequentialSearchST<String, Integer>();
		//此时key为String 而String继承了Comparable接口并实现了comparaTo方法，以及equals方法,hashCode方法
		File file = new File("H:\\java_test_data\\test_data\\tale_3.txt");
		Scanner input = new Scanner(System.in);
		System.out.print("please input min String length: ");
		int minlen = input.nextInt();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader buf = new BufferedReader(fr);
            String readLine = "";
            int j = 1;
            while((readLine = buf.readLine()) != null){
            	if(readLine.length() < minlen)
            		continue;
            	System.out.println("read...");
            	if(!lst.contains(readLine)){
            		lst.put(readLine, 1);
            		System.out.println(j+" no include...");
            		System.out.println();
            	} //不包含
            		
            	else{
            		lst.put(readLine, lst.get(readLine) + 1); // 键 已存在 即 更新val
            		System.out.println(j+" include...");
            		System.out.println();
            	}
            	
            	j++;
            }
            buf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input.close();
		lst.print();
		
		//找出出现频率最高的单词
		String max = " ";
		lst.put(max, 0);
		for(String word : lst.keys()){
			if(lst.get(word) > lst.get(max)) //比较val大小
				max = word;
		}
		System.out.println();
		System.out.println("max:" + max + " " + "maxValue: "+lst.get(max));
	}
}
