package search.hash;
/**
 * 基于线性探测发 的 符号表
 * 使用并行数组 一条保存键  一条保存值
 * 使用散列函数产生访问数据所需要的数组索引
 * 符号表中维护着两个 数组
 */
public class LinearProbingHashST <Key, Value>{
	
	private int N; //符号表中键值对总数
	private int M = 16; //线性探测表大小
	private Key[] keys; //键
	private Value[] vals; // 值
	
	public LinearProbingHashST(){
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	//自定义平行数组大小
	public LinearProbingHashST(int cap){
		keys = (Key[]) new Object[cap];
		vals = (Value[]) new Object[cap];
	}
	
	//产生键的哈希值
	private int hash(Key key){
		//屏蔽符号位 将一个32位整数 变为一个31位非负整数
		return ( key.hashCode() & 0x7fffffff ) % M;
 	}
	
	//动态调整数组大小
	private void resize(int cap){
		LinearProbingHashST<Key, Value> t;  //新建一个 线性探测发 的 符号表
		t = new LinearProbingHashST<Key, Value>(cap);
		for(int i=0; i < M; i++){    //将原先的线性探测发 的 符号表内容拷贝到扩容后的符号表中
			if(keys[i] != null)
				t.put(keys[i], vals[i]);
		}
		keys = t.keys;  //将扩容后的符号表 中 平行数组 的 引用 赋 给原先的符号表
		vals = t.vals;  
		M = t.M;
	}
	
	//向数组中添加键值
	public void put(Key key, Value val){
		if(N >= M/2)  //当键值对总数 超过 线性探测表一半时
			resize(2*M); //将M加倍
		int i = 0;
		for(i = hash(key); keys[i] != null; i = (i + 1) % M){
			if(keys[i].equals(key))
			    vals[i] = val;
				return;
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public Value get(Key key){
		for(int i = hash(key); keys[i] != null ; i = (i + 1) % M){
			if(keys[i].equals(key))
				return vals[i];
		}
		return null;
	}
	
	public static void main(String[] args){
		
	}
}
