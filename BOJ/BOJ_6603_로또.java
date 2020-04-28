package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행시간 188ms
public class BOJ_6603_로또 {

	public static int origin[], result[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String input[] = br.readLine().split(" ");
			// 0이 입력으로 들어오면 종료
			if(input[0].equals("0")) return;
			
			// 첫번째 K를 제외하고 나머지 수를 담을 origin 배열
			origin = new int[input.length - 1];
			// 로또의 조합을 담을 result 배열
			result = new int[6];
			for(int i = 0; i < origin.length; i++) 
				origin[i] = Integer.parseInt(input[i + 1]);
			
			// 조합 만들기 시작
			dfs(0, 0);
			System.out.println();
		}
	}
	
	public static void dfs(int idx, int cnt) {
		// 6개의 수가 모두 골라졌으면 출력
		if(cnt >= 6) {
			for(int i = 0; i < 6; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		// 탐색하려는 idx가 입력으로 들어온 원소의 크기를 벗어나는 경우 조합 생성이 불가능하므로 return
		if(idx >= origin.length) return;
		
		// 지금 탐색하고 있는 원소를 선택한 경우
		result[cnt] = origin[idx];
		dfs(idx + 1, cnt + 1);
		// 지금 탐색하고 있는 원소를 선택한 경우
		dfs(idx + 1, cnt);
	}
}
