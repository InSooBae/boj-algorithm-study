package _2023._0103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17071_숨바꼭질5_P5 {

    static class Pos {
        int x;
        boolean isSoobin;

        public Pos(int x, boolean isSoobin) {
            this.x = x;
            this.isSoobin = isSoobin;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

//        if (N == K) {
//            System.out.println(0);
//            return;
//        }

        System.out.println(bfs(N, K));
    }

    private static int bfs(int n, int k) {
        Queue<Pos> queue = new LinkedList<>();
        // +1,-1 ,*2 중에 홀수가 짝수쪽 시간 먹을 때 있어서 배열 2개 필요함
        int[][] visited = new int[2][500_001];
        Arrays.fill(visited[0], -1);
        Arrays.fill(visited[1], -1);

        int time = 0;
        queue.add(new Pos(n, true));
        visited[0][n] = time;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int nEvenOdd = (time + 1) % 2;
            if (k > 500_000) return -1;
            if (visited[time % 2][k] != -1) {
                return time;
            }
            for (int i = 0; i < size; i++) {
                Pos cur = queue.poll();

                // 수빈
                if (cur.isSoobin) {
                    for (int nxt : new int[]{cur.x + 1, cur.x - 1, cur.x * 2}) {

                        if (nxt < 0 || nxt > 500_000) continue;
                        if (visited[nEvenOdd][nxt] != -1) continue;

                        queue.add(new Pos(nxt, true));
                        visited[nEvenOdd][nxt] = visited[nEvenOdd][cur.x] + 1;
                    }
                    // 동생
                } else {
                }
            }
            time++;
            k += time;
        }

        return -1;
    }
}
