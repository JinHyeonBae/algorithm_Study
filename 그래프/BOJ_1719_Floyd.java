package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
     중간점이 1개이다 -> 얘네는 대각선으로 [i][j] = j, [j][i] = i의 값을 가진다.
     중간점이 2개 이상이다 -> 얘네는 서로 다르다.
                                       

 */

public class BOJ_1719_Floyd {

    static int[][] distance;
    static int[][] dp;
    static int[][] middleCount;
    static int N, M;

    public static void print(int[][] arr){
        StringBuffer sb = new StringBuffer();
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++) {
                if (arr[i][j] == 0)
                    sb.append("- ");
                else {
                    if(arr[i][j] != j){
                        int k = arr[i][j];
                        while(true){
                            if(arr[i][k] != k){
                                k = arr[i][k];
                                continue;
                            }
                            break;
                        }
                        arr[i][j] = k;
                        //sb.append(k);
                    }
                    sb.append(arr[i][j] + " ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void solution(){

       /*

           현재 distance, dp에 저장된 값들은 중간값이 하나도 없는 값들.
           그렇기 때문에, 비어있는 값들이 중간노드가 최소 하나 있는 노드들


            출발점과 도착점 둘 다 최단 거리는 똑같아야 하는 거 아님/

        */

         // 출발지와 도착지가 서로 같은 경로를 쓴다.

        for(int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(i == j) {
                        distance[i][i] = 0;
                        continue;
                    }


                    if (dp[i][j] > dp[i][k] + dp[k][j]) {
                        //System.out.println("[" + i + "][" + j + "]" + "= [" + i + "]" + "[" + k + "]" + "+" + "[" + k + "]" + "[" + j + "]");
                        //System.out.println("거리 갱신 : " + dp[i][j] + "->" + (dp[i][k] + dp[k][j]));
                        dp[i][j] = dp[i][k] + dp[k][j];
                        distance[i][j] = k; //


                        //else if()
                    }

                }
            }

            //print(dp);
        }
    }

    public static void init(){
        distance = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1];
        middleCount = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j<= N ;j++){
                if(i == j)
                    distance[i][j] = 0;
                else
                    distance[i][j] = i;
            }
        }

        for(int i = 0; i <= N; i++){
            Arrays.fill(dp[i], (int)1e9);
            //Arrays.fill(middleCount[i], 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        init();

        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(stk.nextToken());
            int e = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());

            dp[s][e] = w;
            dp[e][s] = w;
            distance[s][e] = e;
            distance[e][s] = s;
        }

        // dp에서 잘 들어갔고, 이제 1에서 출발할 차례
        solution();
        //System.out.println("==================");
        print(distance);
    }
}
