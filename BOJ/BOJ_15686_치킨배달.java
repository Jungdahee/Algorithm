package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_15686_치킨배달 {

	public static int N, M, result, map[][], tMap[][];
	public static ArrayList<Pair> checkenList, homeList;
	public static boolean isSelected[], visited[][];
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	
	// 집과 치킨집의 좌표를 담을 클래스
	public static class Pair {
		int x, y, dist;
		
		public Pair(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		map = new int[N][N]; // original map
		tMap = new int[N][N]; // 폐업하지 않을 치킨집을 선택하고 거리를 구할 때 필요한 temp map
		checkenList = new ArrayList<Pair>(); // 치킨집의 좌표들을 관리할 ArrayList
		homeList = new ArrayList<Pair>(); // 집의 좌표들을 관리할 ArrayList
		result = Integer.MAX_VALUE; // 결과값 초기화
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				// 1인 경우 집이기 때문에 homeList에 좌표 저장
				if(map[i][j] == 1) homeList.add(new Pair(i, j, 0));
				// 2인 경우 치킨집이기 때문에 checkenList에 좌표 저장
				if(map[i][j] == 2) checkenList.add(new Pair(i, j, 0));
			}
		}
		
		// 폐업하지 않을 곳을 선택할 때 필요한 isSelected 배열 생성
		isSelected = new boolean[checkenList.size()];
		// tMap에 map 값을 복사하기 위한 메소드 실행
		copyArray();
		// 폐업하지 않을 곳을 선택 시작
		combination(0, 0);
		// 결과 출력
		System.out.println(result);
	}
	
	private static void combination(int idx, int cnt) {
		if(cnt == M) { // 최대 M개를 다 뽑은 경우
			for(int i = 0; i < isSelected.length; i++) {
				// 선택된 곳들을 폐업 처리(0)
				if(!isSelected[i]) {
					Pair p = checkenList.get(i);
					tMap[p.x][p.y] = 0;
				}
			}
			// 폐업 처리 후 치킨 거리 계산
			int tmp = calc();
			// 최솟값 갱신
			if(result > tmp) result = tmp;
			// tMap 원래 값으로 초기화
			copyArray();
			return;
		}
		
		if(idx >= checkenList.size()) return;
		
		// 현재의 치킨집을 폐업하지 않을 곳으로 선택하는 경우
		isSelected[idx] = true;
		combination(idx + 1, cnt + 1);
		// 현재의 치킨집을 폐업할 곳으로 선택하는 경우
		isSelected[idx] = false;
		combination(idx + 1, cnt);
	}
	
	private static int calc() {
		int sum = 0;
		// 집 하나하나마다 치킨 거리 계산
		for(int i = 0; i < homeList.size(); i++) {
			Pair p = homeList.get(i);
			
			Queue<Pair> q = new LinkedList<Pair>();
			visited = new boolean[N][N];
			
			// 집 위치 queue에 삽입
			q.offer(new Pair(p.x, p.y, 0));
			// 방문 처리
			visited[p.x][p.y] = true;
			
			while(!q.isEmpty()){
				Pair cur = q.poll();
				
				// 지금 시도해보려는 위치가 2라면 치킨집을 만난 경윙므로 탐색 종료
				if(tMap[cur.x][cur.y] == 2) {
					sum += cur.dist; // 결과값에 거리 덧셈
					break;
				}
				
				// 상하좌우순으로 탐색
				for(int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					// 범위 체크
					if(checkRange(nx, ny)) continue;
					// 이미 처리했던 곳이면 skip
					if(visited[nx][ny]) continue;
					q.offer(new Pair(nx, ny, cur.dist + 1));
					visited[nx][ny] = true;
				}
			}
		}
		
		// 이번 선택에 대한 치킨 거리 반환
		return sum;
	}
	
	private static void copyArray() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				tMap[i][j] = map[i][j];
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}
