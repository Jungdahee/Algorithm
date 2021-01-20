public class LEET_739_Daily_Temperatures {

    public static int[] dailyTemperatures(int[] T) {
        int answer[] = new int[T.length];

        for(int i = 0; i < T.length; i++){
            for(int j = i + 1; j < T.length; j++){
                if(T[i] < T[j]) {
                    answer[i] = j - i;
                    break;
                }
                if(j == T.length - 1) answer[i] = 0;
            }
        }

        return answer;
    }

    public static void main(String arsg[]){
        int T[] = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(T)));
    }
}
