package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16235_나무재테크 {

	static public int N, M, K, map[][], nutri[][], tmp[][];
	static public PriorityQueue<Tree> trees;
	static public Queue<Tree> alive;
	static public Queue<Tree> death;
	static public int dx[] = {-1, 1, 0, 0, -1, -1, 1, 1};
	static public int dy[] = {0, 0, -1, 1, -1, 1, -1, 1};
	
	static class Tree {
		int x, y, age;
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		N = Integer.parseInt(input1[0]);
		M = Integer.parseInt(input1[1]);
		K = Integer.parseInt(input1[2]);
		
		map = new int[N + 1][N + 1];
		nutri = new int[N + 1][N + 1];
		
		// 겨울에 뿌려질 양분 입력 받기
		for(int i = 1; i <= N; i++) {
			String input2[] = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				map[i][j + 1] = 5; // 모든 양분을 5로 셋팅
				nutri[i][j + 1] = Integer.parseInt(input2[j]);
			} 
		}

		// 살아있는 모든 나무 정보를 담을 PriorityQueue와 봄을 지나 살아있는 나무 정보를 담을 Queue, 죽은 나무 정보를 담을 Queue 생성
		trees = new PriorityQueue<Tree>(new Comparator<Tree>() {
			@Override
			public int compare(Tree o1, Tree o2) {
				return o1.age - o2.age; //오름차순 정렬
			}
		});
		
		alive = new LinkedList<Tree>();
		death = new LinkedList<Tree>();
		
		// 나무 정보 trees에 삽입
		for(int i = 1; i <= M; i++) {
			String input3[] = br.readLine().split(" ");
			trees.add(new Tree(Integer.parseInt(input3[0]), Integer.parseInt(input3[1]), Integer.parseInt(input3[2])));
		}
		
		for(int i = 0; i < K; i++) { // K만큼 반복 수행
			spring();
			summer();
			fall();
			winter();
		}
		System.out.println(trees.size()); // 살아있는 나무 수 출력
	}
	
	private static void spring() {
		while(!trees.isEmpty()) {
			Tree t = trees.poll(); // 살아 있는 나무를 하나씩 꺼내서 양분 흡수
			if(map[t.x][t.y] - t.age < 0) { // 조건 : 자신의 나이만큼 양분을 흡수하지 못하는 경우
				death.offer(t); // 흡수하지 못한 경우 죽은 나무를 담는 Queue에 삽입
			}
			else { // 양분을 흡수할 수 있는 경우
				map[t.x][t.y] -= t.age; // 나무의 나이만큼 양분을 흡수
				t.age++; // 나이 +1 증가
				alive.offer(t); // 다시 살아있는 나무를 담는 Queue에 삽입
			}
		}
	}
	
	private static void summer() {
		while(!death.isEmpty()) {
			Tree t = death.poll(); // 죽은 나무를 하나씩 꺼내서 양분으로 변경
			map[t.x][t.y] += (t.age / 2); // 기존에 있는 양분에 죽은 나무의 나이 / 2를 덧셈
		}
	}
	
	private static void fall() {
		while(!alive.isEmpty()) {
			Tree t = alive.poll(); // 살아 있는 나무를 하나씩 처리
			
			if(t.age % 5 == 0) { // 나이가 5의 배수인 경우
				for(int dir = 0; dir < 8; dir++) { // 인접 8방향 탐색
					int nx = t.x + dx[dir];
					int ny = t.y + dy[dir];
					
					// 번식하려는 곳의 위치가 범위를 벗어나는지 체크
					if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
					trees.offer(new Tree(nx, ny, 1)); // 나무의 나이가 1인 나무들이 번식하므로 trees에 삽입
				}
			}
			trees.offer(t); // 어떠한 경우라도 번식을 시작하는 위치의 나무는 살아 있는 나무 정보를 보관하는 trees에 삽입
		}
	}
	
	private static void winter() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] += nutri[i][j]; // 기존 양분을 담는 map에 양분 추가
			}
		}
	}
}
