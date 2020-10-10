class PRO_49993_스킬트리 {
    
    public static boolean isSuccess, isSelected[];
    
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(int i = 0; i < skill_trees.length; i++){
            String tmp = skill_trees[i];
            isSelected = new boolean[skill.length()];
            isSuccess = true;

            for(int j = 0; j < tmp.length(); j++){
                // 일치되는 위치 값 추출
                int idx = skill.indexOf(tmp.charAt(j));

                // 있는 경우
                if(idx >= 0){
                    for(int k = 0; k < idx; k++){
                        if(!isSelected[k]) {
                            isSuccess = false;
                            break;
                        }
                    }

                    if(!isSuccess) break;
                    else isSelected[idx] = true;
                }

                if(j == tmp.length() - 1) isSuccess = true;
            }

            if(isSuccess) answer++;
        }

        return answer;
    }
}
