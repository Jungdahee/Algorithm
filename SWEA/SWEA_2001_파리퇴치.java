package SWEA;

import java.io.*;

public class SWEA_2001_파리퇴치 {

	static int n, m, max;
	static int map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String input1[] = br.readLine().split(" ");
			n = Integer.parseInt(input1[0]);
			m = Integer.parseInt(input1[1]);
			map = new int[n][n];
			max = -1;
			
			for(int i = 0; i < n; i++) {
				String input2[] = br.readLine().split(" ");
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(input2[j]);
				}
			}
			
			for(int i = 0; i <= n - m; i++) { //0 ~ 2
				for(int j = 0; j <= n - m; j++) {
					calc(i, j);
				}
			}
			System.out.println("#" + t + " " + max);
		}
	}
	
	static void calc(int x, int y) {
		int result = 0;
		
		for(int i = x; i < x + m; i++) {
			for(int j = y; j < y + m; j++) {
				result += map[i][j];
			}
		}
		
		if(result > max) max = result;
	}
}
