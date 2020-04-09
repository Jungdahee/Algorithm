import java.io.*;
import java.util.*;

public class BOJ_1197_최소스패닝트리 {
	
	public static int parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		int V = Integer.parseInt(input1[0]);
		int E = Integer.parseInt(input1[1]);
		int edges[][] = new int[E][3];
		parents = new int[V + 1];
		
		// 간선의 정보와 가중치 입력 받기
		for(int i = 0; i < E; i++) {
			String input2[] = br.readLine().split(" ");
			edges[i][0] = Integer.parseInt(input2[0]);
			edges[i][1] = Integer.parseInt(input2[1]);
			edges[i][2] = Integer.parseInt(input2[2]);
		}
		
		// 가중치 오름차순으로 정렬
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		// parents를 -1로 초기화
		Arrays.fill(parents, -1);
		
		int result = 0;
		int cnt = 0;
		for(int i = 0; i < edges.length; i++) {
			// 정점과 정점이 연결될 수 있는지 확인
			if(union(edges[i][0], edges[i][1])) {
				result += edges[i][2]; // 가중치 덧셈
				cnt++;
			}
			// cnt가 정점 - 1개가 된다는 것은 모든 정점을 다 연결했다는 의미이므로 종료
			if(cnt == V - 1) break;
		}
		System.out.println(result);
	}
	
	public static int find(int x) {
		if(parents[x] < 0) return x;
		return parents[x] = find(parents[x]);
	}
	
	public static boolean union(int x, int y) {
		int xRoot = find(x); // x의 root를 찾아서 반환 받음
		int yRoot = find(y); // y의 root를 찾아서 반환 받음
		
		if(xRoot != yRoot) { // 부모가 같지 않다는 것은 합칠 수 있다는 의미
			parents[yRoot] = xRoot; // 따라서 y의 root를 x의 root로 변경
			return true; 
		}
		return false;
	}
}
