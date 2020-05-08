package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2529_부등호 {

	public static int R;
	public static String min, max, origin[], numbers[], sign[];
	public static boolean isSelected[];
	public static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		R = Integer.parseInt(br.readLine()) + 1;
		sign = br.readLine().split(" ");
		origin = new String[11];
		isSelected = new boolean[11];
		numbers = new String[R];
		min = "9876543210";
		max = "0";

		for(int i = 0; i <= 9; i++) origin[i] = String.valueOf(i);
		
		permutation(0);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void permutation(int idx) {
		if(idx == R) {
			sb = new StringBuilder();
			for(int i = 0; i < numbers.length; i++) sb.append(numbers[i]);
			
			if(judge()) {
				String tmp = sb.toString();
				if(Long.parseLong(min) > Long.parseLong(tmp)) min = tmp;
				if(Long.parseLong(max) < Long.parseLong(tmp)) max = tmp;
			}
			return;
		}

		for(int i = 0; i <= 9; i++) {
			if(isSelected[i]) continue;
			numbers[idx] = String.valueOf(i);
			isSelected[i] = true;
			permutation(idx + 1);
			isSelected[i] = false;
		}
	}

	private static boolean judge() {
		for(int i = 0; i < numbers.length - 1; i++) {
			boolean isSuccess = false;
			long num1 = Long.parseLong(numbers[i]);
			long num2 = Long.parseLong(numbers[i + 1]);
			
			switch(sign[i]) {
				case "<" : if(num1 < num2) isSuccess = true;
						break;
				case ">" : if(num1 > num2) isSuccess = true;
						break;
			}
			if(!isSuccess) return false;
		}
		return true;
	}
}
