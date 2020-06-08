package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1697_숨바꼭질 {

	public static int start, end, result, nx;
	public static boolean visited[];
	public static int dy[] = {-1, 1, 2};
	
	public static class Info {
		int y, time;
		
		public Info(int y, int time) {
			this.y = y;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		start = Integer.parseInt(input[0]);
		end = Integer.parseInt(input[1]);
		visited = new boolean[100001];
		
		bfs();
		System.out.println(result);
	}
	
	private static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(start, 0));
		visited[start] = true;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			
			if(cur.y == end) {
				result = cur.time;
				return;
			}
			
			for(int d = 0; d < 3; d++) {
				int ny = 0;
				if(d == 2) ny = cur.y * dy[d];
				else ny = cur.y + dy[d];
				
				if(checkRange(ny)) continue;
				if(visited[ny]) continue;
				q.offer(new Info(ny, cur.time + 1));
				visited[ny] = true;
			}
		}
	}
	
	private static boolean checkRange(int y) {
		return y < 0 || y > 100000;
	}
}
