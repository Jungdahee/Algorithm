package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1786_찾기 {

	public static char text[], pattern[];
	public static int result, pi[];
	public static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		text = br.readLine().toCharArray();
		pattern = br.readLine().toCharArray();
		pi = new int[pattern.length];
		list = new ArrayList<Integer>();
		getPi();
		KMP();
		
		System.out.println(result);
		for(Integer idx : list) System.out.println(idx);
		
	}
	
	public static void getPi() {
		for(int i = 1, j = 0; i < pattern.length; i++) {
			while(j > 0 && pattern[i] != pattern[j]) j = pi[j - 1];
			if(pattern[i] == pattern[j]) pi[i] = ++j;
		}
	}
	
	public static void KMP() {
		for(int i = 0, j = 0; i < text.length; i++) {
			while(j > 0 && text[i] != pattern[j]) j = pi[j - 1];
			if(text[i] == pattern[j]) {
				if(j == pattern.length - 1) {
					result++;
					list.add(i - pattern.length + 2);
					j = pi[j];
				} else j++;
			}
		}
	}
}
