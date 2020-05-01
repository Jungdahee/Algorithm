package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_4366_정식이의은행업무 {
	
	public static String input1, input2, tmp1, tmp2;
	public static int result;
	public static boolean isEnd;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			input1 = br.readLine();
			input2 = br.readLine();
			isEnd = false;
			result = 0;
			
			for(int i = 0; i < input1.length(); i++) {
				tmp1 = null;
				int tmpInt = Integer.parseInt(input1, 2);
				if(compare(tmpInt)) {
					result = tmpInt;
					break;
				}
				
				if(input1.charAt(i) == '1') {
					tmp1 = input1.substring(0, i);
					tmp1 += '0';
					tmp1 += input1.substring(i + 1);
				} else {
					tmp1 = input1.substring(0, i);
					tmp1 += '1';
					tmp1 += input1.substring(i + 1);
				}
				
				tmpInt = Integer.parseInt(tmp1, 2);
				if(compare(tmpInt)) {
					result = tmpInt;
					break;
				}
			}
			System.out.println("#" + t + " " + result);
		}
	}
	
	private static boolean compare(int num) {
		int tmpInt = Integer.parseInt(input2, 3);
		if(num == tmpInt) return true;
			
		for(int i = 0; i < input2.length(); i++) {
			tmp2 = null;
			if(input2.charAt(i) == '0') {
				tmp2 = input2.substring(0, i);
				tmp2 += '1';
				tmp2 += input2.substring(i + 1);
				
				if(num == Integer.parseInt(tmp2, 3)) return true;
				
				tmp2 = null;
				tmp2 = input2.substring(0, i);
				tmp2 += '2';
				tmp2 += input2.substring(i + 1);
				
				if(num == Integer.parseInt(tmp2, 3)) return true;
			}
			else if(input2.charAt(i) == '1') {
				tmp2 = input2.substring(0, i);
				tmp2 += '0';
				tmp2 += input2.substring(i + 1);
				
				if(num == Integer.parseInt(tmp2, 3)) return true;
				
				tmp2 = null;
				tmp2 = input2.substring(0, i);
				tmp2 += '2';
				tmp2 += input2.substring(i + 1);
				
				if(num == Integer.parseInt(tmp2, 3)) return true;
			}
			else {
				tmp2 = input2.substring(0, i);
				tmp2 += '0';
				tmp2 += input2.substring(i + 1);
				
				if(num == Integer.parseInt(tmp2, 3)) return true;
				
				tmp2 = null;
				tmp2 = input2.substring(0, i);
				tmp2 += '1';
				tmp2 += input2.substring(i + 1);
				
				if(num == Integer.parseInt(tmp2, 3)) return true;
			}
		}
		return false;
	}
}
