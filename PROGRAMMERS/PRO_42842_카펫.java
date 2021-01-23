public class PRO_42842_카펫 {
    public static int[] solution(int brown, int yellow) {
        int answer[] = new int[2];

        // 세로의 길이는 최소 3부터 시작(가운 데에 하나의 yellow만 있어도 위 아래를 감싸야해서)
        // brown의 세로의 최대 길잉는 brown을 2로 나눴을 때로 가정, 세로의 길이가 가로의 길이보다 클 수 없으므로)
        for(int i = 3; i <= brown / 2; i++){ 
            int ver = (brown - (i * 2)) / 2; // 양 끝의 세로 블록 수를 빼고 남은 brown의 수의 / 2는 한 줄의 세로 블록 수
            int area = (i - 2) * ver; // 안의 yellow 공간은 세로 길이를 나타내는 i에서 2줄을 뺀 후 위에서 구한 세로 블록 수의 곱

            if(area == yellow) {
                answer[0] = ver + 2;
                answer[1] = i;
                break;
             }
        }
        return answer;
    }

    public static void main(String args[]){
        System.out.println(Arrays.toString(solution(10, 2)));
    }
}
