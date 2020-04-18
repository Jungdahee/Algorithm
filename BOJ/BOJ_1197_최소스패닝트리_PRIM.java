import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_1197_최소스패닝트리_PRIM {

	public static class Vertex implements Comparable<Vertex>{
		int v, dist;

		Vertex(int v, int dist){
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		int V = Integer.parseInt(input1[0]);
		int E = Integer.parseInt(input1[1]);

		int result = 0;

		List<Vertex> list[] = new ArrayList[V];

		for(int i = 0; i < V; i++) list[i] = new ArrayList<Vertex>();

		// 간선의 정보와 가중치 입력 받기
		for(int i = 0; i < E; i++) {
			String input2[] = br.readLine().split(" ");
			int a = Integer.parseInt(input2[0]) - 1;
			int b = Integer.parseInt(input2[1]) - 1;
			int c = Integer.parseInt(input2[2]);
			// 인접 리스트 구성
			list[a].add(new Vertex(b, c));  
			list[b].add(new Vertex(a, c));  
		}

		// 선택되었는지 아닌지 판단하기 위한 boolean 배열
		boolean visited[] = new boolean[V];
		// 현재 선택된 정점들로부터 도달할 수 있는 최소 거리 저장 배열
		int distance[] = new int[V];

		// 모두 최대 값으로 key를 갱신
		Arrays.fill(distance, Integer.MAX_VALUE);

		distance[0] = 0; // 처음 선택한 정점은 거리 0
		int cnt = 0;

		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		q.offer(new Vertex(0, distance[0]));

		while(true) {
			Vertex cur = q.poll();
			if(visited[cur.v]) continue; 
			visited[cur.v] = true;
			result += cur.dist;
			cnt++;

			if(cnt == V) break;

			for(Vertex v : list[cur.v]) {
				if(!visited[v.v] && distance[v.v] > v.dist) {
					distance[v.v] = v.dist;
					q.offer(new Vertex(v.v, distance[v.v]));
				}
			}
		}
		System.out.println(result);
	}
}
