package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행 시간 72ms
public class BOJ_9095_123더하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		int dp[] = new int[12];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		
		for(int i = 4; i <= 11; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		
		while(tc-- > 0) {
			int num = Integer.parseInt(br.readLine());
			System.out.println(dp[num]);
		}
	}
}
