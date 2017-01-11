package njupt.search.hash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import njupt.search.java.SeparateChainingHashST;

/**
 * 基于线性探测发 的 散列表
 * 使用并行数组 一条保存键  一条保存值
 * 使用散列函数产生访问数据所需要的数组索引
 * 符号表中维护着两个 数组
 * 
 * 迭代器 利用队列Queue实现
 */
public class LinearProbingHashST <Key, Value>{
	
	private int N; //符号表中键值对总数
	private int M = 16; //线性探测散列表大小
	private Key[] keys; //键
	private Value[] vals; // 值
	
	public LinearProbingHashST(){
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	//自定义平行数组大小
	public LinearProbingHashST(int cap){
		this.M = cap;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	
	//产生键的哈希值
	private int hash(Key key){
		//屏蔽符号位 将一个32位整数 变为一个31位非负整数
		return ( key.hashCode() & 0x7fffffff ) % M; // 得到0 ~ M-1之间的数
 	}
	
	//动态调整数组大小
	private void resize(int cap){
		System.out.println("resize...");
		LinearProbingHashST<Key, Value> t;  //新建一个 线性探测发 的 符号表
		t = new LinearProbingHashST<Key, Value>(cap);
		for(int i=0; i < M; i++){    //将原先的线性探测发 的 散列内容拷贝到扩容后的散列表中
			if(keys[i] != null)
				t.put(keys[i], vals[i]); // 重新放入平行数组中，当然哈希值也重新计算
		}
		keys = t.keys;  //将扩容后的散列表 中 平行数组 的 引用 赋 给原先的散列表
		vals = t.vals;  
		M = t.M;
	}
	
	//向数组中添加键值
	public void put(Key key, Value val){
		if(N >= M/2)     //当键值对总数 超过 线性探测表一半时
			resize(2*M); //将M加倍
		
		int i = 0;
		for(i = hash(key); keys[i] != null; i = (i + 1) % M){ // (i+1)%M 可以循环访问数组
			System.out.println("hash "+i);
			if(keys[i].equals(key)){
				System.out.println("hash "+i);
			    vals[i] = val;
				return;
			}
		}
		System.out.println("hash "+i);
		keys[i] = key;
		vals[i] = val;
		N++;
		System.out.println("键值对个数："+N);
	}
	
	//从数组中获取指定key对应的val
	public Value get(Key key){
		for(int i = hash(key); keys[i] != null ; i = (i + 1) % M){
			if(keys[i].equals(key))
				return vals[i];
		}
		return null;
	}
	
	//由给定的键 判断 线性探测表中是否已经包含
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	//线性探测表 迭代器  返回 键的集合 放在队列中
	public Iterable<Key> keys(){
		Queue<Key> queue = new Queue<Key>();
		for(int i = 0;i < M; i++){
			if(keys[i] != null)
				queue.enqueue(keys[i]);
		}
		return queue;
	}
	
	//打印线性探测表
	public void print(){
		int j = 1, v, sum=0;
		Queue<Key> qu = (Queue<Key>) keys();
		while(!qu.isEmpty()){
			Key keys = qu.dequeue();
			Value val = get(keys);
			System.out.println(j+ " Key: "+ keys+ " "+ "val: "+val);
		    v = (Integer)val;
		    sum = sum + v;
			j++;
		}
		System.out.println(sum);
	}
	
	public static void main(String[] args){
		// key: String  Value: Integer 表示每个String出现的频率
		LinearProbingHashST<String, Integer> lst = new LinearProbingHashST<String, Integer>();
		//此时key为String 而String继承了Comparable接口并实现了comparaTo方法，以及equals方法,hashCode方法
		File file = new File("H:\\java_test_data\\test_data\\search_test_data.txt");
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
