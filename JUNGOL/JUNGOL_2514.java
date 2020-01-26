import java.io.*;

public class JUNGOL_2514 { //문자열찾기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split("");
		int koiNum = 0;
		int ioiNum = 0;
		
		for(int i = 0; i < input.length - 2; i++) {
			if(input[i].equals("K") || input[i].equals("I")) {
				if(input[i + 1].equals("O") && input[i + 2].equals("I")) {
					if(input[i].equals("K")) koiNum++;
					else ioiNum++;
				}
			}	
		}
		System.out.println(koiNum);	
		System.out.println(ioiNum);	
	}
}
