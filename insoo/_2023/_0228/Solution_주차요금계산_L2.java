package _2023._0228;

import java.util.*;

public class Solution_주차요금계산_L2 {

    public static void main(String[] args) {
        int[] solution = new Solution_주차요금계산_L2().solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
        System.out.println(Arrays.toString(solution));
    }

    static class Res implements Comparable<Res> {

        String key;
        int min;

        public Res(String key, int min) {
            this.key = key;
            this.min = min;
        }

        @Override
        public int compareTo(Res o) {
            return this.key.compareTo(o.key);
        }
    }
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        Map<String, int[]> ioRecs = new HashMap<>();
        Map<String, Integer> res = new HashMap<>();
        StringTokenizer st;

        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        for (int i = 0; i < records.length; i++) {
            st = new StringTokenizer(records[i], " ");

            String[] time = st.nextToken().split(":");
            int hour = Integer.parseInt(time[0]);
            int min = Integer.parseInt(time[1]);
            String carId = st.nextToken();
            String state = st.nextToken();

            if (ioRecs.containsKey(carId)) {
                int[] inTime = ioRecs.get(carId);
                ioRecs.remove(carId);

                int inHour = inTime[0];
                int inMin = inTime[1];
                int resMin = calcResMin(hour, min, inHour, inMin);

                putResMin(res, carId, resMin);

            } else {
                ioRecs.put(carId, new int[]{hour, min});
            }
        }

        for (Map.Entry<String, int[]> rec : ioRecs.entrySet()) {
            int[] value = rec.getValue();
            int resMin = calcResMin(23, 59, value[0], value[1]);
            putResMin(res, rec.getKey(), resMin);
        }

        answer = new int[res.size()];
        int idx = 0;
        List<Res> resForSort = new ArrayList<>();
        for (Map.Entry<String, Integer> resEntry : res.entrySet()) {

            resForSort.add(new Res(resEntry.getKey(), resEntry.getValue()));


        }

        Collections.sort(resForSort);
        for (Res res1 : resForSort) {
            int resMin = res1.min;
            if (resMin <= defaultTime) {
                answer[idx++] = defaultFee;

            } else {
                int temp = (int)Math.ceil((double) (resMin - defaultTime) / unitTime);
                answer[idx++] = defaultFee + temp * unitFee;
            }
        }

        return answer;
    }

    private static void putResMin(Map<String, Integer> res, String carId, int resMin) {
        if (res.containsKey(carId)) {
            res.put(carId, res.get(carId) + resMin);
        } else {
            res.put(carId, resMin);
        }
    }

    private static int calcResMin(int hour, int min, int inHour, int inMin) {
        if (min < inMin) {
            hour -= 1;
            min += 60;
        }

        return ((hour - inHour) * 60) + (min - inMin);
    }
}
