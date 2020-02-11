package SWEA;

import java.io.*;

public class SWEA_1220_Magnetic {

	static int dx[] = {-1, 1};
	static int dy[] = {0, 0};
	static int table[][];
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int t = 1; t <= 10; t++) {
			br.readLine(); //숫자 제거
			table = new int[100][100];
			result = 0;
			for(int i = 0; i < 100; i++) {
				String input1[] = br.readLine().split(" ");
				for(int j = 0; j < 100; j++) {
					table[i][j] = Integer.parseInt(input1[j]);
				}
			}

			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					if(table[i][j] == 1 || table[i][j] == 2) 
						check(i, j);
				}
			}
			System.out.println("#" + t + " " + result / 2);
		}
	}

	static void check(int i, int j) {
		int nx = i;
		int ny = j;
		if(table[i][j] == 1) { //아래 방향으로 탐색
			while(true) {
				nx = nx + dx[1];
				ny = ny + dy[1];
				
				if(nx >= 100 || ny >= 100) break;
				if(table[nx][ny] == 1) break;
				if(table[nx][ny] == 2) {
					result++;
					break;
				}
			}
		}
		else if(table[i][j] == 2) { //위 방향으로 탐색
			while(true) {
				nx = nx + dx[0];
				ny = ny + dy[0];
				
				if(nx < 0 || ny < 0) break;
				if(table[nx][ny] == 2) break;
				if(table[nx][ny] == 1) {
					result++;
					break;
				}
			}
		}
	}
}
