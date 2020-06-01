package PRO;

import java.util.Stack;

class PRO_12973_짝지어제거하기 {
	public int solution(String s) {
		
		Stack<Character> st = new Stack<Character>();
		st.push(s.charAt(0));

		for(int i = 1; i < s.length(); i++) {
			if(!st.isEmpty() && st.peek() == s.charAt(i)) st.pop();
			else st.push(s.charAt(i));
		}

		return st.size() == 0 ? 1 : 0;
	}
}
