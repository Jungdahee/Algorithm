/*
    풀이 시간: 40분
    풀이 방법:
            - 행을 회전하는 함수와 열을 회전하는 함수를 두어 복사하는 방향(상하좌우)에 따라 동작하게 함.
            - 복사를 하면서 min 값 갱신
 */
public class programmers_행렬_테두리_회전하기_jdh {
    public static int min, board[][];

    public static int[] solution(int rows, int columns, int[][] queries) {
        int answer[] = new int[queries.length];

        board = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                board[i][j] = ((i + 1) - 1) * columns + (j + 1);
            }
        }

        for(int i = 0; i < queries.length; i++){
            int startX = queries[i][0] - 1; // 2
            int startY = queries[i][1] - 1; // 2
            int endX = queries[i][2] - 1; // 5
            int endY = queries[i][3] - 1; // 4

            int startNum = board[startX][startY]; // 왼쪽 모서리 값 저장
            min = Math.min(startNum, Integer.MAX_VALUE);

            rotateRows(startX, startY, endX, startY, 1);
            rotateColumns(endX, startY, endX, endY, 3);
            rotateRows(endX, endY, startX, endY, 2);
            rotateColumns(startX, endY, startX, startY, 4);

            board[startX][startY + 1] = startNum;

            answer[i] = min;
        }

        return answer;
    }

    // 열 회전
    public static void rotateRows(int startX, int startY, int endX, int endY, int dir){
        if(dir == 1){
            for(int i = startX + 1; i <= endX; i++){
                min = Math.min(min, board[i][startY]); // 최소 값 갱신
                board[i - 1][startY] = board[i][startY];
            }
        }
        else {
            for(int i = startX - 1; i >= endX; i--){
                min = Math.min(min, board[i][endY]); // 최소 값 갱신
                board[i + 1][endY] = board[i][endY];
            }
        }
    }

    // 행 회전
    public static void rotateColumns(int startX, int startY, int endX, int endY, int dir){
        if(dir == 3){
            for(int i = startY + 1; i <= endY; i++){
                min = Math.min(min, board[startX][i]); // 최소 값 갱신
                board[startX][i - 1] = board[startX][i];
            }
        }
        else {
            for(int i = startY - 1; i >= endY; i--){
                min = Math.min(min, board[endX][i]); // 최소 값 갱신
                board[endX][i + 1] = board[endX][i];
            }
        }
    }

    public static void main(String args[]){
        int queries[][] = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        System.out.println(Arrays.toString(solution(6, 6, queries)));
    }
}
