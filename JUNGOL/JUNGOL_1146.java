import java.io.*;
public class JUNGOL_1146 { //선택정렬(selection sort)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String input[] = br.readLine().split(" ");
		int data[] = new int[n];
		
		for(int i = 0; i < n; i++) data[i] = Integer.parseInt(input[i]);
		
		int min;
		for(int i = 0; i < n - 1; i++) {
			min = i;
			for(int j = i + 1; j < n; j++) {
				if(data[min] > data[j]) {
					min = j;
				}
			}
			int tmp = data[min];
			data[min] = data[i];
			data[i] = tmp;
			
			for(int j = 0; j < n; j++) {
				System.out.print(data[j] + " ");
			}
			System.out.println();
		}
	}
}
