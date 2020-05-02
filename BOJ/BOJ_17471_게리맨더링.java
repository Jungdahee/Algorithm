package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

// 실행 시간 152ms
public class BOJ_17471_게리맨더링 {

	public static int N, total, result, peopleA, people[];
	public static boolean isPossible, visited[];
	public static ArrayList<Integer> adj[];
	public static LinkedList<Integer> listA, listB;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		people = new int[N];
		String input[] = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(input[i]);
			people[i] = num; // 지역별 인구 받기
			total += num; // 총 인원 저장
		}

		adj = new ArrayList[N]; // 인접리스트를 구성하기 위한 ArrayList
		for(int i = 0; i < N; i++) adj[i] = new ArrayList<Integer>();

		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			int num = Integer.parseInt(input[0]);
			for(int j = 0; j < num; j++) {
				int a = Integer.parseInt(input[1 + j]) - 1;
				adj[i].add(a); // 인접리스트 구성
				adj[a].add(i); // 인접리스트 구성
			}
		}
		
		listA = new LinkedList<Integer>(); // A 구역에 담길 지역 관리할 리스트
		listB = new LinkedList<Integer>(); // B 구역에 담길 지역 관리할 리스트
		result = Integer.MAX_VALUE;

		// 어떤 지역구든 1개 이상의 지역과 N - 1개의 지역까지만 차지할 수 있음.
		for(int i = 1; i < N; i++) { 
			// 지역 0번부터 구역을 나눠보는 시도
			for(int j = 0; j < N; j++) {
				listA.clear();
				listB.clear();
				peopleA = 0;
				// (A지역에 들어갈 지역 j, A구역에 들어갈 구역 개수 제한 변수, A구역에 담긴 지역 관리)
				makeComb(j, i, 0); 
			}
		}
		// 한번이라도 지역을 나눌 수 있는 경우는 result값 출력, 아예 지역을 나눌 수 없는 경우라면 -1 출력
		System.out.println(isPossible ? result : "-1");
	}

	private static void dfs(int cur, int type) {
		visited[cur] = true; // 현재 처리하려는 구역 방문 처리

		// 현재 처리하는 구역과 연결되어 있는 다른 구역 탐색
		for(int next : adj[cur]) {
			// 이미 방문했던 구역인지 체크
			if(visited[next]) continue;
			// 0 => A 리스트 1 => B 리스트
			// 임시 A 구역 리스트에 다음 방문하려는 곳이 존재하면 dfs 실행
			if(type == 0 && listA.contains(next)) {
				dfs(next, 0);
			} 
			// 임시 B 구역 리스트에 다음 방문하려는 곳이 존재하면 dfs 실행
			else if(type == 1 && listB.contains(next)){
				dfs(next, 1);
			}
		}
	}

	private static void makeComb(int idx, int end, int cnt) {
		// A 구역 리스트에 들어갈 구역을 다 선정한 경우
		if(cnt == end) {
			visited = new boolean[N];
			dfs(listA.get(0), 0); // A 구역 리스트에 들어가 있는 구역들이 서로 연결되어 있는지 확인
			
			listB.clear(); 
			for(int i = 0; i < N; i++) {
				// A 리스트에 담기지 않은 구역은 자동으로 B 지역구의 지역이 됨.
				if(!listA.contains(i)) listB.add(i);		
			}
			
			dfs(listB.get(0), 1); // B 구역 리스트에 들어가 있는 구역들이 서로 연결되어 있는지 확인

			if(checkPossible()) { // 위에서 dfs 실행 결과로 모든 구역이 방문되었다면 구역을 나눌 수 있다는 의미
				isPossible = true; // 구역을 나눌 수 있는 경우가 나왔기 때문에 isPossible true 처리
				int peopleB = total - peopleA;
				if(result > Math.abs(peopleA - peopleB)) {
					result = Math.abs(peopleA - peopleB); // 최소값 갱신
				}
			}
			return;
		}
		
		if(idx >= N) return; // 선택하려는 구역이 범위를 벗어나면 return
		
		// 현재 구역을 선택한 경우
		listA.add(idx); 
		peopleA += people[idx];
		makeComb(idx + 1, end, cnt + 1);
		
		// 현재 구역을 선택하지 않는 경우
		listA.pollLast();
		peopleA -= people[idx];
		makeComb(idx + 1, end, cnt);
	}

	private static boolean checkPossible() {
		for(int i = 0; i < visited.length; i++) {
			// visited 되지 않은 구역이 있다면 도중에 연결이 끊긴 경우이므로 false 리턴
			if(!visited[i]) return false;
		}
		return true; // 아니라면 정상적으로 구역을 나눌 수 있음.
	}
}
