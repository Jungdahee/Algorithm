package JUNGOL;

import java.util.*;

public class JUNGOL_1175_주사위던지기2 {

	static int numbers[]; 
	static int n, m;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); 
		m = sc.nextInt();
		numbers = new int[n]; 

		choose(0, 0);
	}

	private static void choose(int idx, int sum) {
		if(idx == n && sum == m) {
			for(int i = 0; i < numbers.length; i++) System.out.print(numbers[i] + " ");
			System.out.println();
			return;
		}
	 	
		if(sum > m) return;
		if(idx == n) return;

		for(int k = 1; k <= 6; k++) {
			numbers[idx] = k;
			choose(idx + 1, sum + k); 
		}
	}
}

