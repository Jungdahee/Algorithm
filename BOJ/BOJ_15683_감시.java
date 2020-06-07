package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_15683_감시 {

	public static int N, M, result, map[][];
	public static ArrayList<CCTV> cctvList;
	public static int dir[][][] = {
			{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}, // 1
			{{-1, 0, 1, 0}, {0, -1, 0, 1}}, // 2
			{{-1, 0, 0, 1}, {0, 1, 1, 0}, {1, 0, 0, -1}, {0, -1, -1, 0}}, // 3
			{{-1, 0, 0, 1, 1, 0}, {0, 1, 1, 0, 0, -1}, {1, 0, 0, -1, -1, 0}, {0, -1, -1, 0, 0, 1}}, // 4
			{{-1, 0, 0, 1, 1, 0, 0, -1}} // 5
	}; 

	public static class CCTV {
		int x, y, num;

		public CCTV(int x, int y, int  num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		result = Integer.MAX_VALUE;
		map = new int[N][M];
		cctvList = new ArrayList<CCTV>();

		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j] > 0 && map[i][j] < 6) {
					cctvList.add(new CCTV(i, j, map[i][j]));
				}
			}
		}

		dfs(0);

		System.out.println(result);
	}

	private static void dfs(int idx) {
		if(idx == cctvList.size()) {
			int tmp = count();
			if(result > tmp) result = tmp;
			return;
		} 

		CCTV cctv = cctvList.get(idx);
		int num = cctv.num;
		int curDir[][] = dir[num - 1];

		int nx = 0;
		int ny = 0;
		
		int tMap[][] = new int[N][];
		for (int t = 0; t < N; ++t) tMap[t] = Arrays.copyOf(map[t], M);
		
		for(int i = 0; i < curDir.length; i++) {
			for(int d = 0, r = 0; d < curDir[i].length / 2; d++) {
				nx = cctv.x;
				ny = cctv.y;
				r = d * 2;

				while(true) {
					if(r >= curDir[i].length) break;
					nx = nx + curDir[i][r];
					ny = ny + curDir[i][r + 1];
					// 범위를 벗어나는 경우
					if(checkRange(nx, ny)) break;
					// 벽을 만나는 경우
					if(map[nx][ny] == 6) break;
					map[nx][ny] = 9;
				}
			}
			dfs(idx + 1);
			
			for (int a = 0; a < N; ++a) {
				for (int b = 0; b < M; ++b) map[a][b] = tMap[a][b];
			} 
		}
	}

	private static int count() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M ; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
