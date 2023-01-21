import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

3XN 크기의 벽을 2x1, 1x2 타일로 채우기

-> N이 홀수인 경우, 항상 불가능하다

-> N==2 인 경우 3가지 경우의 수가 생긴다
    -> 즉, N (1≤ N ≤30) 이므로, 홀수인 경우를 제외하고 3^15 정도의 숫자를 예상할 수 있는데, int 범위 내이다
    
-> N=2*M (M>1) 인 경우, 2가지의 추가 case가 항상 발생한다

 */

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N % 2 != 0) { // 타일의 수가 홀수인 경우, 항상 불가능
            System.out.print(0);
            return;
        }

        int[] dp = new int[31]; // dp[A] = B -> 3XA 의 벽을 채울 수 있는 경우의 수 B

        dp[0] = 1; // 0일때는 1로 초기화해야 함,
        dp[1] = 0;
        dp[2] = 3; // 3x2 일 때, 3가지 경우

        for (int i = 4; i <= N; i+=2) { // 4부터 2 간격으로 N 까지 진행

            int extraCase = 0;
            for (int j = 4; (i - j)>=0; j+=2) { // 최소 4부터
               extraCase += dp[i - j] * 2; // 이전 타일의 경우의 수 x 2가지 추가 케이스
            }
            
            dp[i] = (dp[i - 2] * 3) + extraCase; // 이전 타일의 경우의 수 x 3가지 일반 케이스
        }

        System.out.print(dp[N]);

    } // main()

}
