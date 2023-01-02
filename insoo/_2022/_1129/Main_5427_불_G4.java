package _2022._1129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불_G4 {

    static int[] dx = {
            1, 0, -1, 0
    };

    static int[] dy = {
            0, 1, 0, -1
    };

    static class Pos {

        int x;

        int y;

        public Pos() {
        }

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            char[][] mat = new char[H][W];

            Queue<Pos> fires = new LinkedList<>();
            Queue<Pos> person = new LinkedList<>();
            int[][] fireVisited = new int[H][W];
            int[][] personVisited = new int[H][W];


            for (int i = 0; i < H; i++) {
                Arrays.fill(fireVisited[i], -1);
                Arrays.fill(personVisited[i], -1);
            }

            for (int i = 0; i < H; i++) {
                String str = br.readLine();
                for (int j = 0; j < W; j++) {
                    mat[i][j] = str.charAt(j);
                    if (mat[i][j] == '*') {
                        fires.add(new Pos(j, i));
                        fireVisited[i][j] = 0;
                    } else if (mat[i][j] == '@') {
                        person.add(new Pos(j, i));
                        personVisited[i][j] = 0;
                    }
                }
            }

            bfs(mat, fires, person, W, H, fireVisited, personVisited, sb);

        }

        System.out.println(sb);
    }

    private static void bfs(char[][] mat, Queue<Pos> fires, Queue<Pos> person, int w, int h, int[][] fireVisited, int[][] personVisited, StringBuilder sb) {


        while (!fires.isEmpty()) {
            Pos cur = fires.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                if (fireVisited[ny][nx] != -1 || mat[ny][nx] == '#') continue;

                fires.add(new Pos(nx, ny));
                fireVisited[ny][nx] = fireVisited[cur.y][cur.x] + 1;

            }

        }

        boolean isEscaped = false;
        int escapedTime = 0;



        while (!person.isEmpty()) {
            Pos cur = person.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isEscape(w, h, nx, ny)) {
                    // 탈출함
                    isEscaped = true;
                    escapedTime = personVisited[cur.y][cur.x] + 1;
                    break;
                }
                if (personVisited[ny][nx] != -1 || fireVisited[ny][nx] != -1 && fireVisited[ny][nx] <= personVisited[cur.y][cur.x] + 1  || mat[ny][nx] == '#') continue;

                person.add(new Pos(nx, ny));
                personVisited[ny][nx] = personVisited[cur.y][cur.x] + 1;

            }
            if (isEscaped) break;
        }

        if (isEscaped) {
            sb.append(escapedTime).append("\n");
        } else {
            sb.append("IMPOSSIBLE").append("\n");
        }
    }

    private static boolean isEscape(int w, int h, int nx, int ny) {
        return nx < 0 || nx >= w || ny < 0 || ny >= h;
    }
}
