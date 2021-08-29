import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class baekjoon_연구소 {
    public static int N, M, answer, map[][], tMap[][], sMap[][];
    public static Queue<Pair> queue;
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};

    public static class Pair {
        int x, y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        tMap = new int[N][M];
        sMap = new int[N][M];
        answer = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++){
            input = br.readLine().split(" ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < map.length; i++) {
            tMap[i] = map[i].clone();
        }

        makeWall(0);

        System.out.println(answer);
    }

    // 벽 세우기
    public static void makeWall(int cnt){
        if(cnt == 3){
            for (int i = 0; i < tMap.length; i++) {
                sMap[i] = tMap[i].clone();
            }

            spreadVirus();
            answer = Math.max(answer, countSafeArea());
            return;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(tMap[i][j] == 0){
                    tMap[i][j] = 1;
                    makeWall(cnt + 1);
                    tMap[i][j] = 0;
                }
            }
        }
    }

    public static void spreadVirus(){
        Queue<Pair> queue = new LinkedList<Pair>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(sMap[i][j] == 2){
                    queue.offer(new Pair(i, j));
                }
            }
        }

        while(!queue.isEmpty()){
            Pair cur = queue.poll();

            for(int dir = 0; dir < 4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(checkRange(nx, ny)) continue;
                if(sMap[nx][ny] == 0){
                    sMap[nx][ny] = 2;
                    queue.offer(new Pair(nx, ny));
                }
            }
        }
    }

    public static int countSafeArea(){
        int result = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(sMap[i][j] == 0) result++;
            }
        }

        return result;
    }

    public static boolean checkRange(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
