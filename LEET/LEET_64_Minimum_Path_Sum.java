public class LEET_64_Minimum_Path_Sum {
    public static int m, n, answer;
    public static int dx[] = {1, 0};
    public static int dy[] = {0, 1};

    public static class Vertex implements Comparable<Vertex>{
        int x, y, cost;

        public Vertex(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean visited[][] = new boolean[m][n];

        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
        pq.offer(new Vertex(0, 0, grid[0][0]));
        visited[0][0] = true;

        while(!pq.isEmpty()){
            Vertex cur = pq.poll();

            if(cur.x == m - 1 && cur.y == n - 1) {
                answer = cur.cost;
                break;
            }

            for(int dir = 0; dir < 2; dir++){
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(checkRange(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                pq.offer(new Vertex(nx, ny, cur.cost + grid[nx][ny]));
                visited[nx][ny] = true;
            }
        }

        return answer;
    }

    public static boolean checkRange(int x, int y){
        return x >= m || y >= n;
    }

    public static void main(String args[]){
        int grid[][] = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum(grid));
    }
}
