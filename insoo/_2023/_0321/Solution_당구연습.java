package _2023._0321;

import java.util.Arrays;

public class Solution_당구연습 {
    public static void main(String[] args) {
        int[] solution = new Solution_당구연습().solution(10, 10, 3, 7, new int[][]{{7, 7}, {2, 7}, {7, 3}});
        System.out.println(Arrays.toString(solution));

    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];


        int[][] calcs = new int[4][2];
        // 좌측 벽 기준(x) 벽 넘어가기
        calcs[0][0] = -startX;
        calcs[0][1] = startY;

        // 우측 벽 기준 벽 넘어가기
        calcs[1][0] = 2 * (m - startX) + startX;
        calcs[1][1] = startY;

        // 상단 벽 기준 벽 넘어가기
        calcs[2][0] = startX;
        calcs[2][1] = 2*(n - startY) + startY;

        // 하단 벽 기준 넘어가기
        calcs[3][0] = startX;
        calcs[3][1] = -startY;

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < balls.length; i++) {
            int x = balls[i][0];
            int y = balls[i][1];

            for (int j = 0; j < calcs.length; j++) {
                if (j == 0 && startY == y && startX > x) continue;
                if (j == 1 && startY == y && startX < x) continue;
                if (j == 2 && startX == x && startY < y) continue;
                if (j == 3 && startX == x && startY > y) continue;
                min = (int) Math.min(min, Math.pow(Math.abs(x - calcs[j][0]),2) + Math.pow(Math.abs(y - calcs[j][1]),2));
            }


            answer[i] = min;
            min = Integer.MAX_VALUE;
        }

        return answer;
    }
}
