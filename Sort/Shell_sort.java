package trend.njupt.thread;

import java.util.Arrays;

/**
 * ϣ������
 * ���Ȳ���һ��h����[1, 4, 13, .....]
 * �� h = 1 ʱ �����ǲ�������
 */

public class Shell_sort {
	public static void sort(int[] a){
		//��a[] ��������
		int N = a.length;
		int h = 1;
		//����һ��h����
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

//��������
//for(int i =1; i < N; i++){
//	for(int j = i; j>=1; j--){
//		if(a[j] < a[j-1]){
//			exch(a[j], a[j-1]);
//		}
//	}
//}
