package _2023._0213;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution_경주로건설_L3 {
    public static void main(String[] args) {
        int solution = new Solution_경주로건설_L3().solution(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 0},
        });
        System.out.println(solution);
    }

    static int[] dx = {
            1, 0, -1, 0
    };

    static int[] dy = {
            0, 1, 0, -1
    };

    public int solution(int[][] board) {
        int answer = 0;

        int[][][] visited = new int[4][board.length][board[0].length];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        visited[0][0][0] = 0;
        visited[1][0][0] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0, 0});
        queue.add(new int[]{0, 0, 1, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                int sum = cur[3] + 100;
                int preDir = cur[2];

                if (nx < 0 || nx >= board[0].length || ny < 0 || ny >= board.length) continue;
                if (visited[i][ny][nx] <= sum || board[ny][nx] == 1) continue;

                if (preDir != i) sum += 500;
                if ((preDir + 2) % 4 == i) continue;

                queue.add(new int[]{nx, ny, i, sum});
                visited[i][ny][nx] = sum;

            }
        }
//        dfs(0, 0, board, visited, 0, -1);


//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < board.length; j++) {
//                for (int k = 0; k < board[0].length; k++) {
//                    System.out.print(visited[i][j][k] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("=======================");
//        }
//
//        System.out.println(visited[0][4][4]);
//        System.out.println(visited[1][4][4]);
//        System.out.println(visited[2][4][4]);
//        System.out.println(visited[3][4][4]);

        answer = Math.min(Math.min(Math.min(visited[0][board.length - 1][board[0].length - 1], visited[1][board.length - 1][board[0].length - 1]), visited[2][board.length - 1][board[0].length - 1]), visited[3][board.length - 1][board[0].length - 1]);

        return answer;
    }

    private void dfs(int x, int y, int[][] board, int[][][] visited, int sum, int preDir) {
        if (x == board[0].length - 1 && y == board.length - 1) {
            return;
        }

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= board[0].length || ny < 0 || ny >= board.length) continue;
            if (visited[i][ny][nx] <= sum || board[ny][nx] == 1) continue;


            if (preDir != -1 && preDir != i) {
                if ((preDir + 2) % 4 == i) continue;
                visited[i][ny][nx] = sum + 6;
                dfs(nx, ny, board, visited, sum + 6, i);
            } else {
                visited[i][ny][nx] = sum + 1;
                dfs(nx, ny, board, visited, sum + 1, i);
            }

        }
    }

}
