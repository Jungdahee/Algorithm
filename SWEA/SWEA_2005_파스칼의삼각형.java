package SWEA;

import java.io.*;

public class SWEA_2005_파스칼의삼각형 {

	static int map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				 for(int j = 0; j <= i; j++) {
					 if(i == j || j == 0) map[i][j] = 1;
					 else map[i][j] = map[i - 1][j - 1] + map[i - 1][j];
				 }
			}
			
			System.out.println("#" + t);
			for(int i = 0; i < n; i++) {
				for(int j = 0; j <= i; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
