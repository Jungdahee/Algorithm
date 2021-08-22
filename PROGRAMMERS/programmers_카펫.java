import java.util.Arrays;

public class programmers_카펫 {
        public static int[] solution(int brown, int yellow) {
            int answer[] = new int[2];

            for(int i = 3; i < brown / 2; i++){
                int leftBrown = brown - (i * 2); // i는 행의 길이 * 2 ==> brown의 두 개의 행 길이의 합
                int brownRow = leftBrown / 2; // 남은 brown 수 / 2 ==> brown의 두 개의 열 길이의 합
                int yellowCol = yellow % brownRow == 0 ?  yellow / brownRow : 0;

                if(yellowCol == i - 2) {
                    answer[0] = brownRow + 2;
                    answer[1] = i;
                    break;
                }
             }
            return answer;
        }

    public static void main(String args[]){
        System.out.println(Arrays.toString(solution(24, 24)));
    }
}
