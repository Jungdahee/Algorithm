package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_5643_키순서 {

	public static int N, M;
	public static boolean check[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t = 1; t <= tc; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			check =new boolean[N + 1][N + 1];
			
			for(int i =0; i < M ; i++){
				String input[] = br.readLine().split(" ");
				int a = Integer.parseInt(input[0]);
				int b = Integer.parseInt(input[1]);
				check[a][b] = true;
			}
			
			for(int k = 1 ; k <= N; k++){
				for(int i = 1; i <= N; i++){
					for(int j = 1; j <= N; j++){
						if(check[i][k] && check[k][j]) check[i][j] = true;
					}
				}
			}
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					System.out.print(check[i][j] + " ");  
				}
				System.out.println();
			}
			
			int total = 0;
			for(int i = 1; i <= N; i++){
				int cnt = 0;
				for(int j = 1; j <= N; j++){
					if(i == j) continue;
					if(check[i][j] || check[j][i]){
						cnt++;
					}
				}
				if(cnt == N - 1) total++;
			}
			System.out.println("#" + t + " " + total);
		}
	}
}
