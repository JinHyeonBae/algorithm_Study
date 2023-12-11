package SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_10726 {

    public static boolean solution(int num, int target){
        int cnt = 0;

        while(target > 0){
            if((target & 1) != 1) break;

            cnt++;
            target = target >> 1;
        }

        return cnt >= num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());


        for(int tc = 1; tc <= t; tc++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(stk.nextToken());
            int target = Integer.parseInt(stk.nextToken());
            boolean result = solution(num, target);

            System.out.print("#" + tc+ " ");
            if(result) System.out.println("ON");
            else System.out.println("OFF");
        }

    }
}
