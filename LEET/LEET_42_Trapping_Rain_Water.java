public class LEET_42_Trapping_Rain_Water {
    public static int trap(int[] height) {
        int answer = 0;
        int max = 0;
        int idx = 0;
        for(int i = 0; i < height.length; i++){
            if(max < height[i]){
                max = height[i];
                idx = i;
            }
        }

        int secondHigh = height[0];
        for(int i = 0; i < idx; i++){
            if(height[i] < secondHigh) answer += secondHigh - height[i];
            else secondHigh = Math.max(secondHigh, height[i]);
        }

        secondHigh = height[height.length - 1];
        for(int i = height.length - 1; i > idx; i--){
            if(height[i] < secondHigh) answer += secondHigh - height[i];
            else secondHigh = Math.max(secondHigh, height[i]);
        }

        return answer;
    }

    public static void main(String ars[]){
        int height[] = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(height));
    }
}
