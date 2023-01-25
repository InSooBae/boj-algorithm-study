import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
Greedy를 택할 경우 경찰차 1과, 경찰차 2에서 동일한 위치만큼 떨어진 사건을 처리할 수 없다.

완탐을 택할 경우 매번 경찰차 두 대중 한대를 고르므로, O(2^n) 이라 완탐은 불가능하다.

DFS로 진행하면서, DP를 이용해 상태의 중복을 방지
-> 배열 반복문으로 하면 경로를 저장할 수가 없다

 */

public class Main_2618 {

    static int N;
    static int W;

    static int[] x = new int[1001]; // 일어난 사건의 발생 위치 저장용
    static int[] y = new int[1001];

    static int[][] dp = new int[1001][1001]; // 1번 경찰차가 A번 사건 처리, 2번 경찰차가 B번 사건 처리 시 최소 이동 거리

    public static void main(String[] args) throws Exception {

        init();

        int answer = findMinDist(0,0); // 최소 거리

        System.out.println(answer);

        findRoute(0,0);


    } // main()

    // DFS로 dp 배열 -> p1 : 경찰1이 마지막으로 해결한 사건, p2 : 경찰 2가 마지막으로 해결한 사건
    public static int findMinDist(int p1, int p2) {
        if(p1 == W || p2 == W) return 0; // 기저 조건 : 둘 중 하나가 마지막 사건을 처리한 경우
        if(dp[p1][p2] != -1) return dp[p1][p2]; // 이미 계산된 값인 경우 그 값 사용 (Memo)

        int next = Math.max(p1, p2) + 1; // 다음 사건은 둘 중 더 마지막으로 해결한 사건 + 1
        int d1, d2; // 다음 거리

        // 경찰차 dp 계산
        if(p1 == 0) d1 = calcDist(1, 1, x[next], y[next]); // (1,1) 기준으로 계산
        else d1 = calcDist(x[p1], y[p1], x[next], y[next]);

        if(p2 == 0) d2 = calcDist(N, N, x[next], y[next]); // (N,N) 기준으로 계산
        else d2 = calcDist(x[p2], y[p2], x[next], y[next]);

        int r1 = d1 + findMinDist(next, p2); // 경찰차 1이 사건을 해결한 경우
        int r2 = d2 + findMinDist(p1, next); // 경찰차 2가 사건을 해결한 경우

        dp[p1][p2] = Math.min(r1, r2); // 둘 중 최소거리를 사용

        return dp[p1][p2];
    } // findMinDist()

    public static void findRoute(int p1, int p2) {
        if(p1 == W || p2 == W) return; // 기저 조건 : 둘 중 하나가 마지막 사건을 처리한 경우

        int next = Math.max(p1, p2) + 1; // 다음 사건은 둘 중 더 마지막으로 해결한 사건 + 1
        int d1, d2; // 다음 거리

        // 경찰차 dp 계산
        if(p1 == 0) d1 = calcDist(1, 1, x[next], y[next]); // (1,1) 기준으로 계산
        else d1 = calcDist(x[p1], y[p1], x[next], y[next]);

        if(p2 == 0) d2 = calcDist(N, N, x[next], y[next]); // (N,N) 기준으로 계산
        else d2 = calcDist(x[p2], y[p2], x[next], y[next]);

        int total1 = d1 + dp[next][p2]; // 경찰차 1이 사건을 해결한 경우 총 이동거리
        int total2 = d2 + dp[p1][next]; // 경찰차 2가 사건을 해결한 경우 총 이동거리

        if(total1 < total2) {
            System.out.println(1);
            findRoute(next, p2); // 해당 경로로 이동
        }
        else {
            System.out.println(2);
            findRoute(p1, next);
        }
    } // findRoute()

    public static void init() throws Exception { // 입력값 반영
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());

        for (int i = 1; i <= W; i++) { // 1번째 사건부터 처리
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 1001; i++) Arrays.fill(dp[i],-1);
    } // init()

    public static int calcDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    } // calcDist()

} // class Main
