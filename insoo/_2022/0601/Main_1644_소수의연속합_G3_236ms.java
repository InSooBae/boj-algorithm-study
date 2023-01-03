package Algorithm.BOJ._0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1644_소수의연속합_G3_236ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 구하고자 하는 숫자 범위
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] prime = new boolean[N + 1];

        // 소수는 false
        // 1은 소수가 아니므로 제외
        prime[0] = prime[1] = true;

        for (int i = 2; i * i <= N; i++) {
            // prime[i]가 소수라면
            if (!prime[i]) {
                // prime[j] 소수가 아닌 표시
                for (int j = i * i; j <= N; j += i) prime[j] = true;
            }
        }

        int left = 0;
        int right = 0;

        int sum = 0;
        int cnt = 0;

        // 소수 출력
        for (int i = 2; i <= N; i++) {
            if (!prime[i]) list.add(i);
        }

        int size = list.size();

        while (true) {
            if (sum >= N) {
                sum -= list.get(left++);
            } else if (size == right) {
                break;
            } else {
                sum += list.get(right++);
            }
            if (sum == N) cnt++;
        }

        System.out.println(cnt);

    }
}
