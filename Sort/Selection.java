package njupt.ch1.EX;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
				if(less(a[j], a[min])){
					min = j;
				}
			}
			exch(a, i, min);
		}
	}
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0 ;
		//return v < w; 即可应用于基本数据类型
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
		File file = new File("H:\\java_test_data\\test_data\\test_data.txt");
		String[] myArray = new String[30000];  //100：这个值你自己定义，但不宜过大，要根据你文件的大小了，或者文件的行数
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

