import java.util.*;

class Solution {
    public static int answer;
    public static boolean visited[];
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]) {
                visited[i] = true;
                network(i, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void network(int idx, int computers[][]){
        for(int i = 0; i < computers[idx].length; i++){
            if(i == idx) continue;
            if(visited[i]) continue;
            if(computers[idx][i] == 1) {
                visited[i] = true;
                network(i, computers);
            }
        }
    }
}
