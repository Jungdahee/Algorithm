public class PRO_43105_정수삼각형 {
    public static int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int dp[] = new int[(n * (n + 1)) / 2];

        dp[0] = triangle[0][0];

        for(int i = 1; i < n; i++){
            for(int j = 0; j < triangle[i].length; j++){
                int l = (i * (i + 1)) / 2 + j;
                if(j == 0) dp[l] = dp[l - i] + triangle[i][j];
                else if(j == triangle[i].length - 1) dp[l] = dp[l - i - 1] + triangle[i][j];
                else dp[l] = Math.max(dp[l - i - 1], dp[l - i]) + triangle[i][j];

                if(answer < dp[l]) answer = dp[l];
;            }
        }
        return answer;
    }

    public static void main(String args[]){
        int triangle[][] = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(triangle));
    }
}
