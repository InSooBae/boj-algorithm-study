package _2023._0227;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_다단계칫솔판매_L3 {
    public static void main(String[] args) {
        int[] solution = new Solution_다단계칫솔판매_L3().solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}, new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}, new String[]{"young", "john", "tod", "emily", "mary"}, new int[]{12, 4, 2, 5, 10});
        System.out.println(Arrays.toString(solution));
    }

    private static class Seller {

        String parentName;
        int earnMoney;
        Seller parent;

        public Seller(int earnMoney, Seller parent) {
            this.parent = parent;
            this.earnMoney = earnMoney;
        }

        public Seller(Seller parent) {
            this.parent = parent;
        }

        public Seller(String name, Seller parent) {
            this.parentName = name;
            this.parent = parent;
        }

        public Seller() {
        }

        public static void sell(Seller seller, int money) {
            if (seller.parent != null && money != 0) {
                int last = (money * 10) / 100;
                seller.earnMoney += money - last;

                sell(seller.parent, last);
            }
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Seller> sellers = new HashMap<>();

        sellers.put("-", new Seller());
        for (int i = 0; i < referral.length; i++) {
            sellers.put(enroll[i], new Seller(referral[i],sellers.get(referral[i])));
        }

        for (int i = 0; i < seller.length; i++) {
            Seller seller1 = sellers.get(seller[i]);

            Seller.sell(seller1, amount[i] * 100);
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = sellers.get(enroll[i]).earnMoney;
        }


        return answer;
    }
}
