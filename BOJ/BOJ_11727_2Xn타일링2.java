package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 수행 시간 
public class BOJ_11727_2Xn타일링2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int d[] = new int[n + 2];
		
		// 너비가 1인 직사각형을 만드는 방법은 하나이기 때문에 d[1] = 1
		d[1] = 1;
		// 너비가 2인 직사각형을 만드는 방법은 3가지 경우이기 때문에 d[2] = 3;
		d[2] = 3;
		
		// 2 * 1인 직사각형을 가장 오른쪽에 두었을 때 왼쪽에 다른 직사각형으로 만들 수 있는 경우의 수는 d[n - 1]의 모든 경우의 수임.
		// 2 * 1과 2 * 2를 가장 오른쪽에 두었을 때 왼쪽에 다른 직사각형으로 만들 수 있는 경우의 수는 d[n - 2]의 모든 경의의 수임.
		for(int i = 3; i <= n; i++) d[i] = (d[i - 1] + 2 * d[i - 2]) % 10007;
		
		System.out.println(d[n]);
	}
}
