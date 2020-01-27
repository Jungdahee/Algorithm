import java.io.*;
import java.util.*;
public class BOJ_2178 { //미로 탐색
	
	static int n, m;
	static int map[][];
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[] = br.readLine().split(" ");
		n = Integer.parseInt(data[0]);
		m = Integer.parseInt(data[1]);
		map = new int[n][m];
		
		//#1. split으로 한 자리씩 나눠 map에 넣기
		for(int i = 0; i < n; i++) {
			String str[] = br.readLine().split("");
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		//#2. bfs 실행
		bfs(0, 0);
	}
	
	public static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(x, y));
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if(map[nx][ny] == 1) {
						q.add(new Pair(nx, ny));
						map[nx][ny] = map[p.x][p.y] + 1;
					}
				}
			}
		}
		System.out.println(map[n - 1][m - 1]);
	}
}

class Pair{
	int x, y;
	
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}
