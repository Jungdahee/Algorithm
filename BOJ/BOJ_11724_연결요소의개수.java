package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_11724_연결요소의개수 {

	public static int N, M;
	public static boolean visited[];
	public static ArrayList<Integer> list[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		N = Integer.parseInt(input1[0]);
		M = Integer.parseInt(input1[1]);
		
		list = new ArrayList[N]; // 연결 리스트 구성을 위한 ArrayList
		visited = new boolean[N]; // 중복 처리를 방지하기 위한 boolean 배열
		for(int i = 0; i < N; i++) list[i] = new ArrayList<Integer>(); // ArrayList 생성
		
		for(int i = 0; i < M; i++) {
			String input2[] = br.readLine().split(" ");
			int a = Integer.parseInt(input2[0]) - 1;
			int b = Integer.parseInt(input2[1]) - 1;
			
			list[a].add(b); // 인접 리스트 구성
			list[b].add(a); // 인접 리스트 구성
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			// 방문하지 않은 점에서 dfs를 해야 요소의 연결 개수를 구할 수 있음.
			if(!visited[i]) { 
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	private static void dfs(int cur) {
		// 현재 처리하는 지점 방문 표시
		visited[cur] = true;
		
		// 현재 처리하는 정점과 연결되어 있는 다른 정점들 하나씩 처리
		for(int next : list[cur]) {
			// 그 중 방문하지 않은 정점들 탐색
			if(!visited[next]) dfs(next);
		}
	}
}
