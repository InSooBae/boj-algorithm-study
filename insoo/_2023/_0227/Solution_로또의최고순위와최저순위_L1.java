package _2023._0227;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_로또의최고순위와최저순위_L1 {
    public static void main(String[] args) {

    }

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};

        Set<Integer> myLotto = new HashSet<>();
        Map<Integer, Integer> rank = new HashMap<>();

        rank.put(6, 1);
        rank.put(5, 2);
        rank.put(4, 3);
        rank.put(3, 4);
        rank.put(2, 5);
        rank.put(1, 6);
        rank.put(0, 6);

        int winNumCnt = 0;
        int unknownNumCnt = 0;
        for (int lotto : lottos) {
            if (lotto != 0) myLotto.add(lotto);
            else unknownNumCnt++;
        }


        for (int winNum : win_nums) {
            if (myLotto.contains(winNum)) winNumCnt++;
        }

        answer = new int[]{rank.get(winNumCnt + unknownNumCnt), rank.get(winNumCnt)};

        return answer;
    }
}
