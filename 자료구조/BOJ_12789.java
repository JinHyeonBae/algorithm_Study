package 자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12789 {


    static int N;
    static Queue<Integer> q;
    static Stack<Integer> st;


    public static boolean solution(){

        int nxt = 1;

        while(!q.isEmpty()){

            int first = q.peek();
            //System.out.println("찾아야하는 수 : " + nxt);
            //System.out.println("현재 큐의 first : " + first);

            if(first != nxt) {

                if (!st.isEmpty()) {
                    int top = st.peek();

                    //System.out.println("현재 스택의 top : " + top);
                    if (top == nxt) {
                        st.pop();
                        nxt++;
                    }
                    else{
                        st.push(first);
                        q.poll();
                    }
                }
                else{
                    st.push(first);
                    q.poll();
                }
            }
            else{
                q.poll();
                nxt++;
            }
        }

        while(!st.isEmpty()){
            int top = st.pop();
            System.out.println("찾아야하는 수 : " + nxt);
            System.out.println("현재 스택의 top : " + top);
            if(top != nxt) {
                return false;
            }
            nxt++;
        }

        return true;
    }


    public static void init(){
        q = new LinkedList<>();
        st = new Stack<>();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            q.offer(Integer.parseInt(stk.nextToken()));
        }

        boolean result = solution();
        if(result) System.out.println("Nice");
        else System.out.println("Sad");

    }
}
