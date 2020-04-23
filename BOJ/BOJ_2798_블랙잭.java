package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행 시간 84ms
public class BOJ_2798_블랙잭 {

	public static int N, M, max;
	public static Integer cards[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		N = Integer.parseInt(input1[0]);
		M = Integer.parseInt(input1[1]);
		max = Integer.MIN_VALUE;
		cards = new Integer[N];
		String input2[] = br.readLine().split(" ");
		
		for(int i = 0; i < input2.length; i++) cards[i] = Integer.parseInt(input2[i]);

		for(int i = 0; i < cards.length - 2; i++) {
			for(int j = i + 1; j < cards.length - 1; j++) {
				for(int k = j + 1; k < cards.length; k++) {
					int tmp = cards[i] + cards[j] + cards[k];
					if(tmp <= M && tmp > max) max = tmp;
				}
			}
		}
		System.out.println(max);
	}
}
