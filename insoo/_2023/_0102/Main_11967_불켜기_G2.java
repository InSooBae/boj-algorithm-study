package _2023._0102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11967_불켜기_G2 {

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N + 1][N + 1];
        boolean[][] canGo = new boolean[N + 1][N + 1];
        List<Pos>[][] goMat = new List[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int fromX = Integer.parseInt(st.nextToken());
            int fromY = Integer.parseInt(st.nextToken());
            int toX = Integer.parseInt(st.nextToken());
            int toY = Integer.parseInt(st.nextToken());

            if (goMat[fromY][fromX] == null) {
                goMat[fromY][fromX] = new ArrayList<>();
            }
            goMat[fromY][fromX].add(new Pos(toX, toY));
        }

        System.out.println(bfs(visited, goMat, canGo, N));
    }

    private static int bfs(boolean[][] visited, List<Pos>[][] goMat, boolean[][] canGo, int n) {
        int res = 0;
        visited[1][1] = true;
        canGo[1][1] = true;
        Queue<Pos> queue = new LinkedList<>();
        queue.add(new Pos(1, 1));

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
//            System.out.println("현재");
//            System.out.println(cur.x + " " + cur.y);
            if (goMat[cur.y][cur.x] != null) {
//                System.out.println("방불키기");
                for (Pos nxt : goMat[cur.y][cur.x]) {
                    if (!canGo[nxt.y][nxt.x]) {
                        for (int i = 0; i < dx.length; i++) {
                            int nx = dx[i] + nxt.x;
                            int ny = dy[i] + nxt.y;

                            if (nx < 1 || nx > n || ny < 1 || ny > n) continue;
                            if (visited[ny][nx]) {
                                queue.add(new Pos(nx, ny));
                                break;
                            }
                        }
                    }
                    canGo[nxt.y][nxt.x] = true;
//                    System.out.println(nxt.x + " " + nxt.y);
                }
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;

                if (nx < 1 || nx > n || ny < 1 || ny > n) continue;
                if (visited[ny][nx] || !canGo[ny][nx]) continue;

                queue.add(new Pos(nx, ny));
                visited[ny][nx] = true;
            }

        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (canGo[i][j]) res++;
            }
        }

        return res;
    }
}
