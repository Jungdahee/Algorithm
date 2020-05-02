package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 실행 시간 108ms
public class BOJ_9205_맥주마시면서걸어가기 {

	public static int n, endX, endY;
	public static boolean isSuccess, visited[];
	public static ArrayList<Pair> list;
	
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-- > 0) {
			n = Integer.parseInt(br.readLine());
			list = new ArrayList<Pair>();
			visited = new boolean[n + 2];
			
			// 상근이 집부터 펜타포트 락 페스티벌까지의 좌표 모두 리스트에 삽입
			for(int i = 0; i < n + 2; i++) {
				String input[] = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				list.add(new Pair(x, y)); // 좌표값 리스트에 넣기
				
				// dfs 종료 조건을 위해 마지막 좌표 입력값 저장
				if(i == n + 1) {
					endX = x;
					endY = y;
				}
			}
			
			// 무사히 도착 가능한지 판단을 위한 boolean 변수
			isSuccess = false;
			// 상근이의 집부터 탐색 시작
			dfs(list.get(0).x, list.get(0).y, 0);
			System.out.println(isSuccess ? "happy" : "sad");
		}
	}
	
	private static void dfs(int x, int y, int idx) {
		// 현재 방문한 좌표의 지점 방문 표시
		visited[idx] = true;
		
		// 현재 지점이 펜타포트 락 페스티벌 좌표와 일치하면 무사히 도착했다는 의미이므로 종료
		if(x == endX && y == endY) {
			isSuccess = true;
			return;
		}
		
		// 리스트 안에 들어가 있는 좌표들 모두 시도
		for(int i = 0; i < list.size(); i++) {
			// 이미 방문한 지점인지 체크
			if(visited[i]) continue;
			// 현재 있는 위치에서 갈 수 있는 다음 위치를 검사
			// 50m에 한병씩 마시기 때문에 1000m 초과가 된다면 무사히 도착할 수 없음.
			// 따라서 두 거리의 차이가 1000m 이하인 지점을 방문해서 맥주를 구매해야 함.
			if(Math.abs(x - list.get(i).x) + Math.abs(y - list.get(i).y) <= 1000) {
				dfs(list.get(i).x, list.get(i).y, i);
			}
		}
	}
}
