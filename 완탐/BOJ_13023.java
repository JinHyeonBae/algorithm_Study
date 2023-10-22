package 완탐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023 {

    static int N, M;
    static List<List<Integer>> list;
    static boolean[] visited;
    static boolean found;

    public static void solution(int start, int cnt){

        if(visited[start]) {
            return;
        }

        if(cnt == 4){ // A -> B -> C -> D -> E의 4번을 거치면 곧 있다는 의미이므로 성공 출력
            found = true;
            return;
        }

        visited[start] = true;

        for(int i = 0; i < list.get(start).size(); i++) {
            int nxt = list.get(start).get(i);
            if (!visited[nxt]) {
                solution(nxt, cnt + 1);
                visited[nxt] = false;
                //System.out.println(nxt + "의 방문 상태가 false로 변화합니다.");
                if (found)
                    return;
            }
        }
    }

    public static void init(){
        list = new ArrayList<>();
        visited = new boolean[N + 1];

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
        }
    }

    public static void clear(){
        Arrays.fill(visited, false);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        init();

        for(int i = 0; i < M; i++){
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());

            //System.out.println("들어오세여 : " + start + "," + end);

            list.get(start).add(end);
            list.get(end).add(start);
        }

        for(int i = 0; i < N; i++) {
            //clear();
            //System.out.println("스타팅 포인트 : " + i);
            solution(i, 0);
            visited[i] = false;
            if(found)
                break;
        }

        if(found)
            System.out.println("1");
        else
            System.out.println("0");

    }
}
