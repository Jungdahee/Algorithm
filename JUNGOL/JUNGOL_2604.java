 import java.io.*;
public class JUNGOL_2604 { //그릇
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split("");
        int result = 10;
         
        for(int i = 1; i < input.length; i++) {
            if(input[i -1].equals(input[i])) result += 5;
            else result += 10;
        }
        System.out.println(result);
    }
}
