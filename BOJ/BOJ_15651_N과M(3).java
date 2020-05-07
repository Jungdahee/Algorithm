package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행 시간 2020ms
public class BOJ_15651_N과M3 {

	public static int N, R, numbers[];
	public static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		R = Integer.parseInt(input[1]);
		
		numbers = new int[R];
		
		permutation(0);
	}
	
	private static void permutation(int cnt) {
		if(cnt == R) {
			sb = new StringBuilder();
			for(int i = 0; i < numbers.length; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append('\n');
			System.out.print(sb.toString());
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			numbers[cnt] = i;
			permutation(cnt + 1);
		}
	}
}
