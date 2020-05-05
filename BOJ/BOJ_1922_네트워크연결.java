package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 실행 시간 602ms
public class BOJ_1922_네트워크연결 {

	/*
	 * 간선이 많으므로 프림으로 접근
	 */
	public static class Vertex implements Comparable<Vertex>{
		int v, dist;

		public Vertex(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.dist - o.dist; // 오름차순으로 정렬
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		ArrayList<Vertex> list[] = new ArrayList[V]; // 인접 리스트 생성
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(); // 빠른 최소 간선 비용을 가지는 정점을 찾기 위한 pq
		int d[] = new int[V]; // 정점의 최소 비용을 저장할 d 배열
		boolean visited[] = new boolean[V]; // 선택 유무를 판단하기 위한 boolean 배열

		// 인접 리스트 생성
		for(int i = 0; i < V; i++) list[i] = new ArrayList<Vertex>();

		for(int i = 0; i < E; i++) {
			String input1[] = br.readLine().split(" ");
			int a = Integer.parseInt(input1[0]) - 1;
			int b = Integer.parseInt(input1[1]) - 1;
			int c = Integer.parseInt(input1[2]);

			// 인접 리스트 구ㅜ성
			list[a].add(new Vertex(b, c));
			list[b].add(new Vertex(a, c));
		}

		// 모든 정점의 거리를 큰 값으로 셋팅
		Arrays.fill(d, Integer.MAX_VALUE);

		// 임의의 출발점 선택 -> 자신에게 가는 거리는 0이므로 0으로 설정
		d[0] = 0;
		pq.offer(new Vertex(0, d[0]));

		int cnt = 0; // 몇 개의 정점을 처리했는지 비교하기 위한 변수
		int result = 0; // 결과로 출력된 총 최소 비용의 값을 담을 변수

		while(true){
			// pq에서 최소 간선 비용을 가지는 정점 하나를 추출
			Vertex cur = pq.poll();

			// 추출한 정점이 방문되었었으면 continue
			if(visited[cur.v]) continue;
			// 만약 선택되지 않았다면 선택
			visited[cur.v] = true;
			// 선택한 정점이 가지는 거리를 결과에 더해줌
			result += cur.dist;
			// 하나의 정점을 처리했기 때문에 카운트 증가
			cnt++;

			// 증가한 카운트 변수가 V의 수와 같아진다면 모든 정점을 처리한 것이므로 종료
			if(cnt == V) break;

			// 선택한 정점과 연결된 다른 정점들에 대해서 검사
			for(Vertex v : list[cur.v]) {
				// 연결된 정점 중에 선택이 되지 않았고 기존에 알고 있던 거리보다 짧은 거리를 가지고 있다면
				if(!visited[v.v] && v.dist < d[v.v]) {
					// d 배열 갱신
					d[v.v] = v.dist;
					// 그리고 처리를 위해 pq에 삽입
					pq.offer(new Vertex(v.v, d[v.v]));
				}
			}
		}
		// 결과 출력
		System.out.println(result);
	}
}
