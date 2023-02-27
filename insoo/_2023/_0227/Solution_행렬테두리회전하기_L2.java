package _2023._0227;

import java.util.Arrays;

public class Solution_행렬테두리회전하기_L2 {
    public static void main(String[] args) {
        int[] solution = new Solution_행렬테두리회전하기_L2().solution(100, 97, new int[][]{{1, 1, 100, 97}});
        System.out.println(Arrays.toString(solution));
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int[][] mat = new int[rows][columns];

        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mat[i][j] = num++;
            }
        }

        num = 0;

        for (int[] query : queries) {
            int startY = query[0] - 1;
            int startX = query[1] - 1;
            int endY = query[2] - 1;
            int endX = query[3] - 1;

            answer[num++] = rotateEdge(mat, startX, startY, endX, endY);
        }

        return answer;
    }

    private int rotateEdge(int[][] mat, int startX, int startY, int endX, int endY) {
        int minNum = mat[startY][startX];

        int tempFirstNum = mat[startY][startX];
        int moveY = endY - startY;
        int moveX = endX - startX;
        int curY = startY;
        int curX = startX;

        for (int i = 0; i < moveY; i++) {
            if (minNum > mat[curY + 1][curX]) minNum = mat[curY + 1][curX];
            mat[curY][curX] = mat[curY + 1][curX];
            curY++;
        }

        for (int i = 0; i < moveX; i++) {
            if (minNum > mat[curY][curX + 1]) minNum = mat[curY][curX + 1];
            mat[curY][curX] = mat[curY][curX + 1];
            curX++;
        }

        for (int i = 0; i < moveY; i++) {
            if (minNum > mat[curY - 1][curX]) minNum = mat[curY - 1][curX];
            mat[curY][curX] = mat[curY - 1][curX];
            curY--;
        }

        for (int i = 0; i < moveX - 1; i++) {
            if (minNum > mat[curY][curX - 1]) minNum = mat[curY][curX - 1];
            mat[curY][curX] = mat[curY][curX - 1];
            curX--;
        }

        mat[curY][curX] = tempFirstNum;

//        for (int i = 0; i < mat.length; i++) {
//            for (int j = 0; j < mat[i].length; j++) {
//                System.out.print(mat[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("+==========================+");

        return minNum;
    }
}
