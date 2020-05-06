package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

// 실행 시간 80ms
public class BOJ_17472_다리만들기2 {

	public static int N, M, num, island, result, parents[], map[][];
	public static boolean isEnd, visited[][];
	public static int dx[] = {-1, 1, 0, 0};
	public static int dy[] = {0, 0, -1, 1};
	public static ArrayList<Edge> list;

	// 섬에 번호를 붙이기 위한 bfs를 위한 Pair 클래스
	public static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// 섬들의 간선 정보를 저장하기 위한 클래스
	public static class Edge {
		// 정점1과 정점2의 가중치 weight 표현
		int node1, node2, weight;

		public Edge(int node1, int node2, int weight) {
			this.node1 = node1;
			this.node2 = node2;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);

		map = new int[N][M];
		visited = new boolean[N][M];

		// 입력값 받기
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}

		// 1. 섬에 넘버링 하기
		num = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 1){
					setNumber(i, j, num++); //bfs로 넘버링 수행
				}
			}
		}
		
		island = num;
		
		// 2. 다리 만들기
		list = new ArrayList<Edge>(); // 섬의 연결 정보를 저장하기 위한 리스트 생성
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] >= 1) { // 땅인 지점에서만 다리 만들기 시도
					num = map[i][j]; // 현재 처리하고 있는 섬의 번호 저장
					for(int d = 0; d < 4; d++) { // 상하좌우 갈 수 연결할 수 있는지 탐색
						isEnd = false; // dfs를 종료할 변수 false로 초기화
						// 다리를 만들고자 시도하는 곳이 범위를 넘어가는지 체크
						if(checkRange(i + dx[d], j + dy[d])) continue; 
						// 다리를 만들고자 하는 곳이 0인 경우에 다리 생성 가능
						if(map[i + dx[d]][j + dy[d]] == 0) {
							// dfs 수행
							makeBridge(i + dx[d], j + dy[d], d, 1);
						}
					}
				}
			}
		}
		
		// 3. MST 생성
		// 가중치를 기준으로 오름차순 정렬 수행
		Collections.sort(list, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight - o2.weight;
			}
		});
		
		// 부모 노드를 바로 알기 위한 배열 생성
		parents = new int[island + 1];
		
		// 본인을 부모 노드로 셋팅
		Arrays.fill(parents, -1);
		
		int cnt = 0;
		// 리스트에 담긴 연결 정보 하나씩 처리
		for(Edge edge : list) {
			// 정점 1과 정점 2를 합칠 수 있는지 즉, 이미 연결되어 있는지 확인
			if(union(edge.node1, edge.node2)) {
				// 합치는 것이 가능하다면 연결 가능한 것으로 연결 수행
				result += edge.weight;
				cnt++;
			}
			
			// 연결 수행 횟수가 정점 - 1개가 되면 MST 생성 완료
			if(cnt == island - 2) break;
		}
		
		// 간선의 수가 n - 1이 되지 않는다면 MST 생성 불가이므로 이를 만족하는 경우에만 최종 길이 출력
		System.out.println(cnt < island - 2 ? -1 : result);
	}

	private static void setNumber(int x, int y, int num) {
		Queue<Pair> q = new LinkedList<Pair>();
		map[x][y] = num;
		q.offer(new Pair(x, y));
		visited[x][y] = true;

		while(!q.isEmpty()) {
			Pair cur = q.poll();

			for(int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if(checkRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny] == 1) {
					map[nx][ny] = num;
					q.add(new Pair(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}

	private static void makeBridge(int x, int y, int d, int dist) {
		// 범위 벗어나는지 체크
		if(checkRange(x, y)) {
			isEnd = true;
			return;
		}

		// 1보다 크다면 일단 섬이라는 의미
		if(map[x][y] >= 1) {
			// 그 중에서 다리를 건설하기 시작한 곳의 섬 번호와 다른 섬이고 다리 길이가 2이상인 경우 건설 가능
			if(map[x][y] != num && dist - 1 >= 2) {
				// 출발한 섬, 도착 섬, 이들의 다리 길이를 저장
				list.add(new Edge(num, map[x][y], dist - 1));
			} 
			// 1보다 큰 경우는 모두 다리 건설이 끝나는 경우이므로 flag값 변경
			isEnd = true;
			return;
		}

		// 계속 진행되어 왔던 방향으로 다리 건설 시도
		makeBridge(x + dx[d], y + dy[d], d, dist + 1);
		// dfs 수행 후 돌아와서 결과 값이 다리 건설이 종료되었다면 return
		if(isEnd) return;
	}
	
	private static boolean union(int x, int y) {
		int xRoot = find(x); // 정점 x의 부모 노드 반환
		int yRoot = find(y); // 정점 y의 부모 노드 반환
		
		// 둘의 부모가 같지 않다면 합치는 것 가능
		if(xRoot != yRoot) {
			parents[yRoot] = xRoot; // 편의상 yRoot를 xRoot로 변경(path compression)
			return true;
		}
		return false;
	}
	
	private static int find(int x) {
		// 0보다 작은 경우 부모 노드인 것이므로 x 반환
		if(parents[x] < 0) return x;
		// 아닌 경우 계속해서 부모를 찾기 위해 거슬러 올라감.
		return parents[x] = find(parents[x]);
	}

	private static boolean checkRange(int x, int y) {
		return x < 0 || x >= N|| y < 0 || y >= M;
	}
}
