import java.util.*;

class PRO_42583_다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new LinkedList<Integer>();
        int time = 0;
        int tmpWeight = 0;
        
        for(int i = 0; i < bridge_length - 1; i++) bridge.offer(0);
        bridge.offer(truck_weights[0]);
        tmpWeight += truck_weights[0];
        time++;
        
        int idx = 1;
        
        while(true){
            if(idx == truck_weights.length) break;
            
            if(bridge.size() == bridge_length){
                tmpWeight -= bridge.poll();
            }
            
            if(tmpWeight + truck_weights[idx] <= weight){
                bridge.offer(truck_weights[idx]);
                tmpWeight += truck_weights[idx];
                idx++;
            }
            
            else bridge.offer(0);
            time++;
            
        }
        return time + bridge.size();
    }
}
