package BOJ;

import java.io.*;
import java.util.Stack;

// 수행시간 84ms
public class BOJ_9012_괄호 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// Scanner 사용 시 소요되는 입력 시간을 단축하기 위해 버퍼 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 테스트 케이스 입력 받기
		int tc = Integer.parseInt(br.readLine());
		
		// 테스트 케이스만큼 로직 수행
		for(int i = 0 ; i < tc; i++) {
			// 입력 char 배열로 변환(수행시간 줄이기 위함)
			char chars[] = br.readLine().toCharArray();
			// 괄호의 쌍이 유효한지 확인하기 위한 Stack 선언
			Stack<Character> st = new Stack<Character>();
			// Character 하나하나 처리
			for(char ch : chars) {
				// 여는 괄호 ( 이면 stack에 push
				if(ch == '(') st.push(ch);
				// 닫는 괄호 ) 이면 경우는 두 가지
				else if(ch == ')') { 
					// stack의 top에 있는 char가 여는 괄호였으면 쌍이 이루어진 것이므로 pop 수행
					if(!st.isEmpty() && st.peek() == '(') st.pop();
					// 여는 괄호 ( 가 아니라면 push
					else st.push(ch);
				}
			}
			// stack에 char가 남았다면 쌍이 이루어지지 않은 괄호가 있다는 것으므로 valid false
			if(st.size() != 0) System.out.println("NO");
			// 그렇지 않은 경우 valid true
			else System.out.println("YES");
		}
	}
}
