package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행 시간 72ms
public class BOJ_15650_N과M2 {

	public static int N, R, origin[], numbers[];
	public static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		R = Integer.parseInt(input[1]);
		
		numbers = new int[R];
		origin = new int[N + 1];
		
		for(int i = 1; i <= N; i++) origin[i] = i;
		
		combination(0, 1);
	}
	
	private static void combination(int cnt, int idx) {
		if(cnt == R) {
			sb = new StringBuilder();
			for(int i = 0; i < numbers.length; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append('\n');
			System.out.print(sb.toString());
			return;
		}
		
		if(idx >= origin.length) return;
		
		numbers[cnt] = origin[idx];
		combination(cnt + 1, idx + 1);
		
		combination(cnt, idx + 1);
	}
}
