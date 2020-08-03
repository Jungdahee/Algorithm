class Solution
{
    /*
        짝수와 홀수 두 가지 경우를 다 고려해야 하기 때문에 홀수와 짝수의 경우를 나눠서 생각해야 함.
        원소 하나를 잡고 두개의 인덱스로 팰린드롬을 검사하는 방식으로 접근했고 j가 팰린드롬 검사가 시작되는 중앙지점이며
        i는 왼쪽 방향으로, k는 오른쪽 방향으로 이동하면서 일치하는 문자 수를 카운팅하고 최대값을 갱신하는 방식으로 해결했음.
     */
    
    public static int lastIdx;
    public static String s;
    public static boolean isOdd;
    
    public int solution(String str)
    {
        s = str;
        lastIdx = s.length();
        int answer = Integer.MIN_VALUE;

        int i = 0;
        int k = 0;
        
        for(int j = 0; j < s.length(); j++){
            i = j;
            k = j;

            // 홀수의 경우
            int max = isPalindrome(i, k);
            if(max > answer) {
                answer = max;
                isOdd = true;
            }

            // 짝수의 경우
            k = j + 1;
            max = isPalindrome(i, k);
            if(max >= answer) {
                answer = max;
                isOdd = false;
            }
        }

        if(isOdd) return (answer - 1) * 2 + 1;
        else return answer * 2;
    }

    private static int isPalindrome(int i, int k){
        int max = 0;

        while(true){
            if(checkRange(i, k)) break;

            if(s.charAt(i) == s.charAt(k)){
                max++;
                i--;
                k++;
            }
            else break;
        }
        return max;
    }

    private static boolean checkRange(int i, int k){
        return i < 0 || k >= lastIdx;
    }
}
