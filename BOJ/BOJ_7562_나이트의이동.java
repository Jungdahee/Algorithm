package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 실행 시간 224ms
public class BOJ_7562_나이트의이동 {

	public static int N, result, endX, endY, map[][];
	public static int dx[] = {-1, -2, -2, -1, 1, 2, 2, 1};
	public static int dy[] = {-2, -1, 1, 2, -2, -1, 1, 2};
	
	// 나이트의 위치와 이동 횟수를 담을 Knight 클래스
	public static class Knight {
		int x, y, cnt;
		
		public Knight(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		// 테스트케이스만큼 수행
		while(tc-- > 0) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			String input1[] = br.readLine().split(" ");
			String input2[] = br.readLine().split(" ");
			
			// 나이트가 가야할 최종 위치를 담을 endX, endY
			endX = Integer.parseInt(input2[0]);
			endY = Integer.parseInt(input2[1]);
			
			// 출발점을 기준으로 bfs 수행
			bfs(Integer.parseInt(input1[0]), Integer.parseInt(input1[1]));
			// 결과 출력
			System.out.println(result);
		}
	}
	
	private static void bfs(int startX, int startY) {
		Queue<Knight> q = new LinkedList<Knight>();
		q.offer(new Knight(startX, startY, 0)); // 출발지점 queue에 삽입
		map[startX][startY] = 1; // visited 표시를 따로 하지 않고 map에 1로 표시
		
		while(!q.isEmpty()) {
			Knight cur = q.poll(); 
			
			// 지금 처리하려는 Knight의 위치가 도착지와 같다면 수행 종료
			if(cur.x == endX && cur.y == endY) {
				result = cur.cnt; // 이동 횟수 저장하고 종료
				return;
			}
			
			// 8 방향 탐색 수행
			for(int d = 0; d < 8; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				// 다음에 가려는 위치가 범위를 벗어나는지 범위 체크
				if(checkRange(nx, ny)) continue;
				// 0이면 아직 가보지 않은 곳이므로 queue에 삽입
				if(map[nx][ny] == 0) {
					// 한번 더 이동을 하는 것이므로 cnt + 1로 Knight 생성
					q.offer(new Knight(nx, ny, cur.cnt + 1));
					map[nx][ny] = 1; // 방문 표시
				}
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
