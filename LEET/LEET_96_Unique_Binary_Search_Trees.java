public class LEET_96_Unique_Binary_Search_Trees {
    public static int numTrees(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < n + 1; i++){
            for(int j = 0; j < i; j++){
                System.out.println(dp[j] + " - " + dp[i - 1 - j]);
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        return dp[n];
    }

    public static void main(String args[]){
        System.out.println(numTrees(5));
    }
}
