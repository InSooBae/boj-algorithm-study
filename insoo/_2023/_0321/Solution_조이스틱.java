package _2023._0321;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_조이스틱 {
    public static void main(String[] args) {
        int jan = new Solution_조이스틱().solution("JEROEN");
        System.out.println(jan);
    }

    private static class Pair {
        int offset;
        int idx;

        @Override
        public String toString() {
            return "Pair{" +
                    "offset=" + offset +
                    ", idx=" + idx +
                    '}';
        }

        public Pair(int offset, int idx) {
            this.offset = offset;
            this.idx = idx;
        }
    }

    static int min = Integer.MAX_VALUE;

    public int solution(String name) {
        int answer = 0;
        Map<Character, Integer> alphaOffset = new HashMap<>();
        Map<Character, Integer> reverseAlphaOffset = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            alphaOffset.put((char) ('A' + i), i);
        }
        for (int i = 0; i < 26; i++) {
            reverseAlphaOffset.put((char) ('Z' - i), i + 1);
        }
        int cnt = 0;
        for (int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A'));
            if (name.charAt(i) != 'A') {
                if (i != 0) cnt++;
            }
        }
        if (cnt == 0) return answer;

        Pair[] arr = new Pair[cnt * 2 + 1];
        int idx = cnt + 1;
        boolean[] visited = new boolean[20];

        for (int i = 1; i < name.length(); i++) {
            if (name.charAt(i) != 'A') arr[idx++] = new Pair(i, i);
        }
        idx = cnt - 1;
        for (int i = name.length() - 1; i >= 1; i--) {
            if (name.charAt(i) != 'A') arr[idx--] = new Pair(name.length() - i, i);
        }

        arr[cnt] = new Pair(0, 0);
        for (Pair pair : arr) {
            System.out.println(pair);
        }

        int sum = 0;
        for (int i = cnt + 1; i < arr.length; i++) {
            sum += arr[i].offset - arr[i - 1].offset;
            visited[arr[i].idx] = true;

            int restSum = i == arr.length - 1 ? 0 : arr[i].offset;
            for (int j = cnt - 1; j >= 0; j--) {
                if (!visited[arr[j].idx]) restSum += arr[j].offset - arr[j + 1].offset;
            }
//            System.out.println(sum + restSum);

            if (min > sum + restSum) min = sum + restSum;
        }

        Arrays.fill(visited, false);
        sum = 0;
        for (int i = cnt - 1; i >= 0; i--) {
            sum += arr[i].offset - arr[i + 1].offset;
            visited[arr[i].idx] = true;

            int restSum = i == 0 ? 0 : arr[i].offset;
            for (int j = cnt + 1; j < arr.length; j++) {
                if (!visited[arr[j].idx]) restSum += arr[j].offset - arr[j - 1].offset;
            }

//            System.out.println(sum + restSum);

            if (min > sum + restSum) min = sum + restSum;
        }
//        System.out.println(min);

//        for (Map.Entry<Character, Integer> characterIntegerEntry : alphaOffset.entrySet()) {
//            System.out.println(characterIntegerEntry.getKey() + " " + characterIntegerEntry.getValue());
//        }

//        System.out.println((char) ('A' + 25));

        return answer + min;
    }


}
