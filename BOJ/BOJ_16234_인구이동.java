package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 실행 시간 500ms
public class BOJ_16234_인구이동 {

	public static int N, L, R, cnt, sum, result, map[][];
	public static boolean hasUnion, visited[][];
	public static ArrayList<Pair> unionList; // 연합에 등록된 좌표를 등록하기 위한 리스트
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	// 좌표를 한 쌍으로 표현하기 위한 클래스 정의
	public static class Pair {
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		N = Integer.parseInt(input1[0]);
		L = Integer.parseInt(input1[1]);
		R = Integer.parseInt(input1[2]);
		map = new int[N][N];
		visited = new boolean[N][N];
		result = 0;
		
		// 영역 정보 입력 받기
		for(int i = 0; i < N; i++) {
			String input2[] = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input2[j]);
			}
		}
		
		
		while(true) {
			// 각 지점에 대해서 연합을 구성할 수 있는지 탐색
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// 탐색하기 전에 연합에 들어가는 영역 수를 관리할 cnt를 1로 셋팅
					cnt = 1;
					// 연합에 등록된 영역의 총 인구수를 관리할 sum은 탐색을 시작하는 위치의 인구 수로 셋팅
					sum = map[i][j];
					// 처리하지 않은 영역에서 탐색 시작
					if(!visited[i][j]) bfs(i, j);
				}
			}
			
			// 연합이 더 이상 만들어지지 않으면 수행 종료
			if(!hasUnion) break;
			else {
				result++; // 인구이동 증가
				// 다시 루프 수행하기 전에 값들 초기화
				hasUnion = false; 
				visited = new boolean[N][N];
			}
		}
		// 결과 출력
		System.out.println(result);
	}
	
	private static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<Pair>(); 
		unionList = new ArrayList<>();
		q.offer(new Pair(x, y)); // 연합을 구성할 수 있는 영역들을 탐색하기 위한 queue
		unionList.add(new Pair(x, y)); // 현재 탐색을 시작한 영역의 좌표 연합 리스트에 삽입
		visited[x][y] = true; // 현재 지점 방문 표시
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 상하좌우 탐색
			for(int k = 0; k < 4; k++) {
				int nx = cur.x + dx[k];
				int ny = cur.y + dy[k];
				
				// 탐색하는 영역의 위치가 전체의 영역을 벗어나는지 범위 체크
				if(checkRange(nx, ny)) continue;
				// 이미 처리한 지점인지 체크
				if(visited[nx][ny]) continue;
				// 두 영역 간의 차이가 조건을 만족하는지 체크
				if(calc(cur.x, cur.y, nx, ny)) {
					q.offer(new Pair(nx, ny)); // q에 삽입
					visited[nx][ny] = true; // 방문 표시
					sum += map[nx][ny]; // 연합의 인구 수를 관리하는 sum에 인구 수 추가
					cnt++; // 연합을 이루는 영역읙 개수 1추가
					
					unionList.add(new Pair(nx, ny));
				}
			}
		}
		
		// 연합을 구성하고 있는 영역의 개수가 2이상이면 인구 이동 시작
		// 처음에 탐색하는 지점부터 연합 리스트에 추가했기 때문에 자기 자신 제외하고 list에 더 추가되어야지만 연합 구성 완료
		if(unionList.size() > 1) {
			move();
			hasUnion = true;
		}
	}
	
	private static void move() {
		for(Pair p : unionList) {
			map[p.x][p.y] = sum / cnt;
		}
	}
	
	private static boolean calc(int x, int y, int nx, int ny) {
		int diff = Math.abs(map[x][y] - map[nx][ny]);
		return diff >= L && diff <= R;
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
