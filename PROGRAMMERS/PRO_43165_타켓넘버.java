class Solution {
    public static int answer;
    public int solution(int[] numbers, int target) {
        make(0, 0, numbers, target);
        return answer;
    }
    
    public static void make(int idx, int result, int numbers[], int target){
        if(idx == numbers.length){
            if(result == target){
                answer++;
            }
            return;
        }
    
        make(idx + 1, result + numbers[idx], numbers, target);
        make(idx + 1, result - numbers[idx], numbers, target);
    }
}
