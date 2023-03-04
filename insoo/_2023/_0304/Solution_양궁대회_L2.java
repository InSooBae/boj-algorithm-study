package _2023._0304;

import java.util.Arrays;

public class Solution_양궁대회_L2 {
    public static void main(String[] args) {
        int[] solution = new Solution_양궁대회_L2().solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3});
        System.out.println(Arrays.toString(solution));
    }

    static int[] scores = new int[11];
    static int max = -1;

    static int[] res = new int[11];

    public int[] solution(int n, int[] info) {
        int[] answer = {};

//        Arrays.fill(res, -1);

        // 각 점수 별 많이 맞춘 사람이 해당 점수 획득(단 맞춘 숫가 동일시 어피치가 가져감) [0발이면 점수 X]
        dupPermu(0, n, n, info);
        if (max == -1) {
            return new int[]{-1};
        }
//        System.out.println(max);
        return res;
    }

    private void dupPermu(int pos, int n, int rest,int[] info) {
        if (pos == scores.length) {
            int lion =  0;
            for (int i = 0; i < scores.length; i++) {
                if (info[i] == 0 && scores[i] == 0) continue;
                if (info[i] < scores[i]) lion += 10 - i;
                else lion -= 10 - i;
            }
            if (lion <= 0) return;

            if (max < lion) {
//            System.out.println(Arrays.toString(scores));

                System.arraycopy(scores, 0, res, 0, 11);
                max = lion;
            } else if (max == lion) {
                if (cmp(scores, res)) {
                    for (int i = 0; i < scores.length; i++) {
                        res[i] = scores[i];
                    }
                }
            }

            return;
        }

        for (int i = 0; i <= rest; i++) {
            scores[pos] = i;
            dupPermu(pos + 1, n, rest - i, info);
        }
    }
    public boolean cmp(int[] a, int[] b){
        for(int i = 10; i >= 0; i--)
            if(a[i] != b[i]) return a[i] > b[i];
        return false;
    }
}
