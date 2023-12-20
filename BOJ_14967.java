import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14967 {


    public static int solution(int n){

        if(n == 1)
            return -1;

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i = 0; i <= n / 5; i++){
            for(int j = 0; j <= n / 2; j++){
                int cur = 5 * i + 2 * j;
                if(n < cur)
                    break;

                dp[cur] = Math.min(i + j, dp[cur]);
            }
        }

        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int ans = solution(n);

        System.out.println(ans);

    }

}
