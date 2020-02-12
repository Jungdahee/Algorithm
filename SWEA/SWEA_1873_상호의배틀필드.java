package SWEA;

import java.io.*;

public class SWEA_1873_상호의배틀필드 {

	static int h, w, n;
	static int carX, carY;
	static String map[][], order[];
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};

	static boolean checkRange(int x, int y) {
		return x < 0 || x >= h || y < 0 || y >= w;
	}

	static boolean checkOrder(int x, int y) {
		return map[x][y].equals("^") || map[x][y].equals("v") || map[x][y].equals("<") || map[x][y].equals(">");
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for(int t = 1; t <= tc; t++) {
			String input1[] = br.readLine().split(" ");
			h = Integer.parseInt(input1[0]);
			w = Integer.parseInt(input1[1]);
			map = new String[h][w];

			//#1. 입력 받기
			for(int i = 0; i < h; i++) {
				String input2[] = br.readLine().split("");
				for(int j = 0; j < w; j++) {
					map[i][j] = input2[j];
					if(checkOrder(i, j)) {
						carX = i; //전차 위치 표시
						carY = j;
					}
				}
			}

			n = Integer.parseInt(br.readLine()); //명령 개수 받기
			order = br.readLine().split(""); //명령 받기

			for(int i = 0; i < n; i++) {
				if(order[i].equals("U") || order[i].equals("D") 
						|| order[i].equals("L") || order[i].equals("R")){
					changeDir(order[i]);
				}
				else if(order[i].equals("S")) {
					shooting();
				}
			}

			System.out.print("#" + t + " ");
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
	}

	static void changeDir(String order) {
		if(order.equals("U")) {
			if(!checkRange(carX - 1, carY) && map[carX - 1][carY].equals(".")) {
				map[carX - 1][carY] = "^";
				map[carX][carY] = ".";
				carX = carX - 1;
			} else map[carX][carY] = "^";
		}
		else if(order.equals("D")) {
			if(!checkRange(carX + 1, carY) && map[carX + 1][carY].equals(".")) {
				map[carX + 1][carY] = "v";
				map[carX][carY] = ".";
				carX = carX + 1;
			} else map[carX][carY] = "v";
		}
		else if(order.equals("L")) {
			if(!checkRange(carX, carY - 1) && map[carX][carY - 1].equals(".")) {
				map[carX][carY - 1] = "<";
				map[carX][carY] = ".";
				carY = carY - 1;
			} else map[carX][carY] = "<";
		}
		else {
			if(!checkRange(carX, carY + 1) && map[carX][carY + 1].equals(".")) {
				map[carX][carY + 1] = ">";
				map[carX][carY] = ".";
				carY = carY + 1;
			} else map[carX][carY] = ">";
		}
	}

	static void shooting() {
		int nx = carX;
		int ny = carY;

		if(map[carX][carY].equals("^")) {
			while(true) {
				nx = nx + dx[0];
				ny = ny + dy[0];

				if(checkRange(nx, ny) || map[nx][ny].equals("#")) return;
				if(map[nx][ny].equals("*")) {
					map[nx][ny] = "."; 
					return;
				}
			}
		}
		else if(map[carX][carY].equals("v")) {
			while(true) {
				nx = nx + dx[1];
				ny = ny + dy[1];

				if(checkRange(nx, ny) || map[nx][ny].equals("#")) return;
				if(map[nx][ny].equals("*")) {
					map[nx][ny] = "."; 
					return;
				}
			}
		}
		else if(map[carX][carY].equals("<")) {
			while(true) {
				nx = nx + dx[2];
				ny = ny + dy[2];

				if(checkRange(nx, ny) || map[nx][ny].equals("#")) return;
				if(map[nx][ny].equals("*")) {
					map[nx][ny] = "."; 
					return;
				}
			}
		}
		else {
			while(true) {
				nx = nx + dx[3];
				ny = ny + dy[3];

				if(checkRange(nx, ny) || map[nx][ny].equals("#")) return;
				if(map[nx][ny].equals("*")) {
					map[nx][ny] = "."; 
					return;
				}
			}
		}
	}
}
