package SW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution_파핑파핑지뢰찾기 {


    static char[][] sign;
    static boolean[][] check;

    static int[] dr = {1, 0, -1, 0, 1, -1, 1, -1};
    static int[] dc = {0, 1, 0, -1, 1, 1, -1, -1};

    static int N;

    public static void init(){
        sign = new char[N][N];
        check = new boolean[N][N];
    }

    // 클릭한 C의 개수는 클릭 수에 포함되겠지?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++){
            N = Integer.parseInt(br.readLine());

            init();

            for(int i = 0; i < N; i++){
                String str = br.readLine();
                for(int j = 0; j < N; j++){
                    sign[i][j] = str.charAt(j);
                }
            }

            signNumber();
            //print(sign); // 점수가 잘 들어갔는지를 확인
            int answer = solution();

            System.out.println("#" + tc + " " + answer);
        }
    }

    // 0인 요소들을 모두 파핑파핑 시킨다.
    private static void trace(int r, int c){

        for (int l = 0; l < 8; l++) {
            int nr = dr[l] + r;
            int nc = dc[l] + c;

            if (!isRanged(nr, nc)) continue;
            if(check[nr][nc]) continue;
            if (sign[nr][nc] == '*') continue;

            check[nr][nc] = true;

            if(sign[nr][nc] == '0')
                trace(nr, nc);

            check[nr][nc] = true;
        }
        return;
    }
    private static int solution() {

        int ans = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                // 0 주위에 또 다른 0이 있다면 그 0도 연쇄적으로 터져야한다.
                if(sign[i][j] == '0' && !check[i][j]){
                    check[i][j] = true;
                    for(int k = 0; k < 8; k++) {
                        int nr = dr[k] + i;
                        int nc = dc[k] + j;

                        if (!isRanged(nr, nc)) continue;
                        if (sign[nr][nc] == '*') continue;

                        // 0 이지만, 사각형 꼭짓점에 있으면 주위에 터트릴 요소가 없다는 뜻이므로, 이 경우 true로 변경한다.
                        check[nr][nc] = true;

                        if(sign[nr][nc] == '0')
                            trace(nr, nc);

                    }
                    ans++;
                }
            }
        }
        //System.out.println("ANS : " + ans);
        //System.out.println("ANS : " + getNotOpenedElement());

        return ans + getNotOpenedElement();
    }

    private static int getNotOpenedElement(){
        int cnt = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(sign[i][j] != '*' && !check[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }


    public static boolean isRanged(int r, int c){
        return r >= 0 && c >= 0 && r < N && c < N;
    }


    // 주변의 지뢰가 몇 개인지를 파악하는 함수
    private static void signNumber() {

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                char cur = sign[i][j];
                int cnt = 0;
                if(cur == '*') continue;

                for(int k = 0; k < 8; k++){
                    int nr = dr[k] + i;
                    int nc = dc[k] + j;

                    if(!isRanged(nr, nc)) continue;

                    if(sign[nr][nc] == '*')
                        cnt++;
                }

                sign[i][j] = (char) (cnt + '0');
            }
        }
    }
}