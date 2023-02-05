package _2023._0205;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_야근지수_L3 {
    public static void main(String[] args) {

    }

    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }

        while (n-- > 0) {
            if (pq.isEmpty()) break;
            int nxt = pq.poll() - 1;
            if (nxt > 0) pq.add(nxt);
        }

        while (!pq.isEmpty()) {
            Integer num = pq.poll();
            answer += (long) num * num;
        }

        return answer;
    }
}
