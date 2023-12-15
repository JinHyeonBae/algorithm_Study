package SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_암호문3 {

    static class Node{
        int id;
        Node nxt;

        public Node(int id, Node nxt){
            this.id = id;
            this.nxt = nxt;
        }

        public Node(int id){
            this.id = id;
        }
    }

    static LinkedList<Node> node;
    static Node tail;


    public static void init(){
        node = new LinkedList<>();
    }

    public static void clear(){
    }

    public static void insert(Node preNode, int value, Node nxt){
        preNode.nxt = new Node(value, nxt);
    }

    public static void remove(Node preNode, Node nxtNode){
        preNode.nxt = nxtNode;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int tc = 1; tc <= 10; tc++){
            init();

            int t = Integer.parseInt(br.readLine());
            StringTokenizer stk = new StringTokenizer(br.readLine());

            for(int i = 1; i <= t; i++){
                if(node.isEmpty()) {
                    node.addFirst(new Node(0)); // 0ㅂㅓㄴ째 인덱스 삭제
                    node.add(new Node(Integer.parseInt(stk.nextToken())));
                    continue;
                }

                Node newNode =  new Node(Integer.parseInt(stk.nextToken()));
                node.add(newNode);

                if(i == t)
                    tail = newNode;
            }

            int cmdCnt = 0;
            cmdCnt = Integer.parseInt(br.readLine());

            String str = br.readLine();
            stk = new StringTokenizer(str, " ");

            for(int i = 0; i < cmdCnt; i++) { // 500
                String cmd = stk.nextToken();

                if (cmd.equals("I")) { //

                    int idx = Integer.parseInt(stk.nextToken());
                    int cnt = Integer.parseInt(stk.nextToken());

                    for (int j = 1; j <= cnt; j++) {
                        int value = Integer.parseInt(stk.nextToken());

                        node.add(idx + j, new Node(value));
                        //System.out.print(" " + value + " ");
                    }
                }
                if (cmd.equals("A")) { // 2가지

                    int cnt = Integer.parseInt(stk.nextToken());

                    for (int j = 0; j < cnt; j++) {
                        int value = Integer.parseInt(stk.nextToken());
                        Node newNode = new Node(value, null);

                        node.addLast(newNode);
                    }
                }
                if (cmd.equals("D")) { // 2가지
                    int idx = Integer.parseInt(stk.nextToken());
                    int count = Integer.parseInt(stk.nextToken());

                    //System.out.print(idx + " " + count);
                    for (int j = 0; j < count; j++) {
                        node.remove(idx + 1);
                    }
                }

            }

            System.out.print("#" + tc + " ");

            for(int j = 1; j <= 10; j++){
                System.out.print(node.get(j).id + " ");
            }
            System.out.println();
        }
    }

}