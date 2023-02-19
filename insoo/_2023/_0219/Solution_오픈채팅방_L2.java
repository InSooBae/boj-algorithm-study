package _2023._0219;

import java.util.*;

public class Solution_오픈채팅방_L2 {
    public static void main(String[] args) {
        String[] solution = new Solution_오픈채팅방_L2().solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"});
        System.out.println(Arrays.toString(solution));

    }

    static class Log {
        String id;
        String action;

        public Log(String id, String action) {
            this.id = id;
            this.action = action;
        }
    }

    static class User {
        String nickname;
        String action;

        public User(String nickname, String action) {
            this.nickname = nickname;
            this.action = action;
        }
    }

    public String[] solution(String[] record) {
        String[] answer = {};
        StringTokenizer st;
        Map<String, User> users = new HashMap<>();
        List<Log> logs = new ArrayList<>();

        for (String s : record) {
            st = new StringTokenizer(s, " ");
            String action = st.nextToken();
            if (action.equals("Leave")) {
                String id = st.nextToken();
                users.get(id).action = "Leave";
                logs.add(new Log(id, action));
                continue;
            }
            String id = st.nextToken();
            String nickname = st.nextToken();

            if (action.equals("Change")) {
                if (users.get(id).action.equals("Enter")) {
                    users.get(id).nickname = nickname;
                }
            } else {
                users.put(id, new User(nickname, action));
                logs.add(new Log(id, action));
            }
        }

        answer = new String[logs.size()];
        int idx = 0;
        for (Log log : logs) {
            String action = "들어왔습니다.";
            if (log.action.equals("Leave")) {
                action = "나갔습니다.";
            }
            answer[idx++] = users.get(log.id).nickname + "님이 " + action;
        }

        return answer;
    }

}
