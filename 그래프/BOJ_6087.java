package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6087 {

    // 11%에서 틀리는 스타일
    static char[][] map;
    static int[][] visited;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static List<Integer> candidates;

    static int W, H;
    static int startR, startC, endR, endC;

    public static boolean isRanged(int r, int c) {
        return r >= 1 && c >= 1 && r <= H && c <= W;
    }

    public static void solution() {

        Queue<int[]> q = new LinkedList<>();

        /*
         * 좌표, direction, direction이 바뀐 횟수
         */
        q.offer(new int[] {startR, startC, 0, 0});
        q.offer(new int[] {startR, startC, 1, 0});
        q.offer(new int[] {startR, startC, 2, 0});
        q.offer(new int[] {startR, startC, 3, 0});

        visited[startR][startC] = 0;

        while(!q.isEmpty()) {

            int[] arr = q.poll();

            int cr = arr[0];
            int cc = arr[1];
            int dir = arr[2];
            int dirCost = arr[3];

            if(cr == endR && cc == endC) {
                candidates.add(visited[cr][cc]);
                continue;
            }

            for(int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if(!isRanged(nr, nc)) continue;
                if(visited[nr][nc] <= dirCost) continue;
                if(map[nr][nc] == '*') continue;

                int dct = dirCost;
                if(d == dir) {
                    visited[nr][nc] = dirCost;
                }
                else {
                    dct += 1;
                    visited[nr][nc] = dirCost + 1;
                }

                q.offer(new int[] {nr, nc, d, dct});
            }
        }

        Collections.sort(candidates); // nlogn
    }

    private static String getDir(int d) {
        // 북서남동
        switch (d){
            case 0 :
                return "북";
            case 1 :
                return "서";
            case 2 :
                return "남";
            case 3 :
                return "동";
            default :
                return "NO";
        }
    }

    public static void init() {
        visited = new int[H + 1][W + 1];
        map = new char[H + 1][W + 1];
        candidates = new ArrayList<>();

        for(int i = 1; i<= H; i++) {
            for(int j = 1; j <= W; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        W = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());

        init();
        for(int i = 1; i <= H; i++) {
            String str = br.readLine();
            for(int j = 1; j <= W; j++) {
                map[i][j] = str.charAt(j - 1);
                if(map[i][j] == 'C') {
                    if(startR == 0) {
                        startR = i;
                        startC = j;
                    }
                    else {
                        endR = i;
                        endC = j;
                    }
                }
            }
        }

        solution();
//
        Integer result = candidates.get(0);
        System.out.println(result);

        br.close();

    }

}
