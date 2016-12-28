package njupt.ch1.EX;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 排序算法模板适用于任意   实现了Comparable接口的   数据类型
 */
public class Selection {
	public static void sort(Comparable[] a)
	{   //  The array in ascending order
		int N = a.length;
		for(int i = 0; i < N; i++){
			int min = i;
			for(int j = i+1; j < N; j++){
				if(a[j].compareTo(a[min]) < 0){
					min = j;
				}
			    exch(a, i, min);
			}
		}
	}
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0 ;
	}
	
	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	private static void show(Comparable[] a){
		//  //  在单行中打印数组
		for(int i = 0; i < a.length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static boolean isSorted(Comparable[] a)
	{
		//测试数组元素是否有序
		for(int i = 0; i < a.length; i++){
			if(less(a[i], a[i-1])){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		//从标准输入中读取字符串，将他们排序并输出
		Scanner input = new Scanner(System.in);
		System.out.println("please input number...");
		int N = input.nextInt();
		String[] a = new String[N];
		for(int i = 0; i < a.length; i++){
			a[i] = input.next();
		}
		System.out.println(Arrays.toString(a));
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
