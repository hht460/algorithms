package njupt.string.search;
/**
 * KMP字符串匹配
 **/
public class KMPStringSearch {
	
	/**
	 * 由模式串p生成next[]函数
	 * 在不匹配元素处之前  找到最大的 前缀=后缀
	 **/
	private static int[] getNext(String p){
		int[] next = new int[p.length()];
		int j = 1;
		int k =0;
		next[0] = -1;
		next[1] = 0;
		
		while(j < p.length() - 1){
			if(p.charAt(j) == p.charAt(k)){  //匹配
				next[j + 1] = k + 1;
				j++;
				k++;
			}
			
			else if(k == 0){                 //不匹配
				next[j + 1] = 0;
				j++;
			}
			else{                            //不匹配
				k = next[k];
			}
		}
		return next;
	}
	
	public static int index_KMP(String txt, String p){
		int[] next = getNext(p);
		int i = 0;  //主串指针
		int j = 0;  //模式串指针
		//对两串从左到右逐个比较字符
		while(i < txt.length() && j < p.length()){
			//若对应字符匹配
			if(j == -1 || txt.charAt(i) == p.charAt(j)){  // j == -1 表示S[i] != T[0]
				i++;
				j++;
			}else{
				j = next[j];  //当对应字符不匹配时，根据next[]函数 将模式指针j返回到合适位置
			}
		}
		if(j < p.length()){
			return -1;            //匹配失败 
		}else{
			return i - p.length();
		}
	}
	
	public static void main(String[] args){
		String txt = "qazwxxedcrfvtbhny";
		String p = "bh";
		int n = index_KMP(txt, p);
		System.out.println(n);
	}
}
