import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class swea_2112 {

    static boolean[] visited;
    static int[][] cells;
    static int[][] origin;

    // 세로, 가로, 조건
    static int D, W, K;

    public static void print(int[][] m){

        System.out.println("==========================");
        for(int i = 1; i <= D; i++){
            for(int j = 1; j <=W; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("==========================");
    }
    public static boolean check(int col){ // -----> O(D)

        int cnt = 0;
        for(int r = 2; r <= D; r++){
            if(cells[r-1][col] == cells[r][col]) {
                cnt++;

                if (cnt >= K - 1)
                    return true;
            }
            else{
                cnt = 0;
            }
//            // W - r
//            if(W - r < K - 1) -> 이거 사용했더니 더 안 맞네..ㅠㅠ
//                break;
        }

        return false;
    }

    // 셀 정보를 갱신
    public static void update(int row, int state){ // ---> O(W)
        for(int i = 1; i <= W; i++){
            cells[row][i] = state;
        }
    }

    public static boolean isPassed(){ //---> O(W)
        for (int c = 1; c <= W; c++) {
            if (!check(c)){
                return false;
            }
        }
        return true;
    }

    public static void recover(int row){
        for(int i = 1; i <= D; i++){ // O(D)
            cells[i] = origin[i].clone();
        }
    }

    static boolean isReached = false;
    public static void solution(int cnt, int target, int[] tc){ // 이미 순열 때문에 13! 터질 수 밖에 없다..

        //if(cnt > K)
        //return;

        if(cnt == target){
            int idx = 0;
            for(int r = 1; r <= D; r++) {
                if (visited[r]) { // row 기반으로
                    //idx++;
                    update(r, tc[r]);
                }
            }

            if(isPassed()){
                isReached = true;
                return;
            }

            for(int r = 1; r <= D; r++) {
                if (visited[r]) { // row 기반으로
                    recover(r);
                }
            }
            return;
        }

        for(int i = 1; i <= D; i++){
            if(!visited[i]){
                visited[i] = true;
                tc[i] = 0;
                solution(cnt + 1, target, tc);

                if(isReached)
                    return;

                tc[i] = 1;
                solution(cnt + 1, target, tc);

                if(isReached)
                    return;

                visited[i] = false;
            }

        }

        return;
    }

    public static void init(){
        cells = new int[D + 1][W + 1];
        origin = new int[D + 1][W + 1];
        visited = new boolean[D + 1];
        isReached = false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            D = Integer.parseInt(stk.nextToken());
            W = Integer.parseInt(stk.nextToken());
            K = Integer.parseInt(stk.nextToken());

            init();

            for(int r = 1; r <= D; r++) {
                stk = new StringTokenizer(br.readLine());
                for (int c = 1; c <= W; c++) {
                    int value = Integer.parseInt(stk.nextToken());
                    cells[r][c] = value;
                    origin[r][c] = value;
                }
            }
            //print(cells);

            if(isPassed()){
                System.out.println("#" + tc + " 0");
                continue;
            }

            for(int ct = 1; ct <= D; ct++) {
                int[] tca = new int[D + 1];
                solution(0, ct, tca);

                if(isReached) {
                    System.out.println("#" + tc + " " + ct);
                    break;
                }
            }
            //isReached = false;
        }


    }
}
