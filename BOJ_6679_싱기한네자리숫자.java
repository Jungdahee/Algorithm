package BOJ;

public class BOJ_6679_싱기한네자리숫자 {

	public static int num1, num2, num3;
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1000; i < 10000; i++) {
			num1 = calc(i, 10);
			num2 = calc(i, 12);
			num3 = calc(i, 16);
			
			if(num1 == num2 && num2 == num3) sb.append(i).append('\n');
		}
		
		System.out.println(sb.toString());
	}
	
	private static int calc(int num, int base) {
		int result = 0;
		while(num > 0) {
			result += num % base;
			num = num / base;
		}
		return result;
	}
}
