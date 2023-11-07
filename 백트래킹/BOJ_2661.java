package 백트래킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2661 {

    static StringBuilder sb;

    static int N;
    static boolean flag;

    // goodSequence임을 판별하는 과정
    public static boolean isGoodSequence() {

        int l = sb.length();
        for(int i = 1; i <= l; i++) {
            int cnt = 0;
            for(int j = 1; j + i <= l; j++) {
                if(sb.charAt(j-1) != sb.charAt(j + i - 1)) {
                    cnt = 0;
                    continue;
                }
                cnt++;
                if(cnt == i) {
                    return false;
                }
            }

        }

        return true;
    }

    public static void dfs(int cnt) {

        if(2 <= cnt && !isGoodSequence())
            return;

        if(cnt == N) {
            System.out.println(sb.toString());
            flag = true;
            return;
        }

        //System.out.println(cnt);
        for(int i = 1; i <= 3; i++) {
            sb.append(i);
            if(!isGoodSequence()) {
                sb.deleteCharAt(sb.length()-1);
                continue;
            }
            dfs(cnt + 1);
            sb.deleteCharAt(sb.length() - 1);
            if(flag)
                return;
        }

    }

    public static void init() {
        sb = new StringBuilder();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        init();

        dfs(0);

    }
}
