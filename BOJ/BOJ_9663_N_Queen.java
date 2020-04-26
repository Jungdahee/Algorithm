package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663_N_Queen {

	public static int N, result, status[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		status = new int[N];
		
		dfs(0); // 첫 번째 row부터 시작
		System.out.println(result);
	}
	
	public static void dfs(int row) {
		if(row == N) { // row의 수가 N이면 한 가지 경우가 생성된 것
			result++; // 결과값 증가
		} else {
			for(int i = 0; i < N; i++) {
				// 처리하고 있는 행에 모든 경우의 수를 시도
				status[row] = i; 
				// 둘 수 있는 경우인지 체크
				if(isPossible(row)) { 
					// 해당 위치에 퀸을 두는 것이 가능하다면 다음 행으로 넘어가기
					dfs(row + 1);
				} 
			}
		}
	}
	
	public static boolean isPossible(int col) {
		for(int i = 0; i < col; i++) {
			// 여태까지 놓았던 말과 지금 두려는 말의 열의 위치가 같다면 둘 수 없는 경우
			if(status[i] == status[col]) return false;
			// 여태까지 놓았던 말과 지금 두려는 말이 위치가 대각선이면 둘 수 없는 경우
			if(Math.abs(status[i] - status[col]) == Math.abs(i - col)) return false;
		}
		return true;
	}
}
