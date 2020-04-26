package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14888_연산자끼워넣기 {

	public static int N, min, max, num[], operator[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N]; // 숫자를 담을 배열
		operator = new int[4]; // 연산자의 횟수를 담을 배열
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		// input 입력 받기
		String input1[] = br.readLine().split(" ");
		for(int i = 0 ; i < N; i++) num[i] = Integer.parseInt(input1[i]);
		String input2[] = br.readLine().split(" ");
		for(int i = 0 ; i < 4; i++) operator[i] = Integer.parseInt(input2[i]);
		
		// 재귀 수행으로 모든 연산의 경우 탐색
		go(1, num[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void go(int idx, int result) {
		if(idx == N) { // 모든 숫자를 다 계산한 경우
			if(result > max) max = result;
			if(result < min) min = result;
			return;
		} 
		
		for(int i = 0; i < 4; i++) {
			// 연산자의 연산 횟수가 남지 않은 경우 건너뛰기
			if(operator[i] == 0) continue;
			else {
				// 하나의 연산자를 사용하기 때문에 해당 연산자 수 감소
				operator[i]--;
				switch(i) {
					case 0: go(idx + 1, result + num[idx]);
							break;
					case 1: go(idx + 1, result - num[idx]);
							break;
					case 2: go(idx + 1, result * num[idx]);
							break;
					case 3: go(idx + 1, result / num[idx]);
							break;
				}
				// 연산자를 사용해서 결과를 만들고 다시 돌아온 것이므로 처리했던 연산자 수 증가
				operator[i]++;
			}
		}
	}
}
