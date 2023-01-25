package _2023._0104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_9328_열쇠_G1 {

    static class Pos {
        int x;
        int y;
        int z;

        public Pos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        Queue<Pos> queue = new LinkedList<>();
        boolean[] keys = new boolean[26];

        Map<Integer, Integer> map = new HashMap<>();

        for (Integer integer : map.keySet()) {

        }

        for (int T = 0; T < t; T++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cnt = 0;

            char[][] mat = new char[h][w];
            boolean[][][] visited = new boolean[26][h][w];

            inputMat(br, h, w, mat);

            earnKeys(br, keys);

            cnt = findEntryPointFromEdge(queue, keys, h, w, mat, visited);

            sb.append(bfs(mat, visited, h, w, queue, keys, cnt)).append('\n');
            Arrays.fill(keys, false);
            queue.clear();
        }
        System.out.println(sb);
    }

    private static int bfs(char[][] mat, boolean[][][] visited, int h, int w, Queue<Pos> queue, boolean[] keys, int cnt) {

        Queue<Pos> entry = new LinkedList<>(queue);

        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = dx[i] + cur.x;
                int ny = dy[i] + cur.y;

                if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                if (mat[ny][nx] == '*' || visited[cur.z][ny][nx]) continue;

                if (mat[ny][nx] == '$') {
                    cnt++;
                    mat[ny][nx] = '.';
                    visited[cur.z][ny][nx] = true;
                    queue.add(new Pos(nx, ny, cur.z));
                } else if ('a' <= mat[ny][nx] && mat[ny][nx] <= 'z') {
                    if (!keys[mat[ny][nx] - 'a']) {
                        keys[mat[ny][nx] - 'a'] = true;
                        visited[cur.z][ny][nx] = true;
                        visited[cur.z + 1][ny][nx] = true;
                        queue.add(new Pos(nx, ny, cur.z + 1));
                        for (Pos pos : entry) {
                            if ('A' <= mat[pos.y][pos.x] && mat[pos.y][pos.x] <= 'Z') {
                                if (keys[mat[pos.y][pos.x] - 'A']) {
                                    queue.add(new Pos(pos.x, pos.y, cur.z + 1));
                                    visited[cur.z + 1][pos.y][pos.x] = true;
                                }
                            } else {
                                queue.add(new Pos(pos.x, pos.y, cur.z + 1));
                                visited[cur.z + 1][pos.y][pos.x] = true;
                            }
                        }
                    } else {
                        visited[cur.z][ny][nx] = true;
                        queue.add(new Pos(nx, ny, cur.z));
                    }
                } else if ('A' <= mat[ny][nx] && mat[ny][nx] <= 'Z') {
                    if (keys[mat[ny][nx] - 'A']) {
                        visited[cur.z][ny][nx] = true;
                        queue.add(new Pos(nx, ny, cur.z));
                    }
                } else if (mat[ny][nx] == '.') {
                    visited[cur.z][ny][nx] = true;
                    queue.add(new Pos(nx, ny, cur.z));
                }
            }
        }

//        for (int i = 0; i < h; i++) {
//            for (int j = 0; j < w; j++) {
//                System.out.print(mat[i][j]);
//            }
//            System.out.println();
//        }
//
//        for (char i = 'a'; i <= 'z'; i++) {
//            if (keys[i - 'a']) {
//                System.out.print(i);
//            }
//        }
//        System.out.println();

        return cnt;
    }

    private static int findEntryPointFromEdge(Queue<Pos> queue, boolean[] keys, int h, int w, char[][] mat, boolean[][][] visited) {
        int x = 0;
        int y = 0;
        int cnt = 0;
        for (int i = 0; i < w - 1; i++) {
            if (mat[y][x] == '.') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
            } else if ('A' <= mat[y][x] && mat[y][x] <= 'Z') {
                queue.add(new Pos(x, y, 0));
                if (keys[mat[y][x] - 'A']) {
                    visited[0][y][x] = true;
                }
            } else if (mat[y][x] == '$') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
                mat[y][x] = '.';
                cnt++;
            } else if ('a' <= mat[y][x] && mat[y][x] <= 'z') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
                keys[mat[y][x] - 'a'] = true;
            }
            x++;
        }
        for (int i = 0; i < h - 1; i++) {
            if (mat[y][x] == '.') {
                visited[0][y][x] = true;
                queue.add(new Pos(x, y, 0));
            } else if ('A' <= mat[y][x] && mat[y][x] <= 'Z') {
                queue.add(new Pos(x, y, 0));
                if (keys[mat[y][x] - 'A']) {
                    visited[0][y][x] = true;
                }
            } else if (mat[y][x] == '$') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
                mat[y][x] = '.';
                cnt++;
            } else if ('a' <= mat[y][x] && mat[y][x] <= 'z') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
                keys[mat[y][x] - 'a'] = true;
            }
            y++;
        }
        for (int i = 0; i < w - 1; i++) {
            if (mat[y][x] == '.') {
                visited[0][y][x] = true;
                queue.add(new Pos(x, y, 0));
            } else if ('A' <= mat[y][x] && mat[y][x] <= 'Z') {
                queue.add(new Pos(x, y, 0));
                if (keys[mat[y][x] - 'A']) {
                    visited[0][y][x] = true;
                }
            } else if (mat[y][x] == '$') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
                mat[y][x] = '.';
                cnt++;
            } else if ('a' <= mat[y][x] && mat[y][x] <= 'z') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
                keys[mat[y][x] - 'a'] = true;
            }
            x--;
        }
        for (int i = 0; i < h - 1; i++) {
            if (mat[y][x] == '.') {
                visited[0][y][x] = true;
                queue.add(new Pos(x, y, 0));
            } else if ('A' <= mat[y][x] && mat[y][x] <= 'Z') {
                queue.add(new Pos(x, y, 0));
                if (keys[mat[y][x] - 'A']) {
                    visited[0][y][x] = true;
                }
            } else if (mat[y][x] == '$') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
                mat[y][x] = '.';
                cnt++;
            } else if ('a' <= mat[y][x] && mat[y][x] <= 'z') {
                queue.add(new Pos(x, y, 0));
                visited[0][y][x] = true;
                keys[mat[y][x] - 'a'] = true;
            }
            y--;
        }
        return cnt;
    }

    private static void inputMat(BufferedReader br, int h, int w, char[][] mat) throws IOException {
        for (int i = 0; i < h; i++) {
            String rl = br.readLine();
            for (int j = 0; j < w; j++) {
                mat[i][j] = rl.charAt(j);
            }
        }
    }

    private static void earnKeys(BufferedReader br, boolean[] keys) throws IOException {
        String earnKeys = br.readLine();
        for (int i = 0; i < earnKeys.length(); i++) {
            char c = earnKeys.charAt(i);
            if ('a' <= c && c <= 'z') {
                keys[c - 'a'] = true;
            }
        }
    }
}
