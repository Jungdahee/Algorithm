package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_6593_상범빌딩 {
	
	public static int L, R, C, startL, startR, startC;
	public static char map[][][];
	public static boolean visited[][][];
	public static int dx[] = {-1, 1, 0, 0, 0, 0};
	public static int dy[] = {0, 0, -1, 1, 0, 0};
	public static int dz[] = {0, 0, 0, 0, -1, 1};
	
	public static class Loc {
		int z, x, y, dist;
		
		public Loc(int z, int x, int y, int dist) {
			this.z = z;
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String input[] = br.readLine().split(" ");
			
			if(input[0].equals("0")) return;
			
			L = Integer.parseInt(input[0]);
			R = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
			
			map = new char[L][R][C];
			visited = new boolean[L][R][C];
			
			for(int i = 0; i < L; i++) {
				for(int j = 0; j < R; j++) {
					input = br.readLine().split("");
					for(int k = 0; k < C; k++) {
						map[i][j][k] = input[k].charAt(0);
						if(map[i][j][k] == 'S') {
							startL = i;
							startR = j;
							startC = k;
						}
					}
				}
				br.readLine();
			}
			
			int result = bfs();
			
			if(result > 0) System.out.println("Escaped in " + result + " minute(s).");
			else System.out.println("Trapped!");
			
		}
	}
	
	private static int bfs() {
		Queue<Loc> q = new LinkedList<Loc>();
		q.offer(new Loc(startL, startR, startC, 0));
		visited[startL][startR][startC] = true;
		
		while(!q.isEmpty()) {
			Loc cur = q.poll();
			
			if(map[cur.z][cur.x][cur.y] == 'E') {
				return cur.dist;
			}
			
			for(int d = 0; d < 6; d++) {
				int nz = cur.z + dz[d];
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(checkRange(nz, nx, ny)) continue;
				if(map[nz][nx][ny] == '#') continue;
				if(visited[nz][nx][ny]) continue;
				
				q.offer(new Loc(nz, nx, ny, cur.dist + 1));
				visited[nz][nx][ny] = true;
			}
		}
		
		return 0;
	}
	
	private static boolean checkRange(int z, int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C || z < 0 || z >= L;
	}
}
