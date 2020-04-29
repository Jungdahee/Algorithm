import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// 실행 시간 117ms
public class SWEA_5658_보물상자비밀번호 {

	public static int N, K;
	public static LinkedList<Character> origin; // 처음 들어온 값 저장할 origin 리스트
	public static LinkedList<String> filtered; // 중복 제거 후 결과값을 담을 리스트
	public static Set<String> list; // 중복 제거를 위한 Set 자료구조
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {	
			String input[] = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			K = Integer.parseInt(input[1]);
			
			origin = new LinkedList<Character>();
			list = new HashSet<String>();
			
			char input2[] = br.readLine().toCharArray(); // 입력 값 char 배열로 변환
			for(int i  = 0; i < input2.length; i++) origin.add(input2[i]); 
			
			// N / 4번 이상 돌려도 기존과 같은 조합의 비밀번호가 생성되므로 N / 4번만 루프 수행해도 무방
			int loop = N / 4;
			while(loop-- > 0) {
				rotate(); // 한자리씩 이동
				addPassword(); // 이동 후에 비밀번호 생성
			}
			
			// 내림차순으로 정렬해야 K번째에 해당하는 원소를 찾을 수 있음.
			Collections.sort(filtered, new Comparator<String>() {

				@Override
				public int compare(String arg0, String arg1) {
					// 각 원소를 16진수로 변경한 후에 내림차순으로 정렬 수행
					return Integer.parseInt(arg1, 16) - Integer.parseInt(arg0, 16);
				}
			});
			
			// 결과 출력
			System.out.println("#" + t + " " + Integer.parseInt(filtered.get(K - 1), 16));
		}
	}
	
	private static void rotate() {
		// origin 리스트의 마지막 원소를 추출
		char ch = origin.removeLast();
		// 추출한 원소를 origin 맨 앞에 추가하여 rotation 구현
		origin.addFirst(ch);
	}
	
	private static void addPassword() {
		int cnt = 0;
		String tmpStr = "";
		
		for(char tmp : origin) {
			tmpStr += tmp;
			cnt++;
			// N / 4개의 원소가 합쳐졌다면 만들어진 결과의 중복을 처리해줄 se인 list에 추가
			if(cnt % (N / 4) == 0) {
				list.add(tmpStr);
				tmpStr = "";
			}
		}
		// 정렬을 수행하기 위해 set 자료구조인 list를 LinkedList로 생성
		filtered = new LinkedList<>(list);
	}
}
