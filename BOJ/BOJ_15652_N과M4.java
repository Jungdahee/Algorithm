package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행 시간 164ms
public class BOJ_15652_N과M4 {

	public static int N, R, origin[], numbers[];
	public static boolean isEnd;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		R = Integer.parseInt(input[1]);
		
		numbers = new int[R];
		origin = new int[N + 1];
		for(int i = 1; i <= N; i++) origin[i] = i;
		
		permutation(1, 0);
	}
	
	private static void permutation(int idx, int cnt) {
		if(cnt == R) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < numbers.length; i++) {
				sb.append(numbers[i]).append(" ");
			}
			sb.append('\n');
			System.out.print(sb.toString());
			return;
		}
		
		for(int i = idx; i <= N; i++) {
			numbers[cnt] = i;
			permutation(i, cnt + 1);
		}
	}
}
