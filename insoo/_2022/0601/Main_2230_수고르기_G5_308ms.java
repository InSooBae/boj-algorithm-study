package Algorithm.BOJ._0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2230_수고르기_G5_308ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = 0;
        int ans = Integer.MAX_VALUE;
        // 오른쪽으로 더 갈 곳 없으면 끝
        while (right < N) {
            // 두 차이가 M보다 작으면 right를 앞으로 보내서 차이를 늘림
            if (arr[right] - arr[left] < M) {
                right++;
            }
            // 두 차이가 M이면 더 탐색할 필요 X
            else if (arr[right] - arr[left] == M) {
                ans = M;
                break;
            }
            // 두 차이가 M보다 크면 left 앞으로
            else {
                ans = Math.min(ans, arr[right] - arr[left++]);
            }
        }
        System.out.println(ans);
    }
}
