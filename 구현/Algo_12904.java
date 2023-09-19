package 구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Algo_12904 {

    static String S;
    static String T;

    public static int solution(String toS){ // 한 번 들어올 때마다 O(n) 작업을 반복 * 길이 n이므로 O(n^2)

        if(toS.length() < S.length())
            return 0;

        if(toS.equals(S)) // O(n)
            return 1;

        int length = toS.length();
        //System.out.println(length);
        if(toS.charAt(length - 1) == 'A'){
            return solution(toS.substring(0, length - 1)); // O(n)
        }
        else{
            StringBuffer newToS = new StringBuffer(toS.substring(0, length - 1));
            newToS = newToS.reverse(); // O(n)이다.
            return solution(newToS.toString());
        }

    }


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        int result = solution(T);
        System.out.println(result);

    }
}
