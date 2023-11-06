package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Level3_성적평가 {

    /*
        원래 순서
        원래 순위
        점수
     */

    static int N;
    static int[] sums, rank;
    static PriorityQueue<Node> node;


    static class Node implements Comparable<Node>{

        int order;
        int score;

        public Node(int order, int score){
            this.order = order;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            return o.score - this.score;
        }
    }

    public static void print(){
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < N; j++){
            sb.append(rank[j]);
            if(j < N - 1)
                sb.append(" ");
        }
        System.out.println(sb.toString());
    }
    public static void solution(){

        int prevOrder = 0;
        int prevScore = 0;
        int cnt = 1;

        while(!node.isEmpty()){
            Node top = node.poll();

            int ord = top.order;
            int scr = top.score;

            if(prevScore == 0){
                rank[ord] = 1;
                prevScore = scr;
                prevOrder = ord;
                continue;
            }

            if(prevScore == scr){
                rank[ord] = rank[prevOrder];
                cnt++;
            }
            else{
                rank[ord] = rank[prevOrder] + cnt;
                cnt = 1;
            }

            prevScore = scr;
            prevOrder = ord;
        }
    }


    public static void init(){
        sums = new int[N];
        rank = new int[N];
        node = new PriorityQueue<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        for(int i = 0; i <= 3; i++){

            if(i == 3) {
                for(int j = 0; j < N; j++){
                    node.add(new Node(j, sums[j]));
                }

                solution();
                print();
                continue;
            };

            StringTokenizer stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int score = Integer.parseInt(stk.nextToken());
                node.add(new Node(j, score));
                sums[j] += score;
            }

            solution();
            print();
        }
        // 최종 등수 구해야함

    }
}