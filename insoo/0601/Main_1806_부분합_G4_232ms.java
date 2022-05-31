package Algorithm.BOJ._0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806_부분합_G4_232ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        long S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        long sum = 0;
        int min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

        }
        int left = 0;
        int right = 0;
        while (true) {
            if (sum >= S) {
                sum -= arr[left];
                if (min > right - left) min = right - left;
                left++;
            } else if (right == N) {
                break;
            } else {
                sum += arr[right++];
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
