package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 실행 시간 76ms
public class BOJ_2606_바이러스_dfs {

	public static int node, edge, result;
	public static boolean visited[];
	public static ArrayList<Integer> network[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		node = Integer.parseInt(br.readLine());
		edge = Integer.parseInt(br.readLine());
		
		network = new ArrayList[node]; // 인접 리스트 구성을 위한 ArrayList 생성
		visited = new boolean[node]; // 중복 처리를 위한 boolean 배열
		
		for(int i = 0; i < network.length; i++) 
			network[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < edge; i++) {
			String input[] = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]) - 1; // 인덱스를 0부터 사용할 것이므로 -1
			int b = Integer.parseInt(input[1]) - 1;
			network[a].add(b); // a와 b 연결 정보 저장
			network[b].add(a); // b와 a 연결 정보 저장
		}
		
		// 1번 컴퓨터부터 시작하기 때문에 여기 코드에선 0부터 시작
		dfs(0);
		
		// 1번 컴퓨터가 감염시킨 컴퓨터 수를 세는 것이므로 1번 컴퓨터도 카운팅된 result에서 - 1
		System.out.println(result - 1);
	}
	
	private static void dfs(int node) {
		// 현재 노드 방문 처리
		visited[node] = true;
		result++;
		
		// 현재 노드와 연결되어 있고 방문하지 않은 노드들 차례대로 방문
		for(int vertex : network[node]) {
			if(!visited[vertex]) dfs(vertex);
		}
	}
}
