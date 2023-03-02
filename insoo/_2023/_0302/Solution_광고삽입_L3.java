package _2023._0302;

import java.text.DecimalFormat;

public class Solution_광고삽입_L3 {
    public static void main(String[] args) {
        String solution = new Solution_광고삽입_L3().solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
        System.out.println(solution);
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";

        int size = calcToSec(play_time);
        long[] video = new long[size + 1];
//        System.out.println(size);

        for (String log : logs) {
            String[] times = log.split("-");
            int startTime = calcToSec(times[0]);
            int endTime = calcToSec(times[1]);

            video[startTime]++;
            video[endTime]--;
        }

        for (int i = 1; i < video.length; i++) {
            video[i] += video[i - 1];
        }

        int advSec = calcToSec(adv_time);
        long sum = 0;
        long max = 0;
        int maxIdx = 0;
        for (int i = 0; i < advSec; i++) {
            sum += video[i];
        }

        max = sum;

        for (int i = advSec; i < video.length; i++) {
            sum -= video[i - advSec];
            sum += video[i];

            if (max < sum) {
                maxIdx = i - advSec + 1;
                max = sum;
            }
        }

        answer = secToTime(maxIdx);

        return answer;
    }

    private String secToTime(int maxIdx) {
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("00");

        sb.append(df.format(maxIdx / 3600)).append(":");
        maxIdx %= 3600;
        sb.append(df.format(maxIdx / 60)).append(":");
        maxIdx %= 60;
        sb.append(df.format(maxIdx));

        return sb.toString();
    }

    private int calcToSec(String playTime) {
        String[] split = playTime.split(":");

        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        int sec = Integer.parseInt(split[2]);

        return (hour * 60 * 60) + (min * 60) + sec;
    }
}
