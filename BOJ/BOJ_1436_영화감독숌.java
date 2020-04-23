package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1436_영화감독숌 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int result = 666;
		String tmp = "";
		
		while(true) {
			if(N == 0) break;
			
			tmp = String.valueOf(result);
			
			if(tmp.contains("666")) N--;
			result++;
		}
		System.out.println(tmp);
	}
}
