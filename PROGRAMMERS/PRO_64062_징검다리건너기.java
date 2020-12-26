public class PRO_64062_징검다리건너기 {

    public static int solution(int[] stones, int k) {
        int left = 0;
        int right = 200000000;

        while(left <= right){
            int mid = (left + right) / 2;
            // 건널 수 있는 경우 최소 값을 하나 증가
            if(available(stones, mid, k)) left = mid + 1;
            // 건널 수 없는 경우 최대 값을 하나 감소
            else right = mid - 1;
        }

        // 오른쪽에 최대 값을 의미하므로 right return
        return right;
    }

    public static boolean available(int stones[], int mid, int k){
        int cnt = 0;
        for(int i = 0; i < stones.length; i++){
            // 돌의 높이가 건너려는 인원 수보다 작은 경우 카운트
            if(stones[i] - mid < 0) cnt++;
            // 작지 않은 경우 cnt 0으로 초기화
            else cnt = 0;

            // 돌의 높이가 인원 수보다 작은 경우가 k번 연속으로 있다면 이는 건널 수 없는 경우이므로 false 리턴
            if(cnt >= k) return false;
        }

        return true;
    }

    public static void main(String args[]){
        int stones[] = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        System.out.println(solution(stones, 3));
    }
}
