package _2022._1228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14442_벽부수고이동하기2_G3 {

    static class Triple {
        int x;
        int y;
        int z;

        public Triple(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
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

        int[][][] disK = new int[K][N][M];

        int[][] mat = new int[N][M];

        for (int i = 0; i < N; i++) {
            String rl = br.readLine();
            for (int j = 0; j < M; j++) {
                mat[i][j] = rl.charAt(j) - '0';
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(mat[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();


        System.out.println(bfs(mat, disK, K, N, M));
    }

    private static int bfs(int[][] mat, int[][][] disK, int k, int n, int m) {
        Queue<Triple> queue = new LinkedList<>();
        // TODO: 거리 1부터 시작해서 결과 -1 해줘야함
        disK[0][0][0] = 1;
        queue.add(new Triple(0, 0, 0));
        int answer = -1;
        while (!queue.isEmpty()) {
            Triple cur = queue.poll();

            if (isArrived(n, m, cur.x, cur.y)) {
                return answer = disK[cur.z][cur.y][cur.x];
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;
                int nz = cur.z + 1;

                if (isOOB(n, m, nx, ny)) continue;


//                if (disK[cur.z][ny][nx] != 0 && disK[cur.z][ny][nx] <= disK[cur.z][cur.y][cur.x] + 1) continue;
                if (mat[ny][nx] == 0 && disK[cur.z][ny][nx] == 0) {
                    disK[cur.z][ny][nx] = disK[cur.z][cur.y][cur.x] + 1;
                    queue.add(new Triple(nx, ny, cur.z));
                } else if (cur.z + 1 != k && disK[nz][ny][nx] == 0) {
                    disK[nz][ny][nx] = disK[cur.z][cur.y][cur.x] + 1;
                    queue.add(new Triple(nx, ny, nz));
                }

            }
        }

//        for (int i = 0; i < k; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int l = 0; l < m; l++) {
//                    System.out.print(disK[i][j][l]);
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }


        return answer;
    }

    private static boolean isArrived(int n, int m, int nx, int ny) {
        return nx == m - 1 && ny == n - 1;
    }

    private static boolean isOOB(int n, int m, int nx, int ny) {
        return nx < 0 || nx >= m || ny < 0 || ny >= n;
    }
}
