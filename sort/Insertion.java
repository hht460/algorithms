package sort.njupt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Insertion {
	public static void sort(Comparable[] a)
	{   //  The array in ascending order
		int N = a.length;
		for(int i = 1; i < N; i++){
			for(int j = i; j>0 && less(a[j], a[j-1]); j--){
				exch(a, j, j-1);
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
//		//从标准输入中读取字符串，将他们排序并输出
//		Scanner input = new Scanner(System.in);
//		System.out.println("please input number...");
//		int N = input.nextInt();
//		String[] a = new String[N];
//		for(int i = 0; i < a.length; i++){
//			a[i] = input.next();
//		}
//		System.out.println(Arrays.toString(a));
//		long start = System.currentTimeMillis();
//		sort(a);
//		long end = System.currentTimeMillis();
//		assert isSorted(a);
//		show(a);
//		System.out.println(end-start);
		
		File file = new File("E:\\test_data\\test_data.txt");
		String[] myArray = new String[1000];  //100：这个值你自己定义，但不宜过大，要根据你文件的大小了，或者文件的行数
		//建立数据的输入通道
		int i = 0;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader buf = new BufferedReader(fr);
            String readLine = "";
            while((readLine = buf.readLine()) != null){
                myArray[i] = readLine;
                i++;
            }
            buf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(i);
		System.out.println(myArray.length);
		String[] a = new String[i];
        for(int j=0; j < i; j++){
        	a[j] = myArray[j];
        }
        
		System.out.println(Arrays.toString(a));
		long start = System.currentTimeMillis();
		sort(a);
		long end = System.currentTimeMillis();
		assert isSorted(a);
		show(a);
		System.out.println(end-start);	
	}
}
