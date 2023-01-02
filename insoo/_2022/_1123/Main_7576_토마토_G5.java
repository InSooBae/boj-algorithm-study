package _2022._1123;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_7576_토마토_G5 {

    private static int[] dx = {
            1, 0, -1, 0
    };
    private static int[] dy = {
            0, 1, 0, -1
    };

    static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] mat = new int[N][M];
        int canRipeningTomatoCnt = 0;
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
                if (mat[i][j] == 0) {
                    canRipeningTomatoCnt++;
                } else if (mat[i][j] == 1) {
                    positions.add(new Position(j, i));
                }
            }
        }

        System.out.println(bfs(mat, canRipeningTomatoCnt, positions));
    }

    private static int bfs(int[][] mat, int canRipeningTomatoCnt, List<Position> positions) {
        boolean[][] visited = new boolean[mat.length][mat[0].length];
        Queue<Position> queue = new LinkedList<>();
        int ripenedTomatoCnt = 0;
        int ripenedDay = 0;

        for (Position position : positions) {
            queue.add(position);
            visited[position.y][position.x] = true;
        }

        while (!queue.isEmpty()) {
            int sameRipenedDayTomatoSize = queue.size();
            while (sameRipenedDayTomatoSize-- > 0) {

                Position curPos = queue.poll();

                for (int i = 0; i < dx.length; i++) {
                    int nx = curPos.x + dx[i];
                    int ny = curPos.y + dy[i];

                    if (isOOB(mat, nx, ny)) continue;
                    if (visited[ny][nx] || mat[ny][nx] == -1) continue;

                    queue.add(new Position(nx, ny));
                    visited[ny][nx] = true;
                    ripenedTomatoCnt++;
                }
            }
            ripenedDay++;
        }
//        System.out.println(ripenedTomatoCnt);
        return validateRipeningDay(canRipeningTomatoCnt, ripenedTomatoCnt, ripenedDay);
    }

    private static int validateRipeningDay(int canRipeningTomatoCnt, int ripenedTomatoCnt, int ripenedDay) {
        if (ripenedTomatoCnt == canRipeningTomatoCnt) {
            return ripenedDay - 1;
        } else {
            return -1;
        }
    }

    private static boolean isOOB(int[][] mat, int nx, int ny) {
        return ny < 0 || ny >= mat.length || nx < 0 || nx >= mat[0].length;
    }
}
