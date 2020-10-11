import java.util.*;

class PRO_64065_튜플 {
    public static LinkedList<String[]> list;
    public static LinkedList<String> answer;
    
    public int[] solution(String s) {
        String tmpStr1[] = s.split("\\{");

        list = new LinkedList<String[]>();
        for(int i = 0; i < tmpStr1.length; i++){
            if(tmpStr1[i].length() != 0) {
                String tmp = tmpStr1[i].substring(0, tmpStr1[i].length() - 2);
                String arr[] = tmp.split(",");
                Arrays.sort(arr);
                list.add(arr);
            }
        }

        answer = new LinkedList<>();

        Collections.sort(list, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1.length - o2.length;
            }
        });

        answer.add(list.get(0)[0]);
        boolean isFound = false;
        for(int i = 1; i < list.size(); i++){
            String tmp1[] = list.get(i - 1);
            String tmp2[] = list.get(i);

            if(tmp1.length == tmp2.length) continue;

            isFound = false;
            for(int j = 0; j < tmp1.length; j++){
                if(!tmp1[j].equals(tmp2[j])) {
                    answer.add(tmp2[j]);
                    isFound = true;
                    break;
                }
            }

            if(!isFound) {
                answer.add(tmp2[tmp2.length - 1]);
            }
        }

        int result[] = new int[answer.size()];

        for(int i = 0; i < answer.size(); i++) result[i] = Integer.parseInt(answer.get(i));

        return result;
    }
}
