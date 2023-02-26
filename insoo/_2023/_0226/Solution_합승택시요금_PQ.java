package _2023._0226;

import java.util.*;
public class Solution_합승택시요금_PQ {
    private static class Pair implements Comparable<Pair>{
        int vertex;
        int val;

        public Pair(int vertex, int val) {
            this.vertex = vertex;
            this.val = val;
        }

        @Override
        public int compareTo(Pair o) {
            return this.val - o.val;
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        int[][] vertexes = new int[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];
        int[][] mat = new int[n + 1][n + 1];

        Queue<Pair> queue = new PriorityQueue<>();
        // s -> 시작 지점
        for (int i = 1; i <= n; i++) {
            Arrays.fill(vertexes[i], Integer.MAX_VALUE);
        }

        // a , b 도착 지점

        for (int[] fare : fares) {
            mat[fare[0]][fare[1]] = fare[2];
            mat[fare[1]][fare[0]] = fare[2];
        }

        for (int i = 1; i <= n; i++) {
//            if (i == a || i == b) continue;
            queue.add(new Pair(i, 0));
            vertexes[i][i] = 0;

            while (!queue.isEmpty()) {
                Pair cur = queue.poll();
                if (vertexes[i][cur.vertex] < cur.val) continue;

                for (int j = 1; j <= n; j++) {
                    if (mat[cur.vertex][j] != 0 && mat[cur.vertex][j] + cur.val < vertexes[i][j]) {
                        queue.add(new Pair(j, cur.val + mat[cur.vertex][j]));
                        vertexes[i][j] = cur.val + mat[cur.vertex][j];
                    }

                }
            }

        }
        answer = vertexes[s][a] + vertexes[s][b];
        for (int i = 1; i <= n; i++) {
            if (i != s) {
                answer = Math.min(answer, vertexes[s][i] + vertexes[i][a] + vertexes[i][b]);
            }
        }
//        for (int i = 1; i <= n; i++) {
//            System.out.println(Arrays.toString(vertexes[i]));
//        }

        return answer;
    }
}
