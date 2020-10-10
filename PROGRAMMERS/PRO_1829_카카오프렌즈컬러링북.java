import java.util.*;

class PRO_1829_카카오프렌즈컬러링북 {
    
    public static int m, n, map[][];
    public static boolean visited[][];
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0 ,0, -1, 1};
    
    public static class Pair {
        int x, y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int[] solution(int h, int w, int[][] picture) {
        int part = 0;
        int max = Integer.MIN_VALUE;
        
        map = picture;
        m = h;
        n = w;

        visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]) continue;
                if(map[i][j] == 0) continue;
                int cnt = bfs(i, j);
                part++;

                if(cnt > max) max = cnt;
            }
        }
        
        int answer[] = {part, max};
        return answer;
    }
    
    public static int bfs(int i, int j){
        Queue<Pair> q = new LinkedList<Pair>();
        q.offer(new Pair(i, j));
        visited[i][j] = true;

        int cnt = 1;
        while(!q.isEmpty()){
            Pair cur = q.poll();

            for(int dir = 0; dir < 4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(checkRange(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 0) continue;
                if(map[cur.x][cur.y] != map[nx][ny]) continue;
                q.offer(new Pair(nx, ny));
                visited[nx][ny] = true;
                cnt++;
            }
        }

        return cnt;
    }

    public static boolean checkRange(int x, int y){
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}
