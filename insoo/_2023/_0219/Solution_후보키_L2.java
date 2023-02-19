package _2023._0219;

import java.util.*;

public class Solution_후보키_L2 {
    public static void main(String[] args) {
        int solution = new Solution_후보키_L2().solution(new String[][]{{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}});
        System.out.println(solution);
    }

    static boolean[] temp;

    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;

    static List<List<Integer>> vis = new ArrayList<>();


    public int solution(String[][] relation) {
        int answer = 0;

        temp = new boolean[relation[0].length];
        for (int i = 1; i <= temp.length; i++) {
            combination(0, 0, i, relation);
        }

        return answer = cnt;
    }

    private void combination(int pos, int start, int length, String[][] relation) {
        if (pos == length) {
//            System.out.println(Arrays.toString(temp));
            if (!isDup() && isCandidate(relation, length)) {
                ArrayList<Integer> temps = new ArrayList<>();
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i]) temps.add(i);
                }
                vis.add(temps);
                cnt++;
            }
            return;
        }

        for (int i = start; i < temp.length; i++) {
            temp[i] = true;
            combination(pos + 1, i + 1, length, relation);
            temp[i] = false;
        }
    }



    private boolean isDup() {
        boolean dup = false;
        for (List<Integer> vi : vis) {
            for (Integer integer : vi) {
                if (temp[integer]) {
                    dup = true;
                } else {
                    dup = false;
                    break;
                }
            }
            if (dup) break;
        }
        if (dup) return true;
        return false;
    }

    private boolean isCandidate(String[][] relation, int length) {
        Set<String> dup = new HashSet<>();

        for (String[] strings : relation) {
            for (int i = 0; i < strings.length; i++) {
                if (temp[i]) sb.append(strings[i]);
            }
            dup.add(sb.toString());
            sb.setLength(0);
        }
        if (dup.size() == relation.length) return true;
        return false;
    }
}
