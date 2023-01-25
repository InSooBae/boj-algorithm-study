    package _2023._0125;

import java.util.HashSet;

public class Solution_영어끝말잇기_L2 {
    public static void main(String[] args) {

    }

    public int[] solution(int n, String[] words) {
        int[] answer = {};

        HashSet<String> dupWords = new HashSet<>();
        int i = 0;
        int cnt = 0;
        for (i = 0; i < words.length; i++) {
            cnt++;
            if (dupWords.contains(words[i]) || i != 0 && words[i-1].charAt(words[i-1].length() - 1) != words[i].charAt(0)) {
                break;
            }
            dupWords.add(words[i]);
        }

        if (i == words.length) {
            answer = new int[]{0, 0};
        } else {
            answer = new int[]{(i % n) + 1, (i / n) + 1};
        }
        return answer;
    }
}
