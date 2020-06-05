import java.util.*;

class Solution {
    public static String tmp;
    public static boolean visited[];
    public static HashSet<Integer> hs;
    
    public int solution(String numbers) {
        tmp = numbers;
        hs = new HashSet<Integer>();
        
        for(int k = 1; k <= numbers.length(); k++){
            visited = new boolean[numbers.length()];
            permutation(0, "", k);
        }

        return hs.size();
    }
    
    public static void permutation(int cnt, String s, int range){
        if(cnt == range){
            if(isPrime(Integer.parseInt(s))) {
                hs.add(Integer.parseInt(s));
            }
            return;
        }
        
        for(int i = 0; i < tmp.length(); i++){
            if(visited[i]) continue;
            visited[i] = true;
            permutation(cnt + 1, s + tmp.charAt(i), range);
            visited[i] = false;
        }
    }
    
    public static boolean isPrime(int num){
        if(num == 0 || num == 1) return false;
        for(int i = 2; i < num; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}
