import java.io.*;
import java.util.*;

public class Main_2294 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr); // 오름차순 정렬

        int[] dp = new int[K + 1]; // target K 까지 포함하여 계산
        Arrays.fill(dp, 10001); // 초기값 max
        for (int i = 0; i < N; i++) {
            if(arr[i] > K) continue; // 배열 범위 넘어가는 동전은 제낀다

            dp[ arr[i] ] = 1; // dp[a] = b -> a원을 만드는데 사용한 동전의 개수 b
        }

        for (int i = 1; i <= K; i++) { // 1 ~ K원(가격) 반복하며 수를 계산
            for (int j = 0; j < N; j++) { // 모든 동전에 대해서 계산
                int price = i + arr[j]; // 새 가격

                if(price > K) break; // 가격이 인덱스 넘어가면 종료(오름차순이라 그냥 break)
                
                if(dp[price] > dp[i] + 1) { // 기존 사용한 동전 개수보다, 이번 금액에서 동전 쓴게 더 적은 개수라면 변경
                    dp[price] = dp[i] + 1;
                }

            }
        } // bottom-up for

        if(dp[K] == 10001) System.out.print(-1);
        else System.out.print(dp[K]);


    } // main()
} // class Main
