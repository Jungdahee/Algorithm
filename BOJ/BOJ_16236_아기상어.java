import java.io.*;
import java.util.*;

public class BOJ_16236_아기상어 {

	public static int N, time, sx, sy, size, count, map[][];
	public static ArrayList<Info> fishes;
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};

	// 상어 또는 물고기의 위치와 거리를 담을 클래스 
	static class Info { 
		int x, y, dist;

		public Info(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	// 맵 범위 체크 메소드
	public static boolean isRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		size = 2; // 초기 나이 2로 셋팅

		// 1. 입력으로 주어진 값을 받고 상어가 있는 위치 0으로 초기화
		for(int i = 0; i < N; i++) {
			String input1[] = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(input1[j]);
				map[i][j] = num;
				if(num == 9) {
					sx = i;
					sy = j;
					map[i][j] = 0;
				}
			}
		}

		while(true) {
			fishes = new ArrayList<Info>(); // 먹을 수 있는 물고기의 정보를 담을 수 있는 ArrayList 생성
			Queue<Info> q = new LinkedList<Info>(); // bfs 수행을 위한 큐 생성
			boolean visited[][] = new boolean[N][N]; // 방문 표시를 하기 위한 boolean 타입의 2차원 배열 생성
			q.offer(new Info(sx, sy, 0)); // 상어의 위치 q에 삽입, 물고기를 먹었으므로 0으로 거리 갱신
			visited[sx][sy] = true; // 방문 표시

			while(!q.isEmpty()) {
				Info shark = q.poll();

				for(int d = 0; d < 4; d++) { // 상하좌우 탐색
					int nx = shark.x + dx[d];
					int ny = shark.y + dy[d];

					if(isRange(nx, ny)) continue; // 범위 체크
					if(visited[nx][ny]) continue; // 방문했는지 여부 체크
					// 먹이를 찾은 경우(0보다 크고 상어의 사이즈보다 작은 경우만 먹을 수 있다는 조건)
					if(1 <= map[nx][ny] && map[nx][ny] < size) {
						q.offer(new Info(nx, ny, shark.dist + 1)); // 상어의 위치 갱신
						fishes.add(new Info(nx, ny, shark.dist + 1)); // 먹이 리스트에 삽입
						visited[nx][ny] = true; //방문 표시
					} 

					// 먹을 수는 없지만 지나갈 수 있는 경우(0이거나 상어의 사이즈와 같은 경우 지나갈 수 있다는 조건)
					else if(map[nx][ny] == size || map[nx][ny] == 0) {
						q.offer(new Info(nx, ny, shark.dist + 1)); // 상어 위치 갱신
						visited[nx][ny] = true; // 방문 표시
					}
				} 
			}

			// 사이즈가 0인 경우 먹을 수 있는 물고기가 없는 경우이므로 출력
			if(fishes.size() == 0) {
				System.out.println(time);
				return;
			}

			// 먹을 물고기가 있는 경우
			Info eatingFish = fishes.get(0);
			for(int i = 1; i < fishes.size(); i++){
				if(fishes.get(i).dist < eatingFish.dist) { // 거리가 최소인 물고기로 갱신
					eatingFish = fishes.get(i);
				}

				if(eatingFish.dist == fishes.get(i).dist) { // 거리가 같은 경우 X가 작은 물고기가 우선순위가 되므로 갱신
					if(eatingFish.x > fishes.get(i).x){
						eatingFish = fishes.get(i);
					}   
				}
			}

			time += eatingFish.dist; // 먹을 물고기의 거리를 time에 추가
			count++; // 먹었으므로 카운트 증가
			map[eatingFish.x][eatingFish.y] = 0; // 물고기를 먹은 자리 0으로 갱신

			if(count == size) { // 먹은 물고기의 수와 상어의 사이즈가 같은 경우
				size++; // 상어의 사이즈 1 증가
				count = 0; // 다시 카운트는 0으로 초기화
			}

			sx = eatingFish.x; // 상어의 위치 갱신
			sy = eatingFish.y; // 상어의 위치 갱신
		}
	}
}
