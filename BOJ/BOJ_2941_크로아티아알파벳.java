package BOJ;

import java.io.*;
import java.util.LinkedList;

public class BOJ_2941_크로아티아알파벳 {

	static int result;
	static LinkedList<String> list = new LinkedList<String>();
	
	public static void main(String[] args) throws IOException {
		String cro[] = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input[] = br.readLine().split("");

		for(int i = 0; i < input.length; i++) list.add(input[i]);
		list.add(" ");
		list.add(" ");

		int i = 0;
		while(i < list.size()-2) {
			for(int j = 0; j < cro.length; j++) {
				int size = cro[j].length();
				String str = "";
				
				for(int k = i; k < i + size; k++) {
					str += list.get(k);
				}

				int cnt = size;
				int m = i;
				if(cro[j].equals(str)) {
					while(cnt-- != 0) {
						list.remove(m);
					}
					result++;
					break;
				}
				if(j == 7) i++;
			}
		}
		result += list.size() - 2;
		System.out.println(result);
	}
}
