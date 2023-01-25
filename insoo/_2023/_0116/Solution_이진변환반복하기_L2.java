package _2023._0116;

import java.util.Arrays;

public class Solution_이진변환반복하기_L2 {

    public static void main(String[] args) {
        int[] solution = new Solution_이진변환반복하기_L2().solution("1111111");
        System.out.println(Arrays.toString(solution));
    }

    public int[] solution(String s) {
        int[] answer = {};
        int loopCnt = 0;
        int oneCnt = 0;
        int removeCnt = 0;


        while (!("1".equals(s))) {

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1') oneCnt++;
                else removeCnt++;
            }


            s = Integer.toBinaryString(oneCnt);
            loopCnt++;
            oneCnt = 0;
        }

        answer = new int[]{loopCnt, removeCnt};
        return answer;
    }

}
