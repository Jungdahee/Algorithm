public class LEET_46_Permutations {
    public static boolean isSelected[];
    public static List<List<Integer>> result;
    public static int tmpNum[];

    public static List<List<Integer>> permute(int[] nums) {
        isSelected = new boolean[nums.length];
        tmpNum = new int[nums.length];
        result = new LinkedList<>();

        permutation(0, nums);

        return result;
    }

    public static void permutation(int cnt, int nums[]){
        if(cnt == nums.length){
            LinkedList<Integer> tmpList = new LinkedList<Integer>();
            for(int i = 0; i < tmpNum.length; i++){
                tmpList.add(tmpNum[i]);
            }
            result.add(tmpList);
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(isSelected[i]) continue;
            tmpNum[cnt] = nums[i];
            isSelected[i] = true;
            permutation(cnt + 1, nums);
            isSelected[i] = false;
        }
    }
}
