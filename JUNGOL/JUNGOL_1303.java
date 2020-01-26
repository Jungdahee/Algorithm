import java.io.*;
public class JUNGOL_1303{ //숫자사각형1

	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String data[] = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
         
        for(int i = 0; i < n * m; i++) {
            if(i % m == 0 && i != 0) System.out.println();
            System.out.print((i + 1) + " ");
        }
    }
}
