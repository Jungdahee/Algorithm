package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_7701_염라대왕의이름정렬 {

	static int n;
	static String list[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			list = new String[n];
			
			for(int i = 0; i < n; i++) list[i] = br.readLine();
			Arrays.sort(list, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					if(o1.length() == o2.length()) {
						return o1.compareTo(o2);
					}
					return o1.length() - o2.length();
				}
			});
			
			System.out.println("#" + t + " ");
			for(int i = 1; i < list.length; i++) {
				if(!list[i - 1].equals(list[i])) System.out.println(list[i - 1]);
				if(i == list.length - 1) System.out.print(list[i]);
			}
		}
	}
}
