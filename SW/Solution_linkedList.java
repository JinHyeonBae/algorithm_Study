package SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_linkedList {


    static List<Integer> list;
    static List<String> command;

    public static int solution(int index){

        for(String cmd : command){
            StringTokenizer stk = new StringTokenizer(cmd);

            String order = stk.nextToken();
            int idx = Integer.parseInt(stk.nextToken());
            int value = 0;

            if(stk.hasMoreTokens())
                value = Integer.parseInt(stk.nextToken());

            switch (order){
                case "I" :
                    list.add(idx, value);
                    break;
                case "D":
                    list.remove(idx);
                    break;
                case "C":
                    list.set(idx, value);
                    break;
                default:
                    break;
            }

        }
        if(list.size() <= index)
            return -1;
        return list.get(index);
    }


    public static void init(){
        list = new LinkedList<>();
        command = new ArrayList<>();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++){
            init();

            StringTokenizer stk = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(stk.nextToken()); // 수열의 길이
            int m = Integer.parseInt(stk.nextToken()); // 추가 횟수
            int l = Integer.parseInt(stk.nextToken()); // 출력 인덱스 번호

            stk = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                list.add(Integer.parseInt(stk.nextToken()));
            }

            for(int i = 0; i < m; i++) {
                command.add(br.readLine());
            }

            //list.stream().forEach(System.out::print);
            int result = solution(l);

            //list.stream().forEach(System.out::print);
            System.out.println("#" + tc + " " + result);
        }

    }
}
