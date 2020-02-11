package SWEA;

import java.io.*;

public class SWEA_5603_건초더미 {

	static int dummy[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(br.readLine());
			dummy = new int[n];
			int sum = 0;
			for(int i = 0; i < n; i++) {
				int tmp = Integer.parseInt(br.readLine());
				dummy[i] = tmp;
				sum += tmp;
			}
			
			int base = sum / n;
			int result = 0;
			for(int i = 0; i < dummy.length; i++) {
				if(base < dummy[i]) result += dummy[i] - base;
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
}
