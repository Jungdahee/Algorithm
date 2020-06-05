import java.util.*;

class Solution {
    public static String tmpStr, ticketList[][];
    public static boolean isSuccess, visited[];
    
    public String[] solution(String[][] tickets) {
        // 모든 티켓을 다 사용했는지 확인을 하기 위한 boolean 배열
        visited = new boolean[tickets.length];
        isSuccess = true;
        
        Arrays.sort(tickets, new Comparator<String[]>(){
            
            @Override
			public int compare(String[] o1, String[] o2) {
				if(o1[0].equals(o2[0])) {
					return o1[1].compareTo(o2[1]);
				}
				return o1[0].compareTo(o2[0]);
			}
        });
                    
        ticketList = tickets;
        dfs("", "ICN", 0);
        
        String answer[] = tmpStr.split(" ");
        return answer;
    }
    
    public static void dfs(String s, String start, int cnt){
        // 전부 다 처리가 된 경우
        if(cnt == ticketList.length){
            tmpStr = "ICN " + s;
            isSuccess = true;
            return;
        }
        
        for(int i = 0; i < ticketList.length; i++){
            if(visited[i]) continue;
            if(ticketList[i][0].equals(start)){
                visited[i] = true;
                dfs(s + ticketList[i][1] + " ", ticketList[i][1], cnt + 1);
                
                // 만들어지지 못하는 경우
                if(!isSuccess) visited[i] = false;
                // 만든 경우라면 계속 return해서 빠져나감
                else return;
            }
        }
        
        isSuccess = false;
    }
}
