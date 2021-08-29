import java.util.LinkedList;

/*
    풀이 방법:
            - 먼저 3가지 연산자로 만들 수 있는 6가지 케이스 선언
            - String으로 들어오는 인자를 피연산자와 연산자로 구분해서 리스트 생성
            - 6가지 케이스를 하나씩 적용하며 max(answer) 값 갱신
            - LinkedList를 이용하여 계산된 피연산자와 연산자는 제거하며 결과값 다시 추가
 */

public class programmers_수식_최대화 {

    public static String priority[][] = {{"*", "+", "-"},
                                        {"*", "-", "+"},
                                        {"+", "*", "-"},
                                        {"+", "-", "*"},
                                        {"-", "*", "+"},
                                        {"-", "+", "*"}};
    public static LinkedList<String> list;

    public static long solution(String expression) {
        long answer = Long.MIN_VALUE;

        makeList(expression);

        for(int i = 0; i < priority.length; i++){
            answer = Math.max(answer, calculate(i));
        }

        return answer;
    }

    public static void makeList(String expression){
        list = new LinkedList<>();

        int idx = 0;
        for(int i = 0; i < expression.length(); i++){
            if(!Character.isDigit(expression.charAt(i))){
                list.add(expression.substring(idx, i));
                list.add(expression.substring(i, i + 1));
                idx = i + 1;
            }

            if(i == expression.length() - 1){
                list.add(expression.substring(idx, i + 1));
            }
        }
    }

    public static long calculate(int idx){
        LinkedList<String> cpList = (LinkedList) list.clone();

        for(int i = 0; i < priority[idx].length; i++){
            for(int j = 0; j < cpList.size(); j++){
                if(cpList.get(j).equals(priority[idx][i])){
                    long num1 = Long.parseLong(cpList.get(j - 1));
                    long num2 = Long.parseLong(cpList.get(j + 1));

                    long num = 0;
                    if(cpList.get(j).equals("*")) num = num1 * num2;
                    else if(cpList.get(j).equals("+")) num = num1 + num2;
                    else num = num1 - num2;

                    cpList.remove(j + 1);
                    cpList.remove(j);
                    cpList.remove(j - 1);

                    cpList.add(j - 1, Long.toString(num));
                    j = j - 1;
                }
            }
        }

        return Math.abs(Long.parseLong(cpList.peek()));
    }

    public static void main(String args[]){
        System.out.println(solution("100-200*300-500+20"));
    }
}
