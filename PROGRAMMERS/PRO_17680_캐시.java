package PRO;

import java.util.*;

class PRO_17680_캐시 {
    public static class Info {
		String city;
		int time;
		
		public Info(String city, int time) {
			this.city = city;
			this.time = time;
		}
	}
    
    public int solution(int cacheSize, String[] cities) {
        int time = 0;
		int latest = 0;
		
		LinkedList<Info> cache = new LinkedList<Info>();
        
		// 도시 입력이 아예 없는 경우 0 리턴
        if(cities.length == 0) return time;
        // cacheSize가 0인 경우 무조건 * 5인 시간이 소요됨.
        if(cacheSize == 0) return cities.length * 5;

        // 위 경우가 아니라면 cities에 데이터가 있다는 의미이므로 첫번째 도시 캐시에 add
		cache.add(new Info(cities[0], latest));
		time += 5;
		
		for(int i = 1; i < cities.length; i++) {
			String city = cities[i];
			
			// 현재 처리하려는 도시 이름이 cache에 있는지 없는지 확인
			for(int j = 0; j < cache.size(); j++) {
				// 캐시에 있는 경우
				String cacheCity = cache.get(j).city;
				// 대소문자 구분이 없으므로 아래 문장으로 처리
				if(cacheCity.toUpperCase().equals(city.toUpperCase())) {
					time += 1; // 있는 경우이므로 hit
					cache.get(j).time = ++latest; // 가장 최근에 사용된 것으몰 latest 갱신
					break;
				}
				
				// 캐시에 없는 경우
				if(j == cache.size() - 1) {
					// cache가 아직 다 채워지지 않은 경우
					if(cache.size() != cacheSize) {
						// cache에 추가
						cache.add(new Info(cities[i], ++latest));
					} else {
						// 아닌 경우 사용한 순서를 담고 있는 time으로 오름차순 정렬
						Collections.sort(cache, new Comparator<Info>() {

							public int compare(Info o1, Info o2) {
								return o1.time - o2.time;
							}
						});
							
						// 정렬 후 가장 사용한 지 오래된 도시 이름 제거
						cache.removeFirst();
						// 그리고 현재 처리하려는 도시 삽입
						cache.add(new Info(cities[i], ++latest));
					}
					// 위 경우 둘다 캐시에 없는 경우이므로 miss
					time += 5;
					break;
				}
			}
		}
		return time;
    }
}
