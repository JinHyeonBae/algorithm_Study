import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13904 {

    static List<List<Integer>> nodes;
    static int N;
    static int M;

    public static int solution(){
        int answer = 0;

        for(int T = 0; T < M; T++) {
            int max = 0;
            int mxIdxl = 0;
            int mxIdxr = 0;

            for (int t = M - T; t <= M; t++) {

                for (int i = 0; i < nodes.get(t).size(); i++) {
                    int cur = nodes.get(t).get(i);
                    if (cur <= max) continue;

                    max = cur;
                    mxIdxl = t;
                    mxIdxr = i;
                }
            }
            if (nodes.get(mxIdxl).isEmpty()) continue;

            //System.out.println((M - T)+ "일 때의 값 :" + max);
            nodes.get(mxIdxl).remove(mxIdxr);
            answer += max;
        }


        return answer;
    }

    public static void init(){
        nodes = new ArrayList<>();

        for(int i = 0; i <= 1000; i++){
            nodes.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer("");
        N = Integer.parseInt(br.readLine());

        init();

        for(int i = 0; i < N; i++){
            int d, w;
            stk = new StringTokenizer(br.readLine());

            d = Integer.parseInt(stk.nextToken());
            w = Integer.parseInt(stk.nextToken());

            nodes.get(d).add(w);
            if(M < d)
                M = d;
        }

        int answer = solution();
        System.out.println(answer);

    }
}
