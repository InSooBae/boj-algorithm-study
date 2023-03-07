package _2023._0307;

import java.util.Arrays;

public class Solution_이모티콘할인행사_L2 {
    public static void main(String[] args) {
        int[] solution = new Solution_이모티콘할인행사_L2().solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000});
        System.out.println(Arrays.toString(solution));
    }

    static int[] temp;
    static int maxEmoPlusCnt = 0;
    static int maxEarnMoney = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};

        // 이모티콘마다 할인율은 10%, 20%, 30%, 40% 중 하나로 설정
        int[][] emoticonsPrices = new int[emoticons.length][5];
        temp = new int[emoticons.length];

        for (int i = 0; i < emoticons.length; i++) {
            emoticonsPrices[i][0] = emoticons[i];
            int emotionP = emoticonsPrices[i][0] * 100;
            for (int j = 1; j <= 4; j++) {
                int discount = j * 10;

                int subtractDiscount = emoticons[i] * discount;
                int discountedEmotion = (emotionP - subtractDiscount) / 100;
                emoticonsPrices[i][j] = discountedEmotion;

//                System.out.println(discountedEmotion);
            }
        }

        dupCombination(0, emoticonsPrices, users);

        answer = new int[]{maxEmoPlusCnt, maxEarnMoney};
        return answer;
    }

    private void dupCombination(int pos, int[][] emoticonsPrices, int[][] users) {
        if (pos == emoticonsPrices.length) {
//            System.out.println(Arrays.toString(temp));
            calcWhoBuyEmoticons(emoticonsPrices, users);
            return;
        }

        for (int i = 1; i < emoticonsPrices[0].length; i++) {
            temp[pos] = i;

            dupCombination(pos + 1, emoticonsPrices, users);
        }
    }

    private void calcWhoBuyEmoticons(int[][] emoticonsPrices, int[][] users) {
        int emoPlusCnt = 0;
        int earnMoney = 0;
        for (int[] user : users) {
            int discountRate = user[0];
            int money = user[1];
            int payMoney = 0;
            for (int i = 0; i < emoticonsPrices.length; i++) {
                if (temp[i] * 10 >= discountRate) {
                    payMoney += emoticonsPrices[i][temp[i]];
                }
            }
            if (money <= payMoney) emoPlusCnt++;
            else earnMoney += payMoney;
        }

        if (maxEmoPlusCnt < emoPlusCnt) {
            maxEmoPlusCnt = emoPlusCnt;
            maxEarnMoney = earnMoney;
        } else if (maxEmoPlusCnt == emoPlusCnt) {
            if (maxEarnMoney < earnMoney) maxEarnMoney = earnMoney;
        }
    }
}
