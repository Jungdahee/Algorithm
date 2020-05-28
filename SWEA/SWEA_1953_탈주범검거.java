package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1953_탈주범검거 {

	public static int N, M, startX, startY, time, L, result, map[][];
	public static boolean visited[][];
	public static int idx[][] = {{1, 2, 5, 6}, {1, 3, 6, 7}, {1, 2, 4, 7}, {1, 3, 4, 5}};
	public static int dx[] = {-1, 0, 1, 0};
	public static int dy[] = {0, 1, 0, -1};

	public static class Pair {
		int x, y, num;

		public Pair(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t = 1; t <= tc; t++) {
			String input[] = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			startX = Integer.parseInt(input[2]);
			startY = Integer.parseInt(input[3]);
			L = Integer.parseInt(input[4]);

			map = new int[N][M];
			visited = new boolean[N][M];
			time = 1;
			result = 1;

			for(int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			bfs();
			System.out.println("#" + t + " " + result);
		}
	}

	private static void bfs() {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(startX, startY, 1));
		visited[startX][startY] = true;

		while(!q.isEmpty()) {
			Pair cur = q.poll();

			if(cur.num == L) break;

			for(int i = 0; i < 4; i++) {
				int k = map[cur.x][cur.y];
				if(k == 2) {
					if(i % 2 != 0) continue;
				}
				else if(k == 3) {
					if(i % 2 != 1) continue;
				}
				else if(k == 4) {
					if(i != 0 && i != 1) continue;
				}
				else if(k == 5) {
					if(i != 1 && i != 2) continue;
				}
				else if(k == 6) {
					if(i != 2 && i != 3) continue;
				}
				else if(k == 7) {
					if(i != 0 && i != 3) continue;
				}
				
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if(checkRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(!available(nx, ny, i)) continue;
				if(map[nx][ny] == 0) continue;
				q.offer(new Pair(nx, ny, cur.num + 1));
				visited[nx][ny] = true;
				result++;
			}
		}
	}
	
	private static boolean available(int x, int y, int dir) {
		for(int i = 0; i < 4; i++) {
			if(idx[dir][i] == map[x][y]) return true;
		}
		return false;
	}

	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
