package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_18290_NM과K {

	public static int N, M , K, max, map[][];
	public static boolean visited[][];
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		K = Math.min(K, N * M);

		max = Integer.MIN_VALUE;
		map = new int[N][M];

		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}

		visited = new boolean[N][M];
		dfs(0, 0);
		System.out.println(max);
	}

	private static void dfs(int cnt, int sum) {
		if(cnt == K) {
			if(max < sum) max = sum;
			return;
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j]) continue;
				if(checkDistinct(i, j)) continue;
				visited[i][j] = true;
				dfs(cnt + 1, sum + map[i][j]);
				visited[i][j] = false;
			}
		}
	}

	private static boolean checkDistinct(int x, int y) {
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if(checkRange(nx, ny)) continue;
			if(visited[nx][ny]) return true;
		}
		return false;
	}

	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
