package _2023._0208;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_튜플_L2 {
    /*
        o(n^2) 나오면 안됨 n이 백만이라 억이 쉽게 넘어간다.
        O(nlogn)부터 괜찮다.
     */
    public static void main(String[] args) {
        int[] solution = new Solution_튜플_L2().solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
        System.out.println(Arrays.toString(solution));
    }

    public int[] solution(String s) {
        int[] answer = {};
        Map<Integer, Integer> map = new HashMap<>();

        String[] split = s.split("[{}]");
        int resCnt = 0;
        for (String s1 : split) {
            if (s1.length() == 0 || s1.equals(",")) continue;
            resCnt++;
            String[] split1 = s1.split("[,]");
//            int length = split1.length;

            for (String s2 : split1) {
                int val = Integer.parseInt(s2);
                if (map.containsKey(val)) {
                    map.put(val, map.get(val) + 1);
                } else {
                    map.put(val, 1);
                }
            }
        }

        answer = new int[resCnt];

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int idx = resCnt - entry.getValue();
            answer[idx] = entry.getKey();
        }

//        for (String s1 : split) {
//            if (s1.length() == 0 || s1.equals(",")) continue;
//
//            String[] split1 = s1.split("[,]");
//            int idx = split1.length - 1;
//
//            answer[idx] = Integer.parseInt(split1[idx]);
//
//        }
        return answer;
    }
}
