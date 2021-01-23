public class PRO_49189_가장먼노드 {

    public static class Vertex implements Comparable<Vertex>{
        int n, weight;

        public Vertex(int n, int weight){
            this.n = n;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        int d[] = new int[n];
        boolean visited[] = new boolean[n];

        LinkedList<Vertex> list[] = new LinkedList[n];
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++) list[i] = new LinkedList<Vertex>();

        for(int i = 0; i < edge.length; i++){
            list[edge[i][0] - 1].add(new Vertex(edge[i][1] - 1, 1));
            list[edge[i][1] - 1].add(new Vertex(edge[i][0] - 1, 1));
        }

        Arrays.fill(d, Integer.MAX_VALUE);
        d[0] = 0;

        pq.offer(new Vertex(0, d[0]));

        while(!pq.isEmpty()){
            Vertex cur = pq.poll();

            if(visited[cur.n]) continue;
            visited[cur.n] = true;

            for(Vertex v : list[cur.n]){
                if(visited[v.n]) continue;
                if(cur.weight + v.weight >= d[v.n]) continue;
                d[v.n] = cur.weight + v.weight;
                pq.offer(new Vertex(v.n, d[v.n]));
            }
        }

        Arrays.sort(d);

        for(int i = 0; i < d.length; i++){
            if(d[i] == d[d.length - 1]) answer++;
        }

        return answer;
    }

    public static void main(String args[]){
        int edge[][] = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(6, edge));
    }
}
