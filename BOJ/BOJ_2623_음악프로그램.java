package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2623_음악프로그램 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		int n = Integer.parseInt(input1[0]); //가수 수
		int m = Integer.parseInt(input1[1]); //스탭 수
		
		int cnt[] = new int[n + 1];
		ArrayList<Integer> list[] = new ArrayList[n + 1];
		
		for(int i = 1; i <= n; i++) list[i] = new ArrayList<Integer>();
		
		int start = 0, end = 0;
		for(int i = 0; i < m; i++) {
			String input2[] = br.readLine().split(" ");
			for(int j = 1; j < input2.length - 1; j++) {
				start = Integer.parseInt(input2[j]);
				end = Integer.parseInt(input2[j + 1]);
				list[start].add(end);
				cnt[end]++;
			}
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= n; i++) {
			if(cnt[i] == 0) q.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		int res = 0;
		while(!q.isEmpty()) {
			int tmp = q.poll();
			sb.append(tmp).append("\n");
			res++;
			
			for(int i = 0; i < list[tmp].size(); i++) {
				int node = list[tmp].get(i);
				cnt[node]--;
				
				if(cnt[node] == 0) q.offer(node);
			}
		}
		
		if(res != n) System.out.println("0");
		else System.out.println(sb);
	}
}
