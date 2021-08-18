import java.util.Arrays;

/*
    풀이 시간: 10분
    풀이 방법:
            - 두 개의 String 배열을 모두 오름차순으로 정렬 
            - 정렬된 두 개의 배열을 비교하면서 다른 참가자 추출
            - 만약 비교하면서 발견되지 않으면 참여자의 가장 마지막 순서에 완주하지 못한 선수가 있는 것
 */

public class programmers_완주하지_못한_선수 {
    public static String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        for(int i = 0; i < completion.length; i++){
            if(!participant[i].equals(completion[i])){
                return participant[i];
            }
        }

        return participant[participant.length - 1];
    }

    public static void main(String args[]){
        String participant[] = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String completion[] = {"josipa", "filipa", "marina", "nikola"};

        System.out.println(solution(participant, completion));
    }
}
