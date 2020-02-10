import java.io.*;
import java.util.*;

public class BOJ_2667_단지번호붙이기 {

	static int map[][];
	static int visited[][];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static ArrayList<Integer> count;
 	
	static public class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean check(int x, int y) {
		return (x < 0 || x >= map.length || y < 0 || y >= map.length);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new int[n][n];
		count = new ArrayList<Integer>();
		
		//#1. 2차원 배열 map에 입력 받기
		for(int i = 0; i < n; i++) {
			String input[] = br.readLine().split("");
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		//#2. 1인 지점 bfs 수행
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 1 && visited[i][j] == 0) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		Collections.sort(count);
		for(Integer num : count) System.out.println(num);
	}
	
	public static void bfs(int i, int j) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(i, j));
		visited[i][j] = 1;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			Pair tmp = q.poll();
			int x = tmp.x;
			int y = tmp.y;
			
			for(int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				
				if(check(nx, ny)) continue;
				if(map[nx][ny] == 1 && visited[nx][ny] == 0) {
					q.offer(new Pair(nx, ny));
					visited[nx][ny] = 1;
					cnt++;
				}
			}
		}
		count.add(cnt);
	}
}
