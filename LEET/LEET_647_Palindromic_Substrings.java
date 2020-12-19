public class LEET_647_Palindromic_Substrings {
    public static char chArr[];

    public static int countSubstrings(String s) {
        int result = s.length();
        chArr = s.toCharArray();

        for(int i = 1; i < s.length(); i++){
            for(int j = 0; j < s.length() - i; j++){
                if(isPalindrome(j, j + i)) {
                    result++;
                }
            }
        }

        return result;
    }

    public static boolean isPalindrome(int start, int end){
        while(start < end){
            if(chArr[start] != chArr[end]) return false;
            else {
                start++;
                end--;
            }
        }
        return true;
    }


    public static void main(String args[]){
        // System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaa"));
    }
}
