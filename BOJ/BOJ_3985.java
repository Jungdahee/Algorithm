package BOJ;

import java.io.*;

public class BOJ_3985 {

	static int check[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int length = Integer.parseInt(br.readLine()); //케익 길이
		int pNum = Integer.parseInt(br.readLine()); //방청객 수
		check = new int[length + 1];
		int expected = 0;
		int max = 0;
		int p1 = 0;
		int p2 = 0;
	
		for(int i = 1; i <= pNum; i++) {
			String input[] = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int temp = end - start;
			
			if(expected < temp) {
				expected = temp;
				p1 = i;
			}
			
			int cnt = 0;
			for(int j = start; j <= end; j++) {
				if(check[j] == 0) {
					check[j] = i;
					cnt++;
				}
			}
			
			if(cnt > max) {
				max = cnt;
				p2 = i;
			}
		}
		
		System.out.println(p1);
		System.out.println(p2);
		
	}

}
