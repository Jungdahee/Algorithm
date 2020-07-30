package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_7682_틱택토 {

    public static int oCnt, xCnt, eCnt;
    public static char board[][];
    public static boolean isAvailable;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String str = br.readLine().trim();

            if(str.equals("end")) return;
            String input[] = str.split("");
            board = new char[3][3];
            isAvailable = true;
            xCnt = 0;
            oCnt = 0;
            eCnt = 0;

            int idx = 0;
            for(int i = 0; i < input.length; i++){
                char ch = input[i].charAt(0);

                if(i != 0 && i % 3 == 0) idx = 0;
                board[i / 3][idx++] = ch;

                if(ch == 'O') oCnt++;
                else if(ch == 'X') xCnt++;
                else eCnt++;
            }

            if(oCnt > xCnt) isAvailable = false; // O 개수가 X 개수보다 많을 수 없음
            if(oCnt >= 5) isAvailable = false; // X가 먼저 시작하기 때문에 O가 5가 될 수 없음
            if(xCnt - oCnt > 1 || xCnt - oCnt < 0) isAvailable = false; // 두 개의 개수 차이는 0이거나 1이여야 함
            if(eCnt > 4) isAvailable = false; // 최소 개수로 틱택토를 생성한다고 할 때 공백이 4개 이상 생길 수 없음

            if(!isAvailable){
                System.out.println("invalid");
                continue;
            }

            int cntX = 0;
            int cntO = 0;

            // X 체크
            cntX += checkHorizontal(0, 'X');
            cntX += checkHorizontal(1, 'X');
            cntX += checkHorizontal(2, 'X');

            cntX += checkVertical(0, 'X');
            cntX += checkVertical(1, 'X');
            cntX += checkVertical(2, 'X');

            cntX += checkDiagonal(0, 0, 'X', 1);
            cntX += checkDiagonal(0, 2, 'X', 2);

            cntO += checkHorizontal(0, 'O');
            cntO += checkHorizontal(1, 'O');
            cntO += checkHorizontal(2, 'O');

            cntO += checkVertical(0, 'O');
            cntO += checkVertical(1, 'O');
            cntO += checkVertical(2, 'O');

            cntO += checkDiagonal(0, 0, 'O', 1);
            cntO += checkDiagonal(0, 2, 'O', 2);

            // X가 이긴 경우
            if(xCnt > oCnt){
                if(cntO > 0) {
                    System.out.println("invalid");
                    continue;
                }
            }
            // O가 이긴 경우
            else if(xCnt == oCnt) {
                if (cntX > 0) {
                    System.out.println("invalid");
                    continue;
                }
            }

            // 둘 다 만들어 지지 않은 경우
            if(cntO == 0 && cntX == 0){
                // 풀 카운트여야 valid
                if(xCnt != 5 || oCnt != 4) {
                    System.out.println("invalid");
                    continue;
                }
            }

            System.out.println("valid");
        }
    }

    private static int checkHorizontal(int i, char ch){
        if(board[i][0] == ch && board[i][1] == ch && board[i][2] == ch) return 1;
        else return 0;
    }

    private static int checkVertical(int j, char ch){
        if(board[0][j] == ch && board[1][j] == ch && board[2][j] == ch) return 1;
        else return 0;
    }

    private static int checkDiagonal(int i, int j, char ch, int dir){
        // 오른쪽 아래 대각선
        if(dir == 1){
            if(board[i][j] == ch && board[i + 1][j + 1] == ch && board[i + 2][j + 2] == ch) return 1;
        }

        // 왼쪽 아래
        else if(dir == 2){
            if(board[i][j] == ch && board[i + 1][j - 1] == ch && board[i + 2][j - 2] == ch) return 1;
        }

        return 0;
    }
}
