public class LEET_3_Longest_Substring_Without_Repeating_Characters {
    public static int lengthOfLongestSubstring(String s) {
        LinkedList<Character> list = new LinkedList<Character>();
        int result = 0;

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(list.contains(ch)) list.subList(0, list.indexOf(ch) + 1).clear();
            list.add(ch);

            result = Math.max(result, list.size());
        }

        return result;
    }

    public static void main(String args[]){
        int result = lengthOfLongestSubstring("dvdf");
        System.out.println(result);
    }
}
