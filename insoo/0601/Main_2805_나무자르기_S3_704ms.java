package Algorithm.BOJ._0601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2805_나무자르기_S3_704ms {
    static int[] arr = new int[]{1, 5, 4, 2, 9, 7};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trees = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);

        int min = 0;
        int max = trees[trees.length - 1];

        while (min < max) {
            int mid = (min + max) / 2;
            long sum = 0;

            for (int treeHeight :
                    trees) {
                if (treeHeight - mid > 0) {
                    sum += (treeHeight - mid);
                }
            }
            //  M보다 작으면 기준점을 더 낮춰야함
            if (sum < M) {
                max = mid;
            }
            // 기준 높이기(절단기 설정값 중 가장 높은 높이 설정)
            else {
                min = mid + 1;
            }
        }
        // 절단기 설정값 중 가장 높은 높이 설정 - 1
        System.out.println(min - 1);
    }
}
