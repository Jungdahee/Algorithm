package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14889_스타트와링크 {

	public static int N, result, origin[], start[], link[], map[][];
	public static boolean isSelected[];
	public static void main(String[] args) throws NumberFormatException, IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N]; // 입력값을 담을 2차원 배열
		origin = new int[N]; // 조합 생성을 할 때 쓸 배열
		isSelected = new boolean[N]; // start팀과 link팀을 구분하기 위한 boolean 배열
		start = new int[N / 2]; // start팀으로 배정된 팀원 담기 위한 배열
		link = new int[N / 2]; // link팀으로 배정된 팀원을 담기 위한 배열
		result = Integer.MAX_VALUE;
		
		// 입력값 받기
		for(int i = 0; i < N; i++) {
			String input[] = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		// 조합을 만들기 위해 참고할 배열 생성
		for(int i = 0; i < N; i++) origin[i] = i;
		
		// 조합 생성
		combination(0, 0);
		
		// 결과 출력
		System.out.println(result);
	}
	
	private static void combination(int idx, int cnt) {
		if(cnt == N / 2) { // 팀원 구성이 끝난 경우
			// isSelected에 true로 표시된 팀원은 start 팀으로 배정, 아니라면 link 팀으로 배정
			for(int i = 0, j = 0, k = 0; i < isSelected.length; i++) {
				if(isSelected[i] == true) start[j++] = i;
				else link[k++] = i;
			}
			
			int sSum = calc("s"); // start팀의 능력치 계산
			int lSum = calc("l"); // link팀의 능력치 계산
			
			// 최소값 갱신
			if(Math.abs(sSum - lSum) < result) result = Math.abs(sSum - lSum);
			return;
		}
		
		if(idx >= N) return; // 전체 인원을 벗어난 경우
		
		isSelected[idx] = true; 
		combination(idx + 1, cnt + 1); // 지금 뽑으려는 팀원을 뽑는 경우
		isSelected[idx] = false;
		combination(idx + 1, cnt); // 지금 뽑으려는 팀원을 뽑지 않는 경우
	}
	
	private static int calc(String str) {
		int sum = 0;
		
		if(str.equals("s")) {
			for(int i = 0; i < start.length; i++) {
				for(int j = 0; j < start.length; j++) {
					sum += map[start[i]][start[j]];
				}
			}
		}
		else {
			for(int i = 0; i < link.length; i++) {
				for(int j = 0; j < link.length; j++) {
					sum += map[link[i]][link[j]];
				}
			}
		}
		return sum;
	}
}
