package njupt.ch1.EX;

import java.util.Arrays;
/**
 * 冒泡排序
 * 两两比较，将最大数下沉
 * 两层for循环  
 * 外层for :  总趟数   N个数  需要 N-1趟   每一趟将一个最大数下沉到底部
 * 内层for :  从0开始 两两比较并交换大数
 * */
public class BubblingSort {
	public static void BS(int[] a){
		int N =a.length;
		for(int i = 0; i < N-1; i++){
			for(int j = 0; j < N-i-1; j++){
				if(a[j] > a[j+1]){
					int temp = a[j+1];
					a[j+1] = a[j];
					a[j] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args){
		int [] a = {6, 8, 12, 3, 344, 65, 1, 35, 79, 66};
		BS(a);
		System.out.println(Arrays.toString(a));
 	}
}
