public class LEET_394_Decode_String {

    public static String decodeString(String s) {
        String result = "";

        Stack<String> num = new Stack<String>();
        Stack<String> st = new Stack<String>();

        String n = "";
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))) {
                int endIdx = i;
                while (endIdx < s.length()) {
                    if (!Character.isDigit(s.charAt(endIdx))) break;
                    endIdx++;
                }

                n = s.substring(i, endIdx);
                i = endIdx - 1;
            }
            else if (s.charAt(i) == '[') {
                st.push(result);
                num.push(n);
                result = "";
                n = "";
            }
            else if(s.charAt(i) == ']'){
                String str = st.pop();
                String tmp2 = String.join("", Collections.nCopies(Integer.parseInt(num.pop()), result));
                result = str + tmp2;
            }
            else {
                result += s.charAt(i);
            }
        }

        return result;
    }

    public static void main(String args[]){
        System.out.println(decodeString("100[leetcode]"));
    }
}
