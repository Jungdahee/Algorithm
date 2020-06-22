package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1018_체스판다시칠하기 {

	public static int N, M, result;
	public static char map[][];
	public static char white[] = {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'};
	public static char black[] = {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new char[N][M];
		result = Integer.MAX_VALUE;

		// 입력 받기
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i <= N - 8; i++) {
			for(int j = 0; j <= M - 8; j++) {
				check(0, i, j);
				check(1, i, j);
			}
		}
		
		System.out.println(result);
	}
	
	private static void check(int type, int x, int y) {
		int cnt = 0;
		if(type == 0) {
			int line = 0;
			int idx = 0;
			for(int i = x; i < x + 8; i++) {
				idx = 0;
				for(int j = y; j < y + 8; j++) {
					if(line % 2 == 0) {
						if(map[i][j] != white[idx++]) cnt++;
					} else {
						if(map[i][j] != black[idx++]) cnt++;
					}
				}
				line++;
			}
		}
		else {
			int line = 0;
			int idx = 0;
			for(int i = x; i < x + 8; i++) {
				idx = 0;
				for(int j = y; j < y + 8; j++) {
					if(line % 2 == 0) {
						if(map[i][j] != black[idx++]) cnt++;
					} else {
						if(map[i][j] != white[idx++]) cnt++;
					}
				}
				line++;
			}
		}
		
		if(cnt < result) result = cnt;
	}
}
