package _2023._0322;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_리코쳇로봇 {
    public static void main(String[] args) {
        int solution = new Solution_리코쳇로봇().solution(new String[]{".D.R", "....", ".G..", "...D"});
        System.out.println(solution);
    }

    private static class Pos {
        int x;
        int y;
        int move;

        public Pos(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(String[] board) {
        int answer = 0;

        boolean[][] visited = new boolean[board.length][board[0].length()];
        Pos start = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                if (board[i].charAt(j) == 'R') start = new Pos(j, i, 0);
            }
        }

        answer = bfs(start, visited, board);

        return answer;
    }

    private int bfs(Pos start, boolean[][] visited, String[] board) {
        Queue<Pos> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                while (nx >= 0 && ny >= 0 && nx < visited[0].length && ny < visited.length && board[ny].charAt(nx) != 'D') {
                    nx += dx[i];
                    ny += dy[i];
                }

                if (cur.x + dx[i] == nx && cur.y + dy[i] == ny) continue;
                if (!(nx >= 0 && ny >= 0 && nx < visited[0].length && ny < visited.length)) {
                    nx -= dx[i];
                    ny -= dy[i];
                }
                if (board[ny].charAt(nx) == 'D') {
                    nx -= dx[i];
                    ny -= dy[i];
                }
                if (board[ny].charAt(nx) == 'G') return cur.move + 1;
                if (visited[ny][nx]) continue;

                queue.add(new Pos(nx, ny, cur.move + 1));
                visited[ny][nx] = true;
            }
        }

        return -1;
    }
}
