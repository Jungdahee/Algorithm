package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 실행 시간 528ms
public class BOJ_9372_상근이의여행 {

	public static int N, M, result;
	public static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		while(tc-- > 0) {
			String input1[] = br.readLine().split(" ");
			N = Integer.parseInt(input1[0]);
			M = Integer.parseInt(input1[1]);
			result = 0;

			ArrayList<Integer> list[] = new ArrayList[N];
			visited = new boolean[N];
			
			for(int i = 0 ; i < N; i++) list[i] = new ArrayList<Integer>();

			for(int i = 0; i < M; i++) {
				String input2[] = br.readLine().split(" ");
				int a = Integer.parseInt(input2[0]) - 1;
				int b = Integer.parseInt(input2[1]) - 1;

				// 인접 리스트 구성
				list[a].add(b);
				list[b].add(a);
			}
			
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(0); // 연결 그래프이므로 1번 나라부터 시도
			int cnt = 0;
			
			while(true) {
				// 현재 처리할 나라
				Integer cur = q.poll();
				
				// 나라를 이미 방문했다면 continue
				if(visited[cur]) continue;
				// 아니라면 방문했다고 표시
				visited[cur] = true;
				result++;
				// 하나의 나라를 처리했으므로 카운트 증가
				cnt++;
				
				// 모든 나라를 다 방문했다면 종료
				if(cnt == N - 1) break;
				
				// 현재 방문한 나라에서 갈 수 있는 모든 나라 검사
				for(Integer v : list[cur]) {
					// 방문하지 않았다면 q에 삽입하여 다음에 갈 목적지로 결정
					if(!visited[v]) {
						q.offer(v);
					}
				}
			}
			// 결과 출력
			System.out.println(result);
		}
	}
}
