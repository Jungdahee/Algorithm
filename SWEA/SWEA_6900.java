import java.io.*;

public class SWEA_6900 {
	
	static int n, m;
	static String lotto[], ticket[];
	static int money[];
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String input1[] = br.readLine().split(" ");
			n = Integer.parseInt(input1[0]);
			m = Integer.parseInt(input1[1]);
			lotto = new String[n];
			money = new int[n];
			ticket = new String[m];
			result = 0;
			
			for(int i = 0; i < n; i++) {
				String input2[] = br.readLine().split(" ");
				lotto[i] = input2[0];
				money[i] = Integer.parseInt(input2[1]);
			}
			
			for(int i = 0; i < m; i++) {
				ticket[i] = br.readLine();
			}
			
			for(int i = 0; i < n; i++) { //i가 lotto idx
				for(int j = 0; j < m ; j++) { //j가 ticket idx
					if(!compare(i, j)) continue;
					else result += money[i];
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}
	
	private static boolean compare(int n1, int n2) {
		for(int i = 0; i < 8; i++) {
			char ch1 = lotto[n1].charAt(i);
			char ch2 = ticket[n2].charAt(i);
			
			if(ch1 == '*') continue;
			if(ch1 != ch2) return false;
		}
		return true;
	}
}
