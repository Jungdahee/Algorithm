public class LEET_62_Unique_Paths {
    public static int uniquePaths(int m, int n) {
        int map[][] = new int[m][n];

        // 첫 행과 첫 열 1로 세팅(그쪽으로 올 수 있는 경우의 수는 1이므로)
        for(int i = 0; i < m; i++) map[i][0] = 1;
        for(int i = 0; i < n; i++) map[0][i] = 1;

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                map[i][j] = map[i][j - 1] + map[i - 1][j];
            }
        }
        return map[m - 1][n - 1];
    }

    public static void main(String args[]){
        System.out.println(uniquePaths(3, 7));
    }
}
