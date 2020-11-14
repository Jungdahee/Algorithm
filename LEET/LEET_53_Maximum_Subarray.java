package Catch_PS;

public class LEET_53_Maximum_Subarray {

    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int sum = 0;

        for(int i = 0; i < nums.length; i++){
            sum = Math.max(nums[i], sum + nums[i]);
            result = Math.max(result, sum);
        }
        
        return result;
    }
}
