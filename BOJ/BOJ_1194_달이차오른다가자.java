package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1194_달이차오른다가자 {

	public static int N, M, startX, startY, result;
	public static char map[][];
	public static boolean visited[][][];
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};

	public static class Pair {
		int x, y, state, dist;

		public Pair(int x, int y, int state, int dist) {
			this.x = x;
			this.y = y;
			this.state = state;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new char[N][M];
		visited = new boolean[64][N][M];
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split("");
			for(int j = 0; j < M; j++) {
				map[i][j] = input[j].charAt(0);
				if(map[i][j] == '0') {
					startX = i;
					startY = j;
				}
			}
		}
		bfs();
		System.out.println(result == 0 ? "-1" : result);
	}

	private static void bfs() {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(startX, startY, 0, 0));
		visited[0][startX][startY] = true; // 시작점 셋팅
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 탐색 종료
			if(map[cur.x][cur.y] == '1') {
				result = cur.dist;
				break;
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				int newKey = cur.state;
				
				if(checkRange(nx, ny)) continue;
				if(map[nx][ny] == '#') continue;
				// 문을 만난 경우
				if(Character.isUpperCase(map[nx][ny])) {
					// 열 수 있는지 없는지 판단
					if((newKey & 1 << (map[nx][ny] - 'A')) == 0) continue;
					
				} else if(Character.isLowerCase(map[nx][ny])) {
					// 키 값 갱신
					newKey |= (1 << map[nx][ny] - 'a');
				}
				
				if(!visited[cur.state][nx][ny]) {
					visited[cur.state][nx][ny] = true;
					q.offer(new Pair(nx, ny, newKey, cur.dist + 1));
				}
			}
		}
	}

	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
