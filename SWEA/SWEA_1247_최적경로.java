package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_1247_최적경로 {
	
	static int n, min, startX, startY, endX, endY, customers[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			customers = new int[n][2];
			min = Integer.MAX_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < n; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			permutation(0, 0, startX, startY, 0);
			System.out.println("#" + t + " " + min);
		}
	}
	
	private static void permutation(int cnt, int visited, int tmpX, int tmpY, int result) {
		if(result >= min) return; //가지치기 : 계산하고 있는 결과가 기존의 최소 거리보다 큰 경우 더이상 계산할 필요가 없음.
		if(cnt == n) { //순열을 다 만든 기저 조건
			result += Math.abs(tmpX - endX) + Math.abs(tmpY - endY);
			if(min > result) min = result;
			return;
		}
		
		for(int i = 0; i < n; i++) { //모든 고객 집을 다 count 위치에 시도
			if((visited & 1 << i) == 0) { //visited & 1 << i : i 고객집이 기존 순열에 처리되었는지 확인
				 permutation(cnt + 1, visited | (1 << i), customers[i][0], customers[i][1], 
						 result + Math.abs(tmpX - customers[i][0]) + Math.abs(tmpY - customers[i][1]));
			}
		}
	}
}
