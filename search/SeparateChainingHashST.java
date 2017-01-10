package njupt.search.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * 基于拉链法 的 散列表
 */
public class SeparateChainingHashST<Key, Value>{
	private int N;   // 键值对总数
	private int M;   // 散列表大小
	
	private SequentialSearchST<Key, Value>[] st;  // 存放链表对象的数组
	
	public SeparateChainingHashST(){
		this(997);
	}
	public SeparateChainingHashST(int M){
		// 创建M条链表  , SequentialSearchST类型的数组   数组中存放的是链表的引用
		this.M = M;
		st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
		for(int i = 0; i < M; i++){
			st[i] = new SequentialSearchST(); 
		}
	}
	
	//获得key的哈希值
	private int hash(Key key){
		return (key.hashCode() & 0x7fffffff) % M; // M 散列表大小
	}
	
	//从哈希表中取得指定键的值
	public Value get(Key key){
		return  st[hash(key)].get(key);
	}
	
	//往哈希表中添加键值对
	public void put(Key key, Value val){
		st[hash(key)].put(key, val);
	}
	
	//删除散列表中 的 键值对
	//要删除一个键值对  先用 散列值 找到该键的SequentialSearchST对象,然后调用该对象的delete()方法
	public void deleteHS(Key key){
		st[hash(key)].delete(key);
	}
	
	//散列表迭代器 
	public Iterable<Key> keys(){
		return null ;
	}
	
	//判断是否包含指定key及其值
	public boolean contains(Key key){
		return get(key) != null;
	}
	
	//打印散列表
	private void print(){
		
	}
	public static void main(String[] args){
		// key: String  Value: Integer 表示每个String出现的频率
		SeparateChainingHashST<String, Integer> bst = new SeparateChainingHashST<String, Integer>();
		
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
