import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_5397 {

    int L;

    //간과한건, append를.. 사이에 낑워넣는것

    public static void solution(String str){
        Stack<Character> origin = new Stack<>();
        Stack<Character> move = new Stack<>();
        int top = -1;

        for(char ch : str.toCharArray()){

            if(ch == '<'){
                if(origin.isEmpty()) continue;
                //top--;
                char topChar = origin.pop();
                move.push(topChar);
            }
            else if(ch == '>'){
                if(move.isEmpty()) continue;
                char cur = move.pop();
                origin.push(cur);
                //top++;
            }
            else if(ch == '-'){
                if(origin.isEmpty()) continue;
                origin.pop();
                top--;
            }
            else{
                top++;
                origin.push(ch);

            }
        }

        while(!move.isEmpty()){
            char cur = move.pop();
            origin.push(cur);
            top++;
        }

        StringBuilder sb = new StringBuilder();
        while(!origin.isEmpty()){
            sb.append(origin.pop());
        }
        System.out.println(sb.reverse().toString());
        sb.setLength(0);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            solution(str);
        }

    }
}
