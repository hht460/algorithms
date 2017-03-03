package njupt.string.search;

/**
 * 维护了两个指针
 * 指针  i 跟踪 文本  ; 指针 j 跟踪模式
 **/
public class ViolenceStringSearch {
	public static int search(String pat, String txt){
		int M = pat.length();
		int N = txt.length();
		for(int i =0 ; i < N-M; i++){ 
			int j;
			for(j = 0;j < M; j++){
				if(txt.charAt(i+j) != pat.charAt(j))  // i + j 类似于动态指针 在文本中移动
					break;
			}
			if(j == M){   //找到匹配
				return i;
			}
		}
		return N;          //未找到匹配
	}
	
	public static void main(String[] args){
		String str = "qazwxxedcrfvtbhny";
		String pat = "bh";
		System.out.println(search(pat, str));
	}
}
