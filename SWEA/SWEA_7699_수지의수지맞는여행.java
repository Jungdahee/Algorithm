package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행 시간 1673ms
public class SWEA_7699_수지의수지맞는여행 {

	public static int R, C, result;
	public static char island[][];
	public static boolean visited[];
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

		for(int t = 1; t <= tc; t++) {
			String input1[] = br.readLine().split(" ");
			R = Integer.parseInt(input1[0]);
			C = Integer.parseInt(input1[1]);

			island = new char[R][C];
			visited = new boolean[26];
			result = 0;

			// input값 입력 받기
			for(int i = 0; i < R; i++) {
				String input2 = br.readLine();
				island[i] = input2.toCharArray();
			}

			// 아스키 코드 값을 이용하여 명물 구경 여부 판단
			visited[island[0][0] - 65] = true;
			// 여행의 시작이 1행 1열이므로 (0, 0)부터 탐색 시작
			go(0, 0, 1);
			System.out.println(result);
		}
	}

	// dfs로 탐색
	public static void go(int x, int y, int cnt) {
		// 지금 탐색하고 있는 경로가 기존 탐색한 경로보다 더 명물을 많이 탐색했다면 갱신
		if(result < cnt)  result = cnt;
		
		// 상하좌우 네 방향 처리
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			// 탐색하려는 곳의 범위 체크
			if(checkRange(nx, ny)) continue;
			// 봤던 명물이 아니라면
			if(!visited[island[nx][ny] - 65]) {
				// 본 명물로 처리
				visited[island[nx][ny] - 65] = true;
				// 그쪽으로 또 탐색 시작
				go(nx, ny, cnt + 1);
				// 탐색 후 돌아왔다면 본 명물로 처리했던 것을 보지 않은 상태로 변경
				visited[island[nx][ny] - 65] = false;
			}
		}
	}

	public static boolean checkRange(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C;
	}
}
