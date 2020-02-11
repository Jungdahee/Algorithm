package SWEA;

import java.io.*;

public class SWEA_1209_Sum {

	static int max, map[][];
	static int dx[] = {1, 1};
	static int dy[] = {1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			br.readLine();
			map = new int[100][100];
			max = -1;
			
			for(int i = 0; i < 100; i++) {
				String input1[] = br.readLine().split(" ");
 				for(int j = 0; j < 100; j++) map[i][j] = Integer.parseInt(input1[j]);
			}
			
			for(int i = 0; i < 100; i++) calc(i, 0, 0); //row => 0
			for(int j = 0; j < 100; j++) calc(0, j, 1); //col => 1
			calc2();
			System.out.println("#" + t + " " + max);
		}
	}
	
	static void calc(int i, int j, int op) {
		int result = 0;
		
		if(op == 0) for(int k = 0; k < 100; k++) result += map[i][k];
		else if(op == 1) for(int k = 0; k < 100; k++) result += map[k][j];
		
		if(result > max) max = result;
	}
	
	static void calc2() {
		int result = 0;
		int nx = 0;
		int ny = 0;
		
		while(true) {
			nx = nx + dx[0];
			ny = ny + dy[0];
			
			if(nx >= 100 || ny >= 100) break;
			result += map[nx][ny];
		}
		
		if(result > max) max = result;
		
		result = 0;
		nx = 99;
		ny = 99;
		while(true) {
			nx = nx + dx[1];
			ny = ny + dy[1];
			
			if(nx >= 100 || ny < 0) break;
			result += map[nx][ny];
		}
		
		if(result > max) max = result;
	}
}
