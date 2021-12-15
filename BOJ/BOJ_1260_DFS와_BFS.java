package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1260_DFS와_BFS {
    public static int N, M, V, path[], map[][];
    public static boolean visited[];

    public static void dfs(int vertex) {
        for(int i = 0; i < N; i++) {
            if (visited[i]) continue;
            if (map[vertex][i] == 0) continue;

            visited[i] = true;
            System.out.print((i + 1) + " ");
            dfs(i);
        }
    }

    public static void bfs(int vertex) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(vertex);

        while(!q.isEmpty()) {
            Integer cur = q.poll();

            for(int i = 0; i < N; i++) {
                if(visited[i]) continue;
                if(map[cur][i] == 0) continue;

                q.offer(i);
                visited[i] = true;
                System.out.print((i + 1) + " ");
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        V = Integer.parseInt(input[2]) - 1;

        map = new int[N][N];

        for(int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            int v1 = Integer.parseInt(input[0]) - 1;
            int v2 = Integer.parseInt(input[1]) - 1;

            map[v1][v2] = 1;
            map[v2][v1] = 1;
        }

        visited = new boolean[N];
        visited[V] = true;
        System.out.print((V + 1) + " ");
        dfs(V);

        System.out.println();

        visited = new boolean[N];
        visited[V] = true;
        System.out.print((V + 1) + " ");
        bfs(V);
    }
}
