package search;

public class SequentialSearch<Key, Value> {
	private Node first;
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
	}
}
