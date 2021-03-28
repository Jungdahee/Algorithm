public class PRO_12600_2XN타일링 {

    public static int solution(int n) {
        int answer = 0;

        int dp[] = new int[60000];

        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }

    public static void main(String args[]){
        System.out.println(solution(5));
    }
}
