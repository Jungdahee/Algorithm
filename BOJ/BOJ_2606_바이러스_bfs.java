package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2606_바이러스_bfs {

	public static int node, edge, result, network[][];
	public static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		node = Integer.parseInt(br.readLine());
		edge = Integer.parseInt(br.readLine());
		
		network = new int[node][node]; // 인접 리스트 구성을 위한 ArrayList 생성
		visited = new boolean[node]; // 중복 처리를 위한 boolean 배열

		for(int i = 0; i < edge; i++) {
			String input[] = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]) - 1; // 인덱스를 0부터 사용할 것이므로 -1
			int b = Integer.parseInt(input[1]) - 1;
			network[a][b] = 1; // a와 b 연결 정보 저장
			network[b][a] = 1; // b와 a 연결 정보 저장
		}
		
		bfs(0); // 1번 컴퓨터부터 시작
		
		// 1번 컴퓨터를 제외하고부터 세었기 때문에 따로 - 1할 필요가 없음.
		System.out.println(result);
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start); // 1번 컴퓨터 큐에 삽입
		visited[start] = true; // 방문 처리
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			// 컴퓨터 개수만큼 수행하면서 연결된 컴퓨터 확인
			for(int i = 0; i < network.length; i++) {
				// 이미 감염했는지 체크
				if(visited[i]) continue;
				// 연결된 컴퓨터가 맞는지 체크
				if(network[cur][i] == 0) continue;
				q.offer(i);
				visited[i] = true;
				result++;
			}
		}
	}
}
