package _2023._0219;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_후보키_L2_flagver {

    public static void main(String[] args) {

    }

    static StringBuilder sb = new StringBuilder();
    static List<Integer> minimals = new ArrayList<>();
    static int cnt = 0;

    public int solution(String[][] relation) {
        int answer = 0;

        for (int i = 1; i <= relation[0].length; i++) {
            flagCombination(0,0,0,i,relation);
        }

        return answer = cnt;
    }

    private void flagCombination(int pos, int start, int flag, int length, String[][] relation) {
        if (pos == length) {
            if (isMinimal(flag) && isCandidate(relation, flag)) {
                minimals.add(flag);
                cnt++;
            }
            return;
        }

        for (int i = start; i < relation[0].length; i++) {
            flagCombination(pos + 1, i + 1, flag | 1 << i, length, relation);
        }
    }

    private boolean isMinimal(int flag) {
        for (Integer minimal : minimals) {
            if ((minimal & flag) == minimal) {
                return false;
            }
        }
        return true;
    }

    private boolean isCandidate(String[][] relation, int flag) {
        Set<String> dup = new HashSet<>();

        for (String[] strings : relation) {
            for (int i = 0; i < strings.length; i++) {
                if ((flag & 1 << i) != 0) sb.append(strings[i]);
            }
            dup.add(sb.toString());
            sb.setLength(0);
        }
        if (dup.size() == relation.length) return true;
        return false;
    }
}
