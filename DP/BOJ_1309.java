package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1309 {

    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        long ans = solution();

        System.out.println(ans);


    }

    private static long solution() {

        long[][] dp = new long[N + 1][3];

        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;

        for(int i = 2; i <= N; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901; // i 번째 줄에 사자가 위치하지 않을 때
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 9901;  // i 번째 줄의 왼쪽에 사자가 위치할 때
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % 9901; // i 번째 줄의 오른쪽에 사자가 위치할 때
        }

        return (dp[N][0] % 9901 + dp[N][1] % 9901 + dp[N][2] % 9901) % 9901;

    }
}
