public class PRO_70129_이진변환반복하기 {

    public static int[] solution(String s) {
        int answer[] = new int[2];

        int cnt = 0;
        int zero = 0;
        while(true){
            if(s.length() == 1) break;

            // 0 제거
            String tmp = s.replace("0", "");
            zero += s.length() - tmp.length();

            // 진법 변환
           s = Integer.toBinaryString(tmp.length());

           cnt++;
        }

        answer[0] = cnt;
        answer[1] = zero;
        return answer;
    }

    public static void main(String args[]){
        System.out.println(Arrays.toString(solution("110010101001")));
    }
}
