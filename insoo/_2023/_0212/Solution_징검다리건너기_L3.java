package _2023._0212;

import java.util.*;
import java.util.stream.IntStream;

public class Solution_징검다리건너기_L3 {
    public static void main(String[] args) {
        int solution = new Solution_징검다리건너기_L3().solution(new int[]{2, 4, 5, 2, 3, 1, 4, 2, 5, 1}, 3);
        System.out.println(solution);
    }

    static class Pair {
        int index;
        int val;

        public Pair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public int solution(int[] stones, int k) {
        int answer = 0;
        int grpMin = Integer.MAX_VALUE;
        Deque<Pair> queue = new ArrayDeque<>();
//        for (int i = 0; i < k - 1; i++) {
//            queue.addLast(new Pair(i, stones[i]));
//        }

        for (int i = 0; i < stones.length; i++) {
            while (!queue.isEmpty() && queue.peekFirst().index + k <= i)
                queue.pollFirst();

            while (!queue.isEmpty() && queue.peekLast().val < stones[i]) queue.pollLast();
            queue.addLast(new Pair(i, stones[i]));
            if (i >= k - 1) grpMin = Math.min(grpMin, queue.peekFirst().val);
        }
        return answer = grpMin;
    }

//    public int solution(int[] stones, int k) {
//        int answer = 0;
//
//        int min = 0;
//        int nxtMin = Integer.MAX_VALUE;
//        boolean isStop = false;
//
//        while (true) {
//            int zeroCnt = 0;
//            boolean isBeforeZero = false;
//
//            for (int i = 0; i < stones.length; i++) {
//                if (stones[i] > 0) {
//                    stones[i] -= min;
//                    if (stones[i] < nxtMin) nxtMin = stones[i];
//                }
//            }
//            answer += min;
//            min = nxtMin;
//            nxtMin = Integer.MAX_VALUE;
//
//            for (int stone : stones) {
//
//                if (stone == 0) {
//                    if (!isBeforeZero) zeroCnt = 1;
//                    else zeroCnt++;
//                    if (zeroCnt >= k) {
//                        isStop = true;
//                        break;
//                    }
//                    isBeforeZero = true;
//                } else {
//                    isBeforeZero = false;
//                }
//            }
//
//            if (isStop) break;
//        }
//        return answer;
//    }
}
