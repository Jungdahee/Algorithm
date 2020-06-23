package PRO;

import java.util.*;

class PRO_17677_뉴스클러스터링 {
    public int solution(String str1, String str2) {
		
		// 1. 두 개씩 단어 잘라 다중 집핳 생성
		LinkedList<String> listA = new LinkedList<String>();
		LinkedList<String> listB = new LinkedList<String>();
		
		String str = "";
		for(int i = 0; i < str1.length() - 1; i++) {
			str = str1.substring(i, i + 2); // 두 개씩 끊어서 원소가 될 수 있는지 확인
			if(checkStr(str)) continue;
			// 두개의 음절이 모두 알파벳인 경우에만 list에 추가
			listA.add(str.toLowerCase());
		}
	
		for(int i = 0; i < str2.length() - 1; i++) {
			str = str2.substring(i, i + 2); // 두 개씩 끊어서 원소가 될 수 있는지 확인
			if(checkStr(str)) continue;
			// 두개의 음절이 모두 알파벳인 경우에만 list에 추가
			listB.add(str.toLowerCase());
		}
		
		// 두 집합 모두 공집합인 경우
		if(listA.size() == 0 && listB.size() == 0) return 65536;
		
		// 2. 비교하면서 conflictList 생성
		Set<String> tmpList = new HashSet<String>();
		
		for(int i = 0; i < listA.size(); i++) {
			for(int j = 0; j < listB.size(); j++) {
				if(listA.get(i).equals(listB.get(j))) {
					tmpList.add(listA.get(i));
				}
			}
		}
		
		// 3.교집합 수, 합집합 수 계산
		Object conflictList[] = tmpList.toArray();
		double same = 0.0;
		int conflictA = 0;
		int conflictB = 0;
		
		for(int i = 0; i < conflictList.length; i++) {
			String conflictStr = (String) conflictList[i];
			conflictA = 0;
			conflictB = 0;
			
			// conflictList에 있는 원소에 대해 몇개씩 중복이 되는지 카운팅
			for(int j = 0; j < listA.size(); j++) {
				if(conflictStr.equals(listA.get(j))) conflictA++;
			}
			
			for(int j = 0; j < listB.size(); j++) {
				if(conflictStr.equals(listB.get(j))) conflictB++;
			}
			
			// 중복되는 숫자 중에 더 작은 쪽이 공집합에 더해져야 함
			if(conflictA < conflictB) same += conflictA;
			else same += conflictB;
		}

		return (int) Math.floor((same / (listA.size() + listB.size() - same)) * 65536);
	}
	
	private static boolean checkStr(String str) {
		char first = str.charAt(0);
		char second = str.charAt(1);
		
		// 둘 중 하나라도 알파벳이 아닌 경우 true 반환
		if(!Character.isAlphabetic(first) || !Character.isAlphabetic(second)) return true;
		return false; // 다 알파벳인 경우 false 반환
	}
}
