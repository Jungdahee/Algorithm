package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 실행 시간 244ms
public class BOJ_2468_안전영역 {

	public static int N, cnt, max, map[][];
	public static boolean visited[][];
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		max = Integer.MIN_VALUE;
		
		// 입력 받기
		for(int i = 0; i < N; i++) {
			String input1[] = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input1[j]);
			}
		}
		
		// 잠기는 영역을 탐색하도록 bfs를 구현하면 결국 잠기지 않는 영역을 다시 카운팅 해줘야 하기 때문임.
		// 따라서 높이에 따라 잠기는 영역이 아닌 잠기지 않는 영역을 세는 것이 더 유리함.
		// 영역의 높이 h는 1이상부터 100이하까지이지만 위처럼 잠기지 않는 영역을 탐색해야 하므로 비가 아예 오지 않는 경우도 고려해야 하기 때문에 0부터 시작
		for(int h = 0; h <= 100; h++) {
			visited = new boolean[N][N]; // 매 높이마다 visited 배열 초기화
			cnt = 0; // 잠기지 않는 영역을 세기 위한 변수 셋팅
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] > h && !visited[i][j]) { // 현재 시도하려는 높이보다 높은 곳은 잠기지 않기 때문에 이러한 경우에 탐색 시작
						bfs(i, j, h); 
						cnt++; // 잠기지 않는 영역을 탐색하기 때문에 bfs 수행 횟수가 곧 안전 영역이 됨.
					}
				}
			}
			if(max < cnt) max = cnt; // 최댓값 갱신
		}
		System.out.println(max); // 결과 출력
	}
	
	private static void bfs(int x, int y, int height) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(x, y)); // 현재 지점 큐에 삽입
		visited[x][y] = true; // 방문 표시
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 상하좌우 탐색
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				// 범위를 벗어나는지 체크
				if(checkRange(nx, ny)) continue;
				// 방문 표시
				if(visited[nx][ny]) continue;
				// 인자로 들어온 height 보다 큰 경우만 큐에 삽입(큰 경우만 잠기지 않으므로)
				if(map[nx][ny] > height) {
					q.offer(new Pair(nx, ny)); // 큐에 삽입
					visited[nx][ny] = true; // 방문 표시
				}
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
