<<<<<<< HEAD
package search.binary;
/**
 * 二分查找     基于有序数组
 * 数据结构 内部维护了两个数组 
 * */
public class BinarySearchST <Key extends Comparable<Key>, Value>{
	
	private Key[] keys;    //键数组
	private Value[] vals;  //值数组
	private int N;         //键值对数目
	
	public BinarySearchST(int cap){
		keys = (Key[]) new Comparable[cap];  //数组没有泛型	
		vals = (Value[]) new Object[cap];
	}
	
	public int size(){
		return N;
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	//获取指定键 在 键数组中的排名
	public int rank(Key key){
		int lo = 0, hi = N-1;
		while(lo < hi){
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;  // key不在keys[]数组中时，或 当key比keys[0] 还小时
	}
	
	public Value get(Key key){
		if(isEmpty())
			return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		else
			return null;
	}
	
	public void put(Key key, Value val){
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0){
			vals[i] = val;
			return;
		}
		for(int j = N ; j > i; j--){
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	
	public void delete(Key key){
		if(!contains(key))
			return;
		int i = rank(key);
		for(int j = i; j < N-1; j++){
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		N--;
	}
}
=======
package njupt.search.binary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


/**
 * 二分查找     基于有序数组
 * 数据结构 内部维护了两个数组 
 * */

public class BinarySearchST <Key extends Comparable<Key>, Value>{
	
	private Key[] keys;    //键数组
	private Value[] vals;  //值数组
	private int N;         //键值对数目
	private int M = 16;    //起始数组大小
	
	public BinarySearchST(){
		keys = (Key[]) new Comparable[M];  //数组没有泛型	
		vals = (Value[]) new Object[M];
	}
	public BinarySearchST(int cap){
		this.M = cap;
		keys = (Key[]) new Comparable[M];  //数组没有泛型	
		vals = (Value[]) new Object[M];
	}

	//获取指定键 在 键数组中的排名
	//此 方法实现了二分查找，是其它方法实现的基石
	public int rank(Key key){
		int lo = 0, hi = N-1;
		while(lo <= hi){
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;  // key不在keys[]数组中时，或 当key比keys[0] 还小时
	}
	//递归方法实现二分查找
	public int rankDG(Key key, int lo, int hi){
		int mid = lo + (hi - lo) / 2;
		int cmp = key.compareTo(keys[mid]);
		if(cmp < 0)
			return rankDG(key, lo, mid-1);
		else if(cmp > 0)
			return rankDG(key, mid+1, hi);
		else
			return mid;
	}
	
	//获取指定键 对应的 值
	public Value get(Key key){
		if(isEmpty())
			return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		else
			return null;
	}
	
	//添加  键值对
	public void put(Key key, Value val){
		if(N > M/2){
			resize(M*2);
		}
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0){   // 遇到相等的键 则更新对应的值
			vals[i] = val;
			return;
		}
		for(int j = N ; j > i; j--){                //添加新的键值对
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	//动态调整数组大小
	public void resize(int cap){
		System.out.println("resize...");
		BinarySearchST<Key, Value> temp;
		temp = new BinarySearchST<Key, Value>(cap);
		for(int i = 0; i < N; i++){
			temp.keys[i] = keys[i];
			temp.vals[i] = vals[i];
		}
		keys = temp.keys;
		vals = temp.vals;
		M = temp.M;
	}
	
	//删除 指定 键 以及 对应的值
	public void delete(Key key){
		if(!contains(key))
			return;
		int i = rank(key);
		for(int j = i; j < N-1; j++){
			keys[j] = keys[j+1];
			vals[j] = vals[j+1];
		}
		N--;
	}
	
	//迭代器 返回所有键的集合
	public Iterable<Key> keys(){
		return keys(keys[0], keys[N-1]);
	}
	//迭代器   返回指定范围内的键的集合
	public Iterable<Key> keys(Key lo, Key hi){
		Queue<Key> queue = new Queue<Key>();
		for(int i = rank(lo); i < rank(hi) ; i++){
			queue.enqueue(keys[i]);
		}
		if(contains(hi)){
			queue.enqueue(keys[rank(hi)]);
		}
		return queue;
	}
	
	public void print(){
		for(int i = 0; i < N-1; i++){
			System.out.println("key: "+ keys[i] + "  val:"+ vals[i]);
		}
	}
	
	//返回最小键
	public Key min(){
		return keys[0];
	}
	
	//返回最大键
	public Key max(){
		return keys[N-1];
	}
	
	public Key select(int k){
		return keys[k];
	}
	
	//返回键值对数
	public int size(){
		return N;
	}
	
	//判断数组是否为空  或  是否还有键值对
	public boolean isEmpty(){
		return N == 0;
	}
	
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	
	public static void main(String[] args){
		// key: String  Value: Integer 表示每个String出现的频率
		BinarySearchST<String, Integer> lst = new BinarySearchST<String, Integer>();
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
            	if(!lst.contains(readLine)){ //不包含
            		lst.put(readLine, 1);
            		System.out.println(j+" no include...");
            		System.out.println();
            	} 
            		
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
		String maxstr = " ";
		lst.put(maxstr, 0);
		for(String word : lst.keys()){
			if(lst.get(word) > lst.get(maxstr)) //比较val大小
				maxstr = word;
		}
		System.out.println();
		System.out.println("max:" + maxstr + " " + "maxValue: "+lst.get(maxstr));
	}
}

>>>>>>> 7f0920f913d4c6cb373c1a29f96b1ba3dbba7f5c
