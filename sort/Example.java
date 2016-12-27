package sort.njupt;

import java.util.Scanner;
/**
 * 排序算法模板适用于任�?  实现了Comparable接口�?   数据类型
 */
public class Example {
	public static void sort(Comparable[] a)
	{
		
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
		//  在单行中打印数组
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i] + " ");
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
		int N = input.nextInt();
		String[] a = new String[N];
		for(int i = 0; i < a.length; i++){
			a[i] = input.next();
		}
		
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
