package _2022._1230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16920_확장게임_G2 {

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
        int P = Integer.parseInt(st.nextToken());

        int[] playerMove = new int[P + 1];
        st = new StringTokenizer(br.readLine());

        Queue<Pos>[] players = new Queue[P + 1];
        int[] pCastleCnt = new int[P + 1];

        for (char i = '1'; i <= P + '0'; i++) {
            playerMove[i - '0'] = Integer.parseInt(st.nextToken());
            players[i - '0'] = new LinkedList<>();
        }

        char[][] mat = new char[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String rl = br.readLine();
            for (int j = 0; j < M; j++) {
                char p = rl.charAt(j);
                if ('1' <= p && p <= '9') {
                    visited[i][j] = true;
                    pCastleCnt[p - '0']++;
                    players[p - '0'].add(new Pos(j, i));
                }
                mat[i][j] = p;
            }
        }


        bfs(players, playerMove, mat, visited, pCastleCnt, N, M, P);

        for (int i = 1; i <= P; i++) {
            System.out.print(pCastleCnt[i] + " ");
        }
    }

    private static void bfs(Queue<Pos>[] players, int[] playerMove, char[][] mat, boolean[][] visited, int[] pCastleCnt, int n, int m, int p) {
        boolean isMatFull = false;
        while (!isMatFull) {
            int pEmptyCnt = 0;
            for (int i = 1; i <= p; i++) {
                Queue<Pos> curQ = players[i];
                int move = 0;
                int curMove = playerMove[i];
                if (curQ.isEmpty()) pEmptyCnt++;
                while (!curQ.isEmpty()) {
                    int size = curQ.size();
                    for (int j = 0; j < size; j++) {
                        Pos cur = curQ.poll();

                        for (int k = 0; k < dx.length; k++) {
                            int nx = dx[k] + cur.x;
                            int ny = dy[k] + cur.y;

                            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                            if (mat[ny][nx] == '#' || visited[ny][nx]) continue;

                            pCastleCnt[i]++;
                            curQ.add(new Pos(nx, ny));
                            visited[ny][nx] = true;
                        }
                    }
                    if (curMove == ++move) break;
                }
            }
            if (pEmptyCnt == p) isMatFull = true;
        }
    }
}
