package _2023._0126;

import java.util.Arrays;

public class Solution_최고의집합_L3 {
    public static void main(String[] args) {

    }

    public int[] solution(int n, int s) {
        int[] answer = {};

        if (n > s) return new int[]{-1};
        if (s == 1 && n == 1) return new int[]{1};

        answer = new int[n];
        int mod = s % n;

        Arrays.fill(answer, s / n);

        while (mod-- > 0) answer[--n]++;

        return answer;
    }
}
