import java.io.*;

public class BOJ_15649 {

	static int n, m;
	static int num[];
	static boolean selected[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split(" ");
		n = Integer.parseInt(input[0]);
		m = Integer.parseInt(input[1]);
		num = new int[m];
		selected = new boolean[n + 1];
				
		makeSeq(0);
	}
	
	private static void makeSeq(int idx) {
		if(idx == m) {
			for(int i = 0; i < num.length; i++) {
				System.out.print(num[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(selected[i]) continue;
			num[idx] = i;
			selected[i] = true;
			makeSeq(idx + 1);
			selected[i] = false;
		}
	}
}
