import java.io.*;
import java.util.*;

public class JUNGOL_1828_냉장고 {
	static LinkedList<Pair> list;
	static int result;

	static class Pair {
		int x, y;
		
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new LinkedList<Pair>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String input[] = br.readLine().split(" ");
			list.offer(new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
		}
		
		//최저 범위를 기준으로 정렬
		Collections.sort(list, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return o1.y - o2.y;
			}
		});
		
		int base = list.get(0).y;
		
		for(int i = 0; i < list.size(); i++) {
			int comX = list.get(i).x;
			int comY = list.get(i).y;
			
			if(comX <= base && base <= comY) continue;
			else {
				result++;
				base = comY;
			}
		}
		System.out.println(result + 1);
	}
}
