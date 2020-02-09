import java.io.*;
public class BOJ_14501 { //퇴사
	
	static int time[], pay[];
	static int n, max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		time = new int[n];
		pay = new int[n];

		//#1. time과 pay 배열에 입력받기
		for(int i = 0; i < n; i++) {
			String input[] = br.readLine().split(" ");
			time[i] = Integer.parseInt(input[0]); 
			pay[i] = Integer.parseInt(input[1]);
		}
		calc(0, 0);
		System.out.println(max);
	}
	
	static void calc(int idx, int result) {
		//#2. 기저 사례
		if(idx >= n) { //기저 사례
			if(result > max) max = result;
			return;
		}
		//#3. 계산하려는 날짜에 상담을 진행할 경우
		if(idx + time[idx] <= n) calc(idx + time[idx], result + pay[idx]);
		//#3. 계산하려는 날자에 상담을 진행하지 않을 경우
		calc(idx + 1, result);
	}
}
