package _2022._1124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_4179_불_G4 {

    private static int[] dx = {
            1, 0, -1, 0
    };
    private static int[] dy = {
            0, 1, 0, -1
    };
    static class Pos {
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] mat = new char[R][C];

        Queue<Pos> queue = new LinkedList<>();
        Pos jihoon = null;

        for (int i = 0; i < R; i++) {
            String rl = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = rl.charAt(j);
                if (c == 'J') {
                    jihoon = new Pos(j, i);
                } else if (c == 'F') {
                    queue.add(new Pos(j, i));
                }
                mat[i][j] = c;
            }
        }

        bfs(queue, mat, jihoon);
    }

    private static void bfs(Queue<Pos> queue, char[][] mat, Pos jihoon) {
        int[][] visited = new int[mat.length][mat[0].length];
        int[][] jVisited = new int[visited.length][visited[0].length];

        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], -1);
            Arrays.fill(jVisited[i], -1);
        }
        for (Pos fire : queue) {

            visited[fire.y][fire.x] = 0;
        }
        boolean isBreak = false;
        int escapeTime = 0;

        int step = 0;
        while (!queue.isEmpty()) {
            int sameLevelSize = queue.size();
            step++;
            while (sameLevelSize-- > 0) {
                Pos cur = queue.poll();

                for (int i = 0; i < dx.length; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || nx >= mat[0].length || ny < 0 || ny >= mat.length) continue;
                    if (visited[ny][nx] >= 0 || mat[ny][nx] == '#' || mat[ny][nx] == 'J') continue;

                    queue.add(new Pos(nx, ny));
                    visited[ny][nx] = step;
                }
            }
        }

//        for (int i = 0; i < mat.length; i++) {
//            for (int j = 0; j < mat[0].length; j++) {
//                System.out.print(visited[i][j]);
//            }
//            System.out.println();
//        }

        queue.add(jihoon);
        jVisited[jihoon.y][jihoon.x] = 0;
        step = 0;
        while (!queue.isEmpty()) {
            int sameLevelSize = queue.size();
            step++;
            while (sameLevelSize-- > 0) {
                Pos cur = queue.poll();

                for (int i = 0; i < dx.length; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (nx < 0 || nx >= mat[0].length || ny < 0 || ny >= mat.length) {
                        isBreak = true;
                        break;
                    }

                    if (jVisited[ny][nx] >= 0 || visited[ny][nx] != -1 && visited[ny][nx] <= step || mat[ny][nx] == '#') continue;

                    queue.add(new Pos(nx, ny));
                    jVisited[ny][nx] = step;
                }
                if (isBreak) break;
            }
            if (isBreak) break;
        }

        if (isBreak) System.out.println(step);
        else System.out.println("IMPOSSIBLE");
    }
}
