package PRO;

public class PRO_12914_멀리뛰기 {
    /*
        5까지 만들 수 있는 경우의 수를 고려해보니 n을 만들기 위해서는
        n - 1을 만들기 위한 경우의 수 + n - 2를 만들기 위한 경우의 수의 합이 됨.
        따라서 점화식은 d[n - 1] + d[n - 2]이 됨.
     */

    public static void main(String args[]){
        int n = 3;

        int d[] = new int[2001];
        d[1] = 1;
        d[2] = 2;
        d[3] = 3;

        for(int i = 4; i <= n; i++){
            d[i] = (d[i - 1] + d[i - 2]) % 1234567;
        }

        return d[n];
    }
}
