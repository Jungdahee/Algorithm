import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for(int i = 0; i < scoville.length; i++){
            pq.offer(scoville[i]);
        }
        
        while(!pq.isEmpty()){
            if(pq.peek() >= K) return answer;
            int a = pq.poll();
            
            if(!pq.isEmpty()){
                int b = pq.poll();
                pq.offer(a + (b * 2));
            }
            answer++;
        }
        return -1;
    }
}
