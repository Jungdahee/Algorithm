package PRO;

class PRO_17681_비밀지도 {
    public static String map1[][], map2[][], answer[];
    public String[] solution(int n, int[] arr1, int[] arr2) {
        map1 = new String[n][n];
		map2 = new String[n][n];
		answer = new String[n];
		
		for(int i = 0; i < arr1.length; i++) {
			String binaryString = Integer.toBinaryString(arr1[i]);
			int size = n - binaryString.length();
			
			String front = "";
			while(size-- > 0) front += "0";
			binaryString = front + binaryString;
			
			String strArr[] = binaryString.split("");
			for(int j = 0; j < strArr.length; j++) {
				map1[i][j] = strArr[j];
			}
		}
		
		for(int i = 0; i < arr2.length; i++) {
			String binaryString = Integer.toBinaryString(arr2[i]);
			int size = n - binaryString.length();
			
			String front = "";
			while(size-- > 0) front += "0";
			binaryString = front + binaryString;
			
			String strArr[] = binaryString.split("");
			for(int j = 0; j < strArr.length; j++) {
				map2[i][j] = strArr[j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			String str = "";
			for(int j = 0; j < n; j++) {
				if(map1[i][j].equals("1") || map2[i][j].equals("1")) {
					str += "#";
				} else if(map1[i][j].equals("0") && map2[i][j].equals("0")) {
					str += " ";
				} 
			}
			
			answer[i] = str;
		}
        
        return answer;
    }
}
