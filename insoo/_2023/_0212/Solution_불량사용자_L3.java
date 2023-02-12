package _2023._0212;

import java.util.HashSet;

public class Solution_불량사용자_L3 {

    public static void main(String[] args) {
        int solution = new Solution_불량사용자_L3().solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[]{"fr*d*", "*rodo", "******", "******"});
        System.out.println(solution);

    }




    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;

        HashSet<Integer> cnt = new HashSet<>();
        permutation(0, 0, user_id, banned_id, cnt);

        answer = cnt.size();
        return answer;
    }

    private void permutation(int pos, int flag, String[] userId, String[] bannedId, HashSet<Integer> cnt) {
        if (pos == bannedId.length) {

//            System.out.println(Integer.toBinaryString(flag));
            cnt.add(flag);
            return;
        }

        for (int i = 0; i < userId.length; i++) {
            if ((flag & 1 << i) != 0) continue;

            String user = userId[i];
            String ban = bannedId[pos];
            int userIdLength = user.length();
            int bannedIdLength = ban.length();
            boolean isSame = true;

            if (userIdLength == bannedIdLength) {

                for (int j = 0; j < userIdLength; j++) {
                    if (ban.charAt(j) == '*') continue;
                    if (ban.charAt(j) != user.charAt(j)) {
                        isSame = false;
                        break;
                    }
                }
                if (isSame) permutation(pos + 1, flag | 1 << i, userId, bannedId, cnt);
            }
        }

    }

}
