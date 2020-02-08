import java.util.*;

public class SWEA_2063_중간값찾기 { 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int num[] = new int[n];
		
		for(int i = 0; i < n; i++) num[i] = sc.nextInt();
		
		Arrays.sort(num);
		System.out.println(num[(n - 1) / 2]);
	}
}
