package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_1244_최대상금 {

	static int max, maxIdx, idx, cnt, check[];
	static Integer num[];
	static boolean isNeed;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String input[] = br.readLine().split(" ");
			num = new Integer[input[0].length()];
			
			String input1[] = input[0].split("");
			cnt = Integer.parseInt(input[1]); //교환 횟수
			max = 0; maxIdx = 0;
			isNeed = false; 
			
			check = new int[10];
			for(int i = 0; i < input[0].length(); i++) {
				num[i] = new Integer(input1[i]);
				check[num[i]]++;
			}

			idx = 0;
			getMaxIdx();
			
			while(true) {
				if(cnt == 0) break;
				if(checkMax()) {
					isNeed = true;
					break;
				}
				
				if(num[idx] < num[maxIdx]) {
					int tmp = num[idx];
					num[idx] = num[maxIdx];
					num[maxIdx] = tmp;
					cnt--; idx++;
					getMaxIdx();
				}
			}
			
			if(isNeed) arrange();
			
			for(int i = 0; i < check.length; i++) {
				if(check[i] >= 3) sort();
			}
			
			System.out.print("#" + t + " ");
			for(int i = 0; i < num.length; i++) System.out.print(num[i]);
			System.out.println();
		}
	}
	
	static void getMaxIdx() {
		max = 0;
		maxIdx = 0;
		for(int i = idx; i < num.length; i++) {
			if(num[i] >= max) {
				max = num[i];
				maxIdx = i;
			}
		}
		
		if(idx == maxIdx) {
			idx++;
			getMaxIdx();
		}
	}
	
	static boolean checkMax() {
		int index = 0;
		while(index + 1 < num.length) {
			if(num[index] < num[index + 1]) return false;
			index++;
		}
		return true;
	}
	
	static void arrange() {
		int n = cnt % 2;
		if(n == 1) {
			int tmp = num[num.length - 1];
			num[num.length - 1] = num[num.length - 2];
			num[num.length - 2] = tmp;
		}
	}
	
	static void sort() {
		Arrays.sort(num, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});
	}
}
