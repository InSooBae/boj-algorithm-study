package _2023._0216;

import java.util.*;

public class Solution_압축_L2 {
    public static void main(String[] args) {
        int[] solution = new Solution_압축_L2().solution("TOBEORNOTTOBEORTOBEORNOT");
        System.out.println(Arrays.toString(solution));

    }

    public int[] solution(String msg) {
        int[] answer = {};
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> words = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int idx = 1;
        for (char i = 'A'; i <= 'Z'; i++) {
            words.put(String.valueOf(i), idx++);
        }

        System.out.println(msg.length());
        for (int i = 0; i < msg.length(); i++) {
            int j = i;
            for (; j < msg.length(); j++) {
                sb.append(msg.charAt(j));
                if (!words.containsKey(sb.toString())) {
                    words.put(sb.toString(), idx++);
                    sb.setLength(sb.length() - 1);
                    break;
                }
            }
            i = j - 1;
//            System.out.println(i);
            res.add(words.get(sb.toString()));
            sb.setLength(0);
        }

//        for (Map.Entry<String, Integer> stringIntegerEntry : words.entrySet()) {
//            System.out.println(stringIntegerEntry.getKey() + " " + stringIntegerEntry.getValue());
//        }

        answer = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            answer[i] = res.get(i);
        }
        return answer;
    }

}
