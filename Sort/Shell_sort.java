package trend.njupt.thread;

import java.util.Arrays;

/**
 * 希尔排序
 * 首先产生一个h数组[1, 4, 13, .....]
 * 当 h = 1 时 ，就是插入排序
 */

public class Shell_sort {
	public static void sort(int[] a){
		//将a[] 升序排序
		int N = a.length;
		int h = 1;
		//生成一个h数组
		while(h < N/3){
			h = 3*h + 1;  // 1 4 13 40 ...
		}
		
		while(h >= 1){
			for(int i = h; i < N; i++){
				for(int j = i; j >= h; j -= h){
					if(a[j] < a[j-h]){
						int temp = a[j-h];
						a[j-h] = a[j];
						a[j] = temp;
					}
				}
			}
			h = h / 3;
		}
	}
	public static void main(String[] args){
		int[] a = {10, 23, 1, 53, 654, 54, 16, 646, 65, 3155, 546, 31};
		sort(a);
		System.out.println(Arrays.toString(a));
	}
}

//插入排序
//for(int i =1; i < N; i++){
//	for(int j = i; j>=1; j--){
//		if(a[j] < a[j-1]){
//			exch(a[j], a[j-1]);
//		}
//	}
//}
