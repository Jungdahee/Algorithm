package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

// 실행 시간 96ms
public class BOJ_4386_별자리만들기 {

	public static int N, parents[];
	public static float vertex1, vertex2;
	public static ArrayList<Edge> adjList;
	
	public static class Pair {
		float x, y;
		
		public Pair(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class Edge {
		int vertex1, vertex2;
		float weight;
		
		public Edge(int vertex1, int vertex2, float weight) {
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		ArrayList<Pair> list = new ArrayList<Pair>();
		
		for(int i = 0; i < N; i++) {
			String input[] = br.readLine().split(" ");
			float x = Float.parseFloat(input[0]);
			float y = Float.parseFloat(input[1]);
			
			list.add(new Pair(x, y));
		}
		
		adjList = new ArrayList<Edge>();
		for(int i = 0; i < list.size() - 1; i++) {
			Pair p1 = list.get(i);
			for(int j = i + 1; j < list.size(); j++) {
				Pair p2 = list.get(j);
				float dist = (float) Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
				adjList.add(new Edge(i, j, dist));
			}
		}
		
		parents = new int[N];
		Arrays.fill(parents, -1);
		
		Collections.sort(adjList, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return Float.compare(o1.weight, o2.weight);
			}
		});
		
		int cnt = 0;
		float result = 0;
		for(int i = 0; i < adjList.size(); i++) {
			Edge e = adjList.get(i);
			
			if(union(e.vertex1, e.vertex2)) {
				result += e.weight;
				cnt++;
			}
			if(cnt == N - 1) break;
		}
		System.out.println(Math.round(result * 100) / 100.0);
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
