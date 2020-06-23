package PRO;

class PRO_60057_문자열압축 {
    public static int idx;
	public static String tmp;  
	public static String result;
	public static int min;
    
    public int solution(String s) {
        min = Integer.MAX_VALUE;
		
        // 1단위부터 시작 ~ 문자열 길이 전체까지 단위로 고려
		for(int i = 1; i <= s.length(); i++) {
			// 단위 길이대로 잘라서 tmp에 저장
			tmp = s.substring(0, i);
			result = ""; // 결과값
			int cnt = 0; // 연달아 중복되는 단어를 카운팅하기 위핸 변수
			for(int j = 0; j <= s.length(); j += i) {
				// 비교하는 단위의 글자와 탐색하는 글자가 일치할 때 카운팅 증가
				if(j + i <= s.length() && s.substring(j, j + i).equals(tmp)) cnt++; 
				// 비교하는 단위의 글자와 탐색하는 글자가 일치하지 않을 때
				else { 
					// 연달아 중복된 단어의 카운팅이 1이라면(연이은 중복 단어가 없다면) 그대로 결과값에 append
					if(cnt == 1) result += tmp;
					else {
						// 그게 아니라면 중복 카운트 + 비교 문자를 result에 append
						result += String.valueOf(cnt) + tmp;
						// 카운팅 수 리셋
						cnt = 1;
					}
					// 다음 비교할 문자 tmp에 새로이 셋팅
					if(j + i <= s.length()) tmp = s.substring(j, j + i);
					// 나중에 남는 글자
					idx = j;
				}
			}
			
			// 남는 문자열에 대해 append
			result += s.substring(idx, s.length());
			// 최소값 갱신
			if(result.length() > 0 && result.length() < min) min = result.length();
		}
        
        return min;
    }
}
