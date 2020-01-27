import java.util.*;
public class JUNGOL_1697 { //큐(queue)

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Queue<Integer> q = new LinkedList<>();
		
		while(n-- != 0) {
			String op = sc.next();
			if(op.equals("i")) q.add(sc.nextInt());
			else if(op.equals("o")) {
				if(q.size() == 0) System.out.println("empty");
				else System.out.println(q.poll());
			}
			else if(op.equals("c")) System.out.println(q.size());
		}
	}
}
