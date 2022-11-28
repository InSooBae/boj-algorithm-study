package _1128;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_숨바꼭질_S1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈 위치
        int N = Integer.parseInt(st.nextToken());
        // 동생 위치
        int K = Integer.parseInt(st.nextToken());
        if (N == K) System.out.println(0);
        else bfs(N, K);
    }

    private static void bfs(int n, int k) {
        int[] arr = new int[100_001];
        Arrays.fill(arr, -1);
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(n);
        arr[n] = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for (int nxt : new int[] {
                cur + 1, cur - 1, cur * 2
            }){
                if (nxt == k) {
                    System.out.println(arr[cur] + 1);
                    System.exit(0);
                }
                if (nxt < 0 || nxt > 100_000) continue;
                if (arr[nxt] > 0) continue;

                queue.add(nxt);
                arr[nxt] = arr[cur] + 1;
            }
        }

    }
}
