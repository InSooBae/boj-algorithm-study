package _2023._0127;

import java.util.*;

public class Solution_이중우선순위큐_L3 {
    public static void main(String[] args) {

    }

    public int[] solution(String[] operations) {
        int[] answer = {};
        StringTokenizer st;
        PriorityQueue<Integer> btQ = new PriorityQueue<>();

        for (String operation : operations) {
            st = new StringTokenizer(operation, " ");

            String oper = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            if (oper.equals("I")) {
                btQ.add(num);
            } else {
                if (num == 1) {
                    PriorityQueue<Integer> temp = new PriorityQueue<>();
                    int size = btQ.size();
                    for (int i = 0; i < size - 1; i++) temp.add(btQ.poll());
                    btQ = temp;
                } else {
                    btQ.poll();
                }
            }
        }

        if (btQ.isEmpty()) return new int[]{0, 0};

        int min = btQ.poll();
        int size = btQ.size();
        for (int i = 0; i < size - 1; i++) btQ.poll();
        int max = btQ.poll();

        answer = new int[]{max, min};
        return answer;
    }
}
