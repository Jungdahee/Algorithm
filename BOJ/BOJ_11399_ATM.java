package BOJ;

import java.io.*;
import java.util.Arrays;

public class BOJ_11399_ATM {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int time[] = new int[n];
		String input[] = br.readLine().split(" ");
		
		for(int i = 0; i < input.length; i++) time[i] = Integer.parseInt(input[i]);
		
		int result = 0;
		Arrays.sort(time);
		for(int i = 0; i < time.length; i++) {
			for(int j = 0; j <= i; j++) {
				result += time[j];
			}
		}
		System.out.println(result);
	}
}
