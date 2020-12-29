public class LEET_48_Rotate_Image {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; ++i) {
            for (int j = i; j < n - i - 1; ++j) {
                int tmp = matrix[i][j]; // 위에 있는 원소 tmp에 임시 저장
                matrix[i][j] = matrix[n - j - 1][i]; // 오른쪽 원소를 위로
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1]; // 아래 원소를 오른쪽으로
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1]; // 왼쪽 원소를 아래쪽으로
                matrix[j][n - i - 1] = tmp; // 왼쪽 원소를 tmp에 저장했던 위 원소로
            }
        }
    }
}
