import java.util.*;
import java.io.*;

public class SWEA_1224 {

	static Stack<Character> st1;
	static Stack<Integer> st2;
	static String result = "";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			st1 = new Stack<Character>(); 
			st2 = new Stack<Integer>(); 
			int size = Integer.parseInt(br.readLine());
			result = "";
			String line = br.readLine();

			for(int i = 0; i < size; i++) {
				char c = line.charAt(i);

				if(c == '(' || c == '+' || c == '-' || c == '*' || c == '/') { 
					if(check(c, false)) st1.push(c); //삽입
					else { 
						while(!st1.isEmpty()) {
							if(check(c, true)) break;
							result += st1.pop();
						}
						st1.push(c);
					}
				}
				else if(c == ')') {
					while(!st1.empty()) {
						if(st1.peek() == '(') {
							st1.pop();
							break;
						}
						result += st1.pop();
					}
				}
				else if(c >= '0'&& c <= '9') result += c;
			}

			while(!st1.isEmpty()) result += st1.pop();
			calc();
			
			System.out.println("#" + t + " " + st2.pop());
		}
	}

	private static boolean check(char ch, boolean isInSt) {
		if(st1.isEmpty()) return true; 

		char sCh = st1.peek(); 
		int pNum = 0;
		int cNum = 0;

		if(ch == '*' || ch == '/') cNum = 2;
		else if(ch == '+' || ch == '-') cNum = 1;
		else if(isInSt == true && ch == '(') cNum = 0;
		else if(isInSt == false && ch == '(') cNum = 3;

		if(sCh == '*' || sCh == '/') pNum = 2;
		else if(sCh == '+' || sCh == '-') pNum = 1;
		else if(sCh == '(') pNum = 0;

		if(pNum < cNum) return true; 
		else if(pNum >= cNum) return false;
		return false;
	}

	private static void calc() {
		for(int i = 0; i < result.length(); i++) {
			char c = result.charAt(i);
			if(c >= '0' && c <= '9') st2.push(c - '0'); //숫자 스택2에 넣기
			else {
				int n2 = st2.pop();
				int n1 = st2.pop();
				int num = 0;
				if(c == '-') num = n1 - n2;
				else if(c == '+') num = n1 + n2;
				else if(c == '*') num = n1 * n2;
				else if(c == '/') num = n1 / n2;

				st2.push(num);
			}
		}
	}
}
