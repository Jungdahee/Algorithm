package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1249_보급로 {

	public static int N, result, map[][], tmp[][];
	public static boolean visited[][];
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	public static class Info {
		int x, y;
		
		public Info(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			tmp = new int[N][N];
			visited = new boolean[N][N];
			result = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				String input[] = br.readLine().split("");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			bfs();
			System.out.println("#" + t +  " " + result);
		}
	}
	
	private static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(0, 0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			
			if(result <= tmp[cur.x][cur.y]) continue;
			if(cur.x == N - 1 && cur.y == N - 1) {
				if(tmp[N - 1][N - 1] < result) result = tmp[N - 1][N - 1];
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(checkRange(nx, ny)) continue;
				if(!visited[nx][ny] || tmp[nx][ny] > tmp[cur.x][cur.y] + map[nx][ny]) {
					tmp[nx][ny] = tmp[cur.x][cur.y] + map[nx][ny];
					q.offer(new Info(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
