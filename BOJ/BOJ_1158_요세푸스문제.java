package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1158_요세푸스문제 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input[] = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        Queue<Integer> q = new LinkedList<Integer>();

        for(int i = 1; i <= n; i++) q.offer(i);

        sb.append("<");

        int cnt = 1;
        while(!q.isEmpty()){
            int num = q.poll();

            if(cnt == k) {
                sb.append(num).append(", ");
                cnt = 1;
            } else {
                q.offer(num);
                cnt++;
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(">");

        System.out.println(sb.toString());
    }
}
