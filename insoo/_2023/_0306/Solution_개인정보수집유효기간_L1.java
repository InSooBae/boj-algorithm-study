package _2023._0306;

import java.util.*;

public class Solution_개인정보수집유효기간_L1 {
    public static void main(String[] args) {
        int[] solution = new Solution_개인정보수집유효기간_L1().solution("2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});
        System.out.println(Arrays.toString(solution));
    }

    private static class TempDate {
        int year;
        int month;
        int day;

        public TempDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public void calcValidDate(int month) {
            int inCompleteMonth = this.month + month;
            int addYear = (inCompleteMonth - 1) / 12;
            int lastMonth = inCompleteMonth - (addYear * 12);
            this.year += addYear;
            this.month = lastMonth;
        }

        public boolean isValid(TempDate o) {
            if (this.year < o.year) return true;
            else if (this.year == o.year) {
                if (this.month < o.month) return true;
                else if (this.month == o.month) {
                    return this.day < o.day;
                } else return false;
            } else return false;
        }

        @Override
        public String toString() {
            return "TempDate{" +
                    "year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    '}';
        }
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        StringTokenizer st;
        Map<String, Integer> termsMap = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        String[] toDate = today.split("\\.");
        TempDate todayDate = new TempDate(Integer.parseInt(toDate[0]), Integer.parseInt(toDate[1]), Integer.parseInt(toDate[2]));

        for (String term : terms) {
            st = new StringTokenizer(term, " ");

            String key = st.nextToken();
            int val = Integer.parseInt(st.nextToken());

            termsMap.put(key, val);
        }

        int idx = 1;
        for (String privacy : privacies) {
            st = new StringTokenizer(privacy, " ");
            String[] date = st.nextToken().split("\\.");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            String term = st.nextToken();

            TempDate tempDate = new TempDate(year, month, day);
            tempDate.calcValidDate(termsMap.get(term));
            if (!todayDate.isValid(tempDate)) {
                res.add(idx);
            }
            idx++;
            System.out.println(tempDate);
        }

        answer = new int[res.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = res.get(i);
        }

        return answer;
    }
}
