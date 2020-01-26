import java.io.*;
public class JUNGOL_1719 { //별삼각형2

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[] = br.readLine().split(" ");
		int n = Integer.parseInt(data[0]);
		int m = Integer.parseInt(data[1]);
		
		if(n <= 100 && n % 2 == 1 && m == 1) {
			for(int i = 1; i <= n / 2 + 1; i++) {
				for(int j = 0; j < i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
			for(int i = 1; i <= n / 2; i++) {
				for(int j = 0; j <= n / 2 - i; j++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		else if(n <= 100 && n % 2 == 1 && m == 2) {
			for(int i = 1; i <= n / 2 + 1; i++) {
				for(int j = 0; j < n / 2 + 1; j++) {
					if(j <= n / 2 - i) System.out.print(" ");
					else System.out.print("*");
				}
				System.out.println();
			}
			for(int i = 1; i <= n / 2; i++) {
				for(int j = 0; j <= n /2; j++) {
					if(j < i) System.out.print(" ");
					else System.out.print("*");
				}
				System.out.println();
			}
		}
		else if(n <= 100 && n % 2 == 1 && m == 3) {
			for(int i = 0; i < n / 2 + 1; i++) {
				for(int j = 0; j < n - i; j++) {
					if(j < i) System.out.print(" ");
					else System.out.print("*");
				}
				System.out.println();
			}
			for(int i = 1; i <= n / 2; i++) { //2
				for(int j = 1; j <= n / 2 + 1 + i; j++) {
					if(j <= n / 2 - i) System.out.print(" ");
					else System.out.print("*");
				}
				System.out.println();
			}
		}
		else if(n <= 100 && n % 2 == 1 && m == 4) {
			for(int i = 0; i < n / 2 + 1; i++) {
				for(int j = 0; j < n / 2 + 1; j++) {
					if(j < i) System.out.print(" ");
					else System.out.print("*");
				}
				System.out.println();
			}
			for(int i = 1; i <= n / 2; i++) {
				for(int j = 1; j <= n / 2 + 1 + i; j++) {
					if(j < n / 2 + 1) System.out.print(" ");
					else System.out.print("*");
				}
				System.out.println();
			}
		}
		else System.out.println("INPUT ERROR!");
	}

}
