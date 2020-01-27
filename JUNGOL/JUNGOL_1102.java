import java.util.*;
public class JUNGOL_1102 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Stack<Integer> st = new Stack<>();
		
		while(n-- != 0) {
			String op = sc.next();
			if(op.equals("i")) st.add(sc.nextInt());
			else if(op.equals("o")) {
				if(st.size() == 0) System.out.println("empty");
				else System.out.println(st.pop());
			}
			else if(op.equals("c")) System.out.println(st.size());
		}
	}	
}
