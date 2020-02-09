package SWEA;

import java.io.*;

public class SWEA_6781_삼삼트리플게임 {

	static int numbers[] = new int[9]; 
	static int tmpNum[] = new int[9];
	static String colors[] = new String[9];
	static String tmpCol[] = new String[9];
	static int indexes[] = new int[9];
	static boolean isEnd, selected[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			String input1[] = br.readLine().split("");
			String input2[] = br.readLine().split("");
			isEnd = false;
			selected = new boolean[9];
			for(int i = 0; i < 9; i++) {
				numbers[i] = Integer.parseInt(input1[i]);
				colors[i] = input2[i];
			}
			
			permutation(0);
			String result = isEnd == true ? "Win" : "Continue";
			System.out.println("#" + t + " " + result);
		}
	}
	
	static void permutation(int idx) {
		if(isEnd) return;
		if(idx == 9) judge();
		
		for(int k = 0; k < 9; k++) {
			if(selected[k]) continue;
			indexes[idx] = k;
			selected[k] = true;
			permutation(idx + 1);
			selected[k] = false;
		}
	}
	
	static void judge() {
		for(int i = 0; i < 9; i++) { 
			tmpNum[i] = numbers[indexes[i]]; 
			tmpCol[i] = colors[indexes[i]];
		}
		
		//연속 세개의 카드의 색이 다르면 불가능한 경우
		if(!tmpCol[0].equals(tmpCol[1]) || !tmpCol[1].equals(tmpCol[2]) 
			|| !tmpCol[3].equals(tmpCol[4]) || !tmpCol[4].equals(tmpCol[5])
				|| !tmpCol[6].equals(tmpCol[7]) || !tmpCol[7].equals(tmpCol[8])) {
					return;
		}	
		
		if(!(tmpNum[0] + 1 == tmpNum[1] && tmpNum[1] + 1 == tmpNum[2])
				&& !(tmpNum[0] == tmpNum[1] && tmpNum[1] == tmpNum[2])) {
					return;
		}
		
		//두번째 세자리가 안되는 경우
		if(!(tmpNum[3] + 1 == tmpNum[4] && tmpNum[4] + 1 == tmpNum[5])
				&& !(tmpNum[3] == tmpNum[4] && tmpNum[4] == tmpNum[5])) {
					return;
		}
		
		//두번째 세자리가 안되는 경우
		if(!(tmpNum[6] + 1 == tmpNum[7] && tmpNum[7] + 1 == tmpNum[8])
				&& !(tmpNum[6] == tmpNum[7] && tmpNum[7] == tmpNum[8])) {
					return;
		}
		
		isEnd = true;
	}
}
