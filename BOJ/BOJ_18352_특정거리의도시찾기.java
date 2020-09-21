package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352_특정거리의도시찾기 {

    public static class Vertex implements Comparable<Vertex>{
        int v, dist;

        public Vertex(int v, int dist){
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        int start = Integer.parseInt(input[3]) - 1;

        List<Vertex> list[] = new ArrayList[N];

        for(int i = 0; i < N; i++) list[i] = new ArrayList<Vertex>();

        int d[] = new int[N];
        boolean visited[] = new boolean[N];

        for(int i = 0; i < M; i++){
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]) - 1;
            int b = Integer.parseInt(input[1]) - 1;
            list[a].add(new Vertex(b, 1));
        }

        Arrays.fill(d, Integer.MAX_VALUE);

        d[start] = 0;

        PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
        q.offer(new Vertex(start, d[start]));

        while(!q.isEmpty()){
            Vertex cur = q.poll();

            if(visited[cur.v]) continue;
            visited[cur.v] = true;

            for(Vertex v : list[cur.v]){
                if(!visited[v.v] && cur.dist + v.dist < d[v.v]){
                    d[v.v] = cur.dist + v.dist;
                    q.offer(new Vertex(v.v, d[v.v]));
                }
            }
        }

        int cnt = 0;
        for(int i = 0; i < d.length; i++){
            if(d[i] == K) {
                cnt++;
                System.out.println(i + 1);
            }
        }

        if(cnt == 0) System.out.println(-1);
    }
}
