import java.io.*;
import java.util.*;

public class Main_14939 {

    /*
        아이디어 : n번째 줄의 전구는 항상 n+1번째 줄에서 모두 끌 수 있다.
            즉 첫번째 줄을 모두 누르는 경우의 수 2^10 가지에 대해서만 체크하면 된다
            모든 전구가 꺼졌는지 여부는 마지막 줄에서만 체크하면 된다.
     */
    static boolean[][] initMap = new boolean[10][10];
    static boolean[][] tempMap = new boolean[10][10];
    static int[] dr = { -1, 0, 0, 1};
    static int[] dc = { 0, -1, 1, 0};

    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 초기화
        for (int r = 0; r < 10; r++) {
            String line = br.readLine();

            for (int c = 0; c < 10; c++) {
                if(line.charAt(c) == 'O') initMap[r][c] = true;
            }
        }


        // 2^10가지 경우의 수에 대해 로직 진행
        for (int i = 0; i <(1<<10); i++) simulation(i);
        
        // 결과 출력
        if(minCount == Integer.MAX_VALUE) System.out.print(-1);
        else System.out.print(minCount);

    } // main()

    /** tempMap[][] 에 대하여 로직 진행 */
    public static void simulation(int firstLine) {
        deepCopy(); // tempMap 초기화

        int count = 0;

        for (int i = 0; i < 10; i++) { // 비트마스킹으로 10개 숫자를 체크
            if((firstLine & (1<<i)) != 0) {
                turnLight(0, i); // 첫 번째 줄의 i번째 전구를 켜준다.
                count++;
            }
        }

        for (int r = 1; r < 10; r++) { // 2번째 줄부터 10번째 줄까지 이전 줄의 켜진 전구를 보면 모두 끄면 된다
            for (int c = 0; c < 10; c++) {
                if(tempMap[r - 1][c]) {
                    turnLight(r,c);
                    count++;
                }
            }
        }


        if(isAllTurnedOff()) minCount = Math.min(minCount, count); // 만약 모든 전구를 끈 경우, 횟수 갱신

    } // simulation()

    
    /** tempMap[r][c] 와 그 상하좌우의 전구 상태를 반대로 바꾼다 */
    public static void turnLight(int r, int c) {
        tempMap[r][c] = !tempMap[r][c];

        for (int i = 0; i < 4; i++) {
            int newR = r + dr[i];
            int newC = c + dc[i];

            if(newR < 0 || newC < 0 || newR >= 10 || newC >= 10) continue;

            tempMap[newR][newC] = !tempMap[newR][newC];
        }
    } // turnLight()

    /** tempMap[][]에 대하여 켜진 전구가 남아있는지 체크 */
    public static boolean isAllTurnedOff() {
        for (int i = 0; i < 10; i++) {
            if(tempMap[9][i]) return false; // 켜진 전구를 발견하면 false
        }

        return true;
    } // isAllTurnedOff()

    /** tempMap[][] 을 다시 초기 상태로 변경 */
    public static void deepCopy() {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                tempMap[r][c] = initMap[r][c];
            }
        }
    } // deepCopy()

} // Main
