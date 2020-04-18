package BOJ;

import java.io.*;
import java.util.*;

// 실행 시간 1128ms
public class BOJ_1753_최단경로 {

	/*
	 * 1 <= V <= 20,000
	 * 1 <= E <= 300,000
	 */
	public static class Vertex implements Comparable<Vertex> {
		int v, dist;
		
		public Vertex(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.dist - o.dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		int V = Integer.parseInt(input1[0]); // 정점의 개수
		int E = Integer.parseInt(input1[1]); // 간선의 개수
		int start = Integer.parseInt(br.readLine()) - 1; // 시작점
		
		// 인접 리스트를 저장할 리스트 배열
		ArrayList<Vertex> list[] = new ArrayList[V];
		// 각 정점으로 가는 최단 경로를 저장하기 위한 distance 배열
		int d[] = new int[V];
		// 선택한 정점인지 아닌지 판단하기 위한 boolean 배열
		boolean visited[] = new boolean[V];
		// 최소 간선 비용을 가지는 정점을 빠르게 찾기 위한 pq
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		
		for(int i = 0; i < V; i++) list[i] = new ArrayList<Vertex>();
		
		for(int i = 0; i < E; i++) {
			String input2[] = br.readLine().split(" ");
			int a = Integer.parseInt(input2[0]) - 1;
			int b = Integer.parseInt(input2[1]) - 1;
			int c = Integer.parseInt(input2[2]);
			
			// 인접 리스트 구성
			list[a].add(new Vertex(b, c));
		}
		
		// 큰 값으로 거리 갱신
		Arrays.fill(d, Integer.MAX_VALUE);
		
		// 시작 정점의 거리는 0
		d[start] = 0;
		// 시작 정점, 거리의 Vertex pq에 삽입
		q.offer(new Vertex(start, d[start]));
		
		while(!q.isEmpty()) {
			Vertex cur = q.poll();
			
			// 현재 처리하고 있는 정점이 이미 선택된 정점이라면 pass
			if(visited[cur.v]) continue;
			//아니라면 선택
			visited[cur.v] = true;
			
			// 선택한 정점을 기준으로 연결되어 있는 정점들에 대해서 업데이트 진행
			for(Vertex v : list[cur.v]) {
				/*
				 * 연결되어 있는 정점 중 선택하지 않았고 
				 * 경유지를 거쳐서 가는 경우가 갱신되어 왔던 거리보다 작다면 업데이트
				 */
				if(!visited[v.v] && d[cur.v] + v.dist < d[v.v]) {
					d[v.v] = d[cur.v] + v.dist;
					q.offer(new Vertex(v.v, d[v.v]));
				}
			}
		}
		
		// d배열에 시작점으로부터 각 정점으로 가는 최단 경로의 가중치의 합이 저장되어 있음.
		for(Integer dist : d) {
			// dist가 Integer.MAX_VALUE라는 것은 갱신이 되지 않았다는 의미이므로 경로가 없다는 의미
			if(dist == Integer.MAX_VALUE) System.out.println("INF");
			// 그렇지 않은 경우 dist 출력
			else System.out.println(dist);
		}
	}
}
