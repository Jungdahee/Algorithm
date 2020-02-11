package SWEA;

import java.io.*;

public class SWEA_2805_농작물수확하기 {
	
	static int result, map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			result = 0;
			
			for(int i = 0; i < n; i++) {
				String input[] = br.readLine().split("");
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(input[j]);
				}
			}
			
			for(int i = 0; i < n / 2; i++) {
				for(int j = n / 2 - i; j <= n / 2 + i; j++) {
					result += map[i][j];
				}
			}
			
			for(int i = n / 2; i >= 0; i--) {
				for(int j = n / 2 - i; j <= n / 2 + i; j++) {
					result += map[n - 1 - i][j];
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
}
