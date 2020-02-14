package BOJ;

import java.io.*;

public class BOJ_3109_빵집 {

	static int r, c, cnt;
	static String map[][];
	static boolean visited[][];
	static int dx[] = {-1, 0, 1};
	static int dy[] = {1, 1, 1};
	static boolean isFlag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		r = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);
		map = new String[r][c];
		visited = new boolean[r][c];
		
		for(int i = 0; i < r; i++) {
			String input1[] = br.readLine().split("");
			for(int j = 0; j < c; j++) {
				map[i][j] = input1[j];
			}
		}
		boolean result = false;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < 1; j++) {
				if(map[i][j].equals(".") && visited[i][j] == false) {
					isFlag = false;
					result = go(i, j);
					if(result) cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
	
	static boolean go(int x, int y) {
		visited[x][y] = true;
		
		if(y == c - 1) isFlag = true;
		
		for(int k = 0; k < 3; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			
			if(checkRange(nx, ny)) continue;
			if(map[nx][ny].equals(".") && visited[nx][ny] == false) {
				go(nx, ny);
				if(isFlag) return true;
			}
		}
		return false;
	}
	
	static boolean checkRange(int x, int y) {
		return x < 0 || x >= r | y < 0 || y >= c;
	}
}
