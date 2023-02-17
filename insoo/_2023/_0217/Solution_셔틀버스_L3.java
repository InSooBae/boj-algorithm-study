package _2023._0217;

import java.text.DecimalFormat;
import java.util.*;

public class Solution_셔틀버스_L3 {
    public static void main(String[] args) {
        String solution = new Solution_셔틀버스_L3().solution(10, 25, 1, new String[]{
                "09:00", "09:10", "09:20", "09:30", "09:40", "09:50",
                "10:00", "10:10", "10:20", "10:30", "10:40", "10:50"});
        System.out.println(solution);
    }

    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";

        PriorityQueue<String> temp = new PriorityQueue<>(String::compareTo);
        Deque<String> times = new ArrayDeque<>();
        temp.addAll(Arrays.asList(timetable));

        while (!temp.isEmpty()) times.add(temp.poll());

        DecimalFormat df = new DecimalFormat("00");
        StringBuilder sb = new StringBuilder();
        int[] curTime = new int[]{9, 0};

        int j = 0;
        int i = 0;
        int remain = m;
        String last = "";

        for (; i < n; i++) {
            sb.append(df.format(curTime[0])).append(":").append(df.format(curTime[1]));
            while (!times.isEmpty() && times.peek().compareTo(sb.toString()) <= 0) {
                if (remain != 0) {
                    last = times.poll();
                    remain--;
                } else break;
            }

            if (times.isEmpty()) break;
            if (i == n - 1) break;
            curTime[1] += t;
            if (curTime[1] >= 60) {
                curTime[1] -= 60;
                curTime[0]++;
            }

            remain = m;
            sb.setLength(0);
        }


        if (remain == 0) {
            StringTokenizer st = new StringTokenizer(last, ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());

            min -= 1;
            if (min < 0) {
                min = 59;
                hour -= 1;
            }

            answer = df.format(hour) + ":" + df.format(min);
        } else answer = df.format(curTime[0]) + ":" + df.format(curTime[1]);


        return answer;
    }
}
