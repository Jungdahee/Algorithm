public class PRO_12922_수박수박수박수박수박수 {

	public String solution(int n) {
		String s1 = "수박";
		String s2 = "수";
		
		StringBuilder sb = new StringBuilder();
		if(n % 2 == 0) for(int i = 0; i < n / 2; i++) sb.append(s1);
		else {
			for(int i = 0; i < n / 2; i++) sb.append(s1);
			sb.append(s2);
		}
		return sb.toString();
	}
}
