package _2023._0324;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_열쇠 {
    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        Queue<Pos> startPos = new ArrayDeque<>();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char[][] mat = new char[h][w];

            int keys = 0;
            int stolenDocs = 0;
            for (int i = 0; i < h; i++) {
                String rl = br.readLine();
                for (int j = 0; j < rl.length(); j++) {
                    mat[i][j] = rl.charAt(j);
                    // 진입 부분
                    if ((i == 0 || i == h - 1 || j == 0 || j == w - 1) && mat[i][j] != '*') {
                        if ('a' <= mat[i][j] && mat[i][j] <= 'z') {
                            keys = keys | 1 << (mat[i][j] - 'a');
                            mat[i][j] = '.';
                        } else if (mat[i][j] == '$') {
                            stolenDocs++;
                            mat[i][j] = '.';
                        }
                        startPos.add(new Pos(j, i));
                    }
                }
            }
            for (char c : br.readLine().toCharArray()) {
                if (c == '0') break;
                else keys = keys | 1 << (c - 'a');
            }

            sb.append(bfs(startPos, mat, keys, stolenDocs)).append('\n');

            while (!startPos.isEmpty()) startPos.poll();
        }

        System.out.println(sb);
    }

    private static int bfs(Queue<Pos> startPos, char[][] mat, int keys, int stolenDocs) {

        Queue<Pos> queue = new ArrayDeque<>();
        boolean isFindKey = false;
        boolean[][] visited = new boolean[mat.length][mat[0].length];

        start(startPos, queue, visited, keys, mat);

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= mat[0].length || ny < 0 || ny >= mat.length) continue;
                if (visited[ny][nx] || mat[ny][nx] == '*') continue;

                if (mat[ny][nx] == '$') {
                    stolenDocs++;
                    mat[ny][nx] = '.';
                } else if ('A' <= mat[ny][nx] && mat[ny][nx] <= 'Z') {
                    if ((keys & 1 << (mat[ny][nx] - 'A')) != 0) {
                        mat[ny][nx] = '.';
                    } else continue;
                } else if ('a' <= mat[ny][nx] && mat[ny][nx] <= 'z') {
                    keys = keys | 1 << (mat[ny][nx] - 'a');
                    mat[ny][nx] = '.';

                    while (!queue.isEmpty()) queue.poll();
                    clear(visited);
                    isFindKey = true;
                    break;
                }

                queue.add(new Pos(nx, ny));
                visited[ny][nx] = true;
            }

            if (isFindKey) start(startPos, queue, visited, keys, mat);
            isFindKey = false;
        }

        return stolenDocs;
    }

    private static void start(Queue<Pos> startPos, Queue<Pos> queue, boolean[][] visited, int keys, char[][] mat) {
        for (Pos pos : startPos) {
            if ('A' <= mat[pos.y][pos.x] && mat[pos.y][pos.x] <= 'Z') {
                if ((keys & 1 << (mat[pos.y][pos.x] - 'A')) != 0) {
                    mat[pos.y][pos.x] = '.';
                } else continue;
            }
            queue.add(pos);
            visited[pos.y][pos.x] = true;
        }
    }

    private static void clear(boolean[][] visited) {
        for (boolean[] booleans : visited) {
            Arrays.fill(booleans, false);
        }
    }
}
