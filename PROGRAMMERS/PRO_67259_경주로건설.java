public class PRO_67259_경주로건설 {
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};
    public static int N;

    public static class Loc{
        int x, y, dir, cost;

        public Loc(int x, int y, int dir, int cost){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
    public static int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        N = board.length;

        Queue<Loc> q = new LinkedList<Loc>();
        q.offer(new Loc(0, 0, -1, 0));
        board[0][0] = 1;

        while(!q.isEmpty()){
            Loc cur = q.poll();

            if(cur.x == N - 1 && cur.y == N - 1) {
                if(cur.cost < answer){
                    answer = cur.cost;
                }
                continue;
            }

            for(int dir = 0; dir < 4; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(checkRange(nx, ny)) continue;
                if(board[nx][ny] == 1) continue;

                int cost = 0;
                if(cur.dir == -1 || cur.dir == dir) cost = 100;
                else cost = 600;

                if(board[nx][ny] == 0 || cur.cost + cost <= board[nx][ny]){
                    q.offer(new Loc(nx, ny, dir, cur.cost + cost));
                    board[nx][ny] = cur.cost + cost;
                }
            }
        }
        return answer;
    }

    public static boolean checkRange(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
