import java.io.*;
import java.util.*;

public class BOJ_1058_친구 {

	static int n, max;
	static String rel[][];
	static boolean visited[];
	
	static class Pair{
		int cur, depth;
		
		Pair(int cur, int depth){
			this.cur = cur;
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		rel = new String[n][n];

		//#1. rel 2차원 배열에 입력값 넣기
		for(int i = 0; i < n; i++) {
			String input[] = br.readLine().split("");
			for(int j = 0; j < n; j++) rel[i][j] = input[j];
		}

		//#2. 친구 관계 탐색
		for(int i = 0; i < n; i++) {
			check(i);

			int cnt = 0;
			for(int j = 0; j < n; j++) {
				if(visited[j]) cnt++;
			}

			if(max < cnt) max = cnt;
		}

		//#3. 결과값 출력
		System.out.println(max - 1);
	}

	static void check(int i) {
		Queue<Pair> q = new LinkedList<Pair>();
		visited = new boolean[n];
		
		visited[i] = true;
		q.offer(new Pair(i, 0));

		while(!q.isEmpty()) {
			Pair tmp = q.poll();
			int cur = tmp.cur;
			int depth = tmp.depth;

			if(depth == 2) break;
			
			for(int j = 0; j < n; j++) {
				if(!visited[j] && rel[cur][j].equals("Y")) {
					visited[j] = true;
					q.offer(new Pair(j, depth + 1));
				}
			}
		}
	}
}
