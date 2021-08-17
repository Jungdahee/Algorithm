import java.util.*;

/*
    풀이 시간: 40분
    풀이 방법: bfs 탐색 이용
            - 'P'인 지점을 찾아서 상하좌우 탐색
            - 범위를 벗어나는 경우, 'X'인 경우 -> 큐에 삽입할 필요가 없음.
            - 맨하튼 거리가 2를 초과하는 경우 -> 마찬가지로 큐에 삽입할 필요가 없음.
            - 맨하튼 거리가 2를 초과하지 않는데 'P'인 경우 -> 불가능한 배치색(결과로 봤을 떄 0인 경우)
            - 위 경우가 모두 아니라면 더 탐색해봐야 함 -> 따라서 큐에 넣고 계속 탐색 진행
 */

public class programmers_거리두기_확인하기 {
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};

    public static class Pair {
        int x, y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int[] solution(String[][] places) {
        int answer[] = new int[5];

        for(int i = 0; i < places.length; i++) {
            answer[i] = isPossible(places[i]);
        }

        return answer;
    }

    public static int isPossible(String place[]) {
        for(int i = 0; i < place.length; i++){
            for(int j = 0; j < place.length; j++){
                if(place[i].charAt(j) == 'P'){
                    if(!bfs(i, j, place)) return 0;
                }
            }
        }

        return 1;
    }

    public static boolean bfs(int x, int y, String place[]){
        Queue<Pair> q = new LinkedList<>();
        boolean isVisited[][] = new boolean[5][5];

        q.offer(new Pair(x, y));
        isVisited[x][y] = true;

        while (!q.isEmpty()){
            Pair cur = q.poll();

            for(int d = 0; d < 4; d++){
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if(checkRange(nx, ny)) continue;
                if(isVisited[nx][ny]) continue;
                if(Math.abs(x - nx) + Math.abs(y - ny) > 2) continue;
                if(place[nx].charAt(ny) == 'X') continue;
                if(place[nx].charAt(ny) == 'P') return false;

                q.offer(new Pair(nx, ny));
                isVisited[nx][ny] = true;
            }
        }

        return true;
    }

    public static boolean checkRange(int x, int y){
        return x < 0 || x >= 5 || y < 0 || y >= 5;
    }

    public static void main(String args[]){
        String places[][] = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        int result[] = solution(places);

        System.out.println(Arrays.toString(result));
    }
}
