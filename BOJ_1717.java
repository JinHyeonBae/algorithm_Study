import java.io.*;
import java.net.Inet4Address;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1717 {

    static int N, M;
    static int[] p;
    static int[] r;

    public static int find(int a){ // 갱신이 될텐데..?
        if(a == p[a]) return p[a];
        return p[a] = find(p[a]);
    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(r[a] < r[b]){
            r[b] += r[a];
            p[a] = b;
        }
        else{
            r[a] += r[b];
            p[b] = a;
        }
    }

    public static void init(){
        p = new int[N + 1];
        r = new int[N + 1];

        for(int i = 1; i <= N; i++){
            p[i] = i;
        }

        Arrays.fill(r, 1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        init();

        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());

            if(a == 0){
                union(b, c);
            }
            else {
                if (p[b] == p[c]) {
                    bw.write("YES");
                } else {
                    bw.write("NO");
                }
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();

    }
}
