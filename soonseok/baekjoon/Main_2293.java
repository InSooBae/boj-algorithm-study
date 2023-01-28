import java.io.*;
import java.util.*;

public class Main_2293 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 동전 개수
        int K = Integer.parseInt(st.nextToken()); // K원을 만드는 경우의 수

        int[] coin = new int[N];
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1]; // dp[a]=b -> a금액을 만들수있는 경우의 수
        dp[0] = 1;

        for (int i = 0; i < N; i++) { // 동전 종류마다 반복
            for (int j = coin[i]; j <= K; j++) { // 이번 동전의 금액부터 K원까지 반복
                dp[j] += dp[j - coin[i]]; // 이전 경우의수에서 자기 금액만 더하면 그만큼의 경우의수가 또 생긴다
            }
        }

        System.out.print(dp[K]);

    } // main()

}
