import java.io.*;
import java.util.Stack;

public class SWEA_5432_쇠막대기자르기 {

	static Stack<Character> st;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			st = new Stack<Character>();
			result = 0;
			String str = br.readLine();
			
			for(int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if(c == '(') st.push(c);
				else {
		            st.pop();
		            if(str.charAt(i - 1) == '(')
		                result += st.size(); 
		            else
		                result += 1;  
		        }
			}
			System.out.println("#" + t + " " + result);
		}
	}
}

