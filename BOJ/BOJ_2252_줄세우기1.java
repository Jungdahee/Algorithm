package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2252_줄세우기1 {

	public static int N, M, cnt[];
	public static ArrayList<Integer> list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		cnt = new int[N + 1];
		list = new ArrayList[N + 1];
	
		for(int i = 1; i <= N; i++) list[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			list[a].add(b);
			cnt[b]++;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			if(cnt[i] == 0) {
				q.offer(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			Integer cur = q.poll();
			sb.append(cur).append(" ");
			
			for(int i = 0; i < list[cur].size(); i++) {
				int vertex = list[cur].get(i);
				cnt[vertex]--;
				
				if(cnt[vertex] == 0) {
					q.offer(vertex);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}
