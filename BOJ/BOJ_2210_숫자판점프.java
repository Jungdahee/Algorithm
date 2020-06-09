package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_2210_숫자판점프 {

	public static String map[][];
	public static Set<String> set;
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new String[5][5];
		set = new HashSet<String>();
		
		for(int i = 0; i < 5; i++) {
			String input[] = br.readLine().split(" ");
			for(int j = 0; j < 5; j++) {
				map[i][j] = input[j];
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				dfs(i, j, 0, "");
			}
		}
		
		System.out.println(set.size());
	}
	
	private static void dfs(int x, int y, int cnt, String s) {
		if(cnt == 6) {
			set.add(s);
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(checkRange(nx, ny)) continue;
			dfs(nx, ny, cnt + 1, s + map[nx][ny]);
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= 5 || y < 0 || y >= 5;
	}
}
