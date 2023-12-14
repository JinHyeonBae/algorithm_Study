import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1080 {

    static int N, M, answer;
    static int[][] map;
    static int[][] dest;
    static int [][] copy;

    /*
    001
100
100
000
011
010
100
100
010
010
010
110
101
101
000
110
000
110
     */

    /*
    000
110
001
100
011
000
100
010
011
100
101
101
010
001
010
010
111
110
111
001
     */

    public static boolean isProper(){

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != dest[i][j])
                    return false;
            }
        }
        return true;
    }

    public static void print(){

        System.out.println("=============================");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============================");
    }

    public static void solution(){
        int cnt = 0;
        boolean flag = false;

        if(isProper()) {
            answer = cnt;
            return;
        }

        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++){
                if(r + 2 < N && c + 2 < M && map[r][c] != dest[r][c]) {
                    for (int row = r; row < r + 3; row++) {
                        for (int col = c; col < c + 3; col++) {
                            if (map[row][col] == 1) map[row][col] = 0;
                            else map[row][col] = 1;
                        }
                    }

                    cnt++;
                    if(isProper()) {
                        //System.out.println("CNT : " + cnt);
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            }
            if(flag) break;
        }

        answer = flag == false ? -1 : cnt;
    }


    public static void copy(int[][] src, int[][] dst){
        for(int i = 0; i < N; i++){
            dst[i] = src[i].clone();
        }
    }

    public static void init(){
        map = new int[N][M];
        dest = new int[N][M];
        copy = new int[N][M];
        answer = Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        init();

        for(int i = 0; i < N; i++){
            String st = br.readLine();
            for(int j = 0; j < M; j++) {
                int value = st.charAt(j) - '0';
                map[i][j] = value;
            }
        }

        for(int i = 0; i < N; i++){
            String st = br.readLine();
            for(int j = 0; j < M; j++) {
                int value = st.charAt(j) - '0';
                dest[i][j] = value;
            }
        }

        solution();

        if(answer == Integer.MAX_VALUE)
            answer = -1;

        bw.write(answer + "");
        bw.flush();

        bw.close();
        br.close();
    }
}
