import java.io.*;
public class JUNGOL_1523 { //별삼각형1

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[] = br.readLine().split(" ");
		int n = Integer.parseInt(data[0]);
		int m = Integer.parseInt(data[1]);
		
		if(n <= 100 && m == 1) {
			for(int i = 1; i <= n; i++) {
				for(int j = 0; j < i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		else if(n <= 100 && m == 2) {
			for(int i = n; i >= 1; i--) {
				for(int j = 0; j < i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		else if(n <= 100 && m == 3) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j < n + i; j++) {
					if(j <= n - i) System.out.print(" ");
					else System.out.print("*");
				}
				System.out.println();
			}
		}
		else System.out.println("INPUT ERROR!");
	}
}
