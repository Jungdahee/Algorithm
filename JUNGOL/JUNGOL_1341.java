import java.io.*;
public class JUNGOL_1341 { //구구단2

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String data[] = br.readLine().split(" ");
		int s = Integer.parseInt(data[0]);
		int e = Integer.parseInt(data[1]);
		
		if(s < e) {
			for(int i = s; i <= e; i++) {
				for(int j = 0; j < 9; j++) {
					if(j != 0 && j % 3 == 0) System.out.println();
					System.out.printf("%d * %d = %2d   ", i, j+1, i * (j+1));
				}
				System.out.println('\n');
			}
		}
		else {
			for(int i = s; i >= e; i--) {
				for(int j = 0; j < 9; j++) {
					if(j != 0 && j % 3 == 0) System.out.println();
					System.out.printf("%d * %d = %2d   ", i, j+1, i * (j+1));
				}
				System.out.println('\n');
			}
		}
	}
}
