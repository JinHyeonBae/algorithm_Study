import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class swea_2112 {

    // 합격 기준, 보호필름 깊이, 보호필름 너비
    static int K, D, W;

    // cell들의 정보를 담는 배열
    static char[][] cells;
    static char[][] origin;

    // 조건에 부합하는 셀인지 여부를 따짐
    public static boolean check(int col){
        int A = 0;
        int B = 0;
        for(int i = 0; i < D; i++){
            if(cells[i][col] == 'A')
                A++;
            else
                B++;

            if(A >= K || B >= K)
                return true;
        }

        return false;
    }

    // 셀 정보를 갱신
    public static void update(int row, char state){
        for(int i = 0; i < W; i++){
            cells[row][i] = state;
        }
    }

    public static List<Integer> getCb(int cnt, int target, int[] comb, int start){

        ArrayList<Integer> cbCase = new ArrayList<>();

        if(target == cnt){
            for(int i = 0; i < cnt; i++){
                cbCase.add(comb[i]);
            }
        }

        for(int i = start; i < D; i++){
            comb[cnt] = i;
            getCb(cnt + 1, target, comb, i + 1);
        }

        return cbCase;
    }

    public static boolean isPassed(){
        for (int c = 0; c < W; c++) {
            if (!check(c))
                break;

            if (c == W - 1)
                return true;
        }

        return false;
    }

    public static void recover(){
        for(int i = 0; i < D; i++){
            cells[i] = origin[i].clone();
        }
    }

    public static int solution(){

        int passCount = 0;

        if(isPassed())
            return passCount;

        while(true) {
            // 변경한 걸

            // 통과 못함
            passCount++;
            int[] cb = new int[passCount];
            Arrays.fill(cb, -1);
            List<Integer> result = getCb(0, passCount, cb, 0);

            int size = result.size();
            int changeCount = size - 1;
            for(int i = 0; i < size; i++){
                // 한 번은 A, 한 번은 B 이렇게 하고 싶다
                for(int j = 0; j < 2; j++) {
                    if (i <= changeCount) {
                        update(result.get(i), 'A');
                    } else {
                        update(result.get(i), 'B');
                    }

                }
            }

            // 원상복귀
            recover();

            if(isPassed())
                return passCount;
        }
    }

    public static void init(){
        cells = new char[D][W];
        origin = new char[D][W];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));




    }
}
