package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2206_벽부수고이동하기 {

	public static int N, M, result, map[][];
	public static boolean visited[][][];
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};

	public static class Pair {
		int x, y, dist; // x, y좌표와 거리
		boolean state; // 벽 제거 유무

		public Pair(int x, int y, int dist, boolean state) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.state = state;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		map = new int[N][M];
		// 벽 제거 유무에 따른 visited 관리를 위한 배열
		visited = new boolean[2][N][M];

		for(int i = 0; i < N; i++) {
			input = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}

		bfs();
		System.out.println(result == 0 ? "-1" : result);
	}

	private static void bfs() {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(0, 0, 1, false)); // 시작점 queue에 삽입, 벽을 제거한 적이 없으므로 false로 셋팅
		// 1차원 배열의 0은 아직 벽을 제거한 적이 없는 visited배열, 1은 벽을 제거한 적이 있는 visited배열
		visited[0][0][0] = true; 

		while(!q.isEmpty()) {
			Pair cur = q.poll();

			// 마지막 지점에 도달했으면 result값 셋팅 후 break;
			if(cur.x == N - 1 && cur.y == M - 1) {
				result = cur.dist;
				break;
			}

			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				// 범위 체크 
				if(checkRange(nx, ny)) continue;
				// 현재 처리하는 원소가 벽을 깼었던 상태
				if(cur.state) { 
					// 같은 상태로 이미 방문한 적이 있다면 skip
					if(visited[1][nx][ny]) continue;
					// 이미 벽을 한번 깼기 때문에 벽(1)을 만났다면 skip
					if(map[nx][ny] == 1) continue;
					// 위 경우를 제외하고는 갈 수 있는 상태이므로 진행
					q.offer(new Pair(nx, ny, cur.dist + 1, true));
					// 벽을 제거한 상태로 탐색을 진행하는 원소이므로 1로 셋팅
					visited[1][nx][ny] = true;
				} 
				// 현재 처리하는 원소가 벽을 깨지 않았던 상태
				else { 
					// 같은 상태로 이미 방문한 적이 있다면 skip
					if(visited[0][nx][ny]) continue;
					// 탐색하려는 곳이 벽이라면 깨는 시도
					if(map[nx][ny] == 1) {
						// 이떄 객체의 상태를 벽을 깼기 때문에 true로 셋팅
						q.offer(new Pair(nx, ny, cur.dist + 1, true));
						// 이제 벽을 제거한 상태를 가지고 있는 visited[1]에 방문 표시
						visited[1][nx][ny] = true;
					}else { // 0인 지점
						// 깨지 않고 그대로 진행하므로 false로 셋팅
						q.offer(new Pair(nx, ny, cur.dist + 1, false));
						// 벽을 제거하지 않은 상태를 가지고 있는 visited[0]에 방문 표시
						visited[0][nx][ny] = true;
					}
				}
			}
		}
	}

	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
