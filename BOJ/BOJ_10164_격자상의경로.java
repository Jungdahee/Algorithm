package BOJ;

import java.io.*;

public class BOJ_10164_격자상의경로 {

	static int n, m, t, cnt, tx, ty, map[][];
	static int dx[] = {0, 1};
	static int dy[] = {1, 0};

	static boolean checkRange(int x, int y) {
		return x >= n || y >= m;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		t = Integer.parseInt(input[2]);

		map = new int[n][m];

		int num = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(num == t) {
					tx = i;
					ty = j;
				}
				map[i][j] = num++;
			}
		}

		if(t == 0) {
			go1(0, 0);
			System.out.println(cnt);
		}
		else {
			go1(tx, ty);
			int res1 = cnt;
			cnt = 0;
			go2(0, 0);
			int res2 = cnt;
			System.out.println(res1 * res2);
		}
	}

	static void go1(int x, int y) {
		if(x == n -1 && y == m - 1) {
			cnt++;
			return;
		}

		for(int k = 0; k < 2; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];

			if(nx >= n || ny >= m) continue;
			go1(nx, ny);
		}
	}
	
	static void go2(int x, int y) {
		if(x == tx && y == ty) {
			cnt++;
			return;
		}

		for(int k = 0; k < 2; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];

			if(nx > tx || ny > ty) continue;
			go2(nx, ny);
		}
	}
}
