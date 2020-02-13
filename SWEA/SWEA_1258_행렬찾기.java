import java.io.*;
import java.util.*;

public class SWEA_1258_행렬찾기 {

	static int n, map[][];
	static int dx[] = {1, 0};
	static int dy[] = {0, 1};
	static int xCnt, yCnt;

	static class Pair{
		int x, y;

		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	static boolean checkRange(int x, int y) {
		return x >= n || x < 0 || y >= n || y < 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			LinkedList<Pair> list = new LinkedList<Pair>();

			for(int i = 0; i < n; i++) {
				String input1[] = br.readLine().split(" ");
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(input1[j]);
				}
			}

			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(map[i][j] > 0) {
						count(i, j);
						mark(i, j);
						list.add(new Pair(xCnt, yCnt));
					}
				}
			}
			
			Collections.sort(list, new Comparator<Pair>() {

				@Override
				public int compare(Pair o1, Pair o2) {
					if(o1.x * o1.y == o2.x * o2.y) {
						return o1.x - o2.x;
					}
					return o1.x * o1.y - o2.x * o2.y;
				}
			});

			System.out.print("#" + t + " " + list.size() + " ");
			
			for(int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).x + " " + list.get(i).y + " ");
			}
		}
	}

	static void count(int x, int y) {
		xCnt = 1;
		yCnt = 1;

		for(int k = 0; k < 2; k++) {
			int nx = x;
			int ny = y;

			while(true) {
				nx = nx + dx[k];
				ny = ny + dy[k];

				if(checkRange(nx, ny) || map[nx][ny] == 0) break;
				if(k == 0) xCnt++;
				else if(k == 1) yCnt++;
			}
		}
	}

	static void mark(int x, int y) {
		for(int i = x; i < x + xCnt; i++) {
			for(int j = y; j < y + yCnt; j++) {
				map[i][j] = -1;
			}
		}
	}
}
