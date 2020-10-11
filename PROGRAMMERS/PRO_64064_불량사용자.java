import java.util.*;

class PRO_64064_불량사용자 {
    public static String user[], banned[], list[];
    public static boolean isSuccess, isSelected[];
    public static LinkedList<String[]> answer;
    
    public int solution(String[] user_id, String[] banned_id) {
        user = user_id;
        banned = banned_id;

        list = new String[banned.length];
        isSelected = new boolean[user.length];
        answer = new LinkedList<>();

        permutation(0);
        
        return answer.size();
    }

    public static void permutation(int idx){
        if(idx == banned.length){
            for(int i = 0; i < banned.length; i++){
                if(banned[i].length() != list[i].length()) return;

                for(int j = 0; j < banned[i].length(); j++){
                    if(banned[i].charAt(j) == '*') continue;
                    if(banned[i].charAt(j) != list[i].charAt(j)) return;
                }
            }

            isSuccess = true;
            String copyList[] = list.clone();
            Arrays.sort(copyList);

            for(String strArr[] : answer){
                int cnt = 0;

                for(int i = 0; i < strArr.length; i++){
                    if(strArr[i].equals(copyList[i])) cnt++;
                }

                if(cnt == copyList.length) {
                    isSuccess = false;
                    break;
                }
            }

            if(isSuccess) answer.add(copyList);
            return;
        }

        for(int i = 0; i < user.length; i++){
            if(isSelected[i]) continue;
            isSelected[i] = true;
            list[idx] = user[i];
            permutation(idx + 1);
            isSelected[i] = false;
        }
    }
}
