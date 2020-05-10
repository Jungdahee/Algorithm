package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_6497_전력난 {

	public static int M, N, result, parents[];
	public static PriorityQueue<Info> list;
	
	public static class Info implements Comparable<Info> {
		int vertex1, vertex2, weight;
		
		public Info(int vertex1, int vertex2, int weight) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Info o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String input[] = br.readLine().split(" ");
			M = Integer.parseInt(input[0]);
			N = Integer.parseInt(input[1]);
			result = 0;
			
			if(M == 0 && N == 0) break;
			
			list = new PriorityQueue<Info>();
			
			for(int i = 0; i < N; i++) {
				input = br.readLine().split(" ");
				int vertex1 = Integer.parseInt(input[0]);
				int vertex2 = Integer.parseInt(input[1]);
				int weight = Integer.parseInt(input[2]);
				
				list.add(new Info(vertex1, vertex2, weight));
			}
			
			parents = new int[N];
			Arrays.fill(parents, -1);
			
			while(!list.isEmpty()) {
				Info info = list.poll();
				
				if(!union(info.vertex1, info.vertex2)) result += info.weight;
			}
			System.out.println(result);
		}
	}
	
	private static int find(int x) {
		if(parents[x] < 0) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if(xRoot != yRoot) {
			parents[yRoot] = xRoot;
			return true;
		}
		return false;
	}
}
