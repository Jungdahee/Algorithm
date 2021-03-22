public class PRO_42861_섬연결하기 {
    public static int parents[];

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        int V = n;
        parents = new int[V];

        Arrays.sort(costs, new Comparator<int []>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });

        Arrays.fill(parents, -1);

        int cnt = 0;
        for(int i = 0; i < costs.length; i++){
            if(cnt == V - 1) break;

            if(union(costs[i][0], costs[i][1])){
                answer += costs[i][2];
                cnt++;
            }
        }

        return answer;
    }

    public static int find(int x){
        if(parents[x] < 0) return x;
        return parents[x] = find(parents[x]);
    }

    public static boolean union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);

        if(xRoot != yRoot){
            parents[yRoot] = xRoot;
            return true;
        }
        return false;
    }

    public static void main(String args[]){
        int costs[][] = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        System.out.println(solution(4, costs));
    }
}
