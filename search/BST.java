package njupt.search.java;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class BST<Key extends Comparable<Key>, Value> {
				/**
				 * ？extends E: 接收E类型或者E的子类型。
			     * ? super E: 接收E类型或者E的父类型。
			     */
	private Node root;  //二叉查找树根结点
	
	public BST(){
		this.root = null;
	}
	
	//内部类
	private class Node{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		public Node(Key key, Value val, int N){
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	//获得 结点总数
	public int size(){
		return size(root);
	}
	private int size(Node x){
		if(x == null)
			return 0;
		else
			return x.N;
	}
	
	// 获得指定键 的 值  递归实现
	public Value get(Key key){
		return get(root, key);
	}
	private Value get(Node x, Key key){
		//  在以x为根结点的子树中查找 并返回key所对应的值
		//  如果找不到则返回null
		if(x == null)
			return null;
		int cmp = key.compareTo((Key) x.key);
		if(cmp < 0)
			return get(x.left, key);
		if(cmp > 0)
			return get(x.right, key);
		return (Value) x.val;
	}
	
//	非递归实现get()
//	public Value get(Key key){
//		Node x = root;
//		while(x != null){
//			int cmp = key.compareTo(x.key);
//			if(cmp == 0)
//				return x.val;
//			else if(cmp < 0)
//				x = x.left;
//			else if(cmp > 0)
//				x = x.right;
//		}
//		return null;
//	}
	
	//是否包含指定键
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	//添加键值对
	public void put(Key key, Value val){
		// 查找key， 找到则更新它的值， 否则为它创建一个新的结点
		root = put(root, key, val);
	}
	private Node put(Node x, Key key, Value val){
		// 如果key 存在于以x为根结点的子树中则更新它值
		// 否则将以  key和val为键值对  的结点插入到该子树中
		if(x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
			x.left = put(x.left, key, val);
		else if(cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	 }
	
	//得到二叉查找树 中 最小键
	public Key min(){
		return min(root).key;
	}
	//得到二叉查找树 中 最小键的链接
	private Node min(Node x){
		if(x.left == null)
			return x;
		return min(x.left);
	}
	
	//得到二叉查找树 中 最大键
	public Key max(){
		return max(root).key;
	}
	//得到二叉查找树 中 最大键的链接
	private Node max(Node x){
		if(x.right == null)
			return x;
		return max(x.right);
	}
	
	//返回排名为  k 的结点
	public Key select(int k){
		return select(root, k).key;
	}
	private Node select(Node x, int k){
		if(x == null)
			return null;
		int t = size(x.left);
		if(t > k)
			return select(x.left, k);
		else if(t < k)
			return select(x.right, k-t-1);
		else 
			return x;
	}
	
	//给出指定  key 查看排名
	public int rank(Key key){
		return rank(root, key);
	}
	private int rank(Node x, Key key){
		//返回以x为根结点的子树中 小于 x.key 的键的数量
		// 当遇到key 等于  x.key 时 取以x为根结点的左子树数目
		if(x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
			return rank(x.left, key);
		else if(cmp > 0)
			return rank(x.right, key) + size(x.left) + 1;
		else
			return size(x.left);
	}
	
	//二叉查找树 中 删除最小键 及其对应值   
	public void deleteMin(){
		root = deleteMin(root);
	} 
	//删除以指定结点为根结点的子树中最小键 并 返回该 指定结点 的根的链接
	private Node deleteMin(Node x){
		if(x == null)
			return null;
		if(x.left == null)  // 只有右子树 此时根结点最小 直接返回根结点右子树 链接
			return x.right; //递归终止条件
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
		
	}
	
	//二叉查找树 中 删除最大键及其对应值
	public void deleteMax(){
		root = deleteMax(root);
	}
	//删除以指定结点为根结点的子树中最大键 并 返回该 指定结点 的根的链接
	private Node deleteMax(Node x){
		if(x == null)
			return null;
		if(x.right == null)  // 只有左子树 此时根结点最大 直接返回根结点左子树 链接
			return x.left;   //递归终止条件
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	//二叉查找树 中 删除指定键及其值
	public void delete(Key key){
		root = delete(root, key);
	}
	private Node delete(Node x, Key key){
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
			return delete(x.left, key);
		else if(cmp > 0)
			return delete(x.right, key);
		else{
			if(x.left == null)
				return x.right;
			if(x.right == null)
				return x.left;
			Node t = x;  //暂存找到指定键的链接  
			x = min(x.right); //返回右子树中最小的键 的 链接
			x.right = deleteMin(t.right);  // 将右子树最小键删除 并返回右子树的根链接
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	//二叉查找树  范围 查找
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	private Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new Queue<Key>(); // 前面算法1.3实现的队列算法
		keys(root, queue, lo, hi);
		return queue;
	}
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
		if(x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo < 0)
			keys(x.left, queue, lo, hi);
		if(cmplo <= 0 && cmphi >= 0)
			queue.enqueue(x.key);
		if(cmphi > 0)
			keys(x.right, queue, lo, hi);
	}
	
	
	//中序遍历 二叉查找树 按键顺序打印
	public void print(){
		print(root);
	}
	private void print(Node x){
		if(x == null)
			return ;
		print(x.left);
		System.out.print(x.key +"  "+ x.val + " ; ");
		print(x.right);
	}
	
	public static void main(String[] args){
		// key: String  Value: Integer 表示每个String出现的频率
		BST<String, Integer> bst = new BST<String, Integer>();
		
		File file = new File("H:\\java_test_data\\test_data\\test_data.txt");
		Scanner input = new Scanner(System.in);
		System.out.print("please input min String length: ");
		int minlen = input.nextInt();
		try {
			FileReader fr = new FileReader(file);
			BufferedReader buf = new BufferedReader(fr);
            String readLine = "";
            while((readLine = buf.readLine()) != null){
            	if(readLine.length() < minlen)
            		continue;
            	if(!bst.contains(readLine))
            		bst.put(readLine, 1);
            	else
            		bst.put(readLine, bst.get(readLine) + 1);
            }
            buf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		input.close();
		bst.print();
		
		//找出出现频率最高的单词
		String max = " ";
		bst.put(max, 0);
		for(String word : bst.keys()){
			if(bst.get(word) > bst.get(max))
				max = word;
		}
		System.out.println();
		System.out.println("max:" + max + " " + bst.get(max));
   }
}
