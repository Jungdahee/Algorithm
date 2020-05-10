package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_1647_도시분할계획 {

	public static int N, M, parents[];
	public static PriorityQueue<Edge> adjList; 
	
	public static class Edge implements Comparable<Edge> {
		int node1, node2, weight;
		
		public Edge(int node1, int node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// 건설 비용(가중치) 오름차순으로 정렬
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		adjList = new PriorityQueue<Edge>();
		
		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int to = Integer.parseInt(input[0]);
			int from = Integer.parseInt(input[1]);
			int weight = Integer.parseInt(input[2]);
			
			// 간선리스트 생성
			adjList.add(new Edge(to, from, weight));
		}

		parents = new int[N + 1]; // 크루스칼을 쓰는 데 사용할 uion-find를 위한 parents 배열
		Arrays.fill(parents, -1); // 배열 전체를 -1로 초기화
		
		int cnt = 0;
		int result = 0;
		while(!adjList.isEmpty()) {
			Edge e = adjList.poll(); // 간선 정보 하나 처리
			
			// union 연산으로 정점 연결
			// 간선 정보에 등록되어 있던 정점1과 정점2가 연결 가능한지 확인
			if(union(e.node1, e.node2)) { 
				// 연결되었으므로 가중치 증가
				result += e.weight;
				cnt++;
			}
			// 연결된 정점 수가 전체 집의 수 - 2면 종료
			// 문제 해결 방법 1. n - 1개의 길을 건설하고 마지막으로 연결된 가중치를 빼주는 법(가장 마지막에 연결되었다는 건 가장 가중치가 크다는 의미이므로)
			// 문제 해결 방법 2. n - 2개의 길을 건설하는 방법(자연스럽게 나머지 하나의 집이 다른 마을이 되므로 가중치 계산 필요없음)
			if(cnt == N - 2) break;
		}
		// 결과 출력
		System.out.println(result);
	}
	
	private static int find(int x) {
		// 자신이 부모이면 자신을 그대로 리턴
		if(parents[x] < 0) return x;
		// 아니라면 계속 타고 올라가서 부모를 찾아서 패스 압축
		return parents[x] = find(parents[x]);
	}
	
	private static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		// 두 정점의 원소가 다른 경우
		if(xRoot != yRoot) {
			// yRoot의 부모를 xRoot로 변경
			parents[yRoot] = xRoot;
			return true;
		}
		return false;
	}
}
