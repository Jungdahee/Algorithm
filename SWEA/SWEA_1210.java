import java.util.Scanner;

public class SWEA_1210 { // Ladder1
	static int map[][] = new int[100][100];
	static int visit[][];
	static boolean isFind = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			sc.nextInt(); 
			int result = 0;
			isFind = false;

			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			for (int j = 0; j < 100; j++) {
				if (map[0][j] == 1) {
					go(0, j);
					if(isFind) {
						result = j;
						break;
					}
				}
			}
			System.out.println("#" + test_case + " " + result);
		}
	}

	public static void go(int i, int j) {
		visit = new int[100][100];

		while(i < 100) {
			visit[i][j] = 1;
			if(map[i][j] == 2) {
				isFind = true; 
				break;
			}
			
			if(j - 1 >= 0 && map[i][j - 1] == 1 && visit[i][j - 1] == 0) j--;
			else if(j + 1 < 100 && map[i][j + 1] == 1 && visit[i][j + 1] == 0) j++;
			else i++;
		}
	}
}
