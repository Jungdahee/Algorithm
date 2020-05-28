package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17142_연구소3 {

	public static int N, M, zeroCnt, tmpCnt, result, map[][], tMap[][];
	public static boolean isSelected[];
	public static ArrayList<Pair> virusList, selectedVirus;
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};

	// 좌표를 관리할 Pair 클래스
	public static class Pair {
		int x, y, time;

		public Pair(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		result = Integer.MAX_VALUE; // 결과를 담을 변수

		map = new int[N][N]; // original map
		tMap = new int[N][N]; // 퍼지는 상태를 저장할 temp map
		virusList = new ArrayList<Pair>(); // 바이러스의 위치를 담을 ArrayList

		// input값 입력 받기
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(input[j]);
				if(map[i][j] == 2) virusList.add(new Pair(i, j, 0));
				else if(map[i][j] == 0) zeroCnt++; // 0인 지점 카운팅
			}
		}
		copyArray(); // tMap에 map 복사(초기화)
		isSelected = new boolean[virusList.size()]; // 활성화할 곳을 선택하기 위한 isSelected 배열
		activate(0, 0); // 활성화 위치 선택 시작

		// 결과값 출력
		System.out.println(result == Integer.MAX_VALUE ? "-1" : result);
	}

	// 활성화할 바이러스 위치를 선택하는 combination
	private static void activate(int idx, int cnt) {
		if(cnt == M) { // M개의 활성화 위치를 정한 경우
			selectedVirus = new ArrayList<Pair>(); // 활성화하기로 결정된 바이러스의 위치를 담을 ArrayList
			for(int i = 0; i < isSelected.length; i++) {
				// 선택된 곳의 바이러스 selectedVirus에 입력
				if(isSelected[i]) selectedVirus.add(virusList.get(i));
			}

			tmpCnt = zeroCnt;
			int tmp = spread(); // 바이러스 퍼트리기 시작
			if(result > tmp) result = tmp; // 최소값 갱신

			copyArray(); // tMap에 map 복사(초기화)
			return;
		}

		// 활성화할 곳 선택
		for(int i = idx; i < virusList.size(); i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			activate(i, cnt + 1);
			isSelected[i] = false;
		}
	}

	// 바이러스 퍼트리는 bfs
	private static int spread() {
		Queue<Pair> q = new LinkedList<Pair>();
		// 동시에 퍼져야하기 때문에 q에 선택된 바이러스 위치 전부 삽입
		for(int i = 0; i < selectedVirus.size(); i++) q.offer(selectedVirus.get(i));

		int time = 0;
		while(!q.isEmpty()) {
			Pair cur = q.poll();

			// 상하좌우 탐색
			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				// 범위 체크
				if(checkRange(nx, ny)) continue;
				// 2인 경우 비활성화 바이러스를 만났으므로 활성화해줘야 함.
				if(tMap[nx][ny] == 2) {
					q.offer(new Pair(nx, ny, cur.time + 1));
					tMap[nx][ny] = 9; // 활성화 표시
				}
				// 0인 지점은 바이러스를 퍼트려야 함.
				if(tMap[nx][ny] == 0) {
					tMap[nx][ny] = cur.time + 1; // 현재까지 퍼트리는 데 소요된 시간 + 1 저장
					q.offer(new Pair(nx, ny, cur.time + 1)); // q에 삽입
					if(cur.time + 1 > time) time = cur.time + 1; // 최대값 갱신
					tmpCnt--; // 0인 지점 카운팅 감소
				}
			}
		}

		if(tmpCnt == 0) return time; // 0인 지점이 더이상 없다면 퍼트리기에 성공했으므로 누적된 최대 시간 반환
		else return Integer.MAX_VALUE; // 아니라면 호출된 곳에서 갱신되지 못하도록 최대값 반환
	}

	// tMap 초기화 메소드
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
