package _2023._0317;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_숨바꼭질3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (K <= N) {
            System.out.println(N - K);
            return;
        }

        bfs(visited, N, K);
    }

    private static void bfs(int[] visited, int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        visited[n] = 0;
        while (!queue.isEmpty()) {

            Integer cur = queue.poll();
            int idx = cur * 2;
            while (idx <= 100_000) {
                if (visited[idx] != -1) break;
                visited[idx] = visited[cur];
                queue.add(idx);
                idx *= 2;
            }
            for (int nxt : new int[]{cur + 1, cur - 1}) {

                if (nxt > 100_000 || nxt < 0) continue;
                if (visited[nxt] != -1) continue;

                visited[nxt] = visited[cur] + 1;

                queue.add(nxt);
            }
        }

        System.out.println(visited[k]);
    }
}
