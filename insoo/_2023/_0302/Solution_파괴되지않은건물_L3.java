package _2023._0302;

public class Solution_파괴되지않은건물_L3 {
    public static void main(String[] args) {
        int solution = new Solution_파괴되지않은건물_L3().solution(new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}}, new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}});
        System.out.println(solution);
    }

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int[][] mat = new int[board.length][board[0].length];

        for (int[] sk : skill) {
            int type = sk[0];
            int startY = sk[1];
            int startX = sk[2];
            int endY = sk[3];
            int endX = sk[4];
            int degree = sk[5];

            if (type == 2) {
                    mat[startY][startX] += degree;
                    if (endX + 1 < board[0].length) mat[startY][endX + 1] -= degree;
                    if (endY + 1 < board.length) {
                        mat[endY + 1][startX] -= degree;
                        if (endX + 1 < board[0].length) mat[endY + 1][endX + 1] += degree;
                    }
            } else {
                mat[startY][startX] -= degree;
                if (endX + 1 < board[0].length) mat[startY][endX + 1] += degree;
                if (endY + 1 < board.length) {
                    mat[endY + 1][startX] += degree;
                    if (endX + 1 < board[0].length) mat[endY + 1][endX + 1] -= degree;
                }
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                mat[i][j] += mat[i][j - 1];
            }
        }

        for (int i = 0; i < mat[0].length; i++) {
            for (int j = 1; j < mat.length; j++) {
                mat[j][i] += mat[j - 1][i];
            }
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] + board[i][j] > 0) answer++;
            }
        }



        return answer;
    }
}
