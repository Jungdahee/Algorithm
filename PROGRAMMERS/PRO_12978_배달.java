package Catch_PS;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PRO_12978_배달 {

    public static class Vertex implements Comparable<Vertex> {
        int v, weight;

        public Vertex(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String args[]){
        int road[][] = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};

        System.out.println(solution(5, road, 3));
    }

    public static int solution(int N, int[][] road, int K) {
        int result = 0;

        int adj[][] = new int[N][N];
        int d[] = new int[N];
        boolean visited[] = new boolean[N];


        for(int i = 0; i < road.length; i++){
            int a = road[i][0] - 1;
            int b = road[i][1] - 1;
            int dis = road[i][2];

            if(adj[a][b] != 0 && adj[a][b] < dis) continue;
            adj[a][b] = dis;
            adj[b][a] = dis;
        }

        Arrays.fill(d, Integer.MAX_VALUE);

        d[0] = 0;

        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
        pq.offer(new Vertex(0, d[0]));

        while(!pq.isEmpty()){
            Vertex cur = pq.poll();

            if(visited[cur.v]) continue;
            visited[cur.v] = true;

            for(int i = 0; i < N; i++){
                if(!visited[i] && adj[cur.v][i] != 0 && cur.weight + adj[cur.v][i] < d[i]){
                    d[i] = cur.weight + adj[cur.v][i];
                    pq.offer(new Vertex(i, d[i]));
                }
            }
        }

        for(int i = 0; i < d.length; i++){
            if(d[i] <= K) result++;
        }

        return result;
    }
}
