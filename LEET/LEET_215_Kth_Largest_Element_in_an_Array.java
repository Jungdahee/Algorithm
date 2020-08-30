public class LEET_215_Kth_Largest_Element_in_an_Array {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);

        for(int i = nums.length; i >= 0; i--){
            if(nums.length - k == i) return i;
        }
    }
}
