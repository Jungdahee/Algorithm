import java.util.*;
class Solution {
    public static int scores[];
    public static int supo[][];
    public int[] solution(int[] answers) {
        scores = new int[3];
        supo = new int[][]{{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        
        calc(answers);

        int max = -1;
        for(int i = 0; i < scores.length; i++){
            if(max < scores[i]) max = scores[i];
        }
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < scores.length; i++){
            if(scores[i] == max) list.add(i + 1);
        }
        
        int answer[] = new int[list.size()];
        for(int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        
        return answer;
    }
    
    public static void calc(int answers[]) {
        for(int k = 0; k < 3; k++){
            int result = 0, i = 0, j = 0;

            while(true){
                if(i == answers.length) break;
                if(j % supo[k].length == 0) j = 0;
                if(answers[i++] == supo[k][j++]) result++;
            }
            scores[k] = result;
        }
    }
}
