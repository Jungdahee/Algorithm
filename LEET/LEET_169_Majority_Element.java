public class LEET_169_Majority_Element {

    public static int majorityElement(int[] nums) {
        int result = 0;
        int base = (int) Math.ceil(nums.length / 2.0);
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            if(!hm.isEmpty() && hm.keySet().contains(nums[i])){
                int cnt = hm.get(nums[i]);
                hm.put(nums[i], cnt + 1);
            }
            else {
                hm.put(nums[i], 1);
            }
        }

        Iterator<Integer> it = hm.keySet().iterator();
        while(it.hasNext()){
            int key = it.next();
            int num = hm.get(key);
            if(num >= base) result = key;
        }

        return result;
    }

    public static void main(String args[]){
        int nums[] = {6,5,5};
        System.out.println(majorityElement(nums));
    }
}
