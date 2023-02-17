package _2023._0217;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution_프렌즈4블록_L2 {
    public static void main(String[] args) {
        int solution = new Solution_프렌즈4블록_L2().solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"});
        System.out.println(solution);
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] mat = new char[board.length][];
        for (int i = 0; i < board.length; i++) {
            mat[i] = board[i].toCharArray();
        }
        int temp = 0;
        while ((temp = findBreak(mat)) != 0) {
            answer += temp;
            gravity(mat);
        }

        return answer;
    }

    private void gravity(char[][] mat) {
        char[] temp = new char[mat.length];

        for (int i = 0; i < mat[0].length; i++) {
            int idx = mat.length - 1;
            for (int j = mat.length - 1; j >= 0; j--) {
                if (mat[j][i] != '0') temp[idx--] = mat[j][i];
            }

            for (int j = mat.length - 1; j >= 0; j--) {
                mat[j][i] = temp[j];
                temp[j] = 0;
            }
        }
    }

    private static int findBreak(char[][] mat) {
        Queue<int[]> queue = new ArrayDeque<>();
        int res = 0;

        for (int i = 0; i < mat.length - 1; i++) {
            for (int j = 0; j < mat[0].length - 1; j++) {
                char c = mat[i][j];
                if (c == 0) continue;
                if (c == mat[i][j + 1] && c == mat[i + 1][j + 1] && c == mat[i + 1][j]) {
                    queue.add(new int[]{j, i});
                    queue.add(new int[]{j + 1, i});
                    queue.add(new int[]{j + 1, i + 1});
                    queue.add(new int[]{j, i + 1});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            if (mat[poll[1]][poll[0]] == '0') continue;
            mat[poll[1]][poll[0]] = '0';
            res++;
        }

        return res;
    }
}
