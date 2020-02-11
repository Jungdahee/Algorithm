package SWEA;

import java.io.*;

public class SWEA_1233_사칙연산유효성검사 {

	static Object nodes[];
	static boolean isImpossible;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			nodes = new Object[n + 1];
			isImpossible = false;
			for(int i = 1; i <= n; i++) {				
				String input[] = br.readLine().split(" ");
				nodes[i] = input[1];
			}
			
			int operator = n / 2;
			
			for(int i = 1; i <= operator; i++) { //연산자 개수 확인
				if(!nodes[i].equals("*") && !nodes[i].equals("/") 
						&& !nodes[i].equals("+") && !nodes[i].equals("-")) {
					isImpossible = true;
					break;
				}
			}
			
			for(int i = operator + 1; i <= n; i++) { //피연연산자 개수 확인
				if(nodes[i].equals("*") || nodes[i].equals("/") 
						|| nodes[i].equals("+") || nodes[i].equals("-")) {
					isImpossible = true;
					break;
				}
			}
			
			String result = isImpossible == true ? "0" : "1";
			System.out.println("#" + t + " " + result);
		}
	}
}
