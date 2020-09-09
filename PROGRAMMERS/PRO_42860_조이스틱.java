class PRO_42860_조이스틱 {
    public int solution(String name) {
        int result = 0;
        char arr[] = name.toCharArray();

        int minCnt = name.length() - 1;
        for(int i = 0; i < arr.length; i++){
            int cursor = i + 1;
            while(cursor < name.length() && name.charAt(cursor) == 'A'){
                cursor++;
            }

            minCnt = Math.min(minCnt, 2 * i + name.length() - cursor);
            result += getMinDist(arr[i]);
        }

        result += minCnt;
        return result;
    }

    public static int getMinDist(char ch){
        int chNum = (int) ch;
        return ch - 65 < 90 - ch + 1 ? ch - 65 : 90 - ch + 1;
    }
}
