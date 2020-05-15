package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 실행시간 112ms
public class BOJ_2636_치즈 {

	public static int N, M, time, cheese, pre, map[][];
	public static boolean visited[][];
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++){
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j] == 1) cheese++;
			}
		}
		
		pre = cheese;
		while(true) {
			time++;
			melting();	
			clear();
			
			if(cheese == 0) break;
			pre = cheese;
		}
		
		System.out.println(time);
		System.out.println(pre);
	}
	
	private static void melting() {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(0, 0));
		visited = new boolean[N][M];
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(checkRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == 0) {
					q.offer(new Pair(nx, ny));
					visited[nx][ny] = true;
				} else if(map[nx][ny] == 1) {
					map[nx][ny] = 2;
					visited[nx][ny] = true;
					cheese--;
				}
			}
		}
	}
	
	private static void clear() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 2) map[i][j] = 0;
			}
		}
	}
		
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
