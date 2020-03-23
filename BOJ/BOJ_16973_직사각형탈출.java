package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16973_직사각형탈출 {

	static int row, col, h, w, startX, startY, endX, endY, map[][];
	static boolean visited[][];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	
	public static class Info {
		int x, y, depth;
		
		public Info(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		row = Integer.parseInt(input1[0]);
		col = Integer.parseInt(input1[1]);
		
		//map 생성
		map = new int[row][col];
		for(int i = 0; i < row; i++) {
			String input2[] = br.readLine().split(" ");
			for(int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(input2[j]);
			}
		}
		
		String input3[] = br.readLine().split(" ");
		h = Integer.parseInt(input3[0]) - 1;
		w = Integer.parseInt(input3[1]) - 1;
		startX = Integer.parseInt(input3[2]) - 1;
		startY = Integer.parseInt(input3[3]) - 1;
		endX = Integer.parseInt(input3[4]) - 1;
		endY = Integer.parseInt(input3[5]) - 1;
		
		visited = new boolean[row][col];
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(startX, startY, 0));
		visited[startX][startY] = true;
		
		while(!q.isEmpty()) {
			Info info = q.poll();
			
			//종료 조건(목적지에 도착한 경우)
			if(info.x == endX && info.y == endY) return info.depth;
			
			for(int i = 0; i < 4; i++) {
				int nx = info.x + dx[i];
				int ny = info.y + dy[i];
				
				if(checkCondition(nx, ny, i)) {
					if(visited[nx][ny]) continue;
					
					q.offer(new Info(nx, ny, info.depth + 1));
					visited[nx][ny] = true;
				}
			}
		}
		return -1;
	}
	
	private static boolean checkCondition(int x, int y, int idx) {
		//상 탐색
		if(idx == 0) {
			if(x >= 0 && y + w < col) {
				for(int i = y; i <= y + w; i++) {
					if(map[x][i] == 1) return false;
				}
			} else return false;
		} 
		//하 탐색
		else if(idx == 1) {
			if(x + h < row && y + w < col) {
				for(int i = y; i <= y + w; i++) {
					if(map[x + h][i] == 1) return false;
				}
			} else return false;
		}
		//좌 탐색
		else if(idx == 2) {
			if(x + h < row && y >= 0) {
				for(int i = x; i <= x + h; i++) {
					if(map[i][y] == 1) return false;
				}
			} else return false;
		}
		//우 탐색
		else {
			if(x + h < row && y + w < col) {
				for(int i = x; i <= x + h; i++) {
					if(map[i][y + w] == 1) return false;
				}
			} else return false;
		}
		return true;
	}
}
