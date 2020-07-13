package PRO;

public class PRO_67256_키패드누르기 {

	public static int L[] = {1, 4, 7};
	public static int R[] = {3, 6, 9};
	public static int pad[][] = {{3, 1}, {0, 0}, {0, 1}, {0, 2},
			{1, 0}, {1, 1}, {1, 2},
			{2, 0}, {2, 1}, {2, 2}};
	public static int lx, ly, rx, ry;

	public static void main(String args[]){
		int numbers[] = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
		String hand = "left";
		String answer = "";
		
		lx = 3;
		rx = 3;
		ry = 2;

		for(int i = 0; i < numbers.length; i++){
			int num = numbers[i];

			// L이나 R에 해당하는 숫자의 경우
			for(int j = 0; j < 3; j++){
				if(num == L[j]) {
					answer += "L";
					lx = pad[num][0];
					ly = pad[num][1];
					break;
				}
				if(num == R[j]) {
					answer += "R";
					rx = pad[num][0];
					ry = pad[num][1];
					break;
				}

				if(j == 2) {
					// L이나 R에 해당하는 숫자가 아닌 경우
					char res = calc(num);
					if(res == 'J') {
						if(hand.equals("left")) {
							answer += "L";
							lx = pad[num][0];
							ly = pad[num][1];
						}
						else {
							answer += "R";
							rx = pad[num][0];
							ry = pad[num][1];
						}
					}
					else answer += res == 'L' ? "L" : "R";
				}
			}
		}

		System.out.println(answer);
	}

	private static char calc(int num){
		int nx = pad[num][0];
		int ny = pad[num][1];

		int a = (int) Math.abs(nx - lx) + Math.abs(ny - ly); 
		int b = (int) Math.abs(nx - rx) + Math.abs(ny - ry); 
		
		System.out.println("a :: " + a + "   b :: " + b);

		if(a < b) {
			lx = nx;
			ly = ny;
			return 'L';
		} else if(a > b){
			rx = nx;
			ry = ny;
			return 'R';
		} else return 'J';
	}
}
