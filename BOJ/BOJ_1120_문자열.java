package BOJ;

import java.io.*;

public class BOJ_1120_문자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		int aSize = input[0].length();
		int bSize = input[1].length();
		int result = 1000000000;
		
		for(int i = 0; i <= bSize - aSize; i++) {
			int cnt = 0;
			for(int j = 0; j < aSize; j++) {
				if(input[0].charAt(j) != input[1].charAt(i + j)) {
					cnt++;
				}
			}
			if(result > cnt) result = cnt;
		}
		System.out.println(result);
	}
}
 
