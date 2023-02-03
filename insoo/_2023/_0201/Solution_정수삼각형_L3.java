package _2023._0201;

public class Solution_정수삼각형_L3 {
    public static void main(String[] args) {

    }

    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;

        for (int i = height - 1; i >= 1; i--) {
            int horizonLength = triangle[i].length;
            for (int j = 0; j < horizonLength - 1; j++) {
                triangle[i - 1][j] += Math.max(triangle[i][j], triangle[i][j + 1]);
            }
        }

        answer = triangle[0][0];
        return answer;
    }
}
