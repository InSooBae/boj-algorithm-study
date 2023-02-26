package _2023._0226;

import java.util.Arrays;

public class Solution_합승택시요금_L3 {
    public static void main(String[] args) {
        int solution = new Solution_합승택시요금_L3().solution(6, 4, 5, 6, new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}});
        System.out.println(solution);
    }


    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;

        int[][] vertexes = new int[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];
        int[][] mat = new int[n + 1][n + 1];

        // s -> 시작 지점
        for (int i = 1; i <= n; i++) {
            Arrays.fill(vertexes[i], Integer.MAX_VALUE);
            vertexes[i][i] = 0;
        }

        // a , b 도착 지점

        for (int[] fare : fares) {
            mat[fare[0]][fare[1]] = fare[2];
            mat[fare[1]][fare[0]] = fare[2];
        }

        for (int i = 1; i <= n; i++) {
//            if (i == a || i == b) continue;

            for (int k = 0; k < n - 1; k++) {
                int min = Integer.MAX_VALUE;
                int idx = 0;
                for (int j = 1; j <= n; j++) {
                    if (!visited[j] && min > vertexes[i][j]) {
                        min = vertexes[i][j];
                        idx = j;
                    }
                }
                visited[idx] = true;

                for (int j = 1; j <= n; j++) {
                    if (mat[idx][j] != 0) {
                        vertexes[i][j] = Math.min(vertexes[i][j], vertexes[i][idx] + mat[idx][j]);
                    }

                }
            }
            Arrays.fill(visited, false);
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

