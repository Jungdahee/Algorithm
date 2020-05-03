package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 실행 시간 124ms
public class BOJ_1012_유기농배추 {

	public static int M, N, num, result, map[][];
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
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-- > 0) {
			String input[] = br.readLine().split(" ");
			M = Integer.parseInt(input[0]);
			N = Integer.parseInt(input[1]);
			num = Integer.parseInt(input[2]);
			
			map = new int[M][N];
			visited = new boolean[M][N];
			result = 0;
			
			// 배추가 심어진 위치 1로 표시
			for(int i = 0; i < num; i++) {
				input = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				map[x][y] = 1;
			}
			
			// map에서 1인 지점이 배추가 있는 지점이므로 여기에 흰지렁이를 풀면 됨.
			// 따라서 bfs 수행 횟수가 곧 필요한 흰지렁이의 수가 됨.
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);
						result++;
					}
				}
			}
			
			// 결과 출력
			System.out.println(result);
		}
	}
	
	private static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(x, y));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 인접한 상하좌우로 지렁이가 갈 수 있는지 체크
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				// 범위 체크
				if(checkRange(nx, ny)) continue;
				// 처리한 지점인지 체크
				if(visited[nx][ny]) continue;
				// 배추가 심어진 곳인지 체크
				if(map[nx][ny] != 1) continue;
				q.offer(new Pair(nx, ny));
				visited[nx][ny] = true;
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= M || y < 0 || y >= N;
	}
}
