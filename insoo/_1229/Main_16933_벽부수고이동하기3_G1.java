package _1229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16933_벽부수고이동하기3_G1 {

    static class Quad {
        int x;
        int y;
        int z;
        int dayNight;

        public Quad(int x, int y, int z, int dayNight) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.dayNight = dayNight;
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()) + 1;

        int[][] mat = new int[N][M];
        int[][][][] disK = new int[2][K][N][M];

        for (int i = 0; i < N; i++) {
            String rl = br.readLine();
            for (int j = 0; j < M; j++) {
                mat[i][j] = rl.charAt(j) - '0';
            }
        }

        System.out.println(bfs(mat, disK, N, M, K));

    }

    private static int bfs(int[][] mat, int[][][][] disK, int n, int m, int k) {
        Queue<Quad> queue = new LinkedList<>();
        disK[0][0][0][0] = 1;
        queue.add(new Quad(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Quad cur = queue.poll();

            if (cur.x == m - 1 && cur.y == n - 1) {
                return disK[cur.dayNight][cur.z][cur.y][cur.x];
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                int nz = cur.z + 1;
                int nTime = 0;
                if (cur.dayNight == 0) nTime = 1;

                if (isOOB(n, m, nx, ny)) continue;

                // 갈 곳이 벽 아닐 때
                if (mat[ny][nx] == 0 && disK[nTime][cur.z][ny][nx] == 0) {
                    disK[nTime][cur.z][ny][nx] = disK[cur.dayNight][cur.z][cur.y][cur.x] + 1;
                    queue.add(new Quad(nx, ny, cur.z, nTime));
                    // 갈 곳이 벽일 때
                } else if (cur.z + 1 != k && disK[nTime][nz][ny][nx] == 0) {
                    // 낮
                    if (cur.dayNight == 0) {
                        disK[nTime][nz][ny][nx] = disK[cur.dayNight][cur.z][cur.y][cur.x] + 1;
                        queue.add(new Quad(nx, ny, nz, nTime));
                        // 밤
                    } else {
                        disK[nTime][cur.z][cur.y][cur.x] = disK[cur.dayNight][cur.z][cur.y][cur.x] + 1;
                        queue.add(new Quad(cur.x, cur.y, cur.z, nTime));
                    }

                }
            }
        }

        return -1;
    }

    private static boolean isOOB(int n, int m, int nx, int ny) {
        return nx < 0 || nx >= m || ny < 0 || ny >= n;
    }
}
