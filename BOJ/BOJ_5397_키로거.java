package BOJ;

import java.io.*;
import java.util.Stack;

// 수행 시간 1100ms
public class BOJ_5397_키로거 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 데이터 받을 준비
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 테스트 케이스 수 입력 받기
		int tc = Integer.parseInt(br.readLine());
		
		// 테스트 케이스만큼 로직 수행
		for(int i = 0; i < tc; i++) {
			// char 배열로 입력 받기
			char chars[] = br.readLine().toCharArray();
			// 들어오는 char를 담을 st1
			Stack<Character> st1 = new Stack<Character>();
			// 연산을 수행하고 결과를 임시적으로 저장할 st2
			Stack<Character> st2 = new Stack<Character>();
			// 결과 출력을 위한 StringBuilder 생성
			StringBuilder sb = new StringBuilder();
			
			for(char ch : chars) {
				switch(ch) {
					// char가 '<'이고 st1가 비어 있지 않은 경우 pop 연산 수행
					case '<' : if(!st1.isEmpty()) st2.push(st1.pop());
								break;
					// char가 '>'이고 st2가 비어 있지 않은 경우 pop 연산 수행
					case '>' : if(!st2.isEmpty()) st1.push(st2.pop());
								break;
					// char가 '-'이면 st1에서 pop 수행
					case '-' : if(!st1.isEmpty()) st1.pop();
								break;
					// 위의 경우를 제외한 경우는 연산자가 아닌 char이기 때문에 push 수행
					default : st1.push(ch);
								break;
				}
			}
			
			// st1에서 데이터를 빼서 결과 스트링에 append 수행
			while(!st1.isEmpty()) sb.append(st1.pop());
			// 단, st1은 char가 순서대로 들어가 있으므로 pop하면 역순이 되기 때문에 reverse 수행
			sb.reverse();
			// st2는 역순으로 들어가 있으므로 단순 pop 연산 수행
			while(!st2.isEmpty()) sb.append(st2.pop());
			
			// 결과 출력
			System.out.println(sb);
		}
	}
}
