public class LEET_22_Generate_Parentheses {
    public static List<String> result;
    public static Stack<Character> st;
    public static int end;

    public static List<String> generateParenthesis(int n) {
        st = new Stack<Character>();
        result = new LinkedList<String>();
        end = n * 2;
        make("(", 1);

        return result;
    }

    public static void make(String str, int size){
        if(size == end){
            char arr[] = str.toCharArray();

            st.clear();
            for(int i = 0; i < arr.length; i++){
                char ch = arr[i];

                if(!st.empty()){
                    char open = st.peek();

                    if(open == '(' && ch == ')') {
                        st.pop();
                    } else {
                        st.push(ch);
                    }
                } else st.push(ch);
            }

            if(st.size() == 0) result.add(str);
            return;
        }

        make(str + "(", size + 1);
        make(str + ")", size + 1);
    }

    public static void main(String args[]){
        List<String> result = generateParenthesis(3);

        for(String str : result){
            System.out.println(str);
        }
    }
}
