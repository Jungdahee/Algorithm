package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3943_헤일스톤수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < tc; t++) {
			int N = Integer.parseInt(br.readLine());
			int result = N;
			
			while(true) {
				if(N == 1) break;
				
				if(N % 2 == 0) N /= 2;
				else N = N * 3 + 1;
				
				if(result < N) result = N;
				
			}
			
			System.out.println(result);
		}
	}
}
