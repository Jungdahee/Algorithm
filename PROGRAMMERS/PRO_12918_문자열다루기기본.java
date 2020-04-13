public class PRO_12918_문자열다루기기본 {
	
	public boolean solution(String s) {
		char chars[] = s.toCharArray();
		if(chars.length != 4 && chars.length != 6) return false;
		for(char c : chars) if(Character.isAlphabetic(c)) return false;
		return true;
	}
}
