package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_7576_토마토 {

	static int zeroCnt, oneCnt, n, m, max, map[][];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static Queue<Data> q;

	static class Data {
		int x, y, day;
		public Data(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	
	private static boolean checkRange(int x, int y) { //범위 체크 메소드
		return x < 0 || x >= n || y < 0 || y >= m;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		m = Integer.parseInt(input[0]);
		n = Integer.parseInt(input[1]);

		map = new int[n][m];
		q = new LinkedList<Data>();

		//#1. map에 입력 값 넣으면서 0과 1 개수 세기
		for(int i = 0; i < n; i++) {
			String input1[] = br.readLine().split(" ");
			for(int j = 0; j < m; j++) {
				int tmp = Integer.parseInt(input1[j]);
				map[i][j] = tmp;
				if(tmp == 0) zeroCnt++;
				if(tmp == 1) oneCnt++;
			}
		}

		//#2. 조건 1) 토마토가 모두 익어있으면 0 출력
		if(oneCnt == n * m) System.out.println("0");

		//#3. 상태가 1인 지점 모두 queue에 삽입
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					q.offer(new Data(i, j, 0));
				}
			}
		}
		
		bfs(); //퍼트리기
		
		//#7. 결과 출력
		if(zeroCnt != 0) System.out.println("-1"); 
		else System.out.println(max);
	}
	
	private static void bfs() {
		while(!q.isEmpty()) { 
			Data tmp = q.poll();
			int cx = tmp.x;
			int cy = tmp.y;
			int day = tmp.day;
			
			for(int k = 0; k < 4; k++){ //상하좌우 탐색
				int nx = cx + dx[k];
				int ny = cy + dy[k];
				
				if(checkRange(nx, ny)) continue; //맵 범위 체크
				if(map[nx][ny] == 0) {
					q.offer(new Data(nx, ny, day + 1));
					map[nx][ny] = day + 1;
					zeroCnt--;
				}
			}
			
			//#5. max값 갱신
			if(day > max) max = day; 
		}
	}
}
