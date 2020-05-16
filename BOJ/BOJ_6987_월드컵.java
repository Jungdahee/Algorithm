package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_6987_월드컵 {

	public static ArrayList<Game> list;
	public static int board[][];
	public static boolean isSuccess;
	
	public static class Game {
		int team1, team2;
		
		public Game(int team1, int team2) {
			this.team1 = team1;
			this.team2 = team2;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 4;
		list = new ArrayList<Game>();
		board = new int[6][3];
		combination();
		
		L: while(tc-- > 0) {
			String input[] = br.readLine().split(" ");
			isSuccess = false;
			
			int k = 0;
			for(int i = 0; i < 6; i++) {
				int total = 0;
				for(int j = 0; j < 3; j++) {
					board[i][j] = Integer.parseInt(input[k++]);
					total += board[i][j];
				}
				
				if(total != 5) {
					sb.append(0).append(" ");
					continue L;
				}
			}
			
			judge(0);
			
			if(isSuccess) sb.append(1).append(" ");
			else sb.append(0).append(" ");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void combination() {
		for(int i = 0; i < 5; i++) {
			for(int j = i + 1; j < 6; j++) {
				list.add(new Game(i, j));
			}
		}
	}
	
	private static void judge(int idx) {
		if(idx == 15) {
			isSuccess = true;
			return;
		}
		
		int team1 = list.get(idx).team1;
		int team2 = list.get(idx).team2;
		
		for(int i = 0; i < 3; i++) {
			int j = 2 - i;
			if(board[team1][i] > 0 && board[team2][j] > 0) {
				board[team1][i]--;
				board[team2][j]--;
				judge(idx + 1);
				board[team1][i]++;
				board[team2][j]++;
			}
			
			if(isSuccess) return;
		}
	}
}
