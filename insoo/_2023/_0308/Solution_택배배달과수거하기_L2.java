package _2023._0308;

import java.util.Arrays;

public class Solution_택배배달과수거하기_L2 {
    public static void main(String[] args) {
        long solution = new Solution_택배배달과수거하기_L2().solution(2, 2, new int[]{0,6}, new int[]{0,0});
        System.out.println(solution);
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int lastDeliverPos = deliveries.length - 1;
        int deliverPos = lastDeliverPos;
        int pickPos = lastDeliverPos;
        int capacity = 0;
        int maxPos = -1;
        while (deliverPos != -1 || pickPos != -1) {
            // 배송
            while (deliverPos != -1 && capacity < cap) {
                if (deliveries[deliverPos] == 0) {
                    deliverPos--;
                    continue;
                }

                if (maxPos < deliverPos) maxPos = deliverPos;
                if (deliveries[deliverPos] > cap - capacity) {
                    deliveries[deliverPos] = deliveries[deliverPos] - (cap - capacity);
                    capacity = cap;
                    break;
                } else {
                    capacity += deliveries[deliverPos];
                    deliveries[deliverPos] = 0;
                    deliverPos--;
                }
            }
            // 배송 끝났다 가정
            capacity = 0;
            // 픽업
            while (pickPos != -1 && capacity < cap) {
                if (pickups[pickPos] == 0) {
                    pickPos--;
                    continue;
                }

                if (maxPos < pickPos) maxPos = pickPos;
                if (pickups[pickPos] > cap - capacity) {
                    pickups[pickPos] = pickups[pickPos] - (cap - capacity);
                    capacity = cap;
                    break;
                } else {
                    capacity += pickups[pickPos];
                    pickups[pickPos] = 0;
                    pickPos--;
                }

            }

//            System.out.println("deliver = " + Arrays.toString(deliveries));
//            System.out.println("pickup = " + Arrays.toString(pickups));
//            System.out.println("maxPos = " + (maxPos + 1));
//            System.out.println("deliverPos = " + deliverPos);
//            System.out.println("pickPos = " + pickPos);
//            System.out.println("===============================");

            if (maxPos == -1) break;

            capacity = 0;
            answer += (maxPos + 1) * 2L;

            maxPos = -1;
        }

        return answer;
    }
}
