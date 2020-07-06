package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1861_정사각형방 {

	public static int N, n, result, map[][];
	public static LinkedList<Pair> list;
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
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
			list = new LinkedList<Pair>();
			result = Integer.MIN_VALUE;
			
			for(int i = 0; i < N; i++) {
				String input[] = br.readLine().split(" ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int cnt = bfs(i, j);
					if(cnt >= result) 
						list.add(new Pair(map[i][j], cnt));
				}
			}
			
			Collections.sort(list, new Comparator<Pair>() {

				@Override
				public int compare(Pair o1, Pair o2) {
					if(o1.y == o2.y)
						return o1.x - o2.x;
					return o2.y - o1.y;
				}
			});
			
			System.out.println("#" + t + " " + list.get(0).x + " " + list.get(0).y);
		}
	}
	
	private static int bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(x, y));
		
		int cnt = 1;
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(checkRange(nx, ny)) continue;
				if(map[nx][ny] == map[cur.x][cur.y] + 1) {
					q.offer(new Pair(nx, ny));
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
