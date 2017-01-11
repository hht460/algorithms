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
