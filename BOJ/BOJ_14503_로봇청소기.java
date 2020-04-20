package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실행 시간 80ms
public class BOJ_14503_로봇청소기 {

	public static int N, M, d, robotX, robotY, nx, ny, result, map[][];
	public static boolean isEnd, isBack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input1[] = br.readLine().split(" ");
		N = Integer.parseInt(input1[0]);
		M = Integer.parseInt(input1[1]);

		map = new int[N][M]; // 입력 상태를 저장할 2차원 배열 생성
		String input2[] = br.readLine().split(" ");
		robotX = Integer.parseInt(input2[0]); // 로봇의 시작 x위치
		robotY = Integer.parseInt(input2[1]); // 로봇의 시작 y위치
		d = Integer.parseInt(input2[2]); // 로봇 헤드의 시작 방향

		// input 값 입력 받기
		for(int i = 0; i < N; i++) {
			String input3[] = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input3[j]);
			}
		}
		
		// 로직 수행
		while(true) {
			isBack = false;
			clear(); // 현재 로봇이 있는 위치 청소
			rotate(); // 네 방향으로 청소할 수 있는 영역이 있는지 탐색
			if(!isBack) go(); // 후진을 한 경우가 아니면 로봇 위치 갱신
			if(isEnd) break; // 종료 조건
		}
		// 결과 출력
		System.out.println(result);
	}

	public static void clear() {
		// 현재 로봇이 있는 곳이 0이라면 청소가 필요하므로 청소
		if(map[robotX][robotY] == 0) {	
			result++; // 청소했으니 영역 카운팅 증가
			map[robotX][robotY] = 2; // 청소한 부분을 2로 셋팅
		}
	}

	public static void rotate() {
		nx = robotX; // 로봇이 움직일 위치 저장
		ny = robotY; // 로봇이 움직일 위치 저장

		// 4번 방향 전환 시도
		for(int i = 0; i < 4; i++) {
			// 현재 방향에서 왼쪽 방향 탐색
			d = (d + 3) % 4;
			if(d == 0) {
				if(checkRange(nx - 1, ny)) continue;
				if(map[nx - 1][ny] == 0) { // 북쪽 방향은 좌표 상 (x - 1, y) 위치이므로 청소가 가능한지 확인
					nx = nx - 1;
					break; 
				}
			} else if(d == 3) {
				if(checkRange(nx, ny - 1)) continue;
				if(map[nx][ny - 1] == 0) { // 서쪽 방향은 좌표 상 (x, y - 1) 위치이므로 청소가 가능한지 확인
					ny = ny - 1;
					break;
				}
			} else if(d == 2) {
				if(checkRange(nx + 1, ny)) continue;
				if(map[nx + 1][ny] == 0) { // 남쪽 방향은 좌표 상 (x + 1, y) 위치이므로 청소가 가능한지 확인
					nx = nx + 1;
					break;
				}
			} else {
				if(checkRange(nx, ny + 1)) continue;
				if(map[nx][ny + 1] == 0) { // 동쪽 방향은 좌표 상 (x, y + 1) 위치이므로 청소가 가능한지 확인
					ny = ny + 1;
					break;
				}
			}
			// 한 바퀴를 다 회전했는데 청소할 수 있는 구역을 발견하지 못한 경우
			if(i == 3) back();
		}
	}

	public static void go() {
		// 다음에 이동할 위치로 로봇 위치 변경
		robotX = nx;
		robotY = ny;
	}

	public static void back() {
		// 각 로봇이 바라보는 반대 방향으로 후진했을 때 범위를 벗어나지 않고 벽이 아닌 경우 이동 가능
		// 이동이 가능하다면 로봇의 현재 위치 변경
		if(d == 0) {
			if(!checkRange(robotX + 1, robotY) && map[robotX + 1][robotY] != 1) {
				robotX = robotX + 1;
			}
		} else if(d == 1) {
			if(!checkRange(robotX, robotY - 1) && map[robotX][robotY - 1] != 1) {
				robotY = robotY - 1;
			}
		} else if(d == 2) {
			if(!checkRange(robotX - 1, robotY) && map[robotX - 1][robotY] != 1) {
				robotX = robotX - 1;
			}
		} else {
			if(!checkRange(robotX, robotY + 1) && map[robotX][robotY + 1] != 1)
				robotY = robotY + 1;
		}

		// 기존에 저장해둔 위치와 위에서 갱신된 로봇의 위치가 그대로라면 후진을 못하는 경우이므로 isEnd를 true로 변경
		if(nx == robotX && ny == robotY) isEnd = true;
		// 그게 아니라면 후진이 가능한 경우이므로 isBack을 true로 변경
		else isBack = true;
	}

	public static boolean checkRange(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}
