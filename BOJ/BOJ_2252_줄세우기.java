package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2252_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		
		int n = Integer.parseInt(input1[0]);
		int m = Integer.parseInt(input1[1]);
		
		int cnt[] = new int[n + 1];
		
		ArrayList<Integer> list[] = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) list[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < m; i++) {
			String input2[] = br.readLine().split(" ");
			int x = Integer.parseInt(input2[0]);
			int y = Integer.parseInt(input2[1]);
			list[x].add(y);
			cnt[y]++;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= n; i++) {
			if(cnt[i] == 0) q.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			int tmp = q.poll();
			sb.append(tmp).append(" ");
			
			for(int i = 0; i < list[tmp].size(); i++) {
				int node = list[tmp].get(i);
				cnt[node]--;
				
				if(cnt[node] == 0) {
					q.offer(node);
				}
			}
		}
		System.out.println(sb);
	}
}
