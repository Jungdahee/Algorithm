package SWEA;

import java.io.*;
import java.util.Arrays;

public class SWEA_6719_성수의프로그래밍강좌시청 {

	static int N, K, list[];
	static double A;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String input1[] = br.readLine().split(" ");
			N = Integer.parseInt(input1[0]);
			K = Integer.parseInt(input1[1]);
			A = 0;
			
			list = new int[N];
			String input2[] = br.readLine().split(" ");
			for(int i = 0; i < input2.length; i++) list[i] = Integer.parseInt(input2[i]);
			
			Arrays.sort(list);
			
			for(int i = N - K; i < list.length; i++) A = (A + list[i]) / 2.0;
			
			System.out.println("#" + t + " " + A);
		}
	}
}
