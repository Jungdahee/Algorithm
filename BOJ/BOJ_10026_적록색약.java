package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_적록색약 {

	public static int N, cnt1, cnt2;
	public static char ch, map[][];
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
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
		
		// 적록색약이 아닌 경우
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs(i, j, 0);
					cnt1++;
				}
			}
		}
		
		visited = new boolean[N][N];
		
		// 적록색약인 경우
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs(i, j, 1);
					cnt2++;
				}
			}
		}
		
		// 결과 출력
		System.out.println(cnt1 + " " + cnt2);
	}
	
	public static void bfs(int x, int y, int mode) { // mode에 따라서 처리 방식 상이
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(x, y));
		visited[x][y] = true;
		ch = map[x][y];
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				// 탐색하려는 위치가 범위를 벗어나는지 체크
				if(checkRange(nx, ny)) continue;
				// 이전에 방문했었는지 체크
				if(visited[nx][ny]) continue;
				// 적록색약이 아닌 경우이며, 처리하려는 곳이 이전에 처리한 알파벳과 같은 알파벳인 경우
				if(mode == 0 && map[nx][ny] == ch) {
					q.offer(new Pair(nx, ny)); // 큐에 삽입
					visited[nx][ny] = true; // 방문 표시
				} 
				// 적록색약이 경우
				else if(mode == 1){
					// 이전에 처리한 알파벳이 R이거나 G인경우
					if(ch == 'R' || ch == 'G') {
						// 탐색하려는 곳도 R이거나 G인 경우 같이 처리 가능
						if(map[nx][ny] == 'R' || map[nx][ny] == 'G') {
							q.offer(new Pair(nx, ny)); // 큐에 삽입
							visited[nx][ny] = true; // 방문 표시
						}
					} 
					// 위 경우(R과 G)가 아닌 경우 같은 알파벳인 경우만 처리 가능
					else if(ch == map[nx][ny]) {
						q.offer(new Pair(nx, ny)); // 큐에 삽입
						visited[nx][ny] = true; // 방문 표시
					}
				}
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
