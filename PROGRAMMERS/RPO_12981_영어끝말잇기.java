public class RPO_12981_영어끝말잇기 {

    public static int[] solution(int n, String[] words) {
        int answer[] = new int[2];
        LinkedList<String> list = new LinkedList<String>();

        for(int i = 0; i < words.length; i++){
            if(!list.isEmpty() && list.getLast().charAt(list.getLast().length() - 1) != words[i].charAt(0)
                    || !list.isEmpty() && list.contains(words[i])){
                answer[0] = (list.size() % n) + 1;
                answer[1] = (list.size() / n) + 1;
                break;
            }
            else {
                list.add(words[i]);
            }
        }

        return answer;
    }

    public static void main(String args[]){
        String words[] = {"abb", "baa", "ccc", "cda", "abb"};
        System.out.println(Arrays.toString(solution(2, words)));
    }
}
