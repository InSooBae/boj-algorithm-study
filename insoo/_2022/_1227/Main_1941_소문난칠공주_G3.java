package _2022._1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1941_소문난칠공주_G3 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] mat = new char[5][5];

        for (int i = 0; i < 5; i++) {
            String row = br.readLine();
            for (int j = 0; j < 5; j++) {
                mat[i][j] = row.charAt(j);
            }
        }

        combination(0, 0, mat, 0, 0, 0);

        System.out.println(res);
    }

    private static void combination(int pos, int start, char[][] mat, int flag, int sCnt, int yCnt) {
        if (pos == 7) {

            if (bfs(flag, start - 1)) res++;


            return;
        }

        for (int i = start; i < 25; i++) {

            if (mat[i / 5][i % 5] == 'S') {
                combination(pos + 1, i + 1, mat, flag | 1 << i, sCnt + 1, yCnt);
            } else {
                if (yCnt < 3) combination(pos + 1, i + 1, mat, flag | 1 << i, sCnt, yCnt + 1);
            }
        }
    }

    private static boolean bfs(int flag, int startPos) {
        Queue<Integer> queue = new LinkedList<>();
        int visFlag = 1 << startPos;
        queue.add(startPos);
        int visCnt = 1;


        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = dx[i] + cur % 5;
                int ny = dy[i] + cur / 5;
                int buildMatIdx = ny * 5 + nx;

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || (flag & 1 << buildMatIdx) == 0 || (visFlag & 1 << buildMatIdx) != 0) continue;
                visFlag = visFlag | 1 << buildMatIdx;
                queue.add(ny * 5 + nx);
                visCnt++;
            }
        }

//        System.out.println(visCnt);

        return visCnt == 7;
    }

}
