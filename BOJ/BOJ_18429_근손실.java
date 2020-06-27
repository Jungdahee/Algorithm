package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_18429_근손실 {

	public static int N, K, result, kit[];
	public static boolean isSelected[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		kit = new int[N];
		isSelected = new boolean[N];
		
		input = br.readLine().split(" ");
		for(int i = 0; i < N; i++) kit[i] = Integer.parseInt(input[i]);
		
		dfs(0, 500);
		System.out.println(sb.append(result));
	}
	
	private static void dfs(int cnt, int sum) {
		if(sum < 500) return;
		if(cnt == N) {
			if(sum >= 500) result++;
			return;
		}
		
		for(int i = 0; i < kit.length; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			dfs(cnt + 1, sum + kit[i] - K);
			isSelected[i] = false;
		}
	}
}
