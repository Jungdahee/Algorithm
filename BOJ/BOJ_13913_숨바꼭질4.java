package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_13913_숨바꼭질4 {

	public static int start, end, time, parents[];
	public static int d[] = {-1, 1, 2};
	
	public static class Info {
		int x, time;
		
		public Info(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		start = Integer.parseInt(input[0]);
		end = Integer.parseInt(input[1]);
		parents = new int[100001];
		Arrays.fill(parents, -1);
		
		bfs();
	}
	
	private static void bfs() {
		Queue<Info> q = new LinkedList<Info>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		q.add(new Info(start, 0));
		parents[start] = start;
		
		while(!q.isEmpty()) {
			Info cur = q.poll();
			
			if(cur.x == end) {
				StringBuilder sb = new StringBuilder();
				int K = end;
				
				while(K != start) {
					list.add(K);
					K = parents[K];
				}
				
				sb.append(cur.time).append('\n');
				sb.append(start).append(" ");
				for(int i = list.size() - 1; i >= 0; i--) {
					sb.append(list.get(i)).append(" ");
				}
				System.out.println(sb.toString());
				return;
			}
			
			for(int i = 0; i < 3; i++) {
				int next = 0;
				if(i == 2) next = cur.x * d[2];
				else next = cur.x + d[i];
				
				if(checkRange(next)) continue;
				if(parents[next] != -1) continue;
				q.add(new Info(next, cur.time + 1));
				parents[next] = cur.x;
			}
		}
	}
	
	private static boolean checkRange(int next) {
		return next < 0 || next > 100000;
	}
}
