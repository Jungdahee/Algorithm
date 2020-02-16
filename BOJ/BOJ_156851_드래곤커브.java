package BOJ;

import java.io.*;

public class BOJ_156851_드래곤커브 {

	static int cnt, map[][], dragon[][];
	static int dx[] = {0, 1, 1};
	static int dy[] = {1, 1, 0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[101][101];
		makeCurve();

		for(int i = 0; i < n; i++) {
			String input1[] = br.readLine().split(" ");
			int y = Integer.parseInt(input1[0]);
			int x = Integer.parseInt(input1[1]);
			int d = Integer.parseInt(input1[2]);
			int g = Integer.parseInt(input1[3]);

			drawCurve(x, y, d, g);
		}
		countRec();
		System.out.println(cnt);
	}

	static void makeCurve() {
		dragon = new int[4][1024];
		dragon[0][0] = 0;
		dragon[1][0] = 1;
		dragon[2][0] = 2;
		dragon[3][0] = 3;

		for(int k = 0; k < 4; k++) {
			for(int i = 1; i <= 10; i++) {
				int begin = (int) Math.pow(2, i - 1);
				int end = (int) Math.pow(2, i);
				for(int j = begin, l = 1; j < end; j++, l += 2) {
					dragon[k][j] = dragon[k][j - l] + 1 == 4 ? 0 : dragon[k][j - l] + 1;
				}
			}
		}
	}

	static void drawCurve(int x, int y, int d, int g) {
		int idx = (int) Math.pow(2, g);
		int cx = x;
		int cy = y;
		map[cx][cy] = 1;
		for(int i = 0; i < idx; i++) {
			int tmp = dragon[d][i];
			if(tmp == 0) cy++;
			else if(tmp == 1) cx--;
			else if(tmp == 2) cy--;
			else cx++;

			map[cx][cy] = 1;
		}
	}

	static void countRec() {
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] == 1 && map[i][j + 1] == 1 
						&& map[i + 1][j + 1] == 1 && map[i + 1][j] == 1) cnt++;
			}
		}
	}
}
