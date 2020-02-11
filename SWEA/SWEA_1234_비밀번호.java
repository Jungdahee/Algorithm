package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_1234_비밀번호 {

	static Stack<Character> st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			String input[] = br.readLine().split(" ");
			st = new Stack<Character>();
			for(int i = 0; i < input[1].length(); i++) {
				char ch = input[1].charAt(i);
				if(!st.empty() && st.peek() == ch) st.pop();
				else st.push(ch);
			}
			
			Character result[] = new Character[st.size()];
			int i = st.size() - 1;
			while(!st.empty()) {
				result[i] = st.pop();
				i--;
			}
			System.out.print("#" + t + " ");
			for(Character ch : result) System.out.print(ch);
			System.out.println();
		}
	}
}
