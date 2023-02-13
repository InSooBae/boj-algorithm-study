package _2023._0213;

import java.util.*;

public class Solution_보석쇼핑_L3 {
    public static void main(String[] args) {
        int[] solution = new Solution_보석쇼핑_L3().solution(new String[]{"XYZ", "XYZ", "XYZ"});
        System.out.println(Arrays.toString(solution));
    }

    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int left = 0;
        int right = gems.length;
        int res = Integer.MAX_VALUE;
        int gemCnt = 0;
        HashSet<String> hashSet = new HashSet<>();
        for (String gem : gems) {
            hashSet.add(gem);
        }

        gemCnt = hashSet.size();

        while (left <= right) {
            int mid = (left + right) / 2;

            // 가능하면 더 짧게 잡음
            if (canBuy(mid, res, answer, gems, gemCnt)) {
                right = mid - 1;
                res = Math.min(res, mid);
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private boolean canBuy(int mid, int res, int[] answer, String[] gems, int gemCnt) {
        if (mid == 0) return false;
        Map<String, Integer> checkGems = new HashMap();

        for (int i = 0; i < mid; i++) {
            if (checkGems.containsKey(gems[i])) {
                checkGems.put(gems[i], checkGems.get(gems[i]) + 1);
            } else {
                checkGems.put(gems[i], 1);
            }
        }

        if (checkCanBuy(mid, res, answer, checkGems, 0, mid - 1, gemCnt)) return true;

        for (int i = mid; i < gems.length; i++) {
            if (checkGems.containsKey(gems[i-mid])) {
                Integer last = checkGems.get(gems[i - mid]);
                if (last == 1) checkGems.remove(gems[i - mid]);
                else checkGems.put(gems[i - mid], last - 1);
            }
            if (checkGems.containsKey(gems[i])) {
                checkGems.put(gems[i], checkGems.get(gems[i]) + 1);
            } else {
                checkGems.put(gems[i], 1);
            }


            if (checkCanBuy(mid, res, answer, checkGems, i - mid + 1, i, gemCnt)) return true;
        }


        return false;
    }

    private static boolean checkCanBuy(int mid, int res, int[] answer, Map<String, Integer> checkGems, int start, int end, int gemCnt) {
        if (checkGems.size() >= gemCnt) {
            if (mid < res) {
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
            return true;
        }
        return false;
    }
}
