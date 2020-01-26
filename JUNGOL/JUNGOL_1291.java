import java.io.*;

public class JUNGOL_1291 { //구구단

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[];
		int s, e;

		while(true) {
			data = br.readLine().split(" ");
			s = Integer.parseInt(data[0]);
			e = Integer.parseInt(data[1]);

			if(s < 2 || s > 9 || e < 2 || e > 9) System.out.println("INPUT ERROR!");
			else break;
		}

		if(s < e) {
			for(int i = 1; i <= 9; i++) {
				for(int j = s; j <= e; j++) {
					System.out.printf("%d * %d = %2d   ", j, i, j*i);
				}
				System.out.println();
			}
		}
		else {
			for(int i = 1; i <= 9; i++) {
				for(int j = s; j >= e; j--) {
					System.out.printf("%d * %d = %2d   ", j, i, j*i);
				}
				System.out.println();
			}
		}
		br.close();
	}   
}


