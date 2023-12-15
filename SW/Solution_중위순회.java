package SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_중위순회 {

    /*
        중위순회는 왼 -> 부 -> 오이다.
    */

    static class Node{
        int parent;
        int lc;
        int rc;
        char ch;

        public Node(int parent, int leftChild, int rightChild, char ch){
            this.parent = parent;
            this.lc = leftChild;
            this.rc = rightChild;
            this.ch = ch;
        }
    }

    static Node[] nodeList;
    static int N;
    static StringBuilder sb;

    public static void inorder(Node p){

        if(p.lc > 0) inorder(nodeList[p.lc]);
        sb.append(p.ch);
        if(p.rc > 0) inorder(nodeList[p.rc]);

    }

    public static void init(){
        nodeList = new Node[102];
        sb = new StringBuilder();

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init();

        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine());
            sb.setLength(0);
            for (int i = 1; i <= N; i++) {
                StringTokenizer stk = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(stk.nextToken());
                char ch = stk.nextToken().charAt(0);
                int left = -1;
                int right = -1;

                if (stk.hasMoreTokens()) {
                    left = Integer.parseInt(stk.nextToken());
                }

                if (stk.hasMoreTokens()) {
                    right = Integer.parseInt(stk.nextToken());
                }

                Node newNode = new Node(idx, left, right, ch);
                nodeList[i] = newNode;
            }

            Node root = nodeList[1];
            inorder(root);
            System.out.println("#" + t + " " + sb.toString());
        }

    }
}