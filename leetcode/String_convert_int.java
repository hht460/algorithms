package trend.njupt.thread;
/**
 * string convert to integer
 */
public class String_convert_int {

	    public int myAtoi(String str) {
	    if (str == null || str.length() == 0) //string is null
		return 0;
		str = str.trim(); // remove string spaces both sides
		char firstChar = str.charAt(0);
		int sign = 1, start = 0, len = str.length();
		long sum = 0;
		if (firstChar == '+') {
			sign = 1;
			start++;
		} else if (firstChar == '-') {
			sign = -1;
			start++;
		}
		for (int i = start; i < len; i++) {
			if (!Character.isDigit(str.charAt(i)))   // 判断字符是否为数字的方法    Character.isLetter('c') 判断是否为字母的方法
				return (int) sum * sign;
			sum = sum * 10 + str.charAt(i) - '0';
			if (sign == 1 && sum > Integer.MAX_VALUE) //基本数据类型最大值极限
				return Integer.MAX_VALUE;
			if (sign == -1 && (-1) * sum < Integer.MIN_VALUE) //最小值极限
				return Integer.MIN_VALUE;
		}

		return (int) sum * sign;
	    }

	
	public static void main(String[] agrs){
		String_convert_int demo = new String_convert_int();
		String str= "  -11461k615";
		int i = demo.myAtoi(str);
		System.out.println(i);
	}
}
